package com.cloud.rolemanager.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllRoleData() {
		String sql = "select * from ROLE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}
	
	/**
	 * 得到所有角色
	 * @return
	 */
	public List<Map<String, Object>> getAllRoles() {
		String getAllRoles_sql = "select id,name from SYSTEM_ROLE";							//得到所有角色
		return getJdbcTemplate().queryForList(getAllRoles_sql);
	}
	
	/**
	 * 得到某角色下的所有菜单
	 * @param roleId
	 * @return
	 */
	public List<Map<String, Object>> getRoleMenuUrl(String roleId) {
		String queryMenuUrl_sql = "select sr.linkmenuid,sr.privno,(select url from system_menu_priv where linkmenuid=sr.linkmenuid and privno=sr.privno) as menuurl from SYSTEM_ROLEMENUPRIV sr where linkroleid='"+roleId+"'";
		return getJdbcTemplate().queryForList(queryMenuUrl_sql);
	}

}
