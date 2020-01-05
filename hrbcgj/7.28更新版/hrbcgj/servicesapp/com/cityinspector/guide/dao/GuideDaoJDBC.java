package com.cityinspector.guide.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class GuideDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllGuideData() {
		String sql = "select * from GUIDE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
