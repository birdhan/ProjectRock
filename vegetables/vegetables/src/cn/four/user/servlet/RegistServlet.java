package cn.four.user.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.four.tools.util.CommonUtils;
import cn.four.user.domain.User;
import cn.four.user.service.UserService;

public class RegistServlet extends HttpServlet {
	private UserService userservice=new UserService();
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		/**
		 * 接收表单传过来的数据
		 */
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		userservice.regist(user);
		/**
		 * 保存信息到msg页面
		 */
		request.setAttribute("code", "success");
  	  	request.setAttribute("msg", "恭喜您，注册成功");
  	  	request.getRequestDispatcher("msg.jsp").forward(request, response);
	}

}
