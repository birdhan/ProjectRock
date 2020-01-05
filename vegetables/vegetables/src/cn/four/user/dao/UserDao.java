package cn.four.user.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import cn.four.tool.conn.DBConn;
import cn.four.user.domain.User;

public class UserDao {
/**
 * 添加用户信息
 */
	
	public void addUser(User user){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn=DBConn.getConnection();
			String sql = "insert into t_user(uid,uname,upassword) values(?,?,?) ";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, user.getUid());
			ps.setObject(2, user.getUname());
			ps.setObject(3, user.getUpassword());
			int row = ps.executeUpdate();
			if(row>0){
				System.out.println("注册成功");
			}else {
				System.out.println("注册失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConn.release(conn, ps, null);
		}
	}
	/**
	 * 查询用户信息
	 */
	public User findUser(String uname,String upassword){
		User u =null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			conn=DBConn.getConnection();
			String sql = "select * from t_user where uname=? and upassword=?";
			ps=conn.prepareStatement(sql);
			ps.setObject(1, uname);
			ps.setObject(2, upassword);
			rs=ps.executeQuery();
			if(rs.next()){
				u=new User();
				u.setUname(rs.getString("uname"));
				u.setUpassword(rs.getString("upassword"));
				u.setUid(rs.getString("uid"));
				System.out.println("登录成功！");
			}else {
				System.out.println("用户名或者密码错误1");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConn.release(conn, ps, rs);
		}
		return u;
		
	}
	
}
