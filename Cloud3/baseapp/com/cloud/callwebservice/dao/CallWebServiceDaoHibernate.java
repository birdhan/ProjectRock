package com.cloud.callwebservice.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.cache.SysCache;
import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.callwebservice.model.CallWebService;

@Repository
public class CallWebServiceDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public CallWebService getCallWebServiceById(String id) {
		return (CallWebService)getDataObject(CallWebService.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public CallWebService saveCallWebService(CallWebService callWebService) {
		if(callWebService.getId() == null || callWebService.getId().equals("")) {
			saveData(callWebService);
			SysCache.getInstance().addCallWebService(callWebService);
		} else {
			saveOrUpdate(callWebService);
			SysCache.getInstance().updateCallWebService(callWebService);
		}
		return callWebService;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<CallWebService> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				CallWebService callWebService = list.get(i);
				session.save(callWebService);
				if (i == list.size()-1) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			closeSession();
			SysCache.getInstance().initCWSList();
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
	public Map searchCallWebService(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM CallWebService callWebService";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public CallWebService delCallWebService(CallWebService callWebService) {
		SysCache.getInstance().deleteCallWebService(callWebService);
		return (CallWebService)delData(callWebService);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delCallWebServiceBatch(List<String> list) {
		String delHql = "DELETE CallWebService ";
		delDataBatch(delHql,list);
		SysCache.getInstance().initCWSList();
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM CallWebService callWebService where 1=1 " + where;
		return getDataList(hql);
	}

}
