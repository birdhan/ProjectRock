package com.cloud.menumanager.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllMenuData() {
		String sql = "select * from SYSTEM_MENU";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
