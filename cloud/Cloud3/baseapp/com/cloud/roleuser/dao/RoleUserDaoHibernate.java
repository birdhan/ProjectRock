package com.cloud.roleuser.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.cloud.base.jdbchibernate.JdbcHibernateUtil;
import com.cloud.base.util.DBFM;
import com.cloud.roleuser.model.RoleUser;

@Repository
public class RoleUserDaoHibernate extends JdbcHibernateUtil {

	/**
 	 * 通过id得到某个对象
 	 * @param id
 	 * @return
 	 */
	public RoleUser getRoleUserById(String id) {
		return (RoleUser)getDataObject(RoleUser.class,id);
	}

	/**
	 * 保存
	 * @param user
	 * @return
	 */
	public RoleUser saveRoleUser(RoleUser roleUser) {
		if(roleUser.getId() == null || roleUser.getId().equals("")) {
			saveData(roleUser);
		} else {
			saveOrUpdate(roleUser);
		}
		return roleUser;
	}

	/**
	 * 批量保存数据
	 * @param list
	 * @return
	 */
	public synchronized boolean saveDataBatch(List<RoleUser> list) {
		boolean flag = true;
		Transaction tx = beginTransaction();
		try {
			for(int i=0;i<list.size();i++) {
				RoleUser roleUser = list.get(i);
				session.save(roleUser);
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
	public Map searchRoleUser(Long curPage, Long pageSize,String whereStr) {
		String hql = "FROM RoleUser roleUser";
		return queryData2MapByPage(curPage, pageSize, hql, whereStr);
	}

	/**
	 * 删除数据
	 * @param user
	 * @return
	 */
	public RoleUser delRoleUser(RoleUser roleUser) {
		return (RoleUser)delData(roleUser);
	}

	/**
	 * 批量删除
	 * @param user
	 * @return
	 */
	public void delRoleUserBatch(List<String> list) {
		String delHql = "DELETE RoleUser ";
		delDataBatch(delHql,list);
	}

	/**
	 * 通过条件查询数据(非分页)
	 */
	public List getAllDataByWhere(String where) {
		String hql = "FROM RoleUser roleUser where 1=1 " + where;
		return getDataList(hql);
	}
	
	/**
	 * 通过角色id删除其相关的所有角色与人员关系
	 * @param roleId
	 */
	public void delRoleUserByRoleId(String roleId) {
		String sql = "delete SYSTEM_ROLEUSER where LINKROLEID='" + roleId + "'";
		jdbcTemplate.update(sql);
	}

	/**
	 * 通过角色得到用户的userId
	 * @param roleId
	 * @return
	 */
	public String getUserIdsByRoleId(String roleId) {
		String userId = "";
		String sql = "select replace("+DBFM.WM_CONCAT()+"(userid),',',',') as USERIDS from system_roleuser where linkroleid='"+roleId+"'";
		Map map = jdbcTemplate.queryForMap(sql);
		if(map != null) {
			userId = (String)map.get("USERIDS");
		}
		return userId;
	}
	
	/**
	 * 通过角色id得到关联的用户名字
	 * @param roleId
	 * @return
	 */
	public String getUserNamesByRoleId(String roleId) {
		String userName = "";
		
		String sql = "select replace("+DBFM.WM_CONCAT()+"(su.username),',',',') as USERNAMES from system_roleuser sr,system_user su where sr.userid = su.userid and sr.linkroleid='"+roleId+"'";
		Map map = jdbcTemplate.queryForMap(sql);
		if(map != null) {
			userName = (String)map.get("USERNAMES");
		}
		return userName;
	}
}
