package com.cloud.systemconfig.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class SystemConfigDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllSystemConfigData() {
		String sql = "select * from SYSTEMCONFIG";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
