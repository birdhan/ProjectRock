package com.drugs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@RequestMapping(value="/test", method=RequestMethod.GET)
	private String test() throws Exception {
		return "index";
	}
	
	@RequestMapping(value="/test2", method=RequestMethod.GET)
	@ResponseBody
	private String test2() throws Exception {
		return "haha";
	}
}
