package com.cityinspector.vote.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.vote.dao.IVoteXuanDaoMybatis;
import com.cityinspector.vote.dao.VoteXuanDaoHibernate;
import com.cityinspector.vote.dao.VoteXuanDaoJDBC;
import com.cityinspector.vote.model.VoteXuan;

@Service
public class VoteXuanServiceImpl implements IVoteXuanService {

	@Resource
	private IVoteXuanDaoMybatis voteXuanDaoMybatis;

	@Resource
	private VoteXuanDaoHibernate voteXuanDaoHibernate;

	@Resource
	private VoteXuanDaoJDBC voteXuanDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public VoteXuan getVoteXuanById(String id) {
		return voteXuanDaoHibernate.getVoteXuanById(id);
	}

	/**
	 * 保存
	 */
	public VoteXuan saveVoteXuan(VoteXuan voteXuan) {
		return voteXuanDaoHibernate.saveVoteXuan(voteXuan);
	}

	/**
	 * 列表查询
	 */
	public Map searchVoteXuan(Long curPage, Long pageSize,String whereStr) {
		return voteXuanDaoHibernate.searchVoteXuan(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public VoteXuan delVoteXuan(VoteXuan voteXuan) {
		return voteXuanDaoHibernate.delVoteXuan(voteXuan);
	}

	/**
	 * 批量删除
	 */
	public void delVoteXuanBatch(List<String> list) {
		voteXuanDaoHibernate.delVoteXuanBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return voteXuanDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<VoteXuan> list) {
		return voteXuanDaoHibernate.saveDataBatch(list);
	}

	@Override
	public List<VoteXuan> getVid(String id) {
		
		return voteXuanDaoHibernate.getVid(id);
	}

	@Override
	public void update(String id) {
		voteXuanDaoHibernate.update(id);
	}

}
