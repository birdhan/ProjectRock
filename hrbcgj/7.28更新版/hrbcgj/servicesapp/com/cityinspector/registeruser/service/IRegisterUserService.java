package com.cityinspector.registeruser.service;

import java.util.List;
import java.util.Map;

import com.cityinspector.registeruser .model.RegisterUser;

public interface IRegisterUserService {

	/**
	 * 通过id得到某一对象
	 */
	public RegisterUser getRegisterUserById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public RegisterUser saveRegisterUser(RegisterUser registerUser);

	/**
	 * 分页查询
	 */
	public Map searchRegisterUser(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public RegisterUser delRegisterUser(RegisterUser registerUser);

	/**
	 * 批量删除
	 */
	public void delRegisterUserBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<RegisterUser> list);
	/**
	 * 
	 * 登录增加积分
	 */
	public void updatePoints(String userId);

}
