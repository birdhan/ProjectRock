package com.cloud.base.test.other;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.cloud.base.util.DBUtil;

public class JDBMonitor {

	public static void main(String[] args) throws Exception {
		
		String configPath = JDBMonitor.class.getResource("/").getPath()+"jdbmonitorconfig.xml";
		System.out.println(configPath);
		
		String url = "listenerconfig="+configPath+":url=jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String className = "com.cownew.JDBMonitor.jdbc.DBDriver";
		String user = "cloud3";
		String pwd = "cloud3";
		Connection conn = DBUtil.getConn(url, className, user, pwd);
		PreparedStatement pstm =  conn.prepareStatement("update demo set name=? where id='d4905bbb-ce72-4a8f-b3a1-1db9308ed45a'");
		pstm.setString(1, "cyp");
		System.out.println(pstm.executeUpdate());
		pstm.close();		
	}
}
