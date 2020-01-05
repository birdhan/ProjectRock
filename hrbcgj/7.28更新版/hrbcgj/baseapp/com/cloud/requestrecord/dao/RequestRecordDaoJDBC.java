package com.cloud.requestrecord.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class RequestRecordDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllRequestRecordData() {
		String sql = "select * from SYSTEM_REQUESTRECORD";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
