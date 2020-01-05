package com.cloud.unit.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.unit.model.unit;

@Repository
public class unitDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public unit getunitById(String id) {
		return (unit)getDataObject(unit.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public unit saveunit(unit unit) {
		if(unit.getId() == null || unit.getId().equals("")) {
			saveData(unit);
		} else {
			saveOrUpdate(unit);
		}
		return unit;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<unit> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				unit unit = list.get(i);
				session.save(unit);
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
	public Map searchunit(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM unit unit";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public unit delunit(unit unit) {
		return (unit)delData(unit);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delunitBatch(List<String> list) {
		String delHql = "DELETE unit ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM unit unit where 1=1 " + where;
		return getDataList(hql);
	}

}
