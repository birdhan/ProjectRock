package cn.four.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.four.tools.util.CommonUtils;
import cn.four.user.domain.User;
import cn.four.user.service.UserService;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userservice = new UserService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/**
		 * 接收表单传递过来的数据
		 */
		User fuser = CommonUtils.toBean(request.getParameterMap(), User.class);
		
		User user = userservice.findUser(fuser);

		String uname = request.getParameter("uname");
		
		request.getSession().setAttribute("u", uname);
		
	
		
		
		
		if (user == null) {
			request.setAttribute("msg", "用户名或密码错误，请重新输入！");
			request.setAttribute("form", fuser);
			request.getRequestDispatcher("msg.jsp").forward(request, response);
		} else {
			request.getSession().setAttribute("m", user.getUid());
			response.sendRedirect("/vegetables/FindServlet");
		}
	}
}
