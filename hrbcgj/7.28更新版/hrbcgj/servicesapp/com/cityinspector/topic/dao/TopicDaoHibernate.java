package com.cityinspector.topic.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.base.util.DateHelper;
import com.cityinspector.topic.model.Topic;

@Repository
public class TopicDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Topic getTopicById(String id) {
		return (Topic)getDataObject(Topic.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Topic saveTopic(Topic topic) {
		if(topic.getId() == null || topic.getId().equals("")) {
			saveData(topic);
		} else {
			saveOrUpdate(topic);
		}
		return topic;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Topic> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Topic topic = list.get(i);
				session.save(topic);
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
	public Map searchTopic(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Topic topic";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Topic delTopic(Topic topic) {
		return (Topic)delData(topic);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delTopicBatch(List<String> list) {
		String delHql = "DELETE Topic ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Topic topic where 1=1 " + where;
		return getDataList(hql);
	}

}
