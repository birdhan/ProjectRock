package com.word.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.word.pojo.Wordtype;
import com.word.service.ContrastService;
import com.word.service.PersonalService;
import com.word.service.WordcolumnService;
import com.word.service.WordfileService;
import com.word.service.WordtypeService;

@Controller
public class TestController {

	@Autowired
	private ContrastService contrastservice;
	@Autowired
	private PersonalService personalservice;
	@Autowired
	private WordcolumnService wordcolumnservice;
	@Autowired
	private WordfileService wordfileservice;
	@Autowired
	private WordtypeService wordtypeservice;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	private String test() throws Exception {
		/*Yulan yulan=new Yulan();*/
		/*Yulan.wordToHtml("D:/dcyq.docx", "D:/", "123.html");*/
		
		return "first";
	}
	
	@RequestMapping("/test1")
	public String test1() {
		List<Wordtype> findAll = wordtypeservice.findAll();
		
		System.out.println(findAll);
		
		return "first";
	}



	
}
