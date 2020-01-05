package com.cloud.systemuser.service;

import java.util.List;
import java.util.Map;

import com.cloud.systemuser .model.SystemUser;

public interface ISystemUserService {

	/**
	 * 通过id得到某一对象
	 */
	public SystemUser getSystemUserById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public SystemUser saveSystemUser(SystemUser systemUser);

	/**
	 * 分页查询
	 */
	public Map searchSystemUser(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public SystemUser delSystemUser(SystemUser systemUser);

	/**
	 * 批量删除
	 */
	public void delSystemUserBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<SystemUser> list);

	/**
	 * 将userId转成userName
	 * @param userId
	 * @return
	 */
	public String userId2UserName(String userId);
	
	/**
	 * 得到所有人员部门树串
	 * @return
	 */
	public String getAllUserTree(String initUserIds);
	
}
