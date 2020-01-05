package com.drugs.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.drugs.pojo.Consumer;
import com.drugs.pojo.Dorder;
import com.drugs.pojo.Drugs;
import com.drugs.pojo.Enterprise;
import com.drugs.service.AdministratorService;
import com.drugs.service.ConsumerService;
import com.drugs.service.DorderService;
import com.drugs.service.DrugsService;
import com.drugs.service.EnterpriseService;
import com.drugs.tools.Tools;

@Controller
@RequestMapping("consumer")
public class UserController {

	@Autowired
	private ConsumerService consumerservice;
	@Autowired
	private DrugsService drugsservice;
	@Autowired
	private EnterpriseService enterpriseservice;
	@Autowired
	private DorderService dorderservice;
	@Autowired
	private AdministratorService administratorservice;

	Tools tools = new Tools();

	// 登录/注册页跳转
	@RequestMapping("/signin")
	public String singnin() {
		return "sign";
	}

	
	// 注册controller
	@RequestMapping("/signuser")
	public String signuser(HttpServletRequest reqeust, HttpServletResponse response, Consumer consumer,
			HttpSession session) {

		consumer.setId(tools.randomdate());
		consumer.setUbalance("100");
		consumer.setUtype("普通用户");
		consumerservice.sign(consumer);
		session.setAttribute("consumer", consumer);
		session.setAttribute("ubalance", consumer.getUbalance());
		return "redirect:/consumer/first";
	}

	// 用户登录验证
	@RequestMapping("/logincon")
	public String logincon(String uname, String pwd, HttpSession session, Model model) {
		String resource = " ";
		Consumer login = consumerservice.login(uname, pwd);

		if (login != null) {
			session.setAttribute("consumer", login);
			session.setAttribute("ubalance", login.getUbalance());
			resource = "redirect:/consumer/first";

		} else {
			session.setAttribute("fail", "账号或者密码错误");
			resource = "redirect:/consumer/signin";

		}

		return resource;

	}

	// 注销登录
	@RequestMapping("/zhuxiao")
	public String zhuxiao(HttpServletRequest request) {
		request.getSession().removeAttribute("consumer");
		return "redirect:/consumer/signin";
	}

	// 加载首页
	@RequestMapping("/first")
	public String first(Model model) {
		List<Drugs> selectlimit = drugsservice.selectlimit();
		model.addAttribute("drugss", selectlimit);
		return "index";
	}
	
	/**
	 * 药品详情页
	 * @param model
	 * @param did
	 * @return
	 */
	@RequestMapping("/second")
	public String details(Model model,String did) {
		int id=Integer.parseInt(did);
		Drugs selectdrugsByid = drugsservice.selectdrugsByid(id);
		model.addAttribute("setailsb",selectdrugsByid);
		return "second";
	}

	
	/**
	 * ajax加载更新新内容
	 * @param numbers
	 * @return
	 */
	@RequestMapping("/ajaxupdate")
	@ResponseBody
	public Map<String,Object> ajaxupdate(int numbers) {
		/*int number=Integer.parseInt(numbers);*/
		List<Drugs> selectajax = drugsservice.selectajax(numbers);
		Map<String,Object> map=new HashMap<>();
		map.put("selectajax", selectajax);
		return map;
	}

	// 个人中心
	@RequestMapping("/userhome")
	public String userhome(String id, Model model) {

		int parseInt = Integer.parseInt(id);
		Consumer selectbyid = consumerservice.selectbyid(parseInt);
		model.addAttribute("consbyid", selectbyid);

		return "userhome";
	}

	// ajax根据id修改用户登录名
	@RequestMapping("/upuname")
	@ResponseBody
	public Map<String, Object> upuname(String id, String uname) {
		int parseInt = Integer.parseInt(id);
		enterpriseservice.upuname(parseInt, uname);
		consumerservice.updatebyuname(parseInt, uname);
		Map<String, Object> map = new HashMap<>();
		map.put("data", "修改成功");
		return map;
	}

	// ajax根据id修改用户电话
	@RequestMapping("/uptel")
	@ResponseBody
	public Map<String, Object> uptel(String id, String tel) {
		int parseInt = Integer.parseInt(id);
		consumerservice.updatebytel(parseInt, tel);
		Map<String, Object> map = new HashMap<>();
		map.put("data", "修改成功");
		return map;
	}

	// ajax根据id修改用户收货地址
	@RequestMapping("/upaddress")
	@ResponseBody
	public Map<String, Object> upaddress(String id, String address) {
		int parseInt = Integer.parseInt(id);
		consumerservice.updatebyaddress(parseInt, address);
		Map<String, Object> map = new HashMap<>();
		map.put("data", "修改成功");
		return map;
	}

