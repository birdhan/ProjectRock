package com.cloud.sqllog.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class SqllogDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllSqllogData() {
		String sql = "select * from SQLLOG";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
