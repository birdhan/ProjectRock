package com.cloud.attachment.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class AttachmentDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllAttachmentData() {
		String sql = "select * from ATTACHMENT";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
