package com.cloud.webservice.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class WebServiceDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllWebServiceData() {
		String sql = "select * from WEBSERVICE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
