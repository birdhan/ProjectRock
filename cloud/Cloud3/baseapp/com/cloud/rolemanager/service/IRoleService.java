package com.cloud.rolemanager.service;

import java.util.List;
import java.util.Map;

import com.cloud.rolemanager .model.Role;

public interface IRoleService {

	/**
	 * 通过id得到某一对象
	 */
	public Role getRoleById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public Role saveRole(Role role);

	/**
	 * 分页查询
	 */
	public Map searchRole(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public Role delRole(Role role);

	/**
	 * 批量删除
	 */
	public void delRoleBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<Role> list);

	/**
	 * 得到角色菜单关系的初始数据html格式串
	 * @return
	 */
	public String getInitMenuPrivHTML(String roleId);
}
