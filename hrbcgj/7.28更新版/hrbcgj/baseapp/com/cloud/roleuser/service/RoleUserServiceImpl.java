package com.cloud.roleuser.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.roleuser.dao.IRoleUserDaoMybatis;
import com.cloud.roleuser.dao.RoleUserDaoHibernate;
import com.cloud.roleuser.dao.RoleUserDaoJDBC;
import com.cloud.roleuser.model.RoleUser;

@Service
public class RoleUserServiceImpl implements IRoleUserService {

	@Resource
	private IRoleUserDaoMybatis roleUserDaoMybatis;

	@Resource
	private RoleUserDaoHibernate roleUserDaoHibernate;

	@Resource
	private RoleUserDaoJDBC roleUserDaoJDBC;

	/**
	 * 通过id得到对象
	 */
	public RoleUser getRoleUserById(String id) {
		return roleUserDaoHibernate.getRoleUserById(id);
	}

	/**
	 * 保存
	 */
	public RoleUser saveRoleUser(RoleUser roleUser) {
		return roleUserDaoHibernate.saveRoleUser(roleUser);
	}

	/**
	 * 列表查询
	 */
	public Map searchRoleUser(Long curPage, Long pageSize,String whereStr) {
		return roleUserDaoHibernate.searchRoleUser(curPage, pageSize, whereStr);
	}

	/**
	 * 删除
	 */
	public RoleUser delRoleUser(RoleUser roleUser) {
		return roleUserDaoHibernate.delRoleUser(roleUser);
	}

	/**
	 * 批量删除
	 */
	public void delRoleUserBatch(List<String> list) {
		roleUserDaoHibernate.delRoleUserBatch(list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		return roleUserDaoHibernate.getAllDataByWhere(where);
	}

	/**
	 * 批量保存
	 */
	public boolean saveDataBatch(List<RoleUser> list) {
		return roleUserDaoHibernate.saveDataBatch(list);
	}
	
	/**
	 * 通过角色id删除其相关的所有角色与人员关系
	 * @param roleId
	 */
	public void delRoleUserByRoleId(String roleId) {
		roleUserDaoHibernate.delRoleUserByRoleId(roleId);
	}

	/**
	 * 通过角色
	 * @param roleId
	 * @return
	 */
	public String getUserIdsByRoleId(String roleId) {
		return roleUserDaoHibernate.getUserIdsByRoleId(roleId);
	}
	
	/**
	 * 通过角色id得到关联的用户名字
	 * @param roleId
	 * @return
	 */
	public String getUserNamesByRoleId(String roleId) {
		return roleUserDaoHibernate.getUserNamesByRoleId(roleId);
	}
}
