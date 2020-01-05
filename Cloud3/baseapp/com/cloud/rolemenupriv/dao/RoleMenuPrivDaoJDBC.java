package com.cloud.rolemenupriv.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class RoleMenuPrivDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllRoleMenuPrivData() {
		String sql = "select * from ROLEMENUPRIV";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
