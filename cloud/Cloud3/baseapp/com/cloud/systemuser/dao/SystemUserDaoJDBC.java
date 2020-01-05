package com.cloud.systemuser.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class SystemUserDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllSystemUserData() {
		String sql = "select * from SYSTEMUSER";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
