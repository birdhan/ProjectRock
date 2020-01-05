package com.cloud.base.util;

import org.apache.log4j.Logger;

/**
 * 日志工具类
 * @author cloud7
 *
 */
public class LoggerUtil {
	
	/**
	 * 记录信息日志
	 * @param clazz
	 * @param content
	 */
	public static void info(Class clazz,String content) {
		Logger logger = Logger.getLogger(clazz);
		logger.info("\n[日志信息]"+content);
	}
	
	/**
	 * 记录错误日志
	 * @param clazz
	 * @param content
	 */
	public static void error(Class clazz,String content) {
		Logger logger = Logger.getLogger(clazz);
		logger.error("\n[日志错误信息]"+content);
	}
}
