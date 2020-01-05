package com.cityinspector.premierletter.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class PremierLetterDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllPremierLetterData() {
		String sql = "select * from SERVICE_PREMIERLETTER";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
