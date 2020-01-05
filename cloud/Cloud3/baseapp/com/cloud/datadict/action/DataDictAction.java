package com.cloud.datadict.action;

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
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Controller;

import com.cloud.base.action.BaseAction;
import com.cloud.base.annotation.propertydesc.PropertyDesc;
import com.cloud.base.annotation.security.Security;
import com.cloud.base.importexcel.ExcelMap2Model;
import com.cloud.base.util.ExcelUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.datadict.model.DataDict;
import com.cloud.datadict.service.IDataDictService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author cloudwork
 * 创建时间：2013-11-15 11:37:15
 */
@Controller
public class DataDictAction extends BaseAction implements ModelDriven<Object>{

	private Long PAGESIZE_CONSTANT = 10l;//显示记录数

	@Resource
	private IDataDictService dataDictService;

	/**
	 * 将form转成model
	 */
	private DataDict dataDict;
	public DataDict getModel() {
		if(dataDict == null) {
			dataDict = new DataDict();
		}
		return dataDict;
	}

	/**
	 * 添加
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="add",privName="添加",roleType="PRIV")
	public String addDataDict() throws Exception {
		return "edit";
	}

	/**
	 * 查看详细
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="detail",privName="详细")
	public String detailDataDict() throws Exception {
		String id = request.getParameter("id");
		dataDict = dataDictService.getDataDictById(id);
		request.setAttribute("dataDict", dataDict);
		return "detail";
	}

	/**
	 * 编辑
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="edit",privName="编辑",roleType="PRIV")
	public String editDataDict() throws Exception {
		String id = request.getParameter("id");
		dataDict = dataDictService.getDataDictById(id);
		request.setAttribute("dataDict", dataDict);
		return "edit";
	}

	/**
	 * 删除
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="del",privName="删除",roleType="PRIV")
	public String delDataDictById() throws IOException {
		String id = request.getParameter("id");
		try {
			dataDict.setId(id);
			dataDictService.delDataDict(dataDict);
		} catch (Exception e) {
			String mesg = "删除数据错误，消息为："+e.getMessage();
			LoggerUtil.error(getClass(), mesg);
		}
		return "redirectSuccess";
	}
	/**
	 * 批量删除
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="delBatch",privName="批量删除",roleType="PRIV")
	public String delDataDictByIds() throws IOException {
		String ids = request.getParameter("ids");
		try {
			String[] ids_arr = ids.split(",");
			List idList = new ArrayList();
			for(String id : ids_arr) {
				idList.add(id);
			}
			dataDictService.delDataDictBatch(idList);
		} catch (Exception e) {
			String mesg = "批量删除数据错误，消息为："+e.getMessage();
			LoggerUtil.error(getClass(), mesg);
		}
		return "redirectSuccess";
	}

	/**
	 * 保存
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="save",privName="保存",roleType="PRIV")
	public String saveDataDict() throws Exception {
		dataDictService.saveDataDict(dataDict);
		return "redirectSuccess";														//重定向至列表，避免页面再次刷新弹出页面对话框
	}

	/**
	 * 跳转成功页面
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="success",privName="跳转成功")
	public String success() throws Exception {
		request.setAttribute("listUrl", request.getContextPath() + "/datadict/searchDataDict.do");
		return "success";
	}

	/**
	 * 获取列表中的数据
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="search",privName="列表查询",roleType="PRIV")
	public String searchDataDict() throws Exception {		
		StringBuffer sqlWhere = new StringBuffer("");										//查询条件where
		String pageIndexName = new org.displaytag.util.ParamEncoder("dataDictList").		//dataDictList为jsp页面中<display:table> 的id值
				encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		//查询过滤-start
		String isSearch = StringUtil.null2String(request.getParameter("isSearch"));		//查询标志
		if(isSearch.equals("yes")) {													//表示是查询
			if(!StringUtil.null2String(dataDict.getDictValue()).equals("")) {
				sqlWhere.append(" and dataDict.dictValue='" + dataDict.getDictValue() + "'");
			}
			if(!StringUtil.null2String(dataDict.getDictLabel()).equals("")) {
				sqlWhere.append(" and dataDict.dictLabel='" + dataDict.getDictLabel() + "'");
			}
			if(!StringUtil.null2String(dataDict.getDictType()).equals("")) {
				sqlWhere.append(" and dataDict.dictType='" + dataDict.getDictType() + "'");
			}
			if(!StringUtil.null2String(dataDict.getModuleName()).equals("")) {
				sqlWhere.append(" and dataDict.moduleName='" + dataDict.getModuleName() + "'");
			}
			if(!StringUtil.null2String(dataDict.getProperty()).equals("")) {
				sqlWhere.append(" and dataDict.property='" + dataDict.getProperty() + "'");
			}
			request.setAttribute("dataDict", dataDict);
		} else {
			request.setAttribute("dataDict", null);
		}
		sqlWhere.append(" order by dataDict.moduleName");

		//查询过滤-end

		//当前索引页
		final Long pageIndex = new Long(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 1 : (Long.parseLong(request.getParameter(pageIndexName))));
		Map map = dataDictService.searchDataDict(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());

		request.setAttribute("list", map.get("result"));								//显示结果list
		request.setAttribute("pageSize", PAGESIZE_CONSTANT);							//每页显示的记录数，默认10条
		request.setAttribute("resultSize", map.get("total"));							//所有结果总个数
		return "list";
	}

	/**
	 * 弹出导出数据的页面
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="openExport",privName="弹出导出",roleType="PRIV")
	public String openDataDictExport() throws Exception {
		String ids = request.getParameter("ids");
		Map fieldMap = new TreeMap();
		Field[] fieldArr = dataDict.getClass().getDeclaredFields();							//得到定义的属性
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
	 * * @throws Exception
	 */
	@Security(privKey="exportData",privName="导出数据",roleType="PRIV")
	public String exportData() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				//日期类型格式转换默认是这个格式
		String columns = StringUtil.str2URLDecoder(request.getParameter("columns"));		//得到导出所选的列
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
				where.append(" and dataDict.id in (" + inIds + ")");
			}
		}

		where.append("");																	//排序方式 order by user.createTime asc
		//过滤条件结束

		if(!StringUtil.null2String(columns).equals("")) {
			JSONArray ja = JSONArray.fromObject("[" + columns + "]");					//得到json数组
			List<DataDict> list = dataDictService.getAllDataByWhere(where.toString());			//得到要导出的数据
			List dataList = new ArrayList();												//封装成dataList数据

			Object[] tempTitle = new Object[ja.size()];										//表头
			for(int i=0;i<ja.size();i++) {
				JSONObject jo = (JSONObject)ja.get(i);
				tempTitle[i] = String.valueOf(jo.get("name"));
			}
			dataList.add(tempTitle);														//添加表头

			for(DataDict dataDict : list) {
				Object[] tempModel = new Object[ja.size()];									//临时的model
				for(int i=0;i<ja.size();i++) {												//遍历所有选中的列内容
					JSONObject jo = (JSONObject)ja.get(i);
					Method m = dataDict.getClass().getMethod("get"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(jo.get("value"))), null);		//得到方法
					Object obj = m.invoke(dataDict, null);								//调用方法后的返回值
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
	 * * @throws Exception
	 */
	@Security(privKey="openImport",privName="弹出导入",roleType="PRIV")
	public String openDataDictImport() throws Exception {
		return "import";
	}

	/**
	 * 数据导入
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="importData",privName="数据导入",roleType="PRIV")
	public String importData() throws Exception {
		Map excelMap = (Map)request.getSession().getAttribute("excelMap");					//获取excel文件中的所有数据
		Object[] obj = ExcelMap2Model.excleMap2Model(excelMap, DataDict.class);
		if(obj != null) {
			boolean flag = (Boolean)obj[0];														//验证标志：true为正确
			if(flag) {																			//表示flag值为true
				List<DataDict> saveList = (List)obj[1];									//取出保存对象
				if(saveList.size() != 0) {
					dataDictService.saveDataBatch(saveList);										//批量保存
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
		request.getSession().setAttribute("returnUrl", request.getContextPath()+"/datadict/searchDataDict.do");
		request.getSession().removeAttribute("excelMap");								//移除excelMap数据
		response.getWriter().print("<script language='javascript'>parent.location.href='" + request.getContextPath() + "/commonjsp/excelImportResult.jsp'</script>");		//让父窗口刷新
		return null;
	}

	/**
	 * 通过JSON串保存数据
	 * @return
	 * * @throws Exception
	 */
	@Security
	 public String saveDataDict4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	try {
	 		jsonData = StringUtil.null2String(request.getParameter("jsonData"));
	 		dataDict = (DataDict)JSONUtil.json2Object(jsonData, DataDict.class.getName());
	 		dataDict = dataDictService.saveDataDict(dataDict);
	 		code = 1;
	 		jsonData = JSONUtil.object2JSONObject(dataDict).toString();
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
	 * * @throws Exception
	 */
	@Security
	 public String editDataDict4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	try {
	 		String id = request.getParameter("id");
	 		dataDict = dataDictService.getDataDictById(id);
	 		jsonData = JSONUtil.object2JSONObject(dataDict).toString();
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
	 * * @throws Exception
	 */
	@Security
	 public String delDataDictByIds4Json() throws Exception {
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
	 		dataDictService.delDataDictBatch(idList);												//批量删除
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
	 * * @throws Exception
	 */
	@Security
	 public String searchDataDict4Json() throws Exception {
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
	 		Map map = dataDictService.searchDataDict(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());
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
	 
	 /**
	  * 检测重复数据			
	  * @return
	  * @throws Exception
	  */
	@Security
	public String checkRepeatData() throws Exception {
		String dictValue = request.getParameter("dictValue").trim();
		String dictType = request.getParameter("dictType").trim();
		String moduleName = request.getParameter("moduleName").trim();
		String property = request.getParameter("property").trim();
		dataDict.setDictValue(dictValue);
		dataDict.setDictType(dictType);
		dataDict.setModuleName(moduleName);
		dataDict.setProperty(property);
		DataDict dd = dataDictService.checkRepeatData(dataDict);
		response.getWriter().print(StringUtil.null2String(dd.getId()));
		return null;
	}
	 
	 /**
	  * 得到字典的显示值
	  * @return
	  * @throws Exception
	  */
	@Security
	public String getDictLabel() throws Exception {
		String dictValue = request.getParameter("dictValue");
		String dictType = request.getParameter("dictType");
		String moduleName = request.getParameter("moduleName");
		String property = request.getParameter("property");
		
		String where = " and dataDict.dictValue='" +dictValue+ "' and dataDict.dictType='"+dictType
				+"' and dataDict.moduleName='"+moduleName+"' and dataDict.property='"+property+"'";
		
		List list = dataDictService.getAllDataByWhere(where);
		if(list.size() != 0) {
			DataDict dd = (DataDict)list.get(0);
			if(dd.getDictLabel() != null) {
				response.getWriter().print(dd.getDictLabel());
			}			
		} else {
			response.getWriter().print("<font color='red'>字典类型为：" + dictType + "，属性为："+property+"，模块名称为："+moduleName+"没有在数据字典中配置</font>");
		}
		return null;
	}

}
