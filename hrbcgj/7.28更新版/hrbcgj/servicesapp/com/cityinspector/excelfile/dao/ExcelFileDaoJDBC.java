package com.cityinspector.excelfile.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class ExcelFileDaoJDBC extends JdbcDaoSupport{

	@Resource
	public void setDaoDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}

	public List getAllExcelFileData() {
		String sql = "select * from EXCELFILE";
		List list = getJdbcTemplate().queryForList(sql);
		return list;
	}

}
