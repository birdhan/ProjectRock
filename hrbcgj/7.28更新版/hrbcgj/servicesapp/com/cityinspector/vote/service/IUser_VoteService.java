package com.cityinspector.vote.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.vote .model.User_Vote;

public interface IUser_VoteService {

	/**
	 * 通过id得到某一对象
	 */
	public User_Vote getUser_VoteById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public User_Vote saveUser_Vote(User_Vote user_Vote);

	/**
	 * 分页查询
	 */
	public Map searchUser_Vote(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public User_Vote delUser_Vote(User_Vote user_Vote);

	/**
	 * 批量删除
	 */
	public void delUser_VoteBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<User_Vote> list);

}
