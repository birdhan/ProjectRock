package com.cityinspector.vote.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.vote.model.User_Vote;

@Repository
public class User_VoteDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public User_Vote getUser_VoteById(String id) {
		return (User_Vote)getDataObject(User_Vote.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public User_Vote saveUser_Vote(User_Vote user_Vote) {
		if(user_Vote.getId() == null || user_Vote.getId().equals("")) {
			saveData(user_Vote);
		} else {
			saveOrUpdate(user_Vote);
		}
		return user_Vote;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<User_Vote> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				User_Vote user_Vote = list.get(i);
				session.save(user_Vote);
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
	public Map searchUser_Vote(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM User_Vote user_Vote";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public User_Vote delUser_Vote(User_Vote user_Vote) {
		return (User_Vote)delData(user_Vote);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delUser_VoteBatch(List<String> list) {
		String delHql = "DELETE User_Vote ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM User_Vote where" + where;
		return getDataList(hql);
	}

}
