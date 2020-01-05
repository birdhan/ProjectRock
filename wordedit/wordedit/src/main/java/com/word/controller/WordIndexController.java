package com.word.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.multipart.MultipartFile;

import com.word.html.Client;
import com.word.pojo.Contrastlibrary;
import com.word.pojo.Personal;
import com.word.pojo.Wordcolumn;
import com.word.pojo.Wordfile;
import com.word.pojo.Wordtype;
import com.word.service.ContrastService;
import com.word.service.PersonalService;
import com.word.service.WordcolumnService;
import com.word.service.WordfileService;
import com.word.service.WordtypeService;
import com.word.tools.Contrast;
import com.word.tools.Tools;
import com.word.tools.Wordpicture;
import com.zhuozhengsoft.pageoffice.FileSaver;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

@Controller
@RequestMapping("/word")
public class WordIndexController {
	Tools tools = new Tools();
	Wordpicture wordimg = new Wordpicture();
	@Autowired
	private PersonalService personalService;
	@Autowired
	private WordcolumnService wordcolumnservice;
	@Autowired
	private WordtypeService wordtypeservice;
	@Autowired
	private WordfileService wordfileservice;
	@Autowired
	private ContrastService contrastservice;

	/**
	 * 注册
	 * 
	 * @param respnse
	 * @param personal
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/perregister")
	public String perregister(HttpServletResponse respnse, Personal personal, HttpSession session, Model model) {
		String stringid = tools.stringid();
		personal.setId(stringid);
		personal.setPstatus("活跃");
		personal.setUtype("0");
		personalService.register(personal);
		session.setAttribute("user", personal);
		return "redirect:/word/personal";

	}

	/**
	 * 登录
	 * 
	 * @param name
	 * @param pwd
	 * @param session
	 * @return
	 */
	@RequestMapping("/perlogin")
	public String perlogin(String name, String pwd, HttpSession session) {
		String result = "";
		Personal login = personalService.login(name, pwd);
		if (login == null) {
			result = "login";
		} else {
			result = "redirect:/word/personal";
			session.setAttribute("user", login);
		}
		return result;
	}
	
	@RequestMapping("/personallogin")
	public String personallogin() {
		return "login";
	}

	/**
	 * 验证用户名
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping("/yanzhenglogin")
	@ResponseBody
	public Map<String, Object> yanzhenglogin(String name) {
		Map<String, Object> map = new HashMap<>();
		Personal loginyanzheng = personalService.loginyanzheng(name);
		if (loginyanzheng == null) {
			map.put("result", "注册成功");
		} else {
			map.put("result", "用户已存在");
		}
		return map;
	}

	/**
	 * 主页加载
	 * @param model
	 * @return
	 */
	@RequestMapping("/first")
	public String first(Model model) {
		List<Wordfile> findalllimit = wordfileservice.findalllimit(0);
		int findwordnumber = wordfileservice.findwordnumber();
		model.addAttribute("findalllimit",findalllimit );
		model.addAttribute("findwordnumber",findwordnumber);
		String pagenumber="";
		if(findwordnumber%10==0) {
			pagenumber=String.valueOf(findwordnumber/10);
		}else {
			pagenumber=String.valueOf(findwordnumber/10+1);
		}
		model.addAttribute("pagenumber",pagenumber);
		model.addAttribute("nowpage","1");
		List<Wordtype> findAll = wordtypeservice.findAll();
		model.addAttribute("findAll", findAll);
		return "first";
	}
	
	/**
	 * 分页
	 * @return
	 */
	@RequestMapping("firstpaging")
	public String firstpaging(String page,Model model) {
		int findwordnumber = wordfileservice.findwordnumber();
		model.addAttribute("findwordnumber",findwordnumber);
		String pagenumber="";
		if(findwordnumber%10==0) {
			pagenumber=String.valueOf(findwordnumber/10);
		}else {
			pagenumber=String.valueOf(findwordnumber/10+1);
		}
		int pages=(Integer.parseInt(page)-1)*10;
		List<Wordfile> findalllimit = wordfileservice.findalllimit(pages);
		model.addAttribute("findalllimit", findalllimit);
		model.addAttribute("pagenumber",pagenumber);
		model.addAttribute("nowpage",page);
		List<Wordtype> findAll = wordtypeservice.findAll();
		model.addAttribute("findAll", findAll);
		return "first";
	}
	
