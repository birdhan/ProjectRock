package com.cloud.index.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cityinspector.article.model.Article;
import com.cityinspector.article.service.IArticleService;
import com.cityinspector.easyservice.model.EasyService;
import com.cityinspector.easyservice.service.IEasyServiceService;
import com.cityinspector.interaction.model.Interaction;
import com.cityinspector.interaction.service.IInteractionService;
import com.cityinspector.linkservice.service.ILinkService;
import com.cityinspector.premierletter.model.PremierLetter;
import com.cityinspector.premierletter.service.IPremierLetterService;
import com.cityinspector.problem.model.problem;
import com.cityinspector.problem.service.IproblemService;
import com.cityinspector.registeruser.model.RegisterUser;
import com.cityinspector.registeruser.service.IRegisterUserService;
import com.cityinspector.section.model.Section;
import com.cityinspector.section.service.ISectionService;
import com.cityinspector.topic.model.Topic;
import com.cityinspector.topic.service.ITopicService;
import com.cityinspector.viewmesasge.model.ViewMessage;
import com.cityinspector.viewmesasge.service.IViewMessageService;
import com.cityinspector.vote.model.User_Vote;
import com.cityinspector.vote.model.Vote;
import com.cityinspector.vote.service.IUser_VoteService;
import com.cityinspector.vote.service.IVoteService;
import com.cityinspector.vote.service.IVoteXuanService;
import com.cloud.base.controller.BaseController;
import com.cloud.base.util.StringUtil;
import com.cloud.index.service.IndexService;
import com.cloud.special.service.IspecialService;

@Controller
public class IndexController extends BaseController {

	@Resource
	private IRegisterUserService tregis;

	@Resource
	private IndexService indexService;

	@Resource
	private IEasyServiceService easyServiceService;

	@Resource
	private IArticleService articleService;

	@Resource
	private ISectionService sectionService;

	@Resource
	private IRegisterUserService registerUserService;

	@Resource
	private IPremierLetterService premierLetterService;

	@Resource
	private IproblemService problemLetterService;
	// 王子健
	@Resource
	private ILinkService linkservice;
	// 王子健
	@Resource
	private IspecialService specialService;
	
	//民意征集所用service
	@Autowired
	private IVoteService voteService;
	@Autowired
	private IUser_VoteService User_VoteService;
	
	@Autowired
	private IVoteXuanService voteXuanService;
	
	@Resource
	private IInteractionService interactionService;
	
	@Resource
	private ITopicService iTopicService;
	
	@Resource 
	private IViewMessageService iViewMessageService;
	
