package com.cloud.regwebservice.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.regwebservice.model.RegWebService;

@Repository
public class RegWebServiceDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public RegWebService getRegWebServiceById(String id) {
		return (RegWebService)getDataObject(RegWebService.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public RegWebService saveRegWebService(RegWebService regWebService) {
		if(regWebService.getId() == null || regWebService.getId().equals("")) {
			saveData(regWebService);
		} else {
			saveOrUpdate(regWebService);
		}
		return regWebService;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<RegWebService> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				RegWebService regWebService = list.get(i);
				session.save(regWebService);
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
	public Map searchRegWebService(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM RegWebService regWebService";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public RegWebService delRegWebService(RegWebService regWebService) {
		return (RegWebService)delData(regWebService);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delRegWebServiceBatch(List<String> list) {
		String delHql = "DELETE RegWebService ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM RegWebService regWebService where 1=1 " + where;
		return getDataList(hql);
	}

}
