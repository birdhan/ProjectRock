package com.drugs.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.drugs.pojo.Dorder;
import com.drugs.pojo.Drugs;
import com.drugs.pojo.Enterprise;
import com.drugs.service.DorderService;
import com.drugs.service.DrugsService;
import com.drugs.service.EnterpriseService;
import com.drugs.tools.Tools;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private EnterpriseService enterpriseservice;
	@Autowired
	private DrugsService drugsservice;
	@Autowired
	private DorderService dorderservice;
	Tools tools=new Tools();
	// login
	@RequestMapping("/loginenter")
	public String loginenter() {
		
		return "enterpriselogin";
	}
	
	// 登录验证
	@RequestMapping("/loginyanzheng")
	public String loginyanzheng(String uname,String pwd,Model model,HttpSession session) {
		String source="";
		Enterprise login2 = enterpriseservice.login2(uname, pwd);
		if(login2!=null) {
			session.setAttribute("company", login2);
			source="redirect:/company/enterprise";
		}else {
			model.addAttribute("loginin","登录信息有误,请重新登录");
			source="enterpriselogin";
		}
		return source;
	}
	
	
	// session加载企业信息
	@RequestMapping("/enterprise")
	public String enterprise(HttpSession session,Model model) {
		
		Enterprise enterprise = (Enterprise) session.getAttribute("company");
		model.addAttribute("enterprise",enterprise);
		return "enterprise";
	}

	// 企业药品页加载
	@RequestMapping("/drugsadmin")
	public String drugsadmin(HttpSession session,Model model) {
		Enterprise enterprise=(Enterprise)session.getAttribute("company");
		List<Drugs> selectByeid = drugsservice.selectByeid(enterprise.getId());
		model.addAttribute("drugseid",selectByeid);

		return "drugsadmin";
	}
	
	
	/**
	 * 
	 * ajax根据id修改价格
	 * @param eid
	 * @param dprice
	 * @return
	 */
	@RequestMapping("/uppricebyid")
	@ResponseBody
	public Map<String,Object> uppricebyid(String eid,String dprice) {
		int parseInt = Integer.parseInt(eid);
		drugsservice.updatepriceById(parseInt, dprice);
		Map<String,Object> map=new HashMap<>();
		map.put("date", "修改成功");
		return map;
	}
	
	/**
	 * ajax根据id修改库存量
	 * @param eid
	 * @param stock
	 * @return
	 */
	@RequestMapping("/upstockbyid")
	@ResponseBody
	public Map<String,Object> upstockbuid(String eid,String stock){
		int id=Integer.parseInt(eid);
		drugsservice.updatestockById(id, stock);
		Map<String,Object> map=new HashMap<>();
		map.put("date", "修改成功");
		return map;
	}
	
	
	// 删除药品
	@RequestMapping("/deletedrugs")
	public String deletedrugs(String did) {
		int id=Integer.parseInt(did);
		drugsservice.delete(id);
		return "redirect:/company/drugsadmin";
	}

	// 企业订单管理页加载
	@RequestMapping("/orderadmin")
	public String orderadmin(Model model,HttpSession session) {
		Enterprise enterprise = (Enterprise)session.getAttribute("company");
		List<Dorder> selectbyeid = dorderservice.selectbyeid(enterprise.getId());
		System.out.println(selectbyeid);
		model.addAttribute("woshizhuzhuxia",selectbyeid);

		return "orderadmin";
	}
	
	// 药品上传页加载
	@RequestMapping("/updrugs")
	public String updrugs() {

		return "updrugs";
	}
	
	// 上传药品到数据库
	@RequestMapping("/updatadrugs")
	public String updatadrugs(Drugs drugs,Model model,HttpSession session,MultipartFile imageFile) {
		String fileorgin = imageFile.getOriginalFilename();
		if (fileorgin != null) {
			String newfileName = UUID.randomUUID().toString() + ".jpg";
			try {
				imageFile.transferTo(new File("D:\\picture\\" + newfileName));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			drugs.setPictureurl(newfileName);
		}
		Enterprise enterprise = (Enterprise) session.getAttribute("company");
		drugs.setId(tools.randomdate());
		drugs.setCompanyname(enterprise.getCompanyname());
		drugs.setCompanyadd(enterprise.getCompanyadd());
		drugs.setEid(enterprise.getId());
		drugs.setStatus("正常");
		drugsservice.insertdrugs(drugs);
		model.addAttribute("success","上传成功");
		return "updrugs";
	}
	
	/**
	 * 上传运单号
	 * @param id
	 * @param number
	 * @return
	 */
	@RequestMapping("/updatenumber")
	public String updatenumber(int id,String number) {
		
		dorderservice.updateyunnamber(id, number);
		dorderservice.updatestatus(id, "确认收货");
		return "redirect:/company/orderadmin";
	}
	

	
	
}