	/**
	 * 分类
	 * @param page
	 * @param model
	 * @param wordtype
	 * @return
	 */
	@RequestMapping("findtype")
	public String findtype(String page,Model model,String wordtype) {
		int findwordnumber = wordfileservice.findwordnumberbytype(wordtype);
		model.addAttribute("findwordnumber",findwordnumber);
		String pagenumber="";
		if(findwordnumber%10==0) {
			pagenumber=String.valueOf(findwordnumber/10);
		}else {
			pagenumber=String.valueOf(findwordnumber/10+1);
		}
		int pages=(Integer.parseInt(page)-1)*10;
		List<Wordfile> findalllimit = wordfileservice.findalltypelimit(wordtype, pages);
		model.addAttribute("findalllimit", findalllimit);
		model.addAttribute("pagenumber",pagenumber);
		model.addAttribute("nowpage",page);
		List<Wordtype> findAll = wordtypeservice.findAll();
		model.addAttribute("findAll", findAll);
		return "findtype";
	}
	
	
	/**
	 * 详情页
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/details")
	public String details(String id,Model model) {
		Wordfile findbyid = wordfileservice.findbyid(id);
		model.addAttribute("findbyid",findbyid);
		return "png";
	}

	/**
	 * 进入查重系统
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("/contrast")
	public String contrast(HttpSession session, Model model) {
		Personal personal = (Personal) session.getAttribute("user");
		List<Contrastlibrary> findbyuid = contrastservice.findbyuid(personal.getId());
		model.addAttribute("findbyuid", findbyuid);

		return "contrast";
	}

	/**
	 * 个人中心
	 * 
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/personal")
	public String personal(Model model, HttpSession session) {
		List<Wordtype> findAll = wordtypeservice.findAll();
		model.addAttribute("findAll", findAll);
		Personal personal = (Personal) session.getAttribute("user");
		List<Wordtype> findbyuid = wordcolumnservice.findbyuid(personal.getId());
		model.addAttribute("findbyuid", findbyuid);
		return "personal";
	}

	/**
	 * 个人中心二级加载
	 * 
	 * @param fid
	 * @return
	 */
	@RequestMapping("/findbyfaid")
	@ResponseBody
	public Map<String, Object> findbyfaid(String fid) {
		List<Wordtype> findbyfaid = wordcolumnservice.findbyfaid(fid);
		Map<String, Object> map = new HashMap<>();
		map.put("findbyfaid", findbyfaid);
		return map;
	}

	/**
	 * 个人中心三级加载
	 * 
	 * @return
	 */
	@RequestMapping("/findbyword")
	@ResponseBody
	public Map<String, Object> findbyword(String wfid) {

		List<Wordfile> findbyfacoid = wordfileservice.findbyfacoid(wfid);
		Map<String, Object> map = new HashMap<>();
		map.put("findbyfacoid", findbyfacoid);

		return map;

	}

	/**
	 * 文件上传处理
	 * 
	 * @param word
	 * @param wordfile
	 * @return
	 */
	@RequestMapping("/upfile")
	@ResponseBody
	public Map<String, Object> upfile(MultipartFile word, Wordfile wordfile, HttpSession session) {
		String stringid = tools.stringid();
		String wordname = stringid + ".docx";
		String fileorgin = word.getOriginalFilename();
		try {
			word.transferTo(new File("D:\\wordfile\\word\\" + wordname));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String pngname = stringid + ".png";
		wordimg.wordpicture(wordname, pngname);
		String filename1 = "";
		wordfile.setId(stringid);
		if (fileorgin.contains(".docx")) {
			filename1 = fileorgin.replace(".docx", "...");
		} else {
			filename1 = fileorgin.replace(".doc", "...");
		}

		wordfile.setWname(filename1);
		wordfile.setPicurl("img/" + pngname);
		wordfile.setReadnumber(1);
		wordfile.setPngurl("png/" + pngname);
		Date date = new Date();
		SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
		wordfile.setWorddate(formatdate.format(date));
		Personal personal = (Personal) session.getAttribute("user");
		wordfile.setUid(personal.getId());
		wordfile.setAnnotation("添加批注");
		String htmlname = stringid + ".html";
		wordfile.setHtmlurl("html/" + htmlname);
		wordfile.setWordurl("D:\\wordfile\\word\\" + wordname);
		wordfile.setServeraddress("http://localhost:8081/");
		wordfileservice.addwordfile(wordfile);
		Map<String, Object> map = new HashMap<>();
		return map;
	}

	@RequestMapping("/png")
	public String png() {

		return "png";
	}

	/**
	 * 栏目管理
	 * 
	 * @return
	 */
	@RequestMapping("/column")
	public String column(HttpSession session, Model model) {
		Personal personal = (Personal) session.getAttribute("user");
		List<Wordcolumn> findfirst = wordcolumnservice.findbyall("0", personal.getId(), "first");
		List<Wordcolumn> findsecond = wordcolumnservice.findbyall("0", personal.getId(), "second");
		model.addAttribute("findfirst", findfirst);
		model.addAttribute("findsecond", findsecond);
		return "column";
	}

	/**
	 * 添加栏目
	 * 
	 * @return
	 */
	@RequestMapping("/addcolumn")
	@ResponseBody
	public Map<String, Object> addcolumn(String name, String grade, Wordcolumn column, HttpSession session) {
		String stringid = tools.stringid();
		column.setId(stringid);
		if (grade.equals("first")) {
			column.setFatherid("0");
			column.setFathername("0");
			column.setCgrade(grade);
		} else {
			column.setFatherid(grade);
			String findbyid = wordcolumnservice.findbyid(grade);
			column.setFathername(findbyid);
			column.setCgrade("second");
		}
		column.setColumntype("0");

		column.setCname(name);
		Personal personal = (Personal) session.getAttribute("user");
		column.setUid(personal.getId());
		wordcolumnservice.addcolumn(column);
		Map<String, Object> map = new HashMap<>();
		map.put("result", "添加成功");
		return map;

	}

	/**
	 * 删除栏目
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletecolumn")
	public String deletecolumn(String id) {
		wordcolumnservice.deletecolumn(id);
		return "redirect:/word/column";
	}

	/**
	 * 修改column
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/updatecolumn")
	@ResponseBody
	public Map<String, Object> updatecolumn(String id, String name) {
		wordcolumnservice.updatebyid(id, name);
		wordcolumnservice.updatebyfathid(id, name);
		Map<String, Object> map = new HashMap<>();
		map.put("result", "修改成功");
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
		/*poCtrl.addCustomToolButton("保存", "save", 1);*/
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

		Wordpicture wordimg = new Wordpicture();
		wordimg.wordpicture(fileName, pngname);

	}

