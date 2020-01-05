package com.cloud.callwebservice.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class CallWebServiceDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllCallWebServiceData() {
		String sql = "select * from CALLWEBSERVICE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
