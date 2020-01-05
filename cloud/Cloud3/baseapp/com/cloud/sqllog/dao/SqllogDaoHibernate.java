package com.cloud.sqllog.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.sqllog.model.Sqllog;

@Repository
public class SqllogDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Sqllog getSqllogById(String id) {
		return (Sqllog)getDataObject(Sqllog.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Sqllog saveSqllog(Sqllog sqllog) {
		if(sqllog.getId() == null || sqllog.getId().equals("")) {
			saveData(sqllog);
		} else {
			saveOrUpdate(sqllog);
		}
		return sqllog;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Sqllog> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Sqllog sqllog = list.get(i);
				session.save(sqllog);
				if (i == list.size()-1) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			closeSession();
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
	public Map searchSqllog(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Sqllog sqllog";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Sqllog delSqllog(Sqllog sqllog) {
		return (Sqllog)delData(sqllog);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delSqllogBatch(List<String> list) {
		String delHql = "DELETE Sqllog ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Sqllog sqllog where 1=1 " + where;
		return getDataList(hql);
	}

}
