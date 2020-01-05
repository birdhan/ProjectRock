package com.cloud.webservice.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.webservice.model.WebService;

@Repository
public class WebServiceDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public WebService getWebServiceById(String id) {
		return (WebService)getDataObject(WebService.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public WebService saveWebService(WebService webService) {
		if(webService.getId() == null || webService.getId().equals("")) {
			saveData(webService);
		} else {
			saveOrUpdate(webService);
		}
		return webService;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<WebService> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				WebService webService = list.get(i);
				session.save(webService);
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
	public Map searchWebService(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM WebService webService";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public WebService delWebService(WebService webService) {
		return (WebService)delData(webService);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delWebServiceBatch(List<String> list) {
		String delHql = "DELETE WebService ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM WebService webService where 1=1 " + where;
		return getDataList(hql);
	}

}
