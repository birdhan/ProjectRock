package com.cityinspector.interaction.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class InteractionDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllInteractionData() {
		String sql = "select * from SERVICE_INTERACTION";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
