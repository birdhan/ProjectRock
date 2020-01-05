package com.cityinspector.onlineservice.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.onlineservice.model.OnlineService;

@Repository
public class OnlineServiceDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public OnlineService getOnlineServiceById(String id) {
		return (OnlineService)getDataObject(OnlineService.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public OnlineService saveOnlineService(OnlineService onlineService) {
		if(onlineService.getId() == null || onlineService.getId().equals("")) {
			saveData(onlineService);
		} else {
			saveOrUpdate(onlineService);
		}
		return onlineService;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<OnlineService> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				OnlineService onlineService = list.get(i);
				session.save(onlineService);
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
	public Map searchOnlineService(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM OnlineService onlineService";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public OnlineService delOnlineService(OnlineService onlineService) {
		return (OnlineService)delData(onlineService);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delOnlineServiceBatch(List<String> list) {
		String delHql = "DELETE OnlineService ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM OnlineService onlineService where 1=1 " + where;
		return getDataList(hql);
	}

}
