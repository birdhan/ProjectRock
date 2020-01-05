package com.cityinspector.linkservice.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class LinkDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllLinkData() {
		String sql = "select * from SERVICE_LINK";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
