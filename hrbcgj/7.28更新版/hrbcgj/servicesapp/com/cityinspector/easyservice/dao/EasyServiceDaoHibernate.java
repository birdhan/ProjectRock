package com.cityinspector.easyservice.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.easyservice.model.EasyService;

@Repository
public class EasyServiceDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public EasyService getEasyServiceById(String id) {
		return (EasyService)getDataObject(EasyService.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public EasyService saveEasyService(EasyService easyService) {
		if(easyService.getId() == null || easyService.getId().equals("")) {
			saveData(easyService);
		} else {
			saveOrUpdate(easyService);
		}
		return easyService;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<EasyService> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				EasyService easyService = list.get(i);
				session.save(easyService);
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
	public Map searchEasyService(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM EasyService easyService";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public EasyService delEasyService(EasyService easyService) {
		return (EasyService)delData(easyService);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delEasyServiceBatch(List<String> list) {
		String delHql = "DELETE EasyService ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM EasyService easyService where 1=1 " + where;
		return getDataList(hql);
	}

}
