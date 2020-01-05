package com.cityinspector.openinfor.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.openinfor.model.AppAction;

@Repository
public class AppActionDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public AppAction getAppActionById(String id) {
		return (AppAction)getDataObject(AppAction.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public AppAction saveAppAction(AppAction appAction) {
		if(appAction.getId() == null || appAction.getId().equals("")) {
			saveData(appAction);
		} else {
			saveOrUpdate(appAction);
		}
		return appAction;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<AppAction> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				AppAction appAction = list.get(i);
				session.save(appAction);
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
	public Map searchAppAction(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM AppAction appAction";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public AppAction delAppAction(AppAction appAction) {
		return (AppAction)delData(appAction);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delAppActionBatch(List<String> list) {
		String delHql = "DELETE AppAction ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM AppAction appAction where " + where;
		return getDataList(hql);
	}

}
