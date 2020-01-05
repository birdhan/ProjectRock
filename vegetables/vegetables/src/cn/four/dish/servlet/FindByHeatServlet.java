package cn.four.dish.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.four.dish.domain.Dish;
import cn.four.dish.service.DishService;

public class FindByHeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DishService dishService = new DishService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Dish> hlist = dishService.findByHeat();
		request.setAttribute("hlist", hlist);
		request.getRequestDispatcher("han/home.jsp").forward(request, response);
	}

}
