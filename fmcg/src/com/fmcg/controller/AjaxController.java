package com.fmcg.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fmcg.pojo.Kuaixiao;
import com.fmcg.pojo.Shoppingcart;
import com.fmcg.pojo.User;
import com.fmcg.service.KuaixiaoService;
import com.fmcg.service.ShoppingcartService;

@Controller
public class AjaxController {
	
	
	
	@Resource private KuaixiaoService kuaixiaoservice;
	@Resource private ShoppingcartService shopingcart;

	/**
	 * ajax加载所有商品分页
	 * @param number
	 * @return
	 */
	@RequestMapping("/ajaxindexall")
	@ResponseBody
	public Map<String,Object> ajaxindexall(String number,HttpServletResponse response){
		
		int parseInt = Integer.parseInt(number);
		
		List<Kuaixiao> findAll = kuaixiaoservice.findAll(parseInt);
		int numb=parseInt+8;
		
		Map<String,Object> map=new HashMap<>();
		map.put("findall", findAll);
		map.put("number", numb);
		return map;
	}
	
	
	/**
	 * ajax分类加载商品信息
	 * @param number
	 * @return
	 */
	@RequestMapping("/ajaxindex")
	@ResponseBody
	public Map<String,Object> ajaxindex(String number,String typec,HttpServletResponse response){
		
		int parseInt = Integer.parseInt(number);
		
		List<Kuaixiao> findAll = kuaixiaoservice.findBytype(parseInt, typec);
		int numb=parseInt+8;
		
		Map<String,Object> map=new HashMap<>();
		map.put("findall", findAll);
		map.put("number", numb);
		return map;
	}
	
	
	/**
	 * ajax加入购物车
	 * @return
	 */
	@RequestMapping("/addshopping")
	@ResponseBody
	public Map<String,Object> addshopping(String id,String number,Shoppingcart shopcart,HttpSession session){
		Kuaixiao findById = kuaixiaoservice.findById(id);
		int parseInt = Integer.parseInt(number);
		shopcart.setId(UUID.randomUUID().toString());
		shopcart.setKid(id);
		shopcart.setKname(findById.getKname());
		shopcart.setKpicture(findById.getKpic());
		shopcart.setSnumber(parseInt);
		shopcart.setKpic(findById.getKprice());
		shopcart.setSpay(String.valueOf(Integer.parseInt(findById.getKprice())*parseInt));
		shopcart.setStatus("购物车");
		shopcart.setKtype(findById.getKtype());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		shopcart.setSdate(df.format(new Date()));
		
		User user = (User) session.getAttribute("user");
		shopcart.setUname(user.getName());
		shopcart.setUid(user.getId());
		shopcart.setUtel(user.getTel());
		shopcart.setUaddress(user.getAddress());
		shopingcart.addshopping(shopcart);
		Map<String,Object> map=new HashMap<>();
		return map;
	}
}
