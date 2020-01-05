package com.cloud.login.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.cloud.base.action.BaseAction;
import com.cloud.base.annotation.security.Security;
import com.cloud.base.security.MyUserDetailService;
import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.MD5Util;
import com.cloud.login.model.SuperAdmin;
import com.cloud.menumanager.service.IMenuService;
import com.cloud.systemuser.model.SystemUser;
import com.cloud.systemuser.service.ISystemUserService;

public class LoginAction extends BaseAction {

	@Resource
	private ISystemUserService systemUserService;
	
	@Resource
	private IMenuService menuService;
	
	/**
	 * 登录方法
	 * @return
	 * @throws Exception
	 */
	@Security
	public String login() throws Exception {
		
		String userId = request.getParameter("userId");												//用户登录帐号
		String password = request.getParameter("password");											//得到密码
		
//		UserDetailsService uds = new MyUserDetailService();
//		UserDetails u = uds.loadUserByUsername(userId);
		
		SystemUser su = null;
		List<SystemUser> list = systemUserService.getAllDataByWhere(" and systemUser.userId='"+userId+"' and systemUser.password='"+MD5Util.string2MD5(MD5Util.string2MD5(password))+"'");
		if(list != null) {
			if(list.size() != 0) {
				su = list.get(0);
				request.getSession().setAttribute("user", su);
			}
		}
		
		String menuTree = "";		
		SuperAdmin sa = (SuperAdmin)ApplicationContextHolder.getInstance().getBean("superAdmin");
		Map superMap = sa.getMap();																	//得到超级管理用户的map值
		if(superMap.containsKey(userId)) {															//如果当前用户在超级用户内存里
			String openFlag = (String)superMap.get(userId);
			if(openFlag.equals("yes")) {															//表示已开启超级用户开关，已识别
				menuTree = menuService.getAllMenuTree();											//获取所有菜单
			} else {
				menuTree = menuService.getAllMenuTreeByUserId(userId);								//获取当前人下的所有菜单
			}
		} else {
			menuTree = menuService.getAllMenuTreeByUserId(userId);
		}
		request.setAttribute("menuTree", menuTree);
		return "main";
	}
	
	/**
	 * 退出
	 * @return
	 * @throws Exception
	 */
	@Security
	public String logout() throws Exception {
		request.getSession().removeAttribute("user");
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		return null;
	}
	
	/**
	 * 跳转到登录页面
	 * @return
	 * @throws Exception
	 */
	@Security
	public String toLogin() throws Exception {
		return "login";
	}
}
