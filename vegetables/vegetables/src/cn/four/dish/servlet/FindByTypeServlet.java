package cn.four.dish.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.four.dish.domain.Dish;
import cn.four.dish.service.DishService;

public class FindByTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DishService dishService = new DishService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String parameter = request.getParameter("typeone");
		List<Dish> tlist = dishService.findByType(parameter);
		String retype = "";

		String type = tlist.get(0).getType();

		if (type.equals("cantian")) {
			retype = "餐后甜点";
		}
		if (type.equals("meiwei")) {
			retype = "美味佳肴";
		}
		if (type.equals("zhushi")) {
			retype = "叫点主食";
		}

		request.setAttribute("tlist", tlist);
		request.setAttribute("retype", retype);

		request.getRequestDispatcher("han/services.jsp").forward(request,
				response);
	}

}
