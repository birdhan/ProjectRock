package com.fmcg.controller;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmcg.pojo.Ktype;
import com.fmcg.pojo.Kuaixiao;
import com.fmcg.pojo.Shoppingcart;
import com.fmcg.pojo.User;
import com.fmcg.service.KtypeService;
import com.fmcg.service.KuaixiaoService;
import com.fmcg.service.ShoppingcartService;
import com.fmcg.service.UserService;

@Controller
public class ReceptionController {

	@Resource
	private KuaixiaoService kuaixiaoservice;
	@Resource
	private KtypeService typeservice;
	@Resource
	private ShoppingcartService shoppingc;
	@Resource
	private UserService useservice;
	
	
	
	/**
	 * 首页第一次加载
	 * @param kuaixiao
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Kuaixiao kuaixiao,Model model) {
		
		List<Kuaixiao> findAll = kuaixiaoservice.findAll(0);
		model.addAttribute("findlist",findAll);
		List<Ktype> selectktype = typeservice.selectktype();
		model.addAttribute("typelist",selectktype);
		return "indexall";
	}
	
	/**
	 * 首页根据类型初次加载
	 * @param model
	 * @param types
	 * @return
	 */
	@RequestMapping("/indextype")
	public String idnextype(Model model,String types) {
		List<Ktype> selectktype = typeservice.selectktype();
		model.addAttribute("typelist",selectktype);
		List<Kuaixiao> findBytype = kuaixiaoservice.findBytype(0, types);
		model.addAttribute("findlist", findBytype);
		
		return "index"; 
	}
	
	
/**
 * 详情页
 * @param id
 * @param model
 * @return
 */
	@RequestMapping("/details")
	public String details(String id,Model model) {
		kuaixiaoservice.updatekuai(id); 
		List<Ktype> selectktype = typeservice.selectktype();
		model.addAttribute("typelist",selectktype);
		Kuaixiao findById = kuaixiaoservice.findById(id);
		model.addAttribute("detailss", findById);
		return "details";
	}
/**
 * 购物车页
 * @param model
 * @param session
 * @return
 */
	@RequestMapping("/shopping")
	public String user(Model model,HttpSession session) {
			String type="购物车";
		User user = (User)session.getAttribute("user");
		List<Shoppingcart> findAllByType = shoppingc.findAllByType(type,user.getId());
		model.addAttribute("shopping",findAllByType);

		return "shopping";
	}

	/**
	 * 待发货页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/unshipped")
	public String unshipped(Model model,HttpSession session) {
		String type="待发货";
		User user = (User)session.getAttribute("user");
		List<Shoppingcart> findAllByType = shoppingc.findAllByType(type,user.getId());
		model.addAttribute("shopping",findAllByType);
		return "unshipped";
	}

	
	/**
	 * 待收货页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/unreceived")
	public String unreceived(Model model,HttpSession session) {
		String type="待收货";
		User user = (User)session.getAttribute("user");
		List<Shoppingcart> findAllByType = shoppingc.findAllByType(type,user.getId());
		model.addAttribute("shopping",findAllByType);
		return "unreceived";
	}

	
	/**
	 * 已完成页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/finishorder")
	public String finishorder(Model model,HttpSession session) {
		String type="已完成";
		User user = (User)session.getAttribute("user");
		List<Shoppingcart> findAllByType = shoppingc.findAllByType(type,user.getId());
		model.addAttribute("shopping",findAllByType);
		return "finishorder";
	}
	
	
	/**
	 * 跳转注册页面
	 * @return
	 */
	@RequestMapping("/register")
	public String register() {
		
		return "register";
	}
	
	
	/**
	 * 注册成功
	 * 
	 */
	@RequestMapping("/adduser")
	public String adduser(User user) {
		user.setId(UUID.randomUUID().toString());
		user.setBalance("1200");
		user.setPasspay(user.getPwd());
		useservice.registeruser(user);
		return "redirect:/logintiao";
	}
	
	/**
	 * 跳转登录jsp
	 * @return
	 */
	@RequestMapping("/logintiao")
	public String logintiao() {
		
		return "login";
	}
	
	
	/**
	 * 登录验证
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String name,String pwd,HttpSession session) {
		String resource="redirect:/logintiao";
		User findyanzheng = useservice.findyanzheng(name, pwd);
		
		if(findyanzheng!=null) {
			session.setAttribute("user", findyanzheng);
			session.setAttribute("monery", findyanzheng.getBalance());
			resource="redirect:/index";
			
		}
		return resource;
	}
	
	
	
	/**
	 * 修改购物车商品数量
	 * @param id
	 * @param number
	 * @param spay
	 * @return
	 */
	@RequestMapping("/byIdupnumber")
	public String byIdupnumber(String id,String number,String spay) {
		int pays=Integer.parseInt(spay)*Integer.parseInt(number);
		String spays=String.valueOf(pays);
		shoppingc.updatesnumberById(id, number, spays);
		return "redirect:/shopping";
	}
	
	/**
	 * 订单支付
	 * @param id
	 * @param session
	 * @param payyuan
	 * @return
	 */
	@RequestMapping("/payorder")
	public String payorder(String id,HttpSession session,String payyuan) {
		String status="待发货";
		User user=(User)session.getAttribute("user");
		
		String id2 = user.getId();
		
		String balance = String.valueOf(Integer.parseInt(user.getBalance())-Integer.parseInt(payyuan));
		
		useservice.updateByIdbalance(id2, balance);
		List<User> showuserById = useservice.showuserById(id2);
		
		session.setAttribute("user", showuserById.get(0));
		session.setAttribute("monery",showuserById.get(0).getBalance());
		
		shoppingc.updatestatusById(id, status);
		return "redirect:/shopping";
	}
	
	
	/**
	 * 确认收货
	 * @return
	 */
	@RequestMapping("/received")
	public String received(String id) {
		String status="已完成";
		shoppingc.updatestatusById(id, status);
		return "redirect:/unreceived";
	} 

	/**
	 * 搜索结果
	 * @param guanjianzi
	 * @param model
	 * @return
	 */
	@RequestMapping("/sousuo")
	public String suosuo(String guanjianzi,Model model) {
		
		List<Kuaixiao> findBysousuo = kuaixiaoservice.findBysousuo(guanjianzi);
		model.addAttribute("findlist",findBysousuo);
		List<Ktype> selectktype = typeservice.selectktype();
		model.addAttribute("typelist",selectktype);
		return "sousuo";
	}
}
