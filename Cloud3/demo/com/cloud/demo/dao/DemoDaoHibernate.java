package com.cloud.demo.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.XMLModelUtil;
import com.cloud.demo.hibernateinterceptor.DemoInterceptor;
import com.cloud.demo.model.Demo;

@Repository
public class DemoDaoHibernate extends JdbcHibernateUtil {

	/**
	 * 通过id得到某个对象
	 * @param id
	 * @return
	 */
	public Demo getDemoById(String id) {
		return (Demo)getDataObject(Demo.class,id);
	}
	
	/**
	 * 保存
	 * @param demo
	 * @return
	 */
	public Demo saveDemo(Demo demo) {
		
		/**
		 * 普通保存方式
		 */
//		if(demo.getId() == null || demo.getId().equals("")) {
//			saveData(demo);
//		} else {
//			saveOrUpdate(demo);
//		}		
		
		/**
		 * hibernate拦截器方式，以下写法走的是hibernate拦截器可对sql语句进行修改
		 */
		Transaction tx = null;
		Session session = getHibernateTemplate().getSessionFactory().withOptions().interceptor(new DemoInterceptor()).openSession();
		tx = session.beginTransaction();
		if(demo.getId() == null || demo.getId().equals("")) {
			session.save(demo);
		} else {
			session.saveOrUpdate(demo);
		}		
		tx.commit();
		
		return demo;
	}
	
	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Demo> list) {
		boolean flag = true;
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Demo d = list.get(i);						
				session.save(d);
				if (i == list.size()-1) {
					session.flush();            
					session.clear();        
				} 
			}
			tx.commit();  
			session.close();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			flag = false;
		} finally {
			return flag;
		}			
	}
	
	/**
	 * 列表查询
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	public Map searchDemo(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Demo demo";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}
	
	/**
	 * 删除数据
	 * @param demo
	 * @return
	 */
	public Demo delDemo(Demo demo) {
		return (Demo)delData(demo);
	}
	
	/**
	 * 批量删除
	 */
	public void delDemoBatch(List<String> list) {
		String delHql = "DELETE Demo ";
		delDataBatch(delHql,list);
	}
	
	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Demo demo where 1=1 " + where;
		return getDataList(hql);
	}
	
	/**
	 * webservice入口，由此方法调用相应的逻辑函数
	 * @param interfaceNo
	 * @param method
	 * @param param
	 * @return
	 */
	public String executeWebService(String interfaceNo , String method , String param) {
		String result = "";
		try {
			/**
			 * 此处写调用逻辑
			 */
			if(method.equals("getDemoById")) {										//获取某记录
				result = JSONUtil.object2JSONObject(getDemoById(param)).toString();
			} else if(method.equals("saveDemo")) {									//保存数据
				Demo saveDemo = (Demo)JSONUtil.toBean(param, Demo.class);
				saveDemo(saveDemo);
				result = "1";						
			} else if(method.equals("delDemo")) {									//删除某记录
				Demo delDemo = new Demo();
				delDemo.setId(param);
				delDemo(delDemo);
				result = "1";
			}  else if(method.equals("getAllDataByWhere")) {						//按条件获取数据
				List list = getAllDataByWhere(param);
				result = JSONUtil.list2JSONArray(list).toString();
				System.out.println("JSON格式：\n"+result+"\n");
				result = XMLModelUtil.toXML4Text(list);
				System.out.println("XML格式：\n"+result+"\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}
}
