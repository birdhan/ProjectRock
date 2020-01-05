package com.cloud.menupriv.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class MenuPrivDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllMenuPrivData() {
		String sql = "select * from SYSTEM_MENU_PRIV";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
