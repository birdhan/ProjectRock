package com.cloud.datadict.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class DataDictDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllDataDictData() {
		String sql = "select * from DATADICT";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
