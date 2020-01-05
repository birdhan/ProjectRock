package com.cloud.special.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.special.model.special;

@Repository
public class specialDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public special getspecialById(String id) {
		return (special)getDataObject(special.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public special savespecial(special special) {
		if(special.getId() == null || special.getId().equals("")) {
			saveData(special);
		} else {
			saveOrUpdate(special);
		}
		return special;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<special> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				special special = list.get(i);
				session.save(special);
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
	public Map searchspecial(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM special special";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public special delspecial(special special) {
		return (special)delData(special);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delspecialBatch(List<String> list) {
		String delHql = "DELETE special ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM special special where 1=1 " + where;
		return getDataList(hql);
	}

}
