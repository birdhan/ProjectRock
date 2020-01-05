package com.cityinspector.vote.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.vote .model.VoteXuan;

public interface IVoteXuanService {

	/**
	 * 通过id得到某一对象
	 */
	public VoteXuan getVoteXuanById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public VoteXuan saveVoteXuan(VoteXuan voteXuan);

	/**
	 * 分页查询
	 */
	public Map searchVoteXuan(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public VoteXuan delVoteXuan(VoteXuan voteXuan);

	/**
	 * 批量删除
	 */
	public void delVoteXuanBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<VoteXuan> list);
	
	public List<VoteXuan> getVid(String id);
	
	public void update(String id);

}
