package com.cloud.ibuhui.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cityinspector.article.model.Article;
import com.cityinspector.article.service.IArticleService;
import com.cityinspector.openinfor.model.AppAction;
import com.cityinspector.openinfor.service.IAppActionService;
import com.cityinspector.registeruser.model.RegisterUser;
import com.cityinspector.registeruser.service.IRegisterUserService;
import com.cityinspector.section.model.Section;
import com.cityinspector.section.service.ISectionService;
import com.cloud.base.controller.BaseController;
import com.cloud.base.util.StringUtil;
import com.cloud.index.service.IndexService;


@Controller
public class IbuhuiController1 extends BaseController{
	
	@Resource
	private ISectionService sectionService;
	
	@Resource
	private IRegisterUserService ire;
	
	@Resource
	private IAppActionService iapp;
	
	@Resource
	private IndexService indexService;
	
	@Resource
	private IArticleService articleService;
	
	/**
	 * test
	 * @param request
	 * @return
	 */
	@RequestMapping(value="test01")
	public String mainzhu(HttpServletRequest request){
		String sectionId = StringUtil.null2String(request
				.getParameter("sectionId"));
		String secondSectionId = StringUtil.null2String(request
				.getParameter("secondSectionId"));

		if ("".equals(sectionId)) {
			sectionId = "index";
		}

		if ("".equals(secondSectionId)) {
			secondSectionId = "default";
		}

		this.publicMenu(request, sectionId, secondSectionId);
		
		return "index/appActionForm";
	}
	
	/**
	 * 加载菜单列表
	 * @param request
	 * @param sectionId
	 * @param secondSectionId
	 * @return
	 */
	private Map<String, Map<String, Object>> publicMenu(
			HttpServletRequest request, String sectionId, String secondSectionId) {
		if ("root".equals(sectionId)) {
			sectionId = "index";
		}
		Map<String, Map<String, Object>> resultMap = new HashMap<String, Map<String, Object>>();
		// 获取所有一级
		List<Map<String, Object>> list = indexService.getserviceSection();
		request.setAttribute("list", list);

		// 获取当前一级选中菜单的属性
		Map<String, Object> firstMap = indexService
				.getserviceSectionById(sectionId);

		// 获取所有二级
		List<Map<String, Object>> secondlist = indexService
				.getSecondSection(sectionId);
		request.setAttribute("secondlist", secondlist);

		// 获取当前二级选中菜单属性如果是default，则 取所有二级菜单的第一条数据
		Map<String, Object> secondMap = new HashMap<String, Object>();
		if ("default".equals(secondSectionId)) {
			if (secondlist.size() > 0) {
				secondMap = secondlist.get(0);
			}
		} else {
			secondMap = indexService.getSecondSectionById(secondSectionId);
		}

		request.setAttribute("sectionId", sectionId);
		request.setAttribute("secondSectionId", secondSectionId);
		request.setAttribute("firstMap", firstMap);
		request.setAttribute("secondMap", secondMap);

		resultMap.put("firstMenu", firstMap);
		resultMap.put("secondMenu", secondMap);
		return resultMap;
	}

	/**
	 * 
	 * 查询申请公开信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "gongkai")
	public String gongkai(HttpServletRequest request) {

		String sectionId = StringUtil.null2String(request
				.getParameter("sectionId"));
		String secondSectionId = StringUtil.null2String(request
				.getParameter("secondSectionId"));
		/*
		 * String usercuid = StringUtil.null2String(request
		 * .getParameter("usercuid"));
		 */
		RegisterUser sessionUser1 = (RegisterUser) request.getSession()
				.getAttribute("webSiteLoginUser");

		if ("".equals(sectionId)) {
			sectionId = "index";
		}

		if ("".equals(secondSectionId)) {
			secondSectionId = "default";
		}

		this.publicMenu(request, sectionId, secondSectionId);

		if (sessionUser1 != null && !sessionUser1.getId().trim().isEmpty()) {

			String usercuid = sessionUser1.getId();

			List<AppAction> findlistall1 = iapp
					.getAllDataByWhere("state='待审核' and uid='" + usercuid + "'");

			request.setAttribute("uidfind", findlistall1);
			/*System.out.println("我的申请");
			System.out.println(findlistall1);
			System.out.println("用户id");
			System.out.println(usercuid);*/
		}

