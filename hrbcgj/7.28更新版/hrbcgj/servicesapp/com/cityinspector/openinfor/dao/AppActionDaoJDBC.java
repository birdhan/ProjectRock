package com.cityinspector.openinfor.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AppActionDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllAppActionData() {
		String sql = "select * from APPACTION";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
