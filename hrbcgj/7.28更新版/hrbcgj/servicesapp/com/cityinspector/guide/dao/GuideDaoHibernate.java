package com.cityinspector.guide.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.guide.model.Guide;

@Repository
public class GuideDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Guide getGuideById(String id) {
		return (Guide)getDataObject(Guide.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Guide saveGuide(Guide guide) {
		if(guide.getId() == null || guide.getId().equals("")) {
			saveData(guide);
		} else {
			saveOrUpdate(guide);
		}
		return guide;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Guide> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Guide guide = list.get(i);
				session.save(guide);
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
	public Map searchGuide(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Guide guide";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Guide delGuide(Guide guide) {
		return (Guide)delData(guide);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delGuideBatch(List<String> list) {
		String delHql = "DELETE Guide ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Guide guide where 1=1 " + where;
		return getDataList(hql);
	}

}
