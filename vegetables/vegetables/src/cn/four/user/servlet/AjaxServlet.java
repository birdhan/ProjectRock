package cn.four.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.four.tool.conn.DBConn;

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		// 接收传过来的user数据
		String uname = request.getParameter("uname");

		/* String upassword = request.getParameter("upassword"); */

		// 判断用户名的正则表达式，用以验证用户名是否合法

		boolean flag = false;

		if (uname != "") {

			Connection conn = DBConn.getConnection();

			try {
				String sql = "select * from t_user where uname='" + uname + "'";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();

				System.out.println(rs);

				while (rs.next()) {
					if (rs.getString("uname").equals(uname)) {
						flag = true;
					}

					System.out.println(rs.getString("uname"));

				}
				out.print(flag);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
