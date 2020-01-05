package com.cloud.schedulemanager.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllScheduleData() {
		String sql = "select * from SCHEDULE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