	@RequestMapping(value = "index")
	public String index(HttpServletRequest request) throws Exception {
		String returnPage = "index/index"; // 默认首页
		String sectionId = StringUtil.null2String(request
				.getParameter("sectionId"));
		List<Map<String, Object>> list = indexService.getserviceSection();
		if ("other".equals(sectionId)) {
			returnPage = "index/other";
		}
		request.setAttribute("list", list);
		request.setAttribute("sectionId", sectionId);

		// 王子健 -友情链接
		List<EasyService> linkServiceList = linkservice.getAllDataByWhere(" "); // 友情链接
		request.setAttribute("linkServiceList", linkServiceList);
		// 王子健-专题专栏
		List<EasyService> specialServiceList = specialService
				.getAllDataByWhere(" "); // 专题专栏
		request.setAttribute("specialServiceList", specialServiceList);

		List<EasyService> easeServiceList = easyServiceService
				.getAllDataByWhere(" order by name"); // 便民服务
		request.setAttribute("easeServiceList", easeServiceList);

		List<Section> sectionList = sectionService
				.getAllDataByWhere(" and name='城管风采'");
		if (sectionList != null && sectionList.size() != 0) { // 城管风采的数据
			List<Article> cgfc_articleList = articleService
					.getAllDataByWhere(" and sectionid='"
							+ sectionList.get(0).getId()
							+ "' order by createtime desc");
			request.setAttribute("cgfc_secondSectionId", sectionList.get(0)
					.getId());
			request.setAttribute("cgfc_sectionId", sectionList.get(0).getPid());
			request.setAttribute("cgfc_articleList", cgfc_articleList);
		}

		List<Section> sectionList_tpxw = sectionService
				.getAllDataByWhere(" and name='图片新闻'");
		if (sectionList_tpxw != null && sectionList_tpxw.size() != 0) { // 图片新闻
			List<Article> tpxw_articleList = articleService
					.getAllDataByWhere(" and sectionid='"
							+ sectionList_tpxw.get(0).getId()
							+ "' order by createtime desc");
			request.setAttribute("tpxw_secondSectionId", sectionList_tpxw
					.get(0).getId());
			request.setAttribute("tpxw_sectionId", sectionList_tpxw.get(0)
					.getPid());
			request.setAttribute("tpxw_articleList", tpxw_articleList);
		}

		List<Section> sectionList_cgxw = sectionService
				.getAllDataByWhere(" and name='城管新闻'");
		if (sectionList_cgxw != null && sectionList_cgxw.size() != 0) { // 城管新闻
			List<Article> cgxw_articleList = articleService
					.getAllDataByWhere(" and sectionid='"
							+ sectionList_cgxw.get(0).getId()
							+ "' order by createtime desc");
			request.setAttribute("cgxw_secondSectionId", sectionList_cgxw
					.get(0).getId());
			request.setAttribute("cgxw_sectionId", sectionList_cgxw.get(0)
					.getPid());
			request.setAttribute("cgxw_articleList", cgxw_articleList);
		} 
		    //梁  投票
		    List<Vote> FvoteList=voteService.getAll("0",0,5); 
		    List<Vote> TvoteList=voteService.getAll("1",0,5); 
		    request.setAttribute("FvoteList", FvoteList);
		    request.setAttribute("TvoteList", TvoteList);
			// 互动交流
		    List<Interaction> interactionList = interactionService.getAllDataByWhere(" and isshow='1' order by reqtime desc ");				// 互动交流
			request.setAttribute("interactionList", interactionList);
		   
		/*
		 * List<Section> sectionList_gsggxw =
		 * sectionService.getAllDataByWhere(" and name='公示公告'");
		 * if(sectionList_gsggxw != null && sectionList_gsggxw.size() != 0) { //
		 * 公示公告新闻 List<Article> gsggxw_articleList =
		 * articleService.getAllDataByWhere(" and sectionid='" +
		 * sectionList_gsggxw.get(0).getId() + "' order by createtime desc");
		 * request.setAttribute("gsggxw_articleList", gsggxw_articleList); }
		 */
		setGsggxw(request);

		return returnPage;
	}

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

	@RequestMapping(value = "secondSection")
	public String getSecondSection(HttpServletRequest request,
			@RequestParam String sectionId, @RequestParam String secondSectionId) {
		Map<String, Map<String, Object>> menuMap = this.publicMenu(request,
				sectionId, secondSectionId);
		Map<String, Object> secondMap = menuMap.get("secondMenu");

		setGsggxw(request);

		// 获取当前二级菜单对应的新闻信息--带分页
		long pageSize = 16l; // 默认显示10条记录
		// 当前索引页
		String p = request.getParameter("page"); // 分页参数页码参数名
		if (StringUtils.isBlank(p))
			p = "1";
		final Long pageIndex = new Long(p == null ? 1 : (Long.parseLong(p)));

		String whereStr = "";
		String _secondSectionId = StringUtil.null2String(secondMap.get("ID"));
		if (!"".equals(_secondSectionId)) {
			whereStr = " and sectionid = '" + _secondSectionId + "' ";
		}
		Map<String, Object> articleMap = indexService.getArticleBySectionId(
				pageIndex, pageSize, whereStr);
		List<Map<String, Object>> articleList = (List<Map<String, Object>>) articleMap
				.get("result");
		request.setAttribute("articleList", articleList);
		int total = (Integer) articleMap.get("total"); // 总记录数
		int resultSize = getTotalPageNum(total, pageSize); // 总页数
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curPage", p);

		return "index/secondSection";
	}

