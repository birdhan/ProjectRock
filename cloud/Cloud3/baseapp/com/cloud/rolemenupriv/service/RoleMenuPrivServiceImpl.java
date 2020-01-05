package com.cloud.rolemenupriv.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.rolemenupriv.dao.IRoleMenuPrivDaoMybatis;
import com.cloud.rolemenupriv.dao.RoleMenuPrivDaoHibernate;
import com.cloud.rolemenupriv.dao.RoleMenuPrivDaoJDBC;
import com.cloud.rolemenupriv.model.RoleMenuPriv;

@Service
public class RoleMenuPrivServiceImpl implements IRoleMenuPrivService {

	@Resource
	private IRoleMenuPrivDaoMybatis roleMenuPrivDaoMybatis;

	@Resource
	private RoleMenuPrivDaoHibernate roleMenuPrivDaoHibernate;

	@Resource
	private RoleMenuPrivDaoJDBC roleMenuPrivDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public RoleMenuPriv getRoleMenuPrivById(String id) {
		return roleMenuPrivDaoHibernate.getRoleMenuPrivById(id);
	}

	/**
	 * 保存
	 */
	public RoleMenuPriv saveRoleMenuPriv(RoleMenuPriv roleMenuPriv) {
		return roleMenuPrivDaoHibernate.saveRoleMenuPriv(roleMenuPriv);
	}

	/**
	 * 列表查询
	 */
	public Map searchRoleMenuPriv(Long curPage, Long pageSize,String whereStr) {
		return roleMenuPrivDaoHibernate.searchRoleMenuPriv(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public RoleMenuPriv delRoleMenuPriv(RoleMenuPriv roleMenuPriv) {
		return roleMenuPrivDaoHibernate.delRoleMenuPriv(roleMenuPriv);
	}

	/**
	 * 批量删除
	 */
	public void delRoleMenuPrivBatch(List<String> list) {
		roleMenuPrivDaoHibernate.delRoleMenuPrivBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return roleMenuPrivDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<RoleMenuPriv> list) {
		return roleMenuPrivDaoHibernate.saveDataBatch(list);
	}

	/**
	 * 删除角色菜单权限关系
	 * @param roleId
	 * @param menuId
	 */
	public void delRoleMenuPriv(String roleId , String menuId) {
		roleMenuPrivDaoHibernate.delRoleMenuPriv(roleId, menuId);
	}
	
	/**
	 * 删除角色菜单权限关系
	 * @param roleId
	 * @param menuId
	 */
	public void delRoleMenuPriv(String roleId) {
		roleMenuPrivDaoHibernate.delRoleMenuPriv(roleId);
	}
}
