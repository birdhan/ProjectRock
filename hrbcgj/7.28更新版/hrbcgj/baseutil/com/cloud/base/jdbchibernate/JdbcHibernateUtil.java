package com.cloud.base.jdbchibernate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.cityinspector.vote.model.VoteXuan;
import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.LoggerUtil;

/**
 * 解决普通类中调用hibernate的问题，因为普通类中无法通过注解得到sessionFacotry对象
 * @author cuiyp 2012-09-08
 *
 */
public class JdbcHibernateUtil extends HibernateDaoSupport {

	public Session session = null;
	public SessionFactory sessionFactory = null;
	public static JdbcHibernateUtil jdbcHibernateUtil = null;
	
	@Resource
	public JdbcTemplate jdbcTemplate;
	
	public static JdbcHibernateUtil getInstance() {
		if(jdbcHibernateUtil == null) {
			jdbcHibernateUtil = new JdbcHibernateUtil();
		}
		
		jdbcHibernateUtil.setSessionFactory();//初始化sessionFactory参数
		LoggerUtil.info(JdbcHibernateUtil.class, "===hibernate实例已加载成功===");
		return jdbcHibernateUtil;
	}

	/**
	 * 获取sessionFactory对象 
	 * @return
	 */
	public synchronized SessionFactory getHibernateSessionFactory() {
		return sessionFactory;
	}
	
