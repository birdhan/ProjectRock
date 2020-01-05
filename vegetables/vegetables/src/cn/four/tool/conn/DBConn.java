package cn.four.tool.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	private static String username="root";
	private static String password="123456";
	private static String url="jdbc:mysql://localhost:3306/byording";
	private static Connection conn;
	public static Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	//关闭连接的方法
	public  static void release(Connection conn,PreparedStatement ps,ResultSet rs) {
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps !=null){
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs !=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