	@RequestMapping(value = "articleDetail")
	public String articleDetail(HttpServletRequest request,
			@RequestParam String sectionId,
			@RequestParam String secondSectionId, @RequestParam String articleId) {
		Map<String, Map<String, Object>> menuMap = this.publicMenu(request,
				sectionId, secondSectionId);
		Map<String, Object> secondMap = menuMap.get("secondMenu");

		String secondName = StringUtil.null2String(secondMap.get("NAME"));
		if ("视频新闻".equals(secondName)) {// 如果是视频新闻，则区别处理
			Map<String, Object> articleMap = indexService
					.getArticleVideoDetailById(articleId);
			request.setAttribute("articleMap", articleMap);
		} else {
			Map<String, Object> articleMap = indexService
					.getArticleDetailById(articleId);
			request.setAttribute("articleMap", articleMap);
		}

		setGsggxw(request);
		return "index/articleDetail";
	}

	/**
	 * 个人中心
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "userCenter")
	public String userCenter(HttpServletRequest request) {
		String sectionId = StringUtil.null2String(request
				.getParameter("sectionId"));
		String secondSectionId = StringUtil.null2String(request
				.getParameter("secondSectionId"));
		String usercuid = StringUtil.null2String(request
				.getParameter("usercuid"));
		if ("".equals(sectionId)) {
			sectionId = "index";
		}

		if ("".equals(secondSectionId)) {
			secondSectionId = "default";
		}

		this.publicMenu(request, sectionId, secondSectionId);

		RegisterUser sessionUser = (RegisterUser) request.getSession()
				.getAttribute("webSiteLoginUser");
		if (sessionUser == null || "".equals(sessionUser.getId())) {
			return "redirect:/index";
		} else {
			usercuid = sessionUser.getId();
		}

		String resultPage = "index/userCenter";
		// 根据传入的type 区别查询
		String type = StringUtil.null2String(request.getParameter("type"));
		if ("1".equals(type)) {// 个人中心
			resultPage = "index/userCenter";
		} else {// 账户安全
			resultPage = "index/userPwd";
		}
		RegisterUser ru = registerUserService.getRegisterUserById(usercuid);
		request.setAttribute("registerUser", ru);

		setGsggxw(request);
		return resultPage;
	}

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
	 * 局长信箱
	 */
	@RequestMapping(value = "myEmail")
	public String myEmail(HttpServletRequest request,
			@RequestParam String sectionId, @RequestParam String secondSectionId) {
		String usercuid = StringUtil.null2String(request
				.getParameter("usercuid"));
		this.publicMenu(request, sectionId, secondSectionId);

		setGsggxw(request);

		// 获取当前二级菜单对应的新闻信息--带分页
		long pageSize = 18l; // 默认显示10条记录
		// 当前索引页
		String p = request.getParameter("page"); // 分页参数页码参数名
		if (StringUtils.isBlank(p))
			p = "1";
		final Long pageIndex = new Long(p == null ? 1 : (Long.parseLong(p)));

		String whereStr = " and reqregisteruser = '" + usercuid + "' ";
		Map<String, Object> myEmailMap = indexService.getMyEmail(pageIndex,
				pageSize, whereStr);
		List<Map<String, Object>> emailList = (List<Map<String, Object>>) myEmailMap
				.get("result");
		request.setAttribute("emailList", emailList);
		int total = (Integer) myEmailMap.get("total"); // 总记录数
		int resultSize = getTotalPageNum(total, pageSize); // 总页数
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curPage", p);
		request.setAttribute("usercuid", usercuid);
		request.setAttribute("sectionId", sectionId);
		request.setAttribute("secondSectionId", secondSectionId);

		return "index/myEmail";
	}

