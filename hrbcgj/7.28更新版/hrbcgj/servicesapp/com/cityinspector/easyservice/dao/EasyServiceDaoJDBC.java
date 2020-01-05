package com.cityinspector.easyservice.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class EasyServiceDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllEasyServiceData() {
		String sql = "select * from EASYSERVICE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
