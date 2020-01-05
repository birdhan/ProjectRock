package com.cityinspector.linkservice.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.linkservice.model.Link;

@Repository
public class LinkDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Link getLinkById(String id) {
		return (Link)getDataObject(Link.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Link saveLink(Link link) {
		if(link.getId() == null || link.getId().equals("")) {
			saveData(link);
		} else {
			saveOrUpdate(link);
		}
		return link;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Link> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Link link = list.get(i);
				session.save(link);
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
	public Map searchLink(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Link link";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Link delLink(Link link) {
		return (Link)delData(link);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delLinkBatch(List<String> list) {
		String delHql = "DELETE Link ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Link link where 1=1 " + where;
		return getDataList(hql);
	}

}
