package com.cloud.department.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllDepartmentData() {
		String sql = "select * from SYSTEM_DEPARTMENT";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
