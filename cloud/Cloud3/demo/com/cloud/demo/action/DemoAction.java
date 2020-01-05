package com.cloud.demo.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.persistence.Transient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;

import com.cloud.base.action.BaseAction;
import com.cloud.base.annotation.propertydesc.PropertyDesc;
import com.cloud.base.annotation.security.Security;
import com.cloud.base.importexcel.ExcelMap2Model;
import com.cloud.base.test.other.HttpPostArgumentTest2;
import com.cloud.base.util.DBFM;
import com.cloud.base.util.ExcelUtil;
import com.cloud.base.util.FileUtil;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.datadict.util.DataDictUtil;
import com.cloud.demo.model.Demo;
import com.cloud.demo.service.IDemoService;
import com.opensymphony.xwork2.ModelDriven;


/**
 * 
 * @author 崔云鹏
 *
 */
@SuppressWarnings("finally")
@Controller
public class DemoAction extends BaseAction implements ModelDriven<Object>{

	private Long PAGESIZE_CONSTANT = 10l;//显示记录数
	
	@Resource
	private IDemoService demoService;
	
	/**
	 * 将form转成model
	 */
	private Demo demo;
	public Demo getModel() {
		if(demo == null) {
			demo = new Demo();
		}
		return demo;
	}
	
	private File filedata;
	
	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	/**
	 * 添加
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="add",privName="添加",roleType="PRIV")
	public String addDemo() throws Exception {
		return "edit";
	}
	
	/**
	 * 查看详细
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="detail",privName="详细")
	public String detailDemo() throws Exception {
		String id = request.getParameter("id");					// 数据的唯一标识
		demo = demoService.getDemoById(id);						// 通过id查询某记录
		request.setAttribute("demo", demo);						// 向页面回传对象						
		return "detail";
	}
	
	/**
	 * 编辑
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="edit",privName="编辑",roleType="PRIV")
	public String editDemo() throws Exception {
		String id = request.getParameter("id");					// 数据的唯一标识
		demo = demoService.getDemoById(id);						// 通过id查询某记录
		request.setAttribute("demo", demo);						// 向页面回传对象
		return "edit"; 
	}
	
	/**
	 * 删除
	 * @return
	 * @throws IOException
	 */
	@Security(privKey="del",privName="删除",roleType="PRIV")
	public String delDemoById() throws Exception {
		String id = request.getParameter("id");
		try {
			demo.setId(id);
			demoService.delDemo(demo);
		} catch (Exception e) {
			String mesg = "删除数据错误，消息为："+e.getMessage();
			LoggerUtil.error(getClass(), mesg);
		} 		
		return "redirectSuccess";
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws IOException
	 */
	@Security(privKey="delBatch",privName="批量删除",roleType="PRIV")
	public String delDemoByIds() throws Exception {
		String ids = request.getParameter("ids");
		try {
			String[] ids_arr = ids.split(",");
			List idList = new ArrayList(); 
			for(String id : ids_arr) {
				idList.add(id);				
			}
			demoService.delDemoBatch(idList);											//批量删除
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
	public String saveDemo() throws Exception {
		demoService.saveDemo(demo);
		return "redirectSuccess";														//重定向成功页面
	}
	
	/**
	 * 跳转成功页面
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="success",privName="跳转成功")
	public String success() throws Exception {
		request.setAttribute("listUrl", request.getContextPath() + "/demo/searchDemo.do");
		return "success";
	}
	
	/**
	 * 列表数据查询展现
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="search",privName="列表查询",roleType="PRIV")
	public String searchDemo() throws Exception {
		
		StringBuffer sqlWhere = new StringBuffer("");
		String pageIndexName = new org.displaytag.util.ParamEncoder("demoList").		//demoList为jsp页面中<display:table> 的id值
				encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);
		
		//查询过滤-start
		String isSearch = StringUtil.null2String(request.getParameter("isSearch"));		//查询标志
		if(isSearch.equals("yes")) {													//表示是查询
			if(!StringUtil.null2String(demo.getName()).equals("")) {
				sqlWhere.append(" and demo.name='" + demo.getName() + "'");
			}
			if(demo.getCreateTime() != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sqlWhere.append(" and demo.createTime="+DBFM.TO_DATE()+"('"+sdf.format(demo.getCreateTime())+"','yyyy-mm-dd hh24:mi:ss')");
			}
			request.setAttribute("demo", demo);
		} else {
			request.setAttribute("demo", null);
		}		
		//查询过滤-end
		
		//当前索引页
		final Long pageIndex = new Long(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 1 : (Long.parseLong(request.getParameter(pageIndexName))));
		Map map = demoService.searchDemo(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());
		
		request.setAttribute("list", map.get("result"));								//显示结果list
		request.setAttribute("pageSize", PAGESIZE_CONSTANT);							//每页显示的记录数，默认10条
		request.setAttribute("resultSize", map.get("total"));							//所有结果总个数
		return "list";	
	}
	
	/**
	 * 弹出导出数据的页面
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="openExport",privName="弹出导出",roleType="PRIV")
	public String openDemoExport() throws Exception {
		String ids = request.getParameter("ids");
		Map fieldMap = new TreeMap();
		Field[] fieldArr = demo.getClass().getDeclaredFields();							//得到定义的属性
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				//日期类型格式转换
		String columns = StringUtil.str2URLDecoder(request.getParameter("columns"));	//得到导出所选的列
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
				where.append(" and demo.id in (" + inIds + ")");
			}
		}
		
		String createTimeFrom = StringUtil.null2String(request.getParameter("createTimeFrom"));
		String createTimeTo = StringUtil.null2String(request.getParameter("createTimeTo"));
		if(!createTimeFrom.equals("") && !createTimeTo.equals("")) {
			where.append(" and demo.createTime >= "+DBFM.TO_DATE()+"('" + createTimeFrom + "','yyyy-mm-dd hh24:mi:ss')");//mysql:yyyy-mm-dd hh24:mi:ss
			where.append(" and demo.createTime <= "+DBFM.TO_DATE()+"('" + createTimeTo + "','yyyy-mm-dd hh24:mi:ss')");//mysql:yyyy-mm-dd hh24:mi:ss
		}
		//过滤条件结束
		where.append(" order by demo.createTime asc");									//排序方式
		
		if(!StringUtil.null2String(columns).equals("")) {
			JSONArray ja = JSONArray.fromObject("[" + columns + "]");					//得到json数组
			List<Demo> list = demoService.getAllDataByWhere(where.toString());		//得到要导出的数据	
			
			//为数据中的数据字典转显示值-start(开发人员自行修改)
			for(Demo demoExport : list) {
				if(!demoExport.getSex().equals("")) {
					String sexLabel = DataDictUtil.value2label(demoExport.getSex(), "demo", "radio", "sex");
					demoExport.setSex(sexLabel);
				}
				if(!demoExport.getHobby().equals("")) {
					String hobbyLabel = DataDictUtil.value2label(demoExport.getHobby(), "demo", "checkbox", "hobby");
					demoExport.setHobby(hobbyLabel);
				}
				if(!demoExport.getDepart().equals("")) {
					String departLabel = DataDictUtil.value2label(demoExport.getDepart(), "demo", "select", "depart");
					demoExport.setDepart(departLabel);
				}
				if(!demoExport.getDirection().equals("")) {
					String directionLabel = DataDictUtil.value2label(demoExport.getDirection(), "demo", "select2", "direction");
					demoExport.setDirection(directionLabel);
				}
			}
			//为数据中的数据字典转显示值-end(开发人员自行修改)
			
			List dataList = new ArrayList();											//封装成dataList数据
			
			Object[] tempTitle = new Object[ja.size()];									//表头
			for(int i=0;i<ja.size();i++) {	
				JSONObject jo = (JSONObject)ja.get(i);
				tempTitle[i] = String.valueOf(jo.get("name"));
			}
			dataList.add(tempTitle);													//添加表头
			
			for(Demo demoExport : list) {																					
				Object[] tempModel = new Object[ja.size()];								//临时的model
				for(int i=0;i<ja.size();i++) {											//遍历所有选中的列内容
					JSONObject jo = (JSONObject)ja.get(i);
					Method m = demoExport.getClass().getMethod("get"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(jo.get("value"))), null);		//得到方法
					Object obj = m.invoke(demoExport, null);								//调用方法后的返回值					
					if(obj != null) {
						if(obj.getClass().getName().equals("java.sql.Timestamp")) {			//类型表示是时间
							tempModel[i] = sdf.format(sdf.parse(String.valueOf(obj)));
						} else {															//文本类型
							tempModel[i] = String.valueOf(obj);								//默认情况都是文本
						}
					} else {
						tempModel[i] = String.valueOf("");
					}					
				}
				dataList.add(tempModel);												//添加数据
			}
			response.setHeader("Content-Disposition", "attachment; filename=exportData.xls");
			ExcelUtil.WriteExcel(request.getSession().getServletContext().getRealPath("/template/commonxls/common.xls"),0,dataList,response.getOutputStream());
			System.gc();																//启动jvm垃圾回收
		}
		
		return null;
	}
	
	/**
	 * 弹出导入数据的页面
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="openImport",privName="弹出导入",roleType="PRIV")
	public String openDemoImport() throws Exception {
		return "import";
	}
	
	/**
	 * 数据导入
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="importData",privName="数据导入",roleType="PRIV")
	public String importData() throws Exception {
		Map excelMap = (Map)request.getSession().getAttribute("excelMap");						//获取excel文件中的所有数据
		Object[] obj = ExcelMap2Model.excleMap2Model(excelMap, Demo.class);
		if(obj != null) {
			boolean flag = (Boolean)obj[0];														//验证标志：true为正确
			if(flag) {																			//表示flag值为true
				List<Demo> saveList = (List)obj[1];												//取出保存对象
				if(saveList.size() != 0) {
					demoService.saveDataBatch(saveList);										//批量保存
					request.getSession().setAttribute("saveListSize", saveList.size());							
				}
			} else {																			//表示flag值为false
				if(!StringUtil.null2String(((String)obj[3])).equals("") && obj[2] == null) {	//表示不是模板文件
					request.getSession().setAttribute("isNotTemplate", (String)obj[3]);
				} else {																		//表示数据错误
					List failedList = (List)obj[2];												//错误消息集合
					if(failedList != null) {
						request.getSession().setAttribute("failedList",failedList);				//设置导入失败的消息集合
					}
				}
			}	
			request.getSession().setAttribute("flag", flag);
		}
		
		request.getSession().setAttribute("totalNum", excelMap.size()-1);						//导入的总记录数
		request.getSession().setAttribute("returnUrl", request.getContextPath()+"/demo/searchDemo.do");//返回列表的url
		request.getSession().removeAttribute("excelMap");										//移除excelMap数据
		response.getWriter().print("<script language='javascript'>parent.location.href='" + request.getContextPath() + "/commonjsp/excelImportResult.jsp'</script>");		//让父窗口刷新
		return null;
	}
	
	/**
	 * 通过JSON串保存数据
	 * @return
	 * @throws Exception
	 */
	@Security
	public String saveDemo4Json() throws Exception {
		JSONObject root = new JSONObject();
		int code = 0;																		//默认失败
		String jsonData = "";
		try {
			jsonData = StringUtil.null2String(request.getParameter("jsonData"));
			demo = (Demo)JSONUtil.json2Object(jsonData, Demo.class.getName());
			demo = demoService.saveDemo(demo);
			code = 1;
			jsonData = JSONUtil.object2JSONObject(demo).toString(); 
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
	public String editDemo4Json() throws Exception {
		JSONObject root = new JSONObject();
		int code = 0;																		//默认失败
		String jsonData = "";
		try {
			String id = request.getParameter("id");
			demo = demoService.getDemoById(id);
			jsonData = JSONUtil.object2JSONObject(demo).toString();
			code = 1;
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
	public String delDemoByIds4Json() throws Exception {
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
			demoService.delDemoBatch(idList);												//批量删除
			
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
	public String searchDemo4Json() throws Exception {
		JSONObject root = new JSONObject();
		int code = 0;																		//默认失败
		String jsonData = "";
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
			Map map = demoService.searchDemo(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());
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
		}
		return null;	
	}
	
	/**
	 * 下载word模板例子
	 * @return
	 * @throws Exception
	 */
	@Security
	public String downLoadDoc() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String id = request.getParameter("id"); 
		demo = demoService.getDemoById(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "cloud3的demo统一标题");
		map.put("name", demo.getName());
		String sex = DataDictUtil.value2label(demo.getSex(), "demo", "radio", "sex");
		map.put("sex",sex);
		map.put("createTime", sdf.format(demo.getCreateTime()));
		map.put("age", demo.getAge()+"");
		map.put("contentVal", StringUtil.null2String(demo.getContentVal()));
		String path = request.getSession().getServletContext().getRealPath("/template/demo/demo.doc");
		FileUtil.readwriteWord(response, path, map);		
		return null;
	}
	
	/**
	 * 测试http请求数据
	 * @return
	 * @throws Exception
	 */
	@Security
	public String testHttpDemo() throws Exception {
		String id = request.getParameter("id");
		String urlStr = "http://127.0.0.1:8888/Synjones/demo/editDemo4Json.do";
		HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(urlStr);
        post.addParameter("id", id);
        client.executeMethod(post);
        String content = post.getResponseBodyAsString();
        response.getWriter().print(content);
		return null;
	}
	
	/**
	 * http方式上传文件
	 * @return
	 * @throws Exception
	 */
	@Security
	public String uploadFileTest() throws Exception {
		String fileName = request.getParameter("filename1");
		System.out.println("fileName:"+fileName);
		
		String test = StringUtil.str2URLDecoder(request.getParameter("test"));
		System.out.println("test:"+test);
		
		if (filedata != null) {           
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");							//日期格式
			File savefile = new File(new File("d:/aaa/1/"), sdf.format(new Date())+FileUtil.getExtendName(fileName));	//要上传到服务器上的文件           
			if (!savefile.getParentFile().exists()) {													//检查是否有该路径的文件夹存在，如果没有那么创建文件夹               
				savefile.getParentFile().mkdirs();   
			}         
			FileUtils.copyFile(filedata, savefile);														//上传文件
		}
		return null;
	}
}
