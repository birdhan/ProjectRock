package com.cityinspector.viewmesasge.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ViewMessageDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllViewMessageData() {
		String sql = "select * from SERVICE_VIEWMESSAGE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
