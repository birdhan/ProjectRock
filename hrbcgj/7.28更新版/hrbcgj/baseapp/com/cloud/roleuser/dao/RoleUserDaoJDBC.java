package com.cloud.roleuser.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class RoleUserDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllRoleUserData() {
		String sql = "select * from SYSTEM_ROLEUSER";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