	/**
	 * 局长信箱
	 */
	@RequestMapping(value = "writeEmail")
	public String writeEmail(HttpServletRequest request,
			@RequestParam String sectionId, @RequestParam String secondSectionId) {
		this.publicMenu(request, sectionId, secondSectionId);
		setGsggxw(request);
		return "index/writeEmail";
	}

	/**
	 * 局长信箱
	 */
	@RequestMapping(value = "emailDetail")
	public String emailDetail(HttpServletRequest request,
			@RequestParam String sectionId, @RequestParam String secondSectionId) {
		this.publicMenu(request, sectionId, secondSectionId);
		setGsggxw(request);

		String letterId = StringUtil.null2String(request
				.getParameter("letterId"));
		PremierLetter pl = premierLetterService.getPremierLetterById(letterId);
		request.setAttribute("email", pl);
		return "index/emailDetail";
	}

	/**
	 * 局长信箱
	 */
	@RequestMapping(value = "saveEmail")
	public void saveEmail(HttpServletRequest request,
			HttpServletResponse response) {
		String flag = "0"; // 默认成功
		JSONObject resultJson = new JSONObject();

		String title = StringUtil.null2String(request.getParameter("title"));
		String content = StringUtil
				.null2String(request.getParameter("content"));
		String usercuid = StringUtil.null2String(request
				.getParameter("usercuid"));
		PremierLetter pl = new PremierLetter();
		pl.setTitle(title);
		pl.setReqcontent(content);
		pl.setReqregisteruser(usercuid);
		pl.setReqtime(new Date());
		pl.setRepstatus("0");
		premierLetterService.savePremierLetter(pl);

		resultJson.put("status", flag);
		printJSON(response, resultJson.toString());
	}

	/**
	 * 问题受理
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "problem")
	public String problem(HttpServletRequest request,
			@RequestParam String sectionId, @RequestParam String secondSectionId)
			throws UnsupportedEncodingException {
		String usercuid = StringUtil.null2String(request
				.getParameter("usercuid"));

		this.publicMenu(request, sectionId, secondSectionId);

		setGsggxw(request);

		// 获取当前二级菜单对应的新闻信息--带分页
		long pageSize = 12l; // 默认显示10条记录
		// 当前索引页
		String p = request.getParameter("page"); // 分页参数页码参数名
		if (StringUtils.isBlank(p))
			p = "1";
		final Long pageIndex = new Long(p == null ? 1 : (Long.parseLong(p)));

		String whereStr = " and userName = '" + usercuid + "' ";
		Map<String, Object> myProblemMap = indexService.getProblem(pageIndex,
				pageSize, whereStr);
		List<Map<String, Object>> problemList = (List<Map<String, Object>>) myProblemMap
				.get("result");
		request.setAttribute("problemList", problemList);
		int total = (Integer) myProblemMap.get("total"); // 总记录数
		int resultSize = getTotalPageNum(total, pageSize); // 总页数
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curPage", p);
		request.setAttribute("usercuid", usercuid);
		request.setAttribute("sectionId", sectionId);
		request.setAttribute("secondSectionId", secondSectionId);

		return "index/other";
	}

	/**
	 * 问题受理
	 */
	@RequestMapping(value = "questAsk")
	public String questAsk(HttpServletRequest request,
			@RequestParam String sectionId, @RequestParam String secondSectionId) {
		this.publicMenu(request, sectionId, secondSectionId);
		setGsggxw(request);
		return "index/questAsk";
	}

