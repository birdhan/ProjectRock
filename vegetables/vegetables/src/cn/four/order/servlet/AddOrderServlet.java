package cn.four.order.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.four.order.domain.Order;
import cn.four.order.service.OrderService;
import cn.four.tools.util.CommonUtils;
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderservice=new OrderService();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session = request.getSession();
		
		String uid=(String) session.getAttribute("m");
		
		Order order=CommonUtils.toBean(request.getParameterMap(), Order.class);
		
		order.setUid((String) uid);
		
		order.setStatus("0");
		
		orderservice.addOrder(order);
		response.sendRedirect("FindServlet");
	}

}
