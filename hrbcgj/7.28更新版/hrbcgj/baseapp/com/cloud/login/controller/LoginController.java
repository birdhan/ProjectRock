package com.cloud.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cloud.base.controller.BaseController;

@Controller
public class LoginController extends BaseController {

	@RequestMapping(value = "admin")
	public String admin(HttpServletRequest request) throws Exception {
		return "redirect:/login/toLogin.action";
	}
}
