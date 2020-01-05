package com.cloud.rolemenupriv.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.rolemenupriv.model.RoleMenuPriv;

@Repository
public class RoleMenuPrivDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public RoleMenuPriv getRoleMenuPrivById(String id) {
		return (RoleMenuPriv)getDataObject(RoleMenuPriv.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public RoleMenuPriv saveRoleMenuPriv(RoleMenuPriv roleMenuPriv) {
		if(roleMenuPriv.getId() == null || roleMenuPriv.getId().equals("")) {
			saveData(roleMenuPriv);
		} else {
			saveOrUpdate(roleMenuPriv);
		}
		return roleMenuPriv;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<RoleMenuPriv> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				RoleMenuPriv roleMenuPriv = list.get(i);
				session.save(roleMenuPriv);
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
	public Map searchRoleMenuPriv(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM RoleMenuPriv roleMenuPriv";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public RoleMenuPriv delRoleMenuPriv(RoleMenuPriv roleMenuPriv) {
		return (RoleMenuPriv)delData(roleMenuPriv);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delRoleMenuPrivBatch(List<String> list) {
		String delHql = "DELETE RoleMenuPriv ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM RoleMenuPriv roleMenuPriv where 1=1 " + where;
		return getDataList(hql);
	}

	/**
	 * 删除角色菜单权限关系
	 * @param roleId
	 * @param menuId
	 */
	public void delRoleMenuPriv(String roleId , String menuId) {
		String delSql = "delete from SYSTEM_ROLEMENUPRIV where linkroleid='"+roleId+"' and linkmenuid='"+menuId+"'";
		jdbcTemplate.update(delSql);
	}
	
	/**
	 * 删除角色菜单权限关系
	 * @param roleId
	 * @param menuId
	 */
	public void delRoleMenuPriv(String roleId) {
		String delSql = "delete from SYSTEM_ROLEMENUPRIV where linkroleid='"+roleId+"'";
		jdbcTemplate.update(delSql);
	}
}
