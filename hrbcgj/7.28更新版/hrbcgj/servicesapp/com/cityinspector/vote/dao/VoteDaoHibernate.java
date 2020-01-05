package com.cityinspector.vote.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.vote.model.Vote;

@Repository
public class VoteDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Vote getVoteById(String id) {
		return (Vote)getDataObject(Vote.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Vote saveVote(Vote vote) {
		if(vote.getId() == null || vote.getId().equals("")) {
			saveData(vote);
		} else {
			saveOrUpdate(vote);
		}
		return vote;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Vote> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Vote vote = list.get(i);
				session.save(vote);
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
	public Map searchVote(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Vote vote";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Vote delVote(Vote vote) {
		return (Vote)delData(vote);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delVoteBatch(List<String> list) {
		String delHql = "DELETE Vote ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Vote vote where 1=1 " + where;
		return getDataList(hql);
	}
	/**
	 * 分页查询
	 */
	public List getAll(String where,int start,int s)
	{
		String hql="from Vote v where v.if_Finish='"+where+"' order by v.startTime desc";
		return getVotef(hql, start, s);
	}
	public List getAll(String where)
	{
		String hql="from Vote v where v.if_Finish='"+where+"' order by v.startTime desc";
		return getVote(hql);
	}

}