	@Resource(name="hibernateSessionFactory")  
	public synchronized void setMySessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
		super.setSessionFactory(sessionFactory);  
	}
	
	/**
	 * 设置hibernate的SessionFactory
	 */
	public synchronized void setSessionFactory() {
		this.sessionFactory = getSessionFactory();
		if(sessionFactory == null || sessionFactory.isClosed()) {
			this.sessionFactory = getSessionFactory();
			if(sessionFactory == null) {
				this.sessionFactory = (SessionFactory)ApplicationContextHolder.getInstance().getBean("hibernateSessionFactory");
			}			
		}
		session = sessionFactory.openSession();	
	}
	
	/**
	 * 得到list集合对象
	 * @param hql
	 * @return
	 */
	public synchronized List getDataList(String hql) {
		List list = new ArrayList();
		try {
			Transaction tx = beginTransaction();
			list = session.createQuery(hql).list();		
			tx.commit();
			closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
		
		public synchronized List getVotef(String hql,int start,int s) {
			List list = new ArrayList();
			try {
				Transaction tx = beginTransaction();
				Query query= session.createQuery(hql);
				query.setFirstResult(start);
				query.setMaxResults(s);
				list=query.list();
				
				tx.commit();
				closeSession();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return list;
	   }
		
		public synchronized List getVote(String hql) {
			List list = new ArrayList();
			try {
				Transaction tx = beginTransaction();
				list=session.createQuery(hql).list();
				tx.commit();
				closeSession();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return list;
	   }
	
	/**
	 * 得到某一个对象，只需要将参数中这个对象设置其id即可
	 * @param entityName
	 * @param id
	 * @return
	 */
	public synchronized Object getDataObject(Class entityName, Serializable id) {
		Transaction tx = beginTransaction();
		Object obj = session.get(entityName, id);
		tx.commit();
		closeSession();
		return obj;
	}
	
	/**
	 * 保存更新对象
	 * @param object
	 */
	public synchronized Object saveData(Object object) {
		Transaction tx = beginTransaction();
		session.save(object);
		tx.commit();
		closeSession();	
		return object;
	}
	
	/**
	 * 保存更新对象
	 * @param object
	 */
	public synchronized Object saveOrUpdate(Object object) {
		Transaction tx = beginTransaction();
		session.saveOrUpdate(object);
		tx.commit();
		closeSession();	
		return object;
	}
	
	/**
	 * 删除对象
	 * @param obj
	 */
	public synchronized Object delData(Object object) {
		Transaction tx = beginTransaction();
		session.delete(object);
		tx.commit();
		closeSession();
		return object;
	}
	public synchronized void getupdate(String id) {
		Transaction tx = beginTransaction();
		VoteXuan v=(VoteXuan)session.get(VoteXuan.class,id);
		v.setNumber(v.getNumber()+1);
		tx.commit();
		closeSession();
	}
	/**
	 * 批量删除对象
	 * @param obj
	 */
	public synchronized void delDataBatch(String hql,List<String> idList) {
		Transaction tx = beginTransaction();		
		for(int i=0;i<idList.size();i++) {
			if(i==0) {
				hql += " where id='"+idList.get(i)+"'";
			} else {
				hql += " or id='"+idList.get(i)+"'";
			}
		}	
		session.createQuery(hql).executeUpdate();
		tx.commit();
		closeSession();
	}
	
	/**
	 * @param sql
	 * @return
	 */
	public synchronized List find(String sql) {
		getHibernateTemplate().clear();
		List list = getHibernateTemplate().find(sql);
		return list;
	}

	/**
	 * 运用jdbc直接做数据的增、删、改操作
	 * @param sql
	 * @param param
	 * @return
	 */
	public synchronized int sql_update(String sql,Object[] param) {
		int result = 0;
		Transaction tx = beginTransaction();
		SQLQuery query = session.createSQLQuery(sql);
		if(param != null && param.length != 0) {
			setQueryParameter(query,param);
		}
		result = query.executeUpdate();
		tx.commit();
		closeSession();
		return result;
	}
	
	/**
	 * 运用jdbc直接做数据的增、删、改操作
	 * @param sql
	 * @return
	 */
	public synchronized int sql_update(String sql) {
		int result = 0;
		Transaction tx = beginTransaction();
		SQLQuery query = session.createSQLQuery(sql);
		query.executeUpdate();
		tx.commit();
		closeSession();
		return result;
	}
	
	/**
	 * 通过sql语句直接查询
	 * @param sql
	 * @param c
	 * @return
	 */
	public synchronized List sql_queryForList(String sql,Class c) {
		List list = new ArrayList();
		Transaction tx = beginTransaction();
		SQLQuery query = session.createSQLQuery(sql);
		if(c != null) {
			query.addEntity(c);
		}
		list = query.list();
		tx.commit();
		closeSession();
		return list;
	}
	
	/**
	 * 通过sql语句直接查询
	 * @param sql
	 * @return
	 */
	public synchronized List sql_queryForList(String sql) {
		List list = new ArrayList();
		Transaction tx = beginTransaction();
		SQLQuery query = session.createSQLQuery(sql);
		list = query.list();
		tx.commit();
		closeSession();
		return list;
	}
	
	public synchronized int sql_queryCount(String sql) {
		int result = 0;
		JdbcTemplate jdbcTemplate = (JdbcTemplate)ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
		result = jdbcTemplate.queryForInt(sql);
		return result;
	}
	
	/**
	 * @param parameter
	 * @param query
	 * @throws HibernateException
	 */
	public synchronized void setQueryParameter(Query query, Object[] parameter) {
		Object pValue = null;
		try {
			if (parameter != null) {
				for (int i = 0; i < parameter.length; i++) {
					pValue = parameter[i];
					if (pValue instanceof String) {
						query.setString(i, (String) pValue);
					} else if (pValue instanceof Integer) {
						query.setInteger(i, ((Integer) pValue).intValue());
					} else if (pValue instanceof Boolean) {
						query.setBoolean(i, ((Boolean) pValue).booleanValue());
					} else if (pValue instanceof Short) {
						query.setShort(i, ((Short) pValue).shortValue());
					} else if (pValue instanceof Long) {
						query.setLong(i, ((Long) pValue).longValue());
					} else if (pValue instanceof Float) {
						query.setFloat(i, ((Float) pValue).floatValue());
					} else if (pValue instanceof Double) {
						query.setDouble(i, ((Double) pValue).doubleValue());
					} else if (pValue instanceof BigDecimal) {
						query.setBigDecimal(i, (BigDecimal) pValue);
					} else if (pValue instanceof Byte) {
						query.setByte(i, ((Byte) pValue).byteValue());
					} else if (pValue instanceof java.sql.Date) {
						query.setDate(i, java.sql.Date.valueOf(pValue
								.toString()));
					} else if (pValue instanceof java.sql.Time) {
						query.setTime(i, java.sql.Time.valueOf(pValue
								.toString()));
					} else if (pValue instanceof java.sql.Timestamp) {
						query.setTimestamp(i, java.sql.Timestamp.valueOf(pValue
								.toString()));
					} else if (pValue instanceof java.util.Date) {
						query.setDate(i, java.sql.Date.valueOf(pValue
								.toString()));
					} else {
						// query.setObject(i, pValue);
					}
				}
			}
		} catch (HibernateException e) {
			throw new RuntimeException(
					"setQueryParameter(Query query, Object[] object) throw exception = "
							+ e.toString());
		}
	}
	
	/**
	 * 命名参数设置
	 * 
	 * @param query
	 * @param name
	 * @param parameter
	 */
	public synchronized void setQueryParameterValues(Query query, String[] name,Object[] parameter) {
		try {
			if (name.length != parameter.length) {
				throw new IllegalArgumentException("setQueryParameterValues(Query query, String[] name , Object[] parameter) = Length of paramNames array must match length of values array");
			}
			if (name != null && parameter != null) {
				for (int i = 0; i < name.length; i++)
					query.setParameter(name[i], parameter[i]);
			}
		} catch (HibernateException e) {
			throw new RuntimeException("setQueryParameterValues(Query query, String[] str , Object[] object) throw exception = "+ e.toString());
		}
	}

	/**
	 * 关闭session
	*/
	public synchronized void closeSession() {
		try {
			if(session != null && session.isOpen()) {//连接状态			
				session.close();	
			}		
		} catch (Exception e) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(session != null && session.isOpen()) {//连接状态			
				session.close();	
			}		
		}		
	} 
	
	/**
	 * 开启事务，返回事务对象
	 * @return
	 */
	public synchronized Transaction beginTransaction() {
		closeSession();
		if(session == null || session.isOpen() == false) {
			setSessionFactory();
		}
		return session.beginTransaction();
	}
	
	/**
	 * 分页查询，返回map类型
	 * @param curPage：要查询的页码值
	 * @param pageSize：每页显示的记录数
	 * @param sql：查询的hql语句
	 * @param whereStr：过滤条件
	 * @return
	 */
	public synchronized Map queryData2MapByPage(Long curPage,Long pageSize,String sql, String whereStr) {
		HashMap map = null;
		try {
			beginTransaction();
	    	String queryStr = sql + " where 1=1 ";
	    	if (whereStr != null && whereStr.length() > 0) {
	            queryStr += whereStr;
	        }
	    	String queryCountStr = "select count(*) " + queryStr;
	    	int total = Integer.valueOf((session.createQuery(queryCountStr).iterate().next())+"");//得到总数
	    	Query query = session.createQuery(queryStr);
	        query.setFirstResult(pageSize.intValue()*(curPage.intValue()-1));
	        query.setMaxResults(pageSize.intValue());
	        List result = query.list();
	        map = new HashMap();
	        map.put("total", total);//集合总数
	        map.put("result", result);//查询结果集	        
	        closeSession();
		}catch (Exception e){
			e.printStackTrace();
		} finally {
			if(session.isOpen()) {
				session.close();
			}
		}
		return map;
	}
	
}
