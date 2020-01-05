package com.cloud.roleuser.service;

import java.util.List;
import java.util.Map;

import com.cloud.roleuser .model.RoleUser;

public interface IRoleUserService {

	/**
	 * 通过id得到某一对象
	 */
	public RoleUser getRoleUserById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public RoleUser saveRoleUser(RoleUser roleUser);

	/**
	 * 分页查询
	 */
	public Map searchRoleUser(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public RoleUser delRoleUser(RoleUser roleUser);

	/**
	 * 批量删除
	 */
	public void delRoleUserBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<RoleUser> list);
	
	/**
	 * 通过角色id删除其相关的所有角色与人员关系
	 * @param roleId
	 */
	public void delRoleUserByRoleId(String roleId);
	
	/**
	 * 通过角色
	 * @param roleId
	 * @return
	 */
	public String getUserIdsByRoleId(String roleId);
	
	/**
	 * 通过角色id得到关联的用户名字
	 * @param roleId
	 * @return
	 */
	public String getUserNamesByRoleId(String roleId);

}
