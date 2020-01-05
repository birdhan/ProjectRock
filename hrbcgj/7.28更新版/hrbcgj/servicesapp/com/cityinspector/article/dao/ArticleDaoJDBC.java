package com.cityinspector.article.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllArticleData() {
		String sql = "select * from ARTICLE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
