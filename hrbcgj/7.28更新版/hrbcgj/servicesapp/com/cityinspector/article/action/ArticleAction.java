package com.cityinspector.article.action;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.persistence.Transient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;

import com.cloud.base.action.BaseAction;
import com.cloud.base.annotation.propertydesc.PropertyDesc;
import com.cloud.base.annotation.security.Security;
import com.cloud.base.importexcel.ExcelMap2Model;
import com.cloud.base.util.DBFM;
import com.cloud.base.util.ExcelUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.StringUtil;
import com.cityinspector.article.model.Article;
import com.cityinspector.article.service.IArticleService;
import com.cityinspector.section.model.Section;
import com.cityinspector.section.service.ISectionService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author cuiyp
 * 创建时间：2018-07-16 09:16:55
 */
@SuppressWarnings("finally")
@Controller
public class ArticleAction extends BaseAction implements ModelDriven<Object>{

	private Long PAGESIZE_CONSTANT = 10l;//显示记录数

	@Resource
	private IArticleService articleService;
	
	@Resource
	private ISectionService sectionService;

	/**
	 * 将form转成model
	 */
	private Article article;
	public Article getModel() {
		if(article == null) {
			article = new Article();
		}
		return article;
	}

	/**
	 * 添加
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="add",privName="添加",roleType="PRIV")
	public String addArticle() throws Exception {
		List<Section> sectionList = sectionService.getAllDataByWhere("");
		request.setAttribute("sectionList", sectionList);
		return "edit";
	}

	/**
	 * 查看详细
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="detail",privName="详细")
	public String detailArticle() throws Exception {
		String id = request.getParameter("id");
		article = articleService.getArticleById(id);
		request.setAttribute("article", article);
		return "detail";
	}

	/**
	 * 编辑
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="edit",privName="编辑",roleType="PRIV")
	public String editArticle() throws Exception {
		String id = request.getParameter("id");
		article = articleService.getArticleById(id);
		request.setAttribute("article", article);
		List<Section> sectionList = sectionService.getAllDataByWhere("");
		request.setAttribute("sectionList", sectionList);
		return "edit";
	}

	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="del",privName="删除",roleType="PRIV")
	public String delArticleById() throws IOException {
		String id = request.getParameter("id");
		try {
			article.setId(id);
			articleService.delArticle(article);
		} catch (Exception e) {
			String mesg = "删除数据错误，消息为："+e.getMessage();
			LoggerUtil.error(getClass(), mesg);
		}
		return "redirectSuccess";
	}
	/**
	 * 批量删除
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="delBatch",privName="批量删除",roleType="PRIV")
	public String delArticleByIds() throws IOException {
		String ids = request.getParameter("ids");
		try {
			String[] ids_arr = ids.split(",");
			List idList = new ArrayList();
			for(String id : ids_arr) {
				idList.add(id);
			}
			articleService.delArticleBatch(idList);
		} catch (Exception e) {
			String mesg = "批量删除数据错误，消息为："+e.getMessage();
			LoggerUtil.error(getClass(), mesg);
		}
		return "redirectSuccess";
	}

	/**
	 * 保存
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="save",privName="保存",roleType="PRIV")
	public String saveArticle() throws Exception {
		articleService.saveArticle(article);
		return "redirectSuccess";														//重定向至列表，避免页面再次刷新弹出页面对话框
	}

	/**
	 * 跳转成功页面
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="success",privName="跳转成功")
	public String success() throws Exception {
		request.setAttribute("listUrl", request.getContextPath() + "/article/searchArticle.do");
		return "success";
	}

	/**
	 * 获取列表中的数据
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="search",privName="列表查询",roleType="PRIV")
	public String searchArticle() throws Exception {
		StringBuffer sqlWhere = new StringBuffer("");										//查询条件where
		String pageIndexName = new org.displaytag.util.ParamEncoder("articleList").		//articleList为jsp页面中<display:table> 的id值
				encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		//查询过滤-start
		String isSearch = StringUtil.null2String(request.getParameter("isSearch"));		//查询标志
		if(isSearch.equals("yes")) {													//表示是查询
			if(!StringUtil.null2String(article.getName()).equals("")) {
				sqlWhere.append(" and article.name='" + article.getName() + "'");
			}
			if(!StringUtil.null2String(article.getSectionid()).equals("")) {
				sqlWhere.append(" and article.sectionid='" + article.getSectionid() + "'");
			}
			request.setAttribute("article", article);
		} else {
			request.setAttribute("article", null);
		}

		//查询过滤-end

		//当前索引页
		final Long pageIndex = new Long(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 1 : (Long.parseLong(request.getParameter(pageIndexName))));
		Map map = articleService.searchArticle(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());

		request.setAttribute("list", map.get("result"));								//显示结果list
		request.setAttribute("pageSize", PAGESIZE_CONSTANT);							//每页显示的记录数，默认10条
		request.setAttribute("resultSize", map.get("total"));							//所有结果总个数
		
		List<Section> sectionList = sectionService.getAllDataByWhere("");
		request.setAttribute("sectionList", sectionList);
		
		return "list";
	}

	/**
	 * 弹出导出数据的页面
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="openExport",privName="弹出导出",roleType="PRIV")
	public String openArticleExport() throws Exception {
		String ids = request.getParameter("ids");
		Map fieldMap = new TreeMap();
		Field[] fieldArr = article.getClass().getDeclaredFields();							//得到定义的属性
		for(Field field : fieldArr) {													//field.getName()可得到属性值
			Annotation annotation = field.getAnnotation(PropertyDesc.class);			//得到属性上的注解串
			Annotation transientDesc = field.getAnnotation(Transient.class);
			if(annotation != null && transientDesc == null) {							//表示有描述信息且没有透明注解（透明注解不参与导出功能）
				String desc = annotation.toString();
				if(!StringUtil.null2String(desc).equals("")) {
					desc = desc.substring(desc.indexOf("name=")+5,desc.length()-1);		//得到属性描述
					fieldMap.put(desc, field.getName());
				} else {
					LoggerUtil.info(getClass(), "没有找到描述值："+field.getName());
				}
			}
		}
		request.setAttribute("ids", ids);
		request.setAttribute("idsSize", ids.split(",").length);
		request.setAttribute("fieldMap", fieldMap);										//将属性map传给页面
		return "export";
	}

	/**
	 * 导出数据
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="exportData",privName="导出数据",roleType="PRIV")
	public String exportData() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				//日期类型格式转换默认是这个格式
		String columns = request.getParameter("columns");		//得到导出所选的列
		StringBuffer where = new StringBuffer("");

		//过滤数据条件开始
		String ids = request.getParameter("ids");
		if(!ids.equals("")) {
			String inIds = "";
			for(String id : ids.split(",")) {
				inIds += "'" + id + "',";
			}
			if(!inIds.equals("")) {
				inIds = inIds.substring(0, inIds.length()-1);
				where.append(" and article.id in (" + inIds + ")");
			}
		}

		where.append("");																	//排序方式 order by user.createTime asc
		//过滤条件结束

		if(!StringUtil.null2String(columns).equals("")) {
			JSONArray ja = JSONArray.fromObject("[" + columns + "]");					//得到json数组
			List<Article> list = articleService.getAllDataByWhere(where.toString());			//得到要导出的数据
			List dataList = new ArrayList();												//封装成dataList数据

			Object[] tempTitle = new Object[ja.size()];										//表头
			for(int i=0;i<ja.size();i++) {
				JSONObject jo = (JSONObject)ja.get(i);
				tempTitle[i] = String.valueOf(jo.get("name"));
			}
			dataList.add(tempTitle);														//添加表头

			for(Article article : list) {
				Object[] tempModel = new Object[ja.size()];									//临时的model
				for(int i=0;i<ja.size();i++) {												//遍历所有选中的列内容
					JSONObject jo = (JSONObject)ja.get(i);
					Method m = article.getClass().getMethod("get"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(jo.get("value"))), null);		//得到方法
					Object obj = m.invoke(article, null);								//调用方法后的返回值
					if(obj != null) {
						if(obj.getClass().getName().equals("java.sql.Timestamp")) {			//类型表示是时间
							tempModel[i] = sdf.format(sdf.parse(String.valueOf(obj)));
						} else {
							tempModel[i] = String.valueOf(obj);									//默认情况都是文本
						}
					} else {
						tempModel[i] = String.valueOf("");
					}
				}
				dataList.add(tempModel);														//添加数据
			}
			response.setHeader("Content-Disposition", "attachment; filename=exportData.xls");
			ExcelUtil.WriteExcel(request.getSession().getServletContext().getRealPath("/template/commonxls/common.xls"),0,dataList,response.getOutputStream());
			System.gc();																		//启动jvm垃圾回收
		}
		return null;
	}

	/**
	 * 弹出导入数据的页面
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="openImport",privName="弹出导入",roleType="PRIV")
	public String openArticleImport() throws Exception {
		return "import";
	}

	/**
	 * 数据导入
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="importData",privName="数据导入",roleType="PRIV")
	public String importData() throws Exception {
		Map excelMap = (Map)request.getSession().getAttribute("excelMap");					//获取excel文件中的所有数据
		Object[] obj = ExcelMap2Model.excleMap2Model(excelMap, Article.class);
		if(obj != null) {
			boolean flag = (Boolean)obj[0];														//验证标志：true为正确
			if(flag) {																			//表示flag值为true
				List<Article> saveList = (List)obj[1];									//取出保存对象
				if(saveList.size() != 0) {
					articleService.saveDataBatch(saveList);										//批量保存
					request.getSession().setAttribute("saveListSize", saveList.size());
				}
			} else {																			//表示flag值为false
				if(!StringUtil.null2String(((String)obj[3])).equals("") && obj[2] == null) {	//表示不是模板文件
					request.getSession().setAttribute("isNotTemplate", (String)obj[3]);
				} else {																//表示数据错误
					List failedList = (List)obj[2];											//错误消息集合
					if(failedList != null) {
						request.getSession().setAttribute("failedList",failedList);										//设置导入失败的消息集合
					}
				}
			}
			request.getSession().setAttribute("flag", flag);
		}

		request.getSession().setAttribute("totalNum", excelMap.size()-1);				//导入的总记录数
		request.getSession().setAttribute("returnUrl", request.getContextPath()+"/article/searchArticle.do");
		request.getSession().removeAttribute("excelMap");								//移除excelMap数据
		response.getWriter().print("<script language='javascript'>parent.location.href='" + request.getContextPath() + "/commonjsp/excelImportResult.jsp'</script>");		//让父窗口刷新
		return null;
	}

	/**
	 * 通过JSON串保存数据
	 * @return
	 * @throws Exception
	 */
	@Security
	public String saveArticle4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	try {
	 		article = articleService.saveArticle(article);
	 		code = 1;
	 		jsonData = JSONUtil.object2JSONObject(article).toString();
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		String mess = "json保存数据方式出错:"+e.getMessage();
	 		LoggerUtil.info(getClass(), mess);
	 		code = 0;
	 		jsonData = mess;
	 	} finally {
	 		root.put("code", code);
	 		root.put("jsonData", jsonData);
	 		response.getWriter().print(root.toString());
	 		return null;
	 	}
	 }

	/**
	 * 通过id编辑某数据
	 * @return
	 * @throws Exception
	 */
	@Security
	public String editArticle4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	try {
	 		String id = request.getParameter("id");
	 		article = articleService.getArticleById(id);
	 		if(article != null) {
	 			jsonData = JSONUtil.object2JSONObject(article).toString();
	 			code = 1;
	 		} else {
	 			jsonData = "没有查询到id值为"+id+"的记录";
	 			code = 0;
	 		}
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 		LoggerUtil.info(getClass(), "json获取某记录方式出错:"+e.getMessage());
	 		jsonData = "json获取某记录方式出错:"+e.getMessage();
	 		code = 0;
	 	} finally {
	 		root.put("code", code);
	 		root.put("jsonData", jsonData);
	 		response.getWriter().print(root.toString());
	 		return null;
	 	}
	 }

	/**
	 * 通过ids批量删除数据
	 * @return
	 * @throws Exception
	 */
	@Security
	public String delArticleByIds4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	String ids = request.getParameter("ids");
	 	try {
	 		String[] ids_arr = ids.split(",");
	 		List idList = new ArrayList();
	 		for(String id : ids_arr) {
	 			idList.add(id);	
	 		}
	 		articleService.delArticleBatch(idList);												//批量删除
	 		code = 1;																		//返回代码
	 		jsonData = "删除成功";															//返回信息
	 	} catch (Exception e) {
	 		String mesg = "批量删除数据错误，消息为："+e.getMessage();
	 		code = 99;
	 		LoggerUtil.error(getClass(), mesg);
	 		jsonData = mesg;
	 	} finally {
	 		root.put("code", code);
	 		root.put("jsonData", jsonData);
	 		response.getWriter().print(root.toString());
	 		return null;
	 	}
	 }

	/**
	 * 列表数据查询展现
	 * @return
	 * @throws Exception
	 */
	@Security
	public String searchArticle4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	String ids = request.getParameter("ids");
	 	try {
	 		StringBuffer sqlWhere = new StringBuffer("");
	 		String p = request.getParameter("p");											//分页参数页码参数名

	 		//查询过滤-start
	 		String isSearch = StringUtil.null2String(request.getParameter("isSearch"));		//查询标志
	 		if(isSearch.equals("yes")) {													//表示是查询
	 		} else {
	 		}
	 		//查询过滤-end

	 		//当前索引页
	 		final Long pageIndex = new Long(GenericValidator.isBlankOrNull(p) ? 1 : (Long.parseLong(p)));
	 		Map map = articleService.searchArticle(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());
	 		List list = (List)map.get("result");
	 		if(list.size() != 0) {
	 			jsonData = JSONUtil.list2JSONArray(list).toString();
	 			code = 1;
	 		} else {
	 			jsonData = "没有查到数据";
	 			code = 0;
	 		}
	 	} catch (Exception e) {
			e.printStackTrace();
	 		LoggerUtil.error(getClass(), "获取列表数据错误:" + e.getMessage());
	 		jsonData = "获取列表数据错误:" + e.getMessage();
	 		code = 99;
	 	} finally {
	 		root.put("code", code);
	 		root.put("jsonData", jsonData);
	 		response.getWriter().print(root.toString());
	 		return null;
	 	}
	}

}