		List<AppAction> findlistall = iapp.getAllDataByWhere("state='通过'");
		setGsggxw(request);
		request.setAttribute("list001", findlistall);
		/*System.out.println("已公开申请");
		System.out.println(findlistall);
*/
		return "index/appAction";

	}
	/**
	 * gongshi
	 * @param request
	 */
	private void setGsggxw(HttpServletRequest request) {
		List<Section> sectionList_gsggxw = sectionService
				.getAllDataByWhere(" and name='公示公告'");
		if (sectionList_gsggxw != null && sectionList_gsggxw.size() != 0) { // 公示公告新闻
			List<Article> gsggxw_articleList = articleService
					.getAllDataByWhere(" and sectionid='"
							+ sectionList_gsggxw.get(0).getId()
							+ "' order by createtime desc");
			request.setAttribute("gsggxw_secondSectionId", sectionList_gsggxw
					.get(0).getId());
			request.setAttribute("gsggxw_sectionId", sectionList_gsggxw.get(0)
					.getPid());
			request.setAttribute("gsggxw_articleList", gsggxw_articleList);
		}
	}

	/**
	 * 
	 * 申请公开根据id查询
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "selById")
	public String findById(HttpServletRequest request) {

		String sectionId = StringUtil.null2String(request
				.getParameter("sectionId"));
		String secondSectionId = StringUtil.null2String(request
				.getParameter("secondSectionId"));

		if ("".equals(sectionId)) {
			sectionId = "index";
		}

		if ("".equals(secondSectionId)) {
			secondSectionId = "default";
		}

		this.publicMenu(request, sectionId, secondSectionId);

		String aid = request.getParameter("aaId");

		/*String hql = "id='" + aid + "'";*/

		AppAction list01 = iapp.getAppActionById(aid);

		request.setAttribute("listapp", list01);
		
		String cuid=list01.getUid();
		
		RegisterUser registerUserById = ire.getRegisterUserById(cuid);
		
		/*String uname=registerUserById.getUsername();*/
		
		request.setAttribute("uname", registerUserById);
		setGsggxw(request);
		return "index/appActionxqy";
		
		

	}
	
	/**
	 * 
	 * 申请公开提交申请
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "shenqing")
	public String saveapp(HttpServletRequest request) {

		String sectionId = StringUtil.null2String(request
				.getParameter("sectionId"));
		String secondSectionId = StringUtil.null2String(request
				.getParameter("secondSectionId"));

		if ("".equals(sectionId)) {
			sectionId = "index";
		}

		if ("".equals(secondSectionId)) {
			secondSectionId = "default";
		}

		this.publicMenu(request, sectionId, secondSectionId);
		
		RegisterUser sessionUser1 = (RegisterUser) request.getSession()
				.getAttribute("webSiteLoginUser");
		
		AppAction appAction=new AppAction();
		
		if(sessionUser1==null && sessionUser1.getId().isEmpty()){
			
		}
		
		String uid=sessionUser1.getId();
		appAction.setUid(uid);
		System.out.println(uid);
		String tit=request.getParameter("title");
		String act=request.getParameter("actice");
		appAction.setTitle(tit);
		appAction.setActice(act);
		appAction.setState("待审核");
		iapp.saveAppAction(appAction);
		
		return "redirect:gongkai";
		
		

	}
	/**
	 * 跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "formtiao")
	public String formtiao(HttpServletRequest request) {

		String sectionId = StringUtil.null2String(request
				.getParameter("sectionId"));
		String secondSectionId = StringUtil.null2String(request
				.getParameter("secondSectionId"));

		if ("".equals(sectionId)) {
			sectionId = "index";
		}

		if ("".equals(secondSectionId)) {
			secondSectionId = "default";
		}
		setGsggxw(request);
		this.publicMenu(request, sectionId, secondSectionId);
		
		return "index/appActionForm";
		
		

	}
	

	

}
