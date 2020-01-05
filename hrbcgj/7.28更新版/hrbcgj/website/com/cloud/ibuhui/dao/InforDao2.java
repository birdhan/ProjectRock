package com.cloud.ibuhui.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cloud.base.util.ApplicationContextHolder;

@Repository
public class InforDao2 {

	
	private JdbcTemplate getJdbcTemplate(){
		return (JdbcTemplate) ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
	}
	
	
	
	public List<Map<String, Object>> getserviceSection(){
		String sql = "select * from service_section where isshow = '1' and postion = '1' order by sortnum";
		return getJdbcTemplate().queryForList(sql);
	}
}
