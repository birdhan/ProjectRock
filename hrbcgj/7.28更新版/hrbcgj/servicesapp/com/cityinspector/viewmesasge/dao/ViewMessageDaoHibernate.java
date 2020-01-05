package com.cityinspector.viewmesasge.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.viewmesasge.model.ViewMessage;

@Repository
public class ViewMessageDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public ViewMessage getViewMessageById(String id) {
		return (ViewMessage)getDataObject(ViewMessage.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public ViewMessage saveViewMessage(ViewMessage viewMessage) {
		if(viewMessage.getId() == null || viewMessage.getId().equals("")) {
			saveData(viewMessage);
		} else {
			saveOrUpdate(viewMessage);
		}
		return viewMessage;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<ViewMessage> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				ViewMessage viewMessage = list.get(i);
				session.save(viewMessage);
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
	public Map searchViewMessage(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM ViewMessage viewMessage";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public ViewMessage delViewMessage(ViewMessage viewMessage) {
		return (ViewMessage)delData(viewMessage);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delViewMessageBatch(List<String> list) {
		String delHql = "DELETE ViewMessage ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM ViewMessage viewMessage where 1=1 " + where;
		return getDataList(hql);
	}

}
