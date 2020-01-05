package com.word.controller;

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

import com.word.pojo.Personal;
import com.word.pojo.Wordcolumn;
import com.word.pojo.Wordfile;
import com.word.pojo.Wordtype;
import com.word.service.ContrastService;
import com.word.service.PersonalService;
import com.word.service.WordcolumnService;
import com.word.service.WordfileService;
import com.word.service.WordtypeService;
import com.word.tools.Tools;
import com.word.tools.Wordpicture;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

@Controller
@RequestMapping("/back")
public class WordBackController {

	Tools tools=new Tools();
	@Autowired
	private WordcolumnService wordcolumnservice;
	@Autowired
	private WordtypeService wordtypeservice;
	@Autowired
	private PersonalService personalService;
	@Autowired
	private WordfileService wordfileservice;
	
	
	/**
	 * login
	 * @param name
	 * @param pwd
	 * @return
	 */
	@RequestMapping("/adminlogin")
	public String adminlogin(String name,String pwd,Model model,HttpSession session) {
		String adminlogin;
		Personal adminlogin2 = personalService.adminlogin(name, pwd);
		if(adminlogin2!=null) {
			session.setAttribute("adminuser", adminlogin2);
			adminlogin="redirect:/back/persionmanage?page=1";
		}else {
			adminlogin="adminlogin";
			model.addAttribute("message","账号或密码不正确");
		}
		return adminlogin;
	}
	
	@RequestMapping("/loginlanjie")
	public String loginlanjie(Model model) {
		model.addAttribute("message","请先完成登录");
		return "adminlogin";
	}
	
	/**
	 * 用户管理
	 * @return
	 */
	@RequestMapping("/persionmanage")
	public String persionmanage(Model model,String page) {
		int pages=(Integer.parseInt(page)-1)*10;
		List<Personal> findAll = personalService.findAll(pages);
		model.addAttribute("findAll", findAll);
		int findpersonalnumber = personalService.findpersonalnumber();
		model.addAttribute("findpersonalnumber",findpersonalnumber);
		int pagenumber=1;
		if(findpersonalnumber%10==0) {
			pagenumber=findpersonalnumber/10;
			model.addAttribute("pagenumber",pagenumber);
		}else {
			pagenumber=findpersonalnumber/10+1;
			model.addAttribute("pagenumber",pagenumber);
		}
		model.addAttribute("nownumber",page);
		return "adminis/persionmanage";
	}
	
	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping("/modifystatus")
	public String modifystatus(String id,String status) {
		personalService.uppersonalstatus(id, status);
		return "redirect:/back/persionmanage?page=1";
	}
	
	
	/**
	 * 文章管理
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping("/articlemanage")
	public String articlemanage(String page,Model model) {
		int pages=(Integer.parseInt(page)-1)*10;
		List<Wordfile> findalllimit = wordfileservice.findalllimit(pages);
		model.addAttribute("findalllimit",findalllimit);
		int findwordnumber = wordfileservice.findwordnumber();
		model.addAttribute("findwordnumber",findwordnumber);
		int pagenumber;
		if(findwordnumber%10==0) {
			pagenumber=findwordnumber/10;
			model.addAttribute("pagenumber",pagenumber);
		}else {
			pagenumber=findwordnumber/10+1;
			model.addAttribute("pagenumber",pagenumber);
		}
		model.addAttribute("nownumber", page);
		return "adminis/articlemanage";
	}
	
	/**
	 * 栏目管理
	 * @return
	 */
	@RequestMapping("/columnmanage")
	public String columnmanage(Model model) {
		List<Wordcolumn> findbyfirst = wordcolumnservice.findbyall("1", "1", "first");
		List<Wordcolumn> findbysecond = wordcolumnservice.findbyall("1", "1", "second");
		model.addAttribute("findbyfirst",findbyfirst);
		model.addAttribute("findbysecond",findbysecond);
		return "adminis/columnmanage";
	}
	