	/**
	 * 删除word
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteword")
	@ResponseBody
	public Map<String, Object> deleteword(String id) {
		wordfileservice.deleteword(id);
		wordimg.deletewordfile(id);
		Map<String, Object> map = new HashMap<>();
		map.put("result", "成功删除");
		return map;

	}

	/**
	 * 修改批注
	 * 
	 * @param id
	 * @param annotation
	 * @return
	 */
	@RequestMapping("/updateannotation")
	@ResponseBody
	public Map<String, Object> updateannotation(String id, String annotation) {
		wordfileservice.upannotationbyid(id, annotation);
		Map<String, Object> map = new HashMap<>();
		map.put("result", "修改成功");
		return map;

	}

	/**
	 * 修改文件名字
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	@RequestMapping("/updateawname")
	@ResponseBody
	public Map<String, Object> updateawname(String id, String name) {
		wordfileservice.upnamebyid(id, name);
		Map<String, Object> map = new HashMap<>();
		map.put("result", "修改成功");
		return map;

	}

	/**
	 * 添加查重系统
	 * 
	 * @param wid
	 * @param wname
	 * @param contrast
	 * @param session
	 * @return
	 */
	@RequestMapping("/addcontrast")
	@ResponseBody
	public Map<String, Object> addcontrast(String wid, String wname, Contrastlibrary contrast, HttpSession session) {
		String ids = tools.stringid();
		contrast.setId(ids);
		contrast.setName(wname);
		Personal personal = (Personal) session.getAttribute("user");
		contrast.setUid(personal.getId());
		contrast.setWfid(wid);
		contrast.setWordurl("D:\\wordfile\\word\\" + wid + ".docx");
		contrastservice.addcontrast(contrast);
		Map<String, Object> map = new HashMap<>();
		map.put("result", "成功");
		return map;

	}

	/**
	 * start查重
	 * @param word1
	 * @param word2
	 * @return
	 */
	@RequestMapping("/startcontrast")
	@ResponseBody
	public Map<String, Object> startcontrast(String word1, String word2) {
		String wordurl1="D:\\wordfile\\word\\"+word1+".docx";
		String wordurl2="D:\\wordfile\\word\\"+word2+".docx";
		Contrast contrast = new Contrast();
		String startcontrast = contrast.startcontrast(wordurl1, wordurl2);
		Map<String, Object> map = new HashMap<>();
		map.put("result", startcontrast);
		return map;
	}
	
	/**
	 * 清除查重库
	 * @param uid
	 * @return
	 */
	@RequestMapping("/clearcontrast")
	public String clearcontrast(HttpSession session) {
		Personal personal=(Personal)session.getAttribute("user");
		contrastservice.deletecontrast(personal.getId());
		return "redirect:/word/contrast";
	}
	
	/**
	 * 注销
	 * @param session
	 * @return
	 */
	@RequestMapping("/zhuxiaodenglu")
		public String zhuxiaodenglu(HttpSession session) {
			session.removeAttribute("user");
			return "login";
		}
	}


