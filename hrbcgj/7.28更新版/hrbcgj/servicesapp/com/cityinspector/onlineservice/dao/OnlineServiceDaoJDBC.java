package com.cityinspector.onlineservice.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class OnlineServiceDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllOnlineServiceData() {
		String sql = "select * from ONLINESERVICE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
