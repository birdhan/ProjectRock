package com.cityinspector.vote.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class VoteXuanDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllVoteXuanData() {
		String sql = "select * from VOTEXUAN";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
