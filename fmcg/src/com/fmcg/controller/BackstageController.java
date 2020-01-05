package com.fmcg.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.fmcg.pojo.Adminis;
import com.fmcg.pojo.Ktype;
import com.fmcg.pojo.Kuaixiao;
import com.fmcg.pojo.Shoppingcart;
import com.fmcg.pojo.User;
import com.fmcg.service.AdminisService;
import com.fmcg.service.KtypeService;
import com.fmcg.service.KuaixiaoService;
import com.fmcg.service.ShoppingcartService;
import com.fmcg.service.UserService;


@Controller
public class BackstageController {
	@Autowired
	private KtypeService ktypeService;
	@Autowired
	private KuaixiaoService kuaixiaoService;
	@Autowired
	private ShoppingcartService shoppingcartService;
	@Autowired
	private UserService userService;
	@Autowired
	private AdminisService adminisService;
	/**
	 * 加载主页 查询所有用户信息
	 * 
	 * @return
	 */
	@RequestMapping("/backadmin")
	public String backadmin(Model model) {
		List<User> list = userService.showuser();
		model.addAttribute("list", list);
		return "backstage/backindex";
	}

	/**
	 * 根据id 查询用户信息
	 * 
	 * @return
	 */
	@RequestMapping("/userupdate")
	public String userupdate(String id,Model model) {
		List<User> list = userService.showuserById(id);
		model.addAttribute("list", list);
		return "backstage/userupdate";
	}

	/**
	 * 充值页跳转
	 * 
	 * @return
	 */
	@RequestMapping("/chongzhi")
	public String chongzhi(String id,Model model) {
		List<User> list = userService.showuserById(id);
		model.addAttribute("list", list);
		return "backstage/chongzhi";
	}

	/**
	 * 订单管理
	 * 
	 * @return
	 */
	@RequestMapping("/orderadmin")
	public String orderdamin(Model model) {
		List<Shoppingcart> list = shoppingcartService.showshoppingByAll();
		model.addAttribute("list", list);
		return "backstage/orderadmin";
	}

	/**
	 * 商品管理
	 * 
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	@RequestMapping("/shopadmin")
	public String shopadmin(Model model) {
		List<Kuaixiao> list = kuaixiaoService.findbackkuaixiaoBykcheck();
		model.addAttribute("list", list);
		
		return "backstage/shopadmin";
	}

	/**
	 * 根据id查询商品修改
	 * 
	 * @return
	 */
	@RequestMapping("/shopupdate")
	public String shopupdate(String id,Model model) {
		List<Kuaixiao> shop = kuaixiaoService.findkuaixiaopinById(id);
		model.addAttribute("shop", shop);
		System.out.println(shop);
		return "backstage/shopupdate";
	}

	/**
	 * 上传商品跳转
	 * 
	 * @return
	 */
	@RequestMapping("/upshoping")
	public String upshoping(Model model) {
		List<Ktype> typelist = ktypeService.selectktype();
		model.addAttribute("typelist", typelist);
		return "backstage/upshoping";
	}

	/**
	 * 类型管理
	 * 
	 * @return
	 */
	@RequestMapping("/typeadmin")
	public String typeadmin(Model model) {

		List<Ktype> list = ktypeService.selectktype();
		model.addAttribute("list", list);
		return "backstage/typeadmin";
	}
	/**
	 * 添加类型
	 * @param ktype
	 * @return
	 */
	@RequestMapping("/addktype")
	public String addktype(Ktype ktype) {
		ktype.setId(UUID.randomUUID().toString());
		ktypeService.addtype(ktype);
		return "redirect:/typeadmin";
	}
	/**
	 *删除类型
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletektypeById")
	public String deletektypeById(String id) {
		ktypeService.deletektypeById(id);
		return "redirect:/typeadmin";
	}
	/**
	 * 快消品上传
	 * @param kuaixiao
	 * @param imageFile
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/addkuaixiao")
	public String addkuaixiao(Kuaixiao kuaixiao, MultipartFile imageFile) throws IllegalStateException, IOException {
		String fileorgin = imageFile.getOriginalFilename();
		if (fileorgin != null) {
			String newfileName = UUID.randomUUID().toString() + ".jpg";
			imageFile.transferTo(new File("D:\\img\\" + newfileName));
			kuaixiao.setKpic(newfileName);

		}
		kuaixiao.setId(UUID.randomUUID().toString());
		kuaixiao.setKcheck(0);
		SimpleDateFormat wt = new SimpleDateFormat("yyyy-MM-dd");
		kuaixiao.setKdate(wt.format(new Date()));
		kuaixiaoService.addkuaixiao(kuaixiao);
		
		return "redirect:/upshoping";

	}
	/**
	 * 管理员删除商品
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletekuaixiaoById")
	public String deletekuaixiaoById(String id) {
		kuaixiaoService.deletebackkuaixiaoById(id);
		return "redirect:/shopadmin";
		
	}
	/**
	 * 修改商品信息
	 * @param kuaixiao
	 * @return
	 */
	@RequestMapping("/updatekuaixiaoById")
	public String updatekuaixiaoById(Kuaixiao kuaixiao){
		kuaixiaoService.updateKuaixiaoById(kuaixiao);
		return "redirect:/shopadmin";
		
	}
	/**
	 * 订单详细信息展示
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/showorderdetails")
	public String showorderdetails(String id,Model model) {
		List<Shoppingcart> list = shoppingcartService.findshoppingcartById(id);
		model.addAttribute("list", list);
		return "backstage/orderupdate";
	}
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateuserByback")
	public String updateuserByback(User user) {
		userService.updateuserById(user);
		return "redirect:/backadmin";
		
	}
	/**
	 * 充值
	 * @param user
	 * @return
	 */
	@RequestMapping("/recharge")
	public void recharge(String id,String balanceold,String balancenew,HttpSession session,User user) {
		
		int num=Integer.parseInt(balancenew)+Integer.parseInt(balanceold);
		
		user.setBalance(String.valueOf(num));
		user.setId(id);
		
		userService.updatebalance(user);
		System.out.println(user);
		
	}
	/**
	 * 登录页面跳转
	 * @return
	 */
	@RequestMapping("/adminlogin")
	public String adminlogin() {
		
		return "backstage/adminlogin";
	}
	/**
	 * 登录功能实现
	 * @param Adminis
	 * @param session
	 * @return
	 */
	@RequestMapping("/adminloginachieve")
	public String adminloginachieve(Adminis Adminis,HttpSession session) {
		String url = "error";
		List<Adminis> admin = adminisService.adminloginByback(Adminis);
		if(admin !=null && admin.size() !=0) {
			session.setAttribute("admin", admin.get(0).getName());
		}
		if(admin.size() == 1) {
			return "redirect:/backadmin";
		}else {
			url = "redirect:/adminlogin";
		}
		
		return url;
	}
	
	/**
	 * 发货
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/fahuo")
	public String fahuo(String id) {
		String status="待收货";
		shoppingcartService.updatestatusById(id, status);
		
		return "redirect:/orderadmin";
	}
	
	
}
