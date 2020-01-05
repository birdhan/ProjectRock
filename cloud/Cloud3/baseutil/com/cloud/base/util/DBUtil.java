package com.cloud.base.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/**
 * 数据库工具类
 * @author cyp
 *
 */
public class DBUtil {

	private static final Logger logger = Logger.getLogger(DBUtil.class);
	
	/**
	 * 关闭数据库连接 的方法
	 * @param rs
	 * @param pstm
	 * @param conn
	 */
	public static void closeAll(ResultSet rs,Statement pstm,Connection conn) {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstm!=null) {
				pstm.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e) {				
			e.printStackTrace();
		}
		logger.info("关闭数据库接连！");
	}
	
	/**
	 * 获取连接对象
	 * @param url
	 * @param className
	 * @param user
	 * @param pwd
	 * @return
	 */
	public static Connection getConn(String url,String className,String user,String pwd) {
		Connection conn = null;
		try {
			Class.forName(className);
			conn = DriverManager.getConnection(url,user,pwd);
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return conn;
	}
	
}
