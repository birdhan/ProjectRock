package com.cloud.base.util;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

/**
 * 获取RowMapper对象工具类
 * @author cloud7
 *
 */
public class RowMapperUtil {

	public static RowMapper getRowMapper(Class clazz) {
		return new BeanPropertyRowMapper(clazz);
	}
}
