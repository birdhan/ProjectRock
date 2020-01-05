package com.cloud.rolemanager.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.rolemanager.model.Role;

@Repository
public class RoleDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public Role getRoleById(String id) {
		return (Role)getDataObject(Role.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public Role saveRole(Role role) {
		if(role.getId() == null || role.getId().equals("")) {
			saveData(role);
		} else {
			saveOrUpdate(role);
		}
		return role;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<Role> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				Role role = list.get(i);
				session.save(role);
				if (i == list.size()-1) {
					session.flush();
					session.clear();
				}
			}
			tx.commit();
			closeSession();
		} catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
			flag = false;
		} finally {
			return flag;
		}
	}

	/**
	 * 列表查询
	 * @param curPage
	 * @param pageSize
	 * @param whereStr
	 * @return
	 */
	public Map searchRole(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM Role role";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public Role delRole(Role role) {
		delRoleRelation(role.getId());
		return (Role)delData(role);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delRoleBatch(List<String> list) {
		String delHql = "DELETE Role ";
		
		for(String id : list) {
			delRoleRelation(id);
		}
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM Role role where 1=1 " + where;
		return getDataList(hql);
	}
	
	/**
	 * 删除角色对应关系
	 * @param roleId
	 */
	public void delRoleRelation(String roleId) {
		String delRoleUserSql = "delete SYSTEM_ROLEUSER where LINKROLEID='" + roleId + "'";
		jdbcTemplate.update(delRoleUserSql);
		
		String delRoleMenuPrivSql = "delete SYSTEM_ROLEMENUPRIV where LINKROLEID='"+roleId+"'";
		jdbcTemplate.update(delRoleMenuPrivSql);
	}

}
