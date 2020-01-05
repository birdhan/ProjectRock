package com.cityinspector.vote.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.vote.dao.IVoteDaoMybatis;
import com.cityinspector.vote.dao.VoteDaoHibernate;
import com.cityinspector.vote.dao.VoteDaoJDBC;
import com.cityinspector.vote.model.Vote;

@Service
public class VoteServiceImpl implements IVoteService {

	@Resource
	private IVoteDaoMybatis voteDaoMybatis;

	@Resource
	private VoteDaoHibernate voteDaoHibernate;

	@Resource
	private VoteDaoJDBC voteDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public Vote getVoteById(String id) {
		return voteDaoHibernate.getVoteById(id);
	}

	/**
	 * 保存
	 */
	public Vote saveVote(Vote vote) {
		return voteDaoHibernate.saveVote(vote);
	}

	/**
	 * 列表查询
	 */
	public Map searchVote(Long curPage, Long pageSize,String whereStr) {
		return voteDaoHibernate.searchVote(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public Vote delVote(Vote vote) {
		return voteDaoHibernate.delVote(vote);
	}

	/**
	 * 批量删除
	 */
	public void delVoteBatch(List<String> list) {
		voteDaoHibernate.delVoteBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return voteDaoHibernate.getAllDataByWhere(where);
	}
    
	public List getAll(String where,int start,int s)
	{
		return voteDaoHibernate.getAll(where, start, s);
	}
	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<Vote> list) {
		return voteDaoHibernate.saveDataBatch(list);
	}

	@Override
	public List<Vote> getAll(String where) {
		return voteDaoHibernate.getAll(where);
	}

}
