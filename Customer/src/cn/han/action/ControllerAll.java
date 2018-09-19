package cn.han.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.han.pojo.Customer_address;
import cn.han.pojo.Customer_order;
import cn.han.pojo.Customer_regist;
import cn.han.pojo.Customer_state;
import cn.han.pojo.Customer_type;
import cn.han.pojo.Customer_user;
import cn.han.service.AddressService;
import cn.han.service.OrderService;
import cn.han.service.RegisterService;
import cn.han.service.StateService;
import cn.han.service.TypeService;
import cn.han.service.UserService;

@Controller
public class ControllerAll {

	@Resource
	private RegisterService registerService;

	@Resource
	private UserService userService;

	@Resource
	private OrderService orderService;

	@Resource
	private AddressService addressService;

	@Resource
	private StateService stateService;

	@Resource
	private TypeService typeService;

	private String regis;

	/**
	 * 登录验证
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/register")
	public String register(HttpServletRequest request, HttpServletResponse response) {
		regis = "welcome";
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Customer_regist findByOne = registerService.findByOne();
		String use = request.getParameter("user");
		String pwd = request.getParameter("pwd");

		if (use.equals(findByOne.getUser()) && !use.trim().isEmpty()) {
			if (pwd.equals(findByOne.getPwd()) && !pwd.trim().isEmpty()) {
				HttpSession session = request.getSession();
				// 把用户数据保存在session域对象中
				session.setAttribute("loginName", use);
				// 跳转到用户主页

				regis = "redirect:/index";

			} else {
				regis = "welcome";
			}
		} else {
			regis = "welcome";
		}

		return regis;

	}

	/**
	 * 加载首页信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String indexuser(Model model, HttpServletRequest request) {

		String shouye = "index";
		/* HttpSession session = request.getSession(); */
		// 把用户数据保存在session域对象中
		/*
		 * if (session.getAttribute("loginName") == null) { shouye = "redirect:/"; }
		 * else {
		 */

		List<Customer_user> findAll = userService.findAll();
		model.addAttribute("allUser", findAll);
		System.out.println(findAll);

		Integer count = userService.count();
		model.addAttribute("userNumber", count);

		Integer count1 = orderService.count1();
		model.addAttribute("payment1", count1);

		Integer count2 = orderService.count2();
		model.addAttribute("payment2", count2);

		Integer countAddress = addressService.countAddress();
		model.addAttribute("addressNumber", countAddress);

		List<Customer_address> addressfindAll = addressService.findAll();
		model.addAttribute("address", addressfindAll);

		List<Customer_state> statefindAll = stateService.findAll();
		model.addAttribute("state", statefindAll);

		List<Customer_type> typefindAll = typeService.findAll();
		model.addAttribute("type", typefindAll);

		Integer numpage = count / 10;
		if (count % 10 > 0) {
			numpage = numpage + 1;
		}
		Integer pagenum = 1;

		request.setAttribute("numpage", numpage);
		request.setAttribute("nowpage", pagenum);

