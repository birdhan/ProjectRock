package com.cloud.index.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.cityinspector.registeruser.model.RegisterUser;
import com.cityinspector.registeruser.service.IRegisterUserService;
import com.cloud.base.controller.BaseController;
import com.cloud.base.util.MD5Util;
import com.cloud.base.util.SmsSendUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.index.util.IndexUtil;

@Controller
public class WebSiteLoginController extends BaseController {

	@Resource
	private IRegisterUserService registerUserService;
	
	/**
	 * 获取登录验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getLoginImg")
	public void getLoginImg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		BufferedImage bfi = new BufferedImage(80,25,BufferedImage.TYPE_INT_RGB);
		Graphics g = bfi.getGraphics();
		g.fillRect(0, 0, 80, 25);
 
		//验证码字符范围
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		Random r = new Random(); 
		int index;  
		StringBuffer sb = new StringBuffer(); //保存字符串
		for(int i=0; i<4; i++){
			index = r.nextInt(ch.length);
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			Font font = new Font("宋体", 30, 20);
			g.setFont(font);
			g.drawString(ch[index]+"", (i*20)+2, 23);
			sb.append(ch[index]);
		}
		
		// 添加噪点
	    int area = (int) (0.02 * 80 * 25);
	    for(int i=0; i<area; ++i){
	        int x = (int)(Math.random() * 1);
	        int y = (int)(Math.random() * 1);
	        bfi.setRGB(x, y, (int)(Math.random() * 55));
	    }
		
	  //设置验证码中的干扰线
	    for (int i = 0; i < 2; i++) {  
		      //随机获取干扰线的起点和终点
		      int xstart = (int)(Math.random() * 80);
		      int ystart = (int)(Math.random() * 25);
		      int xend = (int)(Math.random() * 80);
		      int yend = (int)(Math.random() * 25);
		      g.setColor(IndexUtil.interLine(1, 255));
		      g.drawLine(xstart, ystart, xend, yend);
		    }
	    HttpSession session = request.getSession();  //保存到session
		session.setAttribute("verificationCode", sb.toString());
		ImageIO.write(bfi, "JPG", response.getOutputStream());  //写到输出流
	}
	/**
	 * 图片验证码验证方法
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "validateLoginImg")
	public void validateLoginImg(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String flag = "1";			//验证状态， 默认失败
		JSONObject resultJson = new JSONObject();
		String checkcode = StringUtil.null2String(request.getParameter("checkcode"));
		String verificationCode = StringUtil.null2String(request.getSession().getAttribute("verificationCode"));
		checkcode = checkcode.toLowerCase();
		verificationCode = verificationCode.toLowerCase();
		if(verificationCode.equals(checkcode)){
			flag = "0";
		}
		resultJson.put("status", flag);
		printJSON(response, resultJson.toString());
	}
	
	/**
	 * 获取短信验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "getPhoneValCode")
	public void getPhoneValCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String flag = "1";				//默认发送失败
		JSONObject resultJson = new JSONObject();
		String phone = StringUtil.null2String(request.getParameter("phone"));
		if(!"".equals(phone)){	//
			 String code = IndexUtil.createCode();
			 SendSmsResponse ssr = SmsSendUtil.sendSMSRandomCode(phone, code);
			 if("OK".equals(ssr.getCode())){
				 flag = "0";
				 HttpSession session = request.getSession();  //保存到session
				 session.setAttribute("phoneCode", code);
			 }else{
				 resultJson.put("err", ssr.getCode());
			 }
		}
		resultJson.put("status", flag);
		
		printJSON(response, resultJson.toString());
	}
	
	/**
	 * 验证短信验证码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "validatePhoneCode")
	public void validatePhoneCode(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String flag = "1";				//默认失败
		JSONObject resultJson = new JSONObject();
		String phoneCode = StringUtil.null2String(request.getParameter("phoneCode"));
		String valPhoneCode = StringUtil.null2String(request.getSession().getAttribute("phoneCode"));
		if(valPhoneCode.equals(phoneCode)){
			flag = "0";		//验证成功
		}
		resultJson.put("status", flag);
		printJSON(response, resultJson.toString());
	}
	
	/**
	 * 注册
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "register")
	public void register(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject resultJson = new JSONObject();
		String flag = "0";//默认成功
		RegisterUser ru = new RegisterUser();
		String account = StringUtil.null2String(request.getParameter("account"));
		String pwd = StringUtil.null2String(request.getParameter("pwd"));
		String username = StringUtil.null2String(request.getParameter("username"));
		String idnum = StringUtil.null2String(request.getParameter("idnum"));
		String mobile = StringUtil.null2String(request.getParameter("mobile"));
		pwd = MD5Util.string2MD5(pwd);
		ru.setAccount(account);
		ru.setPwd(pwd);
		ru.setUsername(username);
		ru.setIdnum(idnum);
		ru.setMobile(mobile);
		ru.setPoints(0);
		registerUserService.saveRegisterUser(ru);
		resultJson.put("status", flag);
		
		printJSON(response, resultJson.toString());
	}
	
	/**
	 * 验证账号是否注册过
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "valUserId")
	public void valUserId(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject resultJson = new JSONObject();
		String flag = "1";//默认失败
		String userId = StringUtil.null2String(request.getParameter("userId"));
		String whereStr = " and account = '"+userId+"'";
		List list = registerUserService.getAllDataByWhere(whereStr);
		if(list == null || list.size()==0){
			flag = "0";
		}
		resultJson.put("status", flag);
		
		printJSON(response, resultJson.toString());
	}
	
	/**
	 * 登录校验
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "webSiteLoginVal")
	public void webSiteLoginVal(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject resultJson = new JSONObject();
		String flag = "1";//默认失败
		String userId = StringUtil.null2String(request.getParameter("userId"));
		String pwd = StringUtil.null2String(request.getParameter("pwd"));
		String whereStr = " and account = '"+userId+"' ";
		List<RegisterUser> list = registerUserService.getAllDataByWhere(whereStr);
		if(list != null && list.size()>0){
			RegisterUser ru = list.get(0);
			String oldpwd = ru.getPwd();
			pwd = MD5Util.string2MD5(pwd);
			if(oldpwd.equals(pwd)){
				flag = "0";		//校验成功
				//设置session
				request.getSession().setAttribute("webSiteLoginUser", ru);
				resultJson.put("registerUser", ru);
			}
		}
		resultJson.put("status", flag);
		
		printJSON(response, resultJson.toString());
		
		int a=Integer.parseInt(flag);
		if(a==0){
		registerUserService.updatePoints(userId);
		}
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "editPwd")
	public void editPwd(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject resultJson = new JSONObject();
		String flag = "1";//默认失败
		String usercuid = StringUtil.null2String(request.getParameter("usercuid"));
		String oldpwd = StringUtil.null2String(request.getParameter("oldpwd"));
		String newpwd = StringUtil.null2String(request.getParameter("newpwd"));
		
		String whereStr = " and id = '"+usercuid+"' and pwd='"+MD5Util.string2MD5(oldpwd)+"' ";
		List<RegisterUser> list = registerUserService.getAllDataByWhere(whereStr);
		if(list != null && list.size()>0){
			RegisterUser ru = list.get(0);
			ru.setPwd(MD5Util.string2MD5(newpwd));
			registerUserService.saveRegisterUser(ru);
			flag = "0";		//修改成功
		}
		
		resultJson.put("status", flag);
		printJSON(response, resultJson.toString());
	}
	
	/**
	 * 退出登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "webSiteLoginOut")
	public void webSiteLoginOut(HttpServletRequest request,HttpServletResponse response) throws Exception {
		JSONObject resultJson = new JSONObject();
		String flag = "0";//登出状态
		request.getSession().removeAttribute("webSiteLoginUser");
		resultJson.put("status", flag);
		printJSON(response, resultJson.toString());
	}



}