	// ajax根据id修改用户密码
	@RequestMapping("/uppwd")
	@ResponseBody
	public Map<String, Object> uppwd(String id, String pwd) {
		int parseInt = Integer.parseInt(id);
		enterpriseservice.uppwd(parseInt, pwd);
		consumerservice.updatebypwd(parseInt, pwd);
		Map<String, Object> map = new HashMap<>();
		map.put("data", "修改成功");
		return map;
	}

	
	/**
	 * 添加我的药箱（加入购物车）
	 * @param order
	 * @return
	 */
	@RequestMapping("/addorder")
	public String addorder(Dorder order,HttpSession session) {
		Consumer consumer = (Consumer)session.getAttribute("consumer");
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd");
		long now=System.currentTimeMillis();
		order.setId(tools.randomdate());
		order.setDstatus("付款");
		order.setUid(consumer.getId());
		order.setUname(consumer.getUname());
		order.setUaddress(consumer.getAddress());
		order.setUtel(consumer.getTel());
		order.setLoan("放款");
		order.setOdate(myFmt.format(now));
		dorderservice.insertintoo(order);
		
		return "redirect:/consumer/orderdrugs";
	}
	
	
	
	/**
	 * 查看我的药箱（购物车）
	 * @return
	 */
	@RequestMapping("/orderdrugs")
	public String orderdrugs(HttpSession session,Model model) {
		Consumer consumer = (Consumer)session.getAttribute("consumer");
		List<Dorder> selectbyuid = dorderservice.selectbyuid(consumer.getId());
		model.addAttribute("selectbyuid",selectbyuid);

		return "orderdrugs";
	}
	
	
	/**
	 * 根据id修改订单状态ajax
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/updatestatus")
	@ResponseBody
	public Map<String,Object> updatestatus(int id,String status){
		dorderservice.updatestatus(id, status);
		Map<String,Object> map=new HashMap<>();
		
		return map;
		
	}
	
	
	/**
	 * ajax支付后修改用户余额
	 * @param id
	 * @param balance
	 * @return
	 */
	@RequestMapping("/updateubalance")
	@ResponseBody
	public Map<String,Object> updateubalance(String balance,HttpSession session,int price){
		administratorservice.updateabalance(1, price);
		Consumer consumer=(Consumer)session.getAttribute("consumer");
		consumerservice.upubalance(consumer.getId(), balance);
		session.setAttribute("ubalance", balance);
		Map<String,Object> map=new HashMap<>();
		return map;
	}

	// 申请成为企业账号页面展示
	@RequestMapping("/upgroup")
	public String upgroup() {
		return "upgroup";
	}

	// 企业账号注册申请
	@RequestMapping("/signenter")
	public String signenter(Enterprise enterprise, HttpSession session, Model model) {

		Consumer consumer = (Consumer) session.getAttribute("consumer");

		Enterprise login = enterpriseservice.login(consumer.getUname(), consumer.getPwd());
		if (login == null) {
			enterprise.setId(tools.randomdate());
			enterprise.setUname(consumer.getUname());
			enterprise.setPwd(consumer.getPwd());
			enterprise.setEbalance("0");
			enterprise.setUid(consumer.getId());
			enterprise.setEstatus("申请");
			enterpriseservice.sign(enterprise);
			consumerservice.uputype(consumer.getId(), "企业号");
			model.addAttribute("jieguo", "申请已提交，请耐心等待管理员审核企业信息");

		} else {
			model.addAttribute("jieguo", "你的账号已经申请过企业号");

		}

		return "upgroup";
	}

	/**
	 * 年消费清单
	 * @return
	 */
	@RequestMapping("/consumelist")
	public String consumelist(HttpSession session,Model model) {
		Consumer consumer = (Consumer) session.getAttribute("consumer");
		SimpleDateFormat myFmt=new SimpleDateFormat("yyyy");
		long now=System.currentTimeMillis();
		List<Dorder> selectodate = dorderservice.selectodate(consumer.getId(), myFmt.format(now));
		
		model.addAttribute("selectodate",selectodate);
		model.addAttribute("yyyy",myFmt.format(now));
		model.addAttribute("number",selectodate.size());
		int jine=0;
		for(int i=0;i<selectodate.size();i++) {
			int parseInt = Integer.parseInt(selectodate.get(i).getDprice());
			jine=jine+parseInt;
		}
		model.addAttribute("xiaofei",jine);

		return "consumelist";
	}
	
	
	/**
	 * 根据药品名模糊查询
	 * @param search
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectsearch")
	public String selectsearch(String search,Model model) {
		
		List<Drugs> selectsearch = drugsservice.selectsearch(search);
		model.addAttribute("selectsearch",selectsearch);
		return "search";
	}

}
