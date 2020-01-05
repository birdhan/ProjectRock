package com.cityinspector.vote.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class User_VoteDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllUser_VoteData() {
		String sql = "select * from USER_VOTE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