	/**
	 * 问题受理
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "savePrbloem")
	public void savePrbloem(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String flag = "0"; // 默认成功
		JSONObject resultJson = new JSONObject();

		String title = StringUtil.null2String(request.getParameter("title"));
		String content = StringUtil
				.null2String(request.getParameter("content"));
		String usercuid = StringUtil.null2String(request
				.getParameter("usercuid"));
		String phone = StringUtil.null2String(request
				.getParameter("usercuid"));
		
		problem pl = new problem();
		pl.setTitle(title);
		pl.setContent(content);
		pl.setUserName(usercuid);
		pl.setPhone(phone);
		pl.setTime(new Date());
		pl.setStatus("1");
		problemLetterService.saveproblem(pl);

		resultJson.put("status", flag);
		printJSON(response, resultJson.toString());
	}

	/**
	 * 问题受理
	 */
	@RequestMapping(value = "problemDetail")
	public String problemDetail(HttpServletRequest request,
			@RequestParam String sectionId, @RequestParam String secondSectionId) {
		this.publicMenu(request, sectionId, secondSectionId);
		setGsggxw(request);

		String letterId = StringUtil.null2String(request
				.getParameter("letterId"));
		problem pl = problemLetterService.getproblemById(letterId);
		request.setAttribute("problem", pl);
		return "index/problemDetail";
	}

	/*
	 * 积分排行榜
	 */
	@RequestMapping(value = "/showPointd")
	public String showPointd(HttpServletRequest request,
			HttpServletResponse response) {
		/*
		 * Map<String, Object> map=indexService.getPoints();
		 * request.setAttribute("map", map); System.out.println(map); return
		 * "index/userPoints";
		 */

		String sql = "order by points desc";

		List<RegisterUser> aist = tregis.getAllDataByWhere(sql);
		System.out.println(aist);
		request.setAttribute("aist", aist);
		return "index/userPoints";

	}
	
