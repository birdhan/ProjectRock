package com.cityinspector.vote.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.vote .model.Vote;

public interface IVoteService {

	/**
	 * 通过id得到某一对象
	 */
	public Vote getVoteById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Vote saveVote(Vote vote);

	/**
	 * 分页查询
	 */
	public Map searchVote(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Vote delVote(Vote vote);

	/**
	 * 批量删除
	 */
	public void delVoteBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Vote> list);
	
	public List<Vote> getAll(String where,int start,int s);
	
	public List<Vote> getAll(String where);
}
