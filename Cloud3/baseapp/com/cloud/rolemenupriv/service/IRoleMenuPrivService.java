package com.cloud.rolemenupriv.service;

import java.util.List;
import java.util.Map;

import com.cloud.rolemenupriv .model.RoleMenuPriv;

public interface IRoleMenuPrivService {

	/**
	 * 通过id得到某一对象
	 */
	public RoleMenuPriv getRoleMenuPrivById(String id);

	/**
	 * 保存或修改某一对象
	 */
	public RoleMenuPriv saveRoleMenuPriv(RoleMenuPriv roleMenuPriv);

	/**
	 * 分页查询
	 */
	public Map searchRoleMenuPriv(Long curPage, Long pageSize,String whereStr);

	/**
	 * 删除某一对象
	 */
	public RoleMenuPriv delRoleMenuPriv(RoleMenuPriv roleMenuPriv);

	/**
	 * 批量删除
	 */
	public void delRoleMenuPrivBatch(List<String> list);

	/**
	 * 通过条件查询数据
	 */
	public List getAllDataByWhere(String where);

	/**
	 * 批量保存数据
	 */
	public boolean saveDataBatch(List<RoleMenuPriv> list);

	/**
	 * 删除角色菜单权限关系
	 * @param roleId
	 * @param menuId
	 */
	public void delRoleMenuPriv(String roleId , String menuId);
	
	/**
	 * 删除角色菜单权限关系
	 * @param roleId
	 * @param menuId
	 */
	public void delRoleMenuPriv(String roleId);
}