	//民意征集开始
	@RequestMapping(value = "myzj")
	public String myzj(HttpServletRequest request,@RequestParam String sectionId,@RequestParam String secondSectionId){
		String usercuid = StringUtil.null2String(request.getParameter("usercuid"));
		this.publicMenu(request, sectionId, secondSectionId);
		setGsggxw(request);	
		
		
		List FVoteList=voteService.getAll("0");
		List TVoteList=voteService.getAll("1");
		request.setAttribute("FVoteList", FVoteList);
		request.setAttribute("TVoteList", TVoteList);
		request.setAttribute("sectionId", sectionId);
		request.setAttribute("secondSectionId", secondSectionId);
		return "index/myzj";
	}
	@RequestMapping(value = "myDetail")
	public String myDetail(HttpServletRequest request,@RequestParam String sectionId,@RequestParam String secondSectionId){
		this.publicMenu(request, sectionId, secondSectionId);
		setGsggxw(request);	
		String usercuid = request.getParameter("usercuid");
		String letterId=request.getParameter("letterId");
		String s;
		List list=null;
		RegisterUser user=registerUserService.getRegisterUserById(usercuid);
		Vote vote=voteService.getVoteById(letterId);
		if(user!=null && vote!=null)
		{
			list=User_VoteService.getAllDataByWhere(" Uid='"+usercuid+"' and Vid='"+letterId+"'");	
		}
		
		
		if((list==null || list.size()==0)  && vote.getIf_Finish().equals("0"))
		{   
			String voteType=vote.getType();
			if(voteType.equals("0"))
			{   
				 s="index/CstartVote";
			}
			else
			{   		
				s="index/RstartVote";
			}		
		}
		else
		{   
			s="index/VoteResult";
		}
		List xuanList=voteXuanService.getVid(letterId);
		request.setAttribute("xuanList", xuanList);
		request.setAttribute("vote", vote);
		request.setAttribute("usercuid", usercuid);
		request.setAttribute("sectionId", sectionId);
		request.setAttribute("secondSectionId", secondSectionId);
		return s;
	}
	
	
	@RequestMapping(value = "TmyDetail")
	public String TmyDetail(HttpServletRequest request,@RequestParam String sectionId,@RequestParam String secondSectionId){
		this.publicMenu(request, sectionId, secondSectionId);
		setGsggxw(request);
		String letterId=request.getParameter("letterId");
		Vote vote=voteService.getVoteById(letterId);
		List xuanList=voteXuanService.getVid(letterId);
		request.setAttribute("xuanList", xuanList);
		request.setAttribute("sectionId", sectionId);
		request.setAttribute("vote", vote);
		request.setAttribute("secondSectionId", secondSectionId);
		return "index/VoteResult";
	}
	@RequestMapping(value = "tp")
	public String tp(HttpServletRequest request,@RequestParam String sectionId,@RequestParam String secondSectionId){
		this.publicMenu(request, sectionId, secondSectionId);
		setGsggxw(request);
		String letterId=request.getParameter("letterId");
		String userid=request.getParameter("userid");
		
		if(registerUserService.getRegisterUserById(userid)==null || voteService.getVoteById(letterId)==null)return "index/error";
		List list=User_VoteService.getAllDataByWhere(" Uid='"+userid+"' and Vid='"+letterId+"'");
		if(list==null||list.size()==0)
		{  
			String xuan[]=request.getParameterValues("r");
			if(!(xuan ==null || xuan.length==0))
			{
				for(String id:xuan)
				{
					voteXuanService.update(id);
				}
				User_Vote uv=new User_Vote();
				uv.setUid(userid);
				uv.setVid(letterId);
				User_VoteService.saveUser_Vote(uv);
			}
			
		}
		List xuanList=voteXuanService.getVid(letterId);
		request.setAttribute("xuanList", xuanList);
		request.setAttribute("sectionId", sectionId);
		request.setAttribute("secondSectionId", secondSectionId);
		return "index/VoteResult";
	}
 //民意征集结束
	/**
	 * 
	 * 互动交流二级页面
	 */
	@RequestMapping(value = "interactionSecond")
	public String interactionSecond(HttpServletRequest request,@RequestParam String sectionId,@RequestParam String secondSectionId){
		String usercuid = StringUtil.null2String(request.getParameter("usercuid"));
		this.publicMenu(request, sectionId, secondSectionId);
		
		setGsggxw(request);
		
		//获取当前二级菜单对应的新闻信息--带分页
		long pageSize = 11l;																//默认显示10条记录
		//当前索引页
		String p = request.getParameter("page");					//分页参数页码参数名
		if(StringUtils.isBlank(p)) p = "1";
		final Long pageIndex = new Long(p==null ? 1 : (Long.parseLong(p)));
		
		String whereStr = " and isshow = '"+1+"' order by reqtime desc ";
		Map<String,Object> interactionMap = indexService.getInteraction(pageIndex, pageSize, whereStr);
		List<Map<String,Object>> interactionList = (List<Map<String, Object>>) interactionMap.get("result");
		request.setAttribute("interactionList", interactionList);
		int total = (Integer)interactionMap.get("total");				// 总记录数
		int resultSize = getTotalPageNum(total , pageSize);			// 总页数
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curPage", p);
		request.setAttribute("usercuid", usercuid);
		request.setAttribute("sectionId", sectionId);
		request.setAttribute("secondSectionId", secondSectionId);
		
		return "index/interactionSecond";
	}

	/**
	 * 
	 * 我要交流
	 */
	@RequestMapping(value = "writeInteraction")
	public String writeInteraction(HttpServletRequest request,@RequestParam String sectionId,@RequestParam String secondSectionId){
		this.publicMenu(request, sectionId, secondSectionId);
		setGsggxw(request);
		return "index/interactionWrite";
	}

	/**
	 * 
	 * 互动交流详情页
	 */
	@RequestMapping(value = "interactionDetail")
	public String interactionDetail(HttpServletRequest request,@RequestParam String sectionId,@RequestParam String secondSectionId){
		this.publicMenu(request, sectionId, secondSectionId);
		
		setGsggxw(request);
		
		String interid = StringUtil.null2String(request.getParameter("id"));
		Interaction interaction = interactionService.getInteractionById(interid);
		request.setAttribute("interaction", interaction);
		return "index/interactionDetail";
	}
	
