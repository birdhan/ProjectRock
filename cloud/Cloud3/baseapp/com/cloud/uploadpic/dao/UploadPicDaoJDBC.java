package com.cloud.uploadpic.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class UploadPicDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllUploadPicData() {
		String sql = "select * from UPLOADPIC";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