		return shouye;
	}

	/**
	 * 添加用户信息
	 * 
	 * @param request
	 * @param users
	 * @return
	 */
	@RequestMapping(value = "/registers", method = RequestMethod.POST)
	public String register(HttpServletRequest request, Customer_user users) {

		System.out.println("start");

		String id = UUID.randomUUID().toString();
		users.setId(id);

		users.setUsername(request.getParameter("username"));
		users.setTel(request.getParameter("tel"));
		String parameter = request.getParameter("zuidate");

		users.setZuijindate(parameter);

		users.setResult(request.getParameter("result"));
		users.setGuanzhudu(request.getParameter("guanzhudu"));
		users.setBuysome(request.getParameter("buysome"));
		users.setHobby(request.getParameter("hobby"));
		String ages = request.getParameter("age");
		if (ages == "") {
			ages = "20";
		}
		Integer age = Integer.valueOf(ages);

		users.setAge(age);

		users.setState(request.getParameter("state"));
		users.setType(request.getParameter("type"));
		users.setDetails(request.getParameter("details"));
		users.setAddre(request.getParameter("addre"));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		users.setCreatedate(dateFormat.format(date));

		users.setOrderupdate(dateFormat.format(date));

		userService.addUser(users);

		return "redirect:/index";

	}

	/**
	 * 删除客户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteuser")
	public String deletsusers(HttpServletRequest request) {
		String tiaozhuan = "redirect:/index";
		String id = request.getParameter("id");
		/* String opera = request.getParameter("opera"); */
		/* String re="redirect:/index"; */
		String ctype = request.getParameter("cType");
		String cstate = request.getParameter("cState");

		if (ctype != "") {
			tiaozhuan = "forward:/selecttype";

		}
		if (cstate != "") {
			tiaozhuan = "forward:/selectstatus";
		}
		userService.delete(id);

		return tiaozhuan;

	}

	/**
	 * 修改客户信息
	 * 
	 * @param request
	 * @param users
	 * @return
	 */
	@RequestMapping("/updataUser")
	public String updatauser(HttpServletRequest request, Customer_user users, RedirectAttributes attributes) {

		String tiaozhuan = "redirect:/index";
		users.setId(request.getParameter("id"));

		users.setUsername(request.getParameter("username"));
		users.setTel(request.getParameter("tel"));
		String parameter = request.getParameter("zuidate");

		users.setZuijindate(parameter);

		String parameter2 = request.getParameter("cus_sel");
		String typevalue = request.getParameter("type");
		if (parameter2.equals("type")) {

			System.out.println("输出重定向传值");

			tiaozhuan = "redirect:/selecttype?cType=";

			/* tiaozhuan="redirect:/selecttype?cType="+typevalue; */
		}

		if (parameter2.equals("status")) {
			tiaozhuan = "redirect:/selectstatus?cState=";
		}
		users.setResult(request.getParameter("result"));
		users.setGuanzhudu(request.getParameter("guanzhudu"));
		users.setBuysome(request.getParameter("buysome"));
		users.setHobby(request.getParameter("hobby"));
		String ages = request.getParameter("age");
		if (ages == "") {
			ages = "20";
		}
		Integer age = Integer.valueOf(ages);

		users.setAge(age);

		users.setState(request.getParameter("state"));
		users.setType(typevalue);
		users.setDetails(request.getParameter("details"));
		users.setAddre(request.getParameter("addre"));

		userService.update(users);

		return tiaozhuan;

	}

	/**
	 * 条件查询用户信息
	 * 
	 * @param request
	 * @param users
	 * @return
	 */

	@RequestMapping("/selectuser")
	public String userselect(HttpServletRequest request, Customer_user users, Model model) {

		if (users.getUsername() == "") {

			users.setUsername("null");
		}

		if (users.getTel() == "") {
			users.setTel("null");
		}

		if (users.getHobby() == "") {
			users.setHobby("null");
		}

		if (users.getAge() == null) {
			users.setAge(null);
		}

		if (users.getZuijindate() == "") {
			users.setZuijindate("null");
		}

		if (users.getState() == "") {
			users.setState("null");
		}

		System.out.println(users);
		List<Customer_user> findBySome = userService.findBySome(users);
		model.addAttribute("findBySome", findBySome);

		List<Customer_address> addressfindAll = addressService.findAll();
		model.addAttribute("address", addressfindAll);

		List<Customer_state> statefindAll = stateService.findAll();
		model.addAttribute("state", statefindAll);

		List<Customer_type> typefindAll = typeService.findAll();
		model.addAttribute("type", typefindAll);

		return "selectuser";
	}

	/**
	 * 添加订单
	 */
	@RequestMapping("/userorder")
	public String userorder(HttpServletRequest request, Customer_order cusorder) {

		String tiaozhuan = "redirect:/index";
		String id = UUID.randomUUID().toString();

		cusorder.setId(id);
		cusorder.setUid(request.getParameter("uid"));
		cusorder.setUser(request.getParameter("user"));
		cusorder.setTel(request.getParameter("tel"));
		cusorder.setTitle(request.getParameter("title"));
		cusorder.setContractnumber(request.getParameter("contractnumber"));
		cusorder.setOrdernumber(request.getParameter("ordernumber"));

		String parameter = request.getParameter("paymentdate");

		cusorder.setPaymentdate(parameter);
		cusorder.setMoney(request.getParameter("money"));
		cusorder.setPaymentstate(request.getParameter("paymentstate"));
		cusorder.setDetails(request.getParameter("details"));

		String parameter2 = request.getParameter("cus_sel");

		if (parameter2.equals("type")) {

			tiaozhuan = "redirect:/selecttype?cType=";

			/* tiaozhuan="redirect:/selecttype?cType="+typevalue; */
		}
		if (parameter2.equals("status")) {
			tiaozhuan = "redirect:/selectstatus?cState=";
		}

		orderService.inserto(cusorder);

		return tiaozhuan;
	}

	/**
	 * 客户类型查询
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/selecttype")
	public String selecttype(HttpServletRequest request, Model model, HttpServletResponse response) {

		List<Customer_address> addressfindAll = addressService.findAll();
		model.addAttribute("address", addressfindAll);

		List<Customer_state> statefindAll = stateService.findAll();
		model.addAttribute("state", statefindAll);

		List<Customer_type> typefindAll = typeService.findAll();
		model.addAttribute("type", typefindAll);

		String typefind = request.getParameter("cType");

		if (typefind == "") {
			typefind = typefindAll.get(0).getCustomertype();
			System.out.println(typefind);
		} else {

			try {
				if (typefind.equals(new String(typefind.getBytes("iso8859-1"), "iso8859-1"))) {
					typefind = new String(typefind.getBytes("iso8859-1"), "UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		System.out.println(typefind);

		List<Customer_user> findByType = userService.findByType(typefind);
		model.addAttribute("allUser", findByType);

		return "customerType";
	}

	/**
	 * 
	 * 查询客户状态信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */

	@RequestMapping("/selectstatus")
	public String selectstatus(Model model, HttpServletRequest request) {

		List<Customer_address> addressfindAll = addressService.findAll();
		model.addAttribute("address", addressfindAll);

		List<Customer_state> statefindAll = stateService.findAll();
		model.addAttribute("state", statefindAll);

		List<Customer_type> typefindAll = typeService.findAll();
		model.addAttribute("type", typefindAll);

		String statufind = request.getParameter("cState");

		if (statufind == "") {
			statufind = statefindAll.get(0).getCustomerstate();

		}

		try {
			if (statufind.equals(new String(statufind.getBytes("iso8859-1"), "iso8859-1"))) {
				statufind = new String(statufind.getBytes("iso8859-1"), "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		List<Customer_user> findByType = userService.findByState(statufind);
		model.addAttribute("allUser", findByType);

		return "customerState";
	}

	/**
	 * ajax删除用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/shanchuuser")
	public String shanchuuser(HttpServletRequest request, HttpServletResponse response) {
		boolean s = true;
		String parameter = request.getParameter("id");
		userService.delete(parameter);
		try {
			response.getWriter().print(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ajax删除订单信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/shanchuorder")
	public String shanchuorder(HttpServletRequest request, HttpServletResponse response) {
		boolean s = true;
		String parameter = request.getParameter("id");
		orderService.deleteo(parameter);
		try {
			response.getWriter().print(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ajax添加订单
	 * 
	 * @param request
	 * @param response
	 * @param cusos
	 * @return
	 */
	@RequestMapping("/joinorder")
	public String joinorder(HttpServletRequest request, HttpServletResponse response, Customer_order cusos) {

		String string = UUID.randomUUID().toString();
		cusos.setId(string);

		orderService.inserto(cusos);
		boolean s = true;
		try {
			response.getWriter().print(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * ajax修改用户信息
	 * 
	 * @param request
	 * @param response
	 * @param csu
	 * @return
	 */
	@RequestMapping("/xiugaiuser")
	public String xiugaiuser(HttpServletRequest request, HttpServletResponse response, Customer_user csu) {

		boolean s = true;
		String ages = request.getParameter("age1");

		Integer age = Integer.valueOf(ages);

		csu.setAge(age);

		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		csu.setOrderupdate(sim.format(date));

		userService.update(csu);

		try {
			response.getWriter().print(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 修改订单信息
	 * 
	 * @param request
	 * @param response
	 * @param cuo
	 * @return
	 */
	@RequestMapping("/xiugaiorder")
	public String xiugaiorder(HttpServletRequest request, HttpServletResponse response, Customer_order cuo) {

		boolean s = true;
		orderService.updateo(cuo);
		try {
			response.getWriter().print(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 条件查询订单
	 * 
	 * @param request
	 * @param customer_order
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectsomeorder")
	public String selectsomeorder(HttpServletRequest request, Customer_order cuo, Model model) {
		if (cuo.getUser() == "") {
			cuo.setUser("null");
		}
		if (cuo.getContractnumber() == "") {
			cuo.setContractnumber("null");
		}
		if (cuo.getOrdernumber() == "") {
			cuo.setOrdernumber("null");
		}
		if (cuo.getUser() == "") {
			cuo.setUser("null");
		}
		if (cuo.getTitle() == "") {
			cuo.setTitle("null");
		}
		if (cuo.getMoney() == "") {
			cuo.setMoney("null");
		}
		List<Customer_order> findBySome = orderService.findBySome(cuo);

		model.addAttribute("find", findBySome);

		return "orderselect";
	}

	/*
	 * ajax 分页查询 下一页
	 */

	@RequestMapping("/findfen")
	public String findfen(String nowPage, Model model, HttpServletRequest request, HttpServletResponse response) {

		String shouye = "index";

		/*
		 * List<Customer_user> findAll = userService.findAll();
		 * model.addAttribute("allUser", findAll);
		 */

		Integer count = userService.count();
		model.addAttribute("userNumber", count);

		Integer numpage = count / 10;
		if (count % 10 > 0) {
			numpage = numpage + 1;
		}

		Integer pagenum1 = Integer.parseInt(nowPage) + 1;

		if (pagenum1 >= numpage) {
			pagenum1 = numpage;
		}

		int parseInt = Integer.parseInt(nowPage);
		Integer pagenum = 0;
		if (parseInt == numpage) {
			pagenum = (parseInt - 1) * 10;
		} else {
			pagenum = parseInt * 10;
		}

		List<Customer_user> findAll = userService.findByfenye(pagenum);

		model.addAttribute("allUser", findAll);

		Integer count1 = orderService.count1();
		model.addAttribute("payment1", count1);

		Integer count2 = orderService.count2();
		model.addAttribute("payment2", count2);

		Integer countAddress = addressService.countAddress();
		model.addAttribute("addressNumber", countAddress);

		List<Customer_address> addressfindAll = addressService.findAll();
		model.addAttribute("address", addressfindAll);

		List<Customer_state> statefindAll = stateService.findAll();
		model.addAttribute("state", statefindAll);

		List<Customer_type> typefindAll = typeService.findAll();
		model.addAttribute("type", typefindAll);

		request.setAttribute("numpage", numpage);
		request.setAttribute("nowpage", pagenum1);

		return shouye;

		/* List<Customer_user> findByfenye = userService.findByfenye(20); */

	}

	/**
	 * 上一页
	 * 
	 * @param nowPage
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/findfenup")
	public String findfenup(String nowPage, Model model, HttpServletRequest request, HttpServletResponse response) {

		String shouye = "index";

		/*
		 * List<Customer_user> findAll = userService.findAll();
		 * model.addAttribute("allUser", findAll);
		 */

		int parseInt = Integer.parseInt(nowPage);
		Integer pagenum = 0;
		if (parseInt == 1) {
			pagenum = 0;
		} else {
			pagenum = (parseInt - 2) * 10;
		}

		List<Customer_user> findAll = userService.findByfenye(pagenum);
		model.addAttribute("allUser", findAll);

		Integer count = userService.count();
		model.addAttribute("userNumber", count);

		Integer count1 = orderService.count1();
		model.addAttribute("payment1", count1);

		Integer count2 = orderService.count2();
		model.addAttribute("payment2", count2);

		Integer countAddress = addressService.countAddress();
		model.addAttribute("addressNumber", countAddress);

		List<Customer_address> addressfindAll = addressService.findAll();
		model.addAttribute("address", addressfindAll);

		List<Customer_state> statefindAll = stateService.findAll();
		model.addAttribute("state", statefindAll);

		List<Customer_type> typefindAll = typeService.findAll();
		model.addAttribute("type", typefindAll);

		Integer numpage = count / 10;
		if (count % 10 > 0) {
			numpage = numpage + 1;
		}

		Integer pagenum1 = 1;
		if (parseInt == 1) {
			pagenum1 = 1;
		} else {

			pagenum1 = Integer.parseInt(nowPage) - 1;
		}

		request.setAttribute("numpage", numpage);
		request.setAttribute("nowpage", pagenum1);

		return shouye;

		

	}
	
	/**
	 * 分页查询跳转到指定页
	 * @param nowPage
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/findfenselect")
	public String findfenselect(String nowPage, Model model, HttpServletRequest request, HttpServletResponse response) {

		String shouye = "index";

		/*
		 * List<Customer_user> findAll = userService.findAll();
		 * model.addAttribute("allUser", findAll);
		 */

		int parseInt = Integer.parseInt(nowPage);
		
		
			Integer pagenum = (parseInt - 1) * 10;
		

		List<Customer_user> findAll = userService.findByfenye(pagenum);
		model.addAttribute("allUser", findAll);

		Integer count = userService.count();
		model.addAttribute("userNumber", count);

		Integer count1 = orderService.count1();
		model.addAttribute("payment1", count1);

		Integer count2 = orderService.count2();
		model.addAttribute("payment2", count2);

		Integer countAddress = addressService.countAddress();
		model.addAttribute("addressNumber", countAddress);

		List<Customer_address> addressfindAll = addressService.findAll();
		model.addAttribute("address", addressfindAll);

		List<Customer_state> statefindAll = stateService.findAll();
		model.addAttribute("state", statefindAll);

		List<Customer_type> typefindAll = typeService.findAll();
		model.addAttribute("type", typefindAll);

		Integer numpage = count / 10;
		if (count % 10 > 0) {
			numpage = numpage + 1;
		}

		Integer pagenum1 = parseInt;
		
			
		

		request.setAttribute("numpage", numpage);
		request.setAttribute("nowpage", pagenum1);

		return shouye;

		

	}

}