	/**
	 * 
	 * 保存交流的内容
	 */
	@RequestMapping(value = "saveInteraction")
	public void saveInteraction(HttpServletRequest request,HttpServletResponse response){
		String flag = "0";			//默认成功
		JSONObject resultJson = new JSONObject();
		
		String title = StringUtil.null2String(request.getParameter("title"));
		String content = StringUtil.null2String(request.getParameter("content"));
		String usercuid = StringUtil.null2String(request.getParameter("usercuid"));
		Interaction interaction = new Interaction();
		interaction.setTitle(title);
		interaction.setReqcontent(content);
		interaction.setReqregisteruser(usercuid);
		interaction.setReqtime(new Date());
		interaction.setRepstatus("0");
		interaction.setIsshow("2");
		
		interactionService.saveInteraction(interaction);
		
		resultJson.put("status", flag);
		printJSON(response, resultJson.toString());
	}
	
	
	
	/**
	 * 
	 * 城管问计二级页面
	 */
	@RequestMapping(value = "topicSecond")
	public String topicSecond(HttpServletRequest request,@RequestParam String sectionId,@RequestParam String secondSectionId){
		String usercuid = StringUtil.null2String(request.getParameter("usercuid"));
		this.publicMenu(request, sectionId, secondSectionId);
		
		setGsggxw(request);
		
		//获取当前二级菜单对应的新闻信息--带分页
		long pageSize = 14l;																//默认显示10条记录
		//当前索引页
		String p = request.getParameter("page");					//分页参数页码参数名
		if(StringUtils.isBlank(p)) p = "1";
		final Long pageIndex = new Long(p==null ? 1 : (Long.parseLong(p)));
		
		String whereStr = " and isshow ='"+1+"'";
		
		Map<String,Object> topicMap = indexService.getTopic(pageIndex, pageSize, whereStr);
		List<Map<String,Object>> topicList = (List<Map<String, Object>>) topicMap.get("result");
		
		
		request.setAttribute("topicList", topicList);
		int total = (Integer)topicMap.get("total");				// 总记录数
		int resultSize = getTotalPageNum(total , pageSize);			// 总页数
		request.setAttribute("resultSize", resultSize);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("curPage", p);
		request.setAttribute("usercuid", usercuid);
		request.setAttribute("sectionId", sectionId);
		request.setAttribute("secondSectionId", secondSectionId);
		
		return "index/topicSecond";
	}
	
	/**
	 * 
	 * 城管问计详情页
	 */
	@RequestMapping(value = "topicDetail")
	public String topicDetail(HttpServletRequest request,@RequestParam String sectionId,@RequestParam String secondSectionId){
		this.publicMenu(request, sectionId, secondSectionId);
		
		setGsggxw(request);
		
		String topicid = StringUtil.null2String(request.getParameter("id"));
		String title=null;
		try {
			title = new String(request.getParameter("title").getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Topic topic = iTopicService.getTopicById(topicid);
		request.setAttribute("topic", topic);
	
		List<ViewMessage> viewMessageList = iViewMessageService.getAllDataByWhere(" and isshow='1' and title='"+title+"'");
		request.setAttribute("viewMessageList", viewMessageList);
		
		return "index/topicDetail";
	}
	

	/**
	 * 
	 * 保存留言的内容
	 */
	@RequestMapping(value = "saveViewmessage")
	public void saveViewmessage(HttpServletRequest request,HttpServletResponse response){
		String flag = "0";			//默认成功
		JSONObject resultJson = new JSONObject();
		
		String title = StringUtil.null2String(request.getParameter("title"));
		String content = StringUtil.null2String(request.getParameter("content"));
		String usercuid = StringUtil.null2String(request.getParameter("usercuid"));
		ViewMessage viewMessage = new ViewMessage();
		viewMessage.setTitle(title);
		viewMessage.setReqcontent(content);
		viewMessage.setReqregisteruser(usercuid);
		viewMessage.setReqtime(new Date());
		viewMessage.setIsshow("2");
		
		iViewMessageService.saveViewMessage(viewMessage);
		
		resultJson.put("status", flag);
		printJSON(response, resultJson.toString());
	}
	
	
}
