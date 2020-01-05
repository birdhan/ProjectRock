package com.drugs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.drugs.pojo.Administrator;
import com.drugs.pojo.Consumer;
import com.drugs.pojo.Dorder;
import com.drugs.pojo.Drugs;
import com.drugs.pojo.Enterprise;
import com.drugs.service.AdministratorService;
import com.drugs.service.ConsumerService;
import com.drugs.service.DorderService;
import com.drugs.service.DrugsService;
import com.drugs.service.EnterpriseService;

@Controller
@RequestMapping("backmanage")
public class BackController {
	
	
	@Autowired
	private AdministratorService adminservice;
	@Autowired
	private ConsumerService consumerservice;
	@Autowired
	private EnterpriseService enterprises;
	@Autowired
	private DrugsService drugsservice;
	@Autowired
	private DorderService dorderservice;

	
//	加载登录页
	@RequestMapping("/loginback")
	public String loginback() {
		
		return "backstage/loginback";
	}
	
	
	//登录验证
	@RequestMapping("/yanzhenglogin")
	public String yanzhenglogin(String name,String password,HttpSession session,Model model) {
		String source="";
		Administrator loginin = adminservice.loginin(name, password);
		
		if(loginin!=null) {
			
			
			session.setAttribute("backmanage", loginin);
			source="redirect:/backmanage/usermanage";
		}else {
			model.addAttribute("loginerror","账号密码错误");
			source="backstage/loginback";
		}
		
		return source;
	}
	
	
	/**
	 * 更新admin信息
	 * @param session
	 */
	public void adminbyid(HttpSession session) {
		Administrator admin=(Administrator)session.getAttribute("backmanage");
		Administrator selectbyId = adminservice.selectbyId(admin.getId());
		session.setAttribute("backmanage", selectbyId);
	}
	
	
//	用户管理信息查询页
	@RequestMapping("/usermanage")
	public String usermanage(Model model,HttpSession session) {
		List<Consumer> useradmin = consumerservice.selectall();
		model.addAttribute("useradmin",useradmin);
		adminbyid(session);
		return "backstage/usermanage";
	}
	
	
	/**
	 * 用户充值
	 * @param id
	 * @param balance
	 * @return
	 */
	@RequestMapping("/userrecharge")
	public String userrecharge(int id,String balance,String balanceo) {
		String balances=String.valueOf(Integer.parseInt(balance)+Integer.parseInt(balanceo));
		
		consumerservice.upubalance(id, balances);
		
		return "redirect:/backmanage/usermanage";
	}
	
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteconsumer")
	public String deleteconsumer(int id) {
		consumerservice.deleteconsumer(id);
		
		return "redirect:/backmanage/usermanage";
	}
	
	
	
	
	
	/**
	 * 企业提现全部
	 * @param id
	 * @return
	 */
	@RequestMapping("/enterprisetixian")
	public String enterprisetixian(int id) {
		String ablance="0";
		enterprises.updateablance(id, ablance);
		
		return "redirect:/backmanage/enterprisemanage";
	}
	
	/**
	 * 冻结企业账户
	 * @param id
	 * @return
	 */
	@RequestMapping("/enterprisedongjie")
	public String enterprisedongjie(int id) {
		String status="冻结";
		enterprises.updatedtatus(id, status);
		drugsservice.updatestatus(id, "冻结");
		return "redirect:/backmanage/enterprisemanage";
	}
	
	// 药品管理页面查询
	@RequestMapping("/drugsmanage")
	public String drugsmanage(Model model,HttpSession session) {
		List<Drugs> drugsadmin = drugsservice.selectall();
		model.addAttribute("drugsadmin",drugsadmin);
		adminbyid(session);
		return "backstage/drugsmanage";
	}
	
	
	/**
	 * 删除药品
	 * @param id
	 * @return
	 */
	@RequestMapping("/drugsdelete")
	public String drugsdelete(int id) {
		drugsservice.deletedrugs(id);
		return "redirect:/backmanage/drugsmanage";
	}
	
	// 资金管理页面查询
	@RequestMapping("/capitalmanage")
	public String capitalmanage(Model model,HttpSession session) {
		List<Dorder> orderadmin = dorderservice.selectloan();
		model.addAttribute("orderadmin",orderadmin);
		adminbyid(session);
		return "backstage/capitalmanage";
	}
	
	/**
	 * 放款
	 * @param eid
	 * @param dprice
	 * @param id
	 * @param session
	 * @return
	 */
	@RequestMapping("/fangkuana")
	public String fangkuana(int eid,int dprice,int id,HttpSession session) {
		
		enterprises.updateblan(eid, dprice);
		adminservice.updateabalance1(1, dprice);
		dorderservice.updateloan(id, "1");
		adminbyid(session);
		return "redirect:/backmanage/capitalmanage";
	}
	
	/**
	 * 账号审核页加载
	 * @param model
	 * @return
	 */
	@RequestMapping("/examinemanage")
	public String examinemanage(Model model,HttpSession session) {
		List<Enterprise> enteradmin = enterprises.selectenter();
		model.addAttribute("enteradmin",enteradmin);
		adminbyid(session);
		return "backstage/examinemanage";
	}
	
	/**
	 * 企业账号解冻 申请审核
	 * @param id
	 * @return
	 */
	@RequestMapping("/enterprisestatus")
	public String enterprisestatus(int id) {
		enterprises.updatedtatus(id, "正常");
		drugsservice.updatestatus(id, "正常");
		return "redirect:/backmanage/examinemanage";
	}
}