	/**
	 * 创建全局栏目
	 * @param name
	 * @param grade
	 * @param column
	 * @param session
	 * @return
	 */
	@RequestMapping("/addcolumn")
	@ResponseBody
	public Map<String,Object> addcolumn(String name,String grade,Wordcolumn column){
		String stringid = tools.stringid();
		column.setId(stringid);
		if(grade.equals("first")) {
			column.setFatherid("0");
			column.setFathername("0");
			column.setCgrade(grade);
		}else {
			column.setFatherid(grade);
			String findbyid = wordcolumnservice.findbyid(grade);
			column.setFathername(findbyid);
			column.setCgrade("second");
		}
		column.setColumntype("1");
		column.setCname(name);
		column.setUid("1");
		wordcolumnservice.addcolumn(column);
		Map<String,Object> map=new HashMap<>();
		map.put("result", "添加成功");
		return map;
		
	}
	
	/**
	 * 删除全局栏目
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletecol")
	public String deletecol(String id) {
		wordcolumnservice.deletecolumn(id);
		return "redirect:/back/columnmanage";
	}
	
	/**
	 * xiugai
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/updatecolumn")
	@ResponseBody
	public Map<String,Object> updatecolumn(String id,String name){
		wordcolumnservice.updatebyid(id, name);
		wordcolumnservice.updatebyfathid(id, name);
		Map<String,Object> map=new HashMap<>();
		map.put("result", "修改成功");
		return map;
		
	}
	
	/**
	 * 类型管理
	 * @param model
	 * @return
	 */
	@RequestMapping("/typemanage")
	public String typemanage(Model model) {
		List<Wordtype> findAll = wordtypeservice.findAll();
		model.addAttribute("findAll", findAll);
		return "adminis/typemanage";
	}
	
	/**
	 * addtype
	 * @param name
	 * @param wordtype
	 * @return
	 */
	@RequestMapping("/addtype")
	public String addtype(String name,Wordtype wordtype) {
		String stringid = tools.stringid();
		wordtype.setId(stringid);
		wordtype.setName(name);
		wordtype.setWtype("0");
		wordtypeservice.addtype(wordtype);
		return "redirect:/back/typemanage";
	}
	
	/**
	 * delete    type
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletetype")
	public String deletetype(String id) {
		wordtypeservice.deletetype(id);
		return "redirect:/back/typemanage";
	}
	
	/**
	 * 修改type
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/uptype")
	@ResponseBody
	public Map<String,Object> uptype(String id,String name){
		wordtypeservice.uptype(id, name);
		Map<String,Object> map=new HashMap<>();
		map.put("result", "修改成功！");
		return map;
				
	}
	
	/**
	 * word编辑
	 * 
	 * @param request
	 * @param map
	 * @param docxname
	 * @return
	 */
	@RequestMapping("/word")
	public String openWord(HttpServletRequest request, Map<String, Object> map, String docxname) {
		String path = request.getSession().getServletContext().getRealPath("");
		PageOfficeCtrl poCtrl = new PageOfficeCtrl(request);
		poCtrl.setServerPage(request.getContextPath() + "/poserver.zz");// 设置服务页面
		/*poCtrl.addCustomToolButton("保存", "SaveDocuments()", 1);*/
		poCtrl.setSaveFilePage("save");// 设置处理文件保存的请求方法

		// 打开word
		poCtrl.webOpen("D:\\wordfile\\word\\" + docxname + ".docx", OpenModeType.docAdmin, "韩");
		map.put("pageoffice", poCtrl.getHtmlCode("PageOfficeCtrl1"));
		return "word";
	}

	/**
	 * word保存
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/save")
	public void save(HttpServletRequest request, HttpServletResponse response) {
		/* String path = request.getSession().getServletContext().getRealPath(""); */
		FileSaver fs = new FileSaver(request, response);
		String fileName = fs.getFileName();

		fs.saveToFile("D:\\wordfile\\word\\" + fileName);
		fs.close();

		String pngname = fileName.replace(".docx", ".png");
		System.out.println(pngname);

		Wordpicture wordimg = new Wordpicture();
		wordimg.wordpicture(fileName, pngname);

	}

	
}
