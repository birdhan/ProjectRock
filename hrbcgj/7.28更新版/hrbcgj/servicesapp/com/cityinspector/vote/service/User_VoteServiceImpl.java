package com.cityinspector.vote.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cityinspector.vote.dao.IUser_VoteDaoMybatis;
import com.cityinspector.vote.dao.User_VoteDaoHibernate;
import com.cityinspector.vote.dao.User_VoteDaoJDBC;
import com.cityinspector.vote.model.User_Vote;

@Service
public class User_VoteServiceImpl implements IUser_VoteService {

	@Resource
	private IUser_VoteDaoMybatis user_VoteDaoMybatis;

	@Resource
	private User_VoteDaoHibernate user_VoteDaoHibernate;

	@Resource
	private User_VoteDaoJDBC user_VoteDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public User_Vote getUser_VoteById(String id) {
		return user_VoteDaoHibernate.getUser_VoteById(id);
	}

	/**
	 * 保存
	 */
	public User_Vote saveUser_Vote(User_Vote user_Vote) {
		return user_VoteDaoHibernate.saveUser_Vote(user_Vote);
	}

	/**
	 * 列表查询
	 */
	public Map searchUser_Vote(Long curPage, Long pageSize,String whereStr) {
		return user_VoteDaoHibernate.searchUser_Vote(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public User_Vote delUser_Vote(User_Vote user_Vote) {
		return user_VoteDaoHibernate.delUser_Vote(user_Vote);
	}

	/**
	 * 批量删除
	 */
	public void delUser_VoteBatch(List<String> list) {
		user_VoteDaoHibernate.delUser_VoteBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return user_VoteDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<User_Vote> list) {
		return user_VoteDaoHibernate.saveDataBatch(list);
	}

}
