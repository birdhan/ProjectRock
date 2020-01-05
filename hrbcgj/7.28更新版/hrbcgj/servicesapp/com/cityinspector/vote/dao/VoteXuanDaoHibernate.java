package com.cityinspector.vote.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cityinspector.vote.model.VoteXuan;

@Repository
public class VoteXuanDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public VoteXuan getVoteXuanById(String id) {
		return (VoteXuan)getDataObject(VoteXuan.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public VoteXuan saveVoteXuan(VoteXuan voteXuan) {
		if(voteXuan.getId() == null || voteXuan.getId().equals("")) {
			saveData(voteXuan);
		} else {
			saveOrUpdate(voteXuan);
		}
		return voteXuan;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<VoteXuan> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				VoteXuan voteXuan = list.get(i);
				session.save(voteXuan);
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
	public Map searchVoteXuan(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM VoteXuan voteXuan";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public VoteXuan delVoteXuan(VoteXuan voteXuan) {
		return (VoteXuan)delData(voteXuan);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delVoteXuanBatch(List<String> list) {
		String delHql = "DELETE VoteXuan ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM VoteXuan voteXuan where 1=1 " + where;
		return getDataList(hql);
	}
	
	public List getVid(String id)
	{
		String hql="from VoteXuan v where v.vid='"+id+"' order by v.name";
		return getDataList(hql);
	}
	public void update(String id)
	{
		getupdate(id);
	}

}
