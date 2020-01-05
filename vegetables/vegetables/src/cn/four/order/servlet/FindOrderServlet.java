package cn.four.order.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.four.dish.domain.Dish;
import cn.four.order.service.OrderService;

public class FindOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderService orderservice = new OrderService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String attribute = (String) session.getAttribute("m");

		if (attribute == null) {

			response.sendRedirect("");

		} else {

			List<Dish> olist = orderservice.findByOrder(attribute);

			request.setAttribute("olist", olist);
			
			Double prices=0.0;
			
			
			
			for (int i=0; i < olist.size(); i++) {
				Double price = olist.get(i).getPrice();
				
				prices=prices+price;
				
			}
			
			
			
			request.setAttribute("prices", prices);
			request.getRequestDispatcher("han/about.jsp").forward(request,
					response);
			
		}

	}

}
