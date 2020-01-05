package com.cloud.schedulemanager.action;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.cloud.base.listener.ScheduleListener;
import com.cloud.base.util.ExcelUtil;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.schedulemanager.model.Schedule;
import com.cloud.schedulemanager.service.IScheduleService;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author cloudwork
 * 创建时间：2013-11-15 13:52:33
 */
@SuppressWarnings("finally")
@Controller
public class ScheduleAction extends BaseAction implements ModelDriven<Object>{

	private Long PAGESIZE_CONSTANT = 10l;//显示记录数

	@Resource
	private IScheduleService scheduleService;
	
	ScheduleListener scheduleListener = new ScheduleListener();

	/**
	 * 将form转成model
	 */
	private Schedule schedule;
	public Schedule getModel() {
		if(schedule == null) {
			schedule = new Schedule();
		}
		return schedule;
	}

	/**
	 * 添加
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="add",privName="添加",roleType="PRIV")
	public String addSchedule() throws Exception {
		return "edit";
	}

	/**
	 * 查看详细
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="detail",privName="详细")
	public String detailSchedule() throws Exception {
		String id = request.getParameter("id");
		schedule = scheduleService.getScheduleById(id);
		request.setAttribute("schedule", schedule);
		return "detail";
	}

	/**
	 * 编辑
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="edit",privName="编辑",roleType="PRIV")
	public String editSchedule() throws Exception {
		String id = request.getParameter("id");
		schedule = scheduleService.getScheduleById(id);
		request.setAttribute("schedule", schedule);
		return "edit";
	}

	/**
	 * 删除
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="del",privName="删除",roleType="PRIV")
	public String delScheduleById() throws IOException {
		String id = request.getParameter("id");
		try {
			schedule.setId(id);
			scheduleService.delSchedule(schedule);
			
			Schedule delSchedule = new Schedule();
			delSchedule.setId(id);
			scheduleService.delSchedule(delSchedule);
			scheduleListener.deleteJob(delSchedule.getId());
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
	public String delScheduleByIds() throws IOException {
		String ids = request.getParameter("ids");
		try {
			String[] ids_arr = ids.split(",");
			List idList = new ArrayList();
			for(String id : ids_arr) {
				idList.add(id);
				
				Schedule delSchedule = new Schedule();
				delSchedule.setId(id);
				scheduleService.delSchedule(delSchedule);
				scheduleListener.deleteJob(delSchedule.getId());
			}
			scheduleService.delScheduleBatch(idList);
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
	public String saveSchedule() throws Exception {
		scheduleService.saveSchedule(schedule);
		if(StringUtil.null2String(schedule.getId()).equals("")) {						//表示是添加，即新增的
			scheduleListener.addJob(schedule);											//将任务添加到任务列表中
		} else {																		//表示有id，即修改，修改包括两种可能，一个是关闭，一个是修改执行时间等
			if(schedule.getStatus().equals("1")) {										//表示修改，开启
				scheduleListener.modifyJob(schedule);									//修改
			} else {																	//表示关闭
				scheduleListener.deleteJob(schedule.getId());
			}
		}
		return "redirectSuccess";														//重定向至列表，避免页面再次刷新弹出页面对话框
	}

	/**
	 * 跳转成功页面
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="success",privName="跳转成功")
	public String success() throws Exception {
		request.setAttribute("listUrl", request.getContextPath() + "/schedulemanager/searchSchedule.do");
		return "success";
	}

	/**
	 * 获取列表中的数据
	 * @return
	 * * @throws Exception
	 */
	@Security(privKey="search",privName="列表查询",roleType="PRIV")
	public String searchSchedule() throws Exception {
		StringBuffer sqlWhere = new StringBuffer("");										//查询条件where
		String pageIndexName = new org.displaytag.util.ParamEncoder("scheduleList").		//scheduleList为jsp页面中<display:table> 的id值
				encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		//查询过滤-start
		String isSearch = StringUtil.null2String(request.getParameter("isSearch"));		//查询标志
		if(isSearch.equals("yes")) {													//表示是查询
			if(!StringUtil.null2String(schedule.getClassType()).equals("")) {
				sqlWhere.append(" and schedule.classType='" + schedule.getClassType() + "'");
			}
			if(!StringUtil.null2String(schedule.getStatus()).equals("")) {
				sqlWhere.append(" and schedule.status='" + schedule.getStatus() + "'");
			}
			if(!StringUtil.null2String(schedule.getRemark()).equals("")) {
				sqlWhere.append(" and schedule.remark like '%" + schedule.getRemark() + "%'");
			}
			request.setAttribute("schedule", schedule);
		} else {
			request.setAttribute("schedule", null);
		}

		//查询过滤-end

		//当前索引页
		final Long pageIndex = new Long(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 1 : (Long.parseLong(request.getParameter(pageIndexName))));
		Map map = scheduleService.searchSchedule(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());

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
	public String openScheduleExport() throws Exception {
		String ids = request.getParameter("ids");
		Map fieldMap = new TreeMap();
		Field[] fieldArr = schedule.getClass().getDeclaredFields();							//得到定义的属性
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
				where.append(" and schedule.id in (" + inIds + ")");
			}
		}

