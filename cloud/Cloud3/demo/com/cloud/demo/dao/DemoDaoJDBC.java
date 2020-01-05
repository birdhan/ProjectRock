package com.cloud.demo.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class DemoDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	} 
	
	public List testQueryAllData() {
		String sql = "select ID,NAME,CREATETIME,AGE,CONTENTVAL,HOBBY,SEX from DEMO";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}
}