		where.append("");																	//排序方式 order by user.createTime asc
		//过滤条件结束

		if(!StringUtil.null2String(columns).equals("")) {
			JSONArray ja = JSONArray.fromObject("[" + columns + "]");					//得到json数组
			List<Schedule> list = scheduleService.getAllDataByWhere(where.toString());			//得到要导出的数据
			List dataList = new ArrayList();												//封装成dataList数据

			Object[] tempTitle = new Object[ja.size()];										//表头
			for(int i=0;i<ja.size();i++) {
				JSONObject jo = (JSONObject)ja.get(i);
				tempTitle[i] = String.valueOf(jo.get("name"));
			}
			dataList.add(tempTitle);														//添加表头

			for(Schedule schedule : list) {
				Object[] tempModel = new Object[ja.size()];									//临时的model
				for(int i=0;i<ja.size();i++) {												//遍历所有选中的列内容
					JSONObject jo = (JSONObject)ja.get(i);
					Method m = schedule.getClass().getMethod("get"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(jo.get("value"))), null);		//得到方法
					Object obj = m.invoke(schedule, null);								//调用方法后的返回值
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
	public String openScheduleImport() throws Exception {
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
		Object[] obj = ExcelMap2Model.excleMap2Model(excelMap, Schedule.class);
		if(obj != null) {
			boolean flag = (Boolean)obj[0];														//验证标志：true为正确
			if(flag) {																			//表示flag值为true
				List<Schedule> saveList = (List)obj[1];									//取出保存对象
				if(saveList.size() != 0) {
					scheduleService.saveDataBatch(saveList);										//批量保存
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
		request.getSession().setAttribute("returnUrl", request.getContextPath()+"/schedulemanager/searchSchedule.do");
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
	 public String saveSchedule4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	try {
	 		jsonData = StringUtil.null2String(request.getParameter("jsonData"));
	 		schedule = (Schedule)JSONUtil.json2Object(jsonData, Schedule.class.getName());
	 		schedule = scheduleService.saveSchedule(schedule);
	 		code = 1;
	 		jsonData = JSONUtil.object2JSONObject(schedule).toString();
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
	 public String editSchedule4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	try {
	 		String id = request.getParameter("id");
	 		schedule = scheduleService.getScheduleById(id);
	 		jsonData = JSONUtil.object2JSONObject(schedule).toString();
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
	 public String delScheduleByIds4Json() throws Exception {
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
	 		scheduleService.delScheduleBatch(idList);												//批量删除
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
	 public String searchSchedule4Json() throws Exception {
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
	 		Map map = scheduleService.searchSchedule(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());
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
	  * 检查是否存在要添加的类
	  * @return
	  * @throws Exception
	  */
	@Security
	 public String checkClassIsExist() throws Exception {
		 String checkClass = request.getParameter("checkClass");
		 String result = "";
		 PrintWriter out = null;
		 try {
			 out = response.getWriter();
			 Class.forName(checkClass);
			
			 if(result.equals("")) {														//表示存在该类，那么再判断数据库是是否存在重复类名
				 String id = request.getParameter("id");
				 List<Schedule> list = scheduleService.getAllDataByWhere(" and schedule.classType='" + checkClass + "'");
				 if(list.size() != 0) {													//表示数据库中已经存在当前输入的类了
					 if(!list.get(0).getId().equals(id)) {								//当前的id和数据库里是相同的不算重复
						 result = "当前录入的类已经存在，建议不要重复录入";
					 } 
				 }
			 }
		 } catch (ClassNotFoundException e) {
			 result = "没有找到当前输入的类";
		 } catch (IOException e) {
			 result = "异常信息："+e.getMessage();
			 e.printStackTrace();
		 } finally {
			 out.print(result);
		 }
		 return null;
	 }

	/**
	 * 立刻执行
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	@Security
	public String executeNow() throws IOException {
		String classType = request.getParameter("classType");
		String mess = "";
		try {
			Class clazz = Class.forName(classType);
			Object obj = clazz.newInstance();
			Method method = clazz.getMethod("executeJob",null);
			
			if(method != null) {															//表示已找到该方法
				method.invoke(obj, null);
				mess = "执行成功";
			} else {																		//表示没有找到该方法
				mess = "在该类下没有找到executeJob方法";
			}
		} catch (Exception e) {
			e.printStackTrace();
			mess = "在该类下没有找到executeJob方法";
		} finally {
			response.getWriter().print(mess);
		}
		return null;
	}			

}
