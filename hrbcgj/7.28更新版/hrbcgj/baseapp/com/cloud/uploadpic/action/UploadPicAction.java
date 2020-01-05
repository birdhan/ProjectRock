package com.cloud.uploadpic.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.cloud.base.action.BaseAction;
import com.cloud.base.annotation.propertydesc.PropertyDesc;
import com.cloud.base.annotation.security.Security;
import com.cloud.base.cache.SysCache;
import com.cloud.base.importexcel.ExcelMap2Model;
import com.cloud.base.util.ExcelUtil;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.uploadpic.model.UploadPic;
import com.cloud.uploadpic.service.IUploadPicService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author cuiyunpeng
 * 创建时间：2014-05-07 11:36:23
 */
@SuppressWarnings("finally")
@Controller
public class UploadPicAction extends BaseAction implements ModelDriven<Object>{

	private Long PAGESIZE_CONSTANT = 10l;//显示记录数

	@Resource
	private IUploadPicService uploadPicService;
	
	private File picFile;
	
	public File getPicFile() {
		return picFile;
	}

	public void setPicFile(File picFile) {
		this.picFile = picFile;
	}

	/**
	 * 将form转成model
	 */
	private UploadPic uploadPic;
	public UploadPic getModel() {
		if(uploadPic == null) {
			uploadPic = new UploadPic();
		}
		return uploadPic;
	}

	/**
	 * 添加
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="add",privName="添加",roleType="PRIV")
	public String addUploadPic() throws Exception {
		return "edit";
	}

	/**
	 * 查看详细
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="detail",privName="详细")
	public String detailUploadPic() throws Exception {
		String id = request.getParameter("id");
		uploadPic = uploadPicService.getUploadPicById(id);
		request.setAttribute("uploadPic", uploadPic);
		return "detail";
	}

	/**
	 * 编辑
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="edit",privName="编辑",roleType="PRIV")
	public String editUploadPic() throws Exception {
		String id = request.getParameter("id");
		uploadPic = uploadPicService.getUploadPicById(id);
		request.setAttribute("uploadPic", uploadPic);
		return "edit";
	}

	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="del",privName="删除",roleType="PRIV")
	public String delUploadPicById() throws IOException {
		String id = request.getParameter("id");
		try {
			uploadPic.setId(id);
			uploadPicService.delUploadPic(uploadPic);
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
	public String delUploadPicByIds() throws IOException {
		String ids = request.getParameter("ids");
		try {
			String[] ids_arr = ids.split(",");
			List idList = new ArrayList();
			for(String id : ids_arr) {
				idList.add(id);
			}
			uploadPicService.delUploadPicBatch(idList);
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
	public String saveUploadPic() throws Exception {
		uploadPicService.saveUploadPic(uploadPic);
		return "redirectSuccess";														//重定向至列表，避免页面再次刷新弹出页面对话框
	}

	/**
	 * 跳转成功页面
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="success",privName="跳转成功")
	public String success() throws Exception {
		request.setAttribute("listUrl", request.getContextPath() + "/uploadpic/searchUploadPic.do");
		return "success";
	}

	/**
	 * 获取列表中的数据
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="search",privName="列表查询",roleType="PRIV")
	public String searchUploadPic() throws Exception {
		StringBuffer sqlWhere = new StringBuffer("");										//查询条件where
		String pageIndexName = new org.displaytag.util.ParamEncoder("uploadPicList").		//uploadPicList为jsp页面中<display:table> 的id值
				encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		//查询过滤-start
		String isSearch = StringUtil.null2String(request.getParameter("isSearch"));		//查询标志
		if(isSearch.equals("yes")) {													//表示是查询
			if(!StringUtil.null2String(uploadPic.getOriName()).equals("")) {
				sqlWhere.append(" and uploadPic.oriName='" + uploadPic.getOriName() + "'");
			}
			if(!StringUtil.null2String(uploadPic.getUploadUserId()).equals("")) {
				sqlWhere.append(" and uploadPic.uploadUserId='" + uploadPic.getUploadUserId() + "'");
			}
			request.setAttribute("uploadPic", uploadPic);
		} else {
			request.setAttribute("uploadPic", null);
		}

		//查询过滤-end

		//当前索引页
		final Long pageIndex = new Long(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 1 : (Long.parseLong(request.getParameter(pageIndexName))));
		Map map = uploadPicService.searchUploadPic(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());

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
	public String openUploadPicExport() throws Exception {
		String ids = request.getParameter("ids");
		Map fieldMap = new TreeMap();
		Field[] fieldArr = uploadPic.getClass().getDeclaredFields();							//得到定义的属性
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
				where.append(" and uploadPic.id in (" + inIds + ")");
			}
		}

		where.append("");																	//排序方式 order by user.createTime asc
		//过滤条件结束

		if(!StringUtil.null2String(columns).equals("")) {
			JSONArray ja = JSONArray.fromObject("[" + columns + "]");					//得到json数组
			List<UploadPic> list = uploadPicService.getAllDataByWhere(where.toString());			//得到要导出的数据
			List dataList = new ArrayList();												//封装成dataList数据

			Object[] tempTitle = new Object[ja.size()];										//表头
			for(int i=0;i<ja.size();i++) {
				JSONObject jo = (JSONObject)ja.get(i);
				tempTitle[i] = String.valueOf(jo.get("name"));
			}
			dataList.add(tempTitle);														//添加表头

			for(UploadPic uploadPic : list) {
				Object[] tempModel = new Object[ja.size()];									//临时的model
				for(int i=0;i<ja.size();i++) {												//遍历所有选中的列内容
					JSONObject jo = (JSONObject)ja.get(i);
					Method m = uploadPic.getClass().getMethod("get"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(jo.get("value"))), null);		//得到方法
					Object obj = m.invoke(uploadPic, null);								//调用方法后的返回值
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
	public String openUploadPicImport() throws Exception {
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
		Object[] obj = ExcelMap2Model.excleMap2Model(excelMap, UploadPic.class);
		if(obj != null) {
			boolean flag = (Boolean)obj[0];														//验证标志：true为正确
			if(flag) {																			//表示flag值为true
				List<UploadPic> saveList = (List)obj[1];									//取出保存对象
				if(saveList.size() != 0) {
					uploadPicService.saveDataBatch(saveList);										//批量保存
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
		request.getSession().setAttribute("returnUrl", request.getContextPath()+"/uploadpic/searchUploadPic.do");
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
	public String saveUploadPic4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	try {
	 		jsonData = StringUtil.null2String(request.getParameter("jsonData"));
	 		uploadPic = (UploadPic)JSONUtil.json2Object(jsonData, UploadPic.class.getName());
	 		uploadPic = uploadPicService.saveUploadPic(uploadPic);
	 		code = 1;
	 		jsonData = JSONUtil.object2JSONObject(uploadPic).toString();
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
	public String editUploadPic4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	try {
	 		String id = request.getParameter("id");
	 		uploadPic = uploadPicService.getUploadPicById(id);
	 		jsonData = JSONUtil.object2JSONObject(uploadPic).toString();
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
	public String delUploadPicByIds4Json() throws Exception {
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
	 		uploadPicService.delUploadPicBatch(idList);												//批量删除
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
	public String searchUploadPic4Json() throws Exception {
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
	 		Map map = uploadPicService.searchUploadPic(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());
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
	 * 跳转上传图片页面窗口
	 * @return
	 * @throws Exception
	 */
	@Security
	public String toUploadPic() throws Exception {
		String imgId = request.getParameter("imgId");					//图片标签的属性id
		request.setAttribute("imgId", imgId);
		
		String closeWinMethod = request.getParameter("closeWinMethod");	//关闭窗口的js方法名称
		request.setAttribute("closeWinMethod", closeWinMethod);
		
		String picFileId = request.getParameter("picFileId");
		if(!picFileId.trim().equals("")) {
			uploadPic = uploadPicService.getUploadPicById(picFileId);
			request.setAttribute("uploadPic", uploadPic);
		}
				
		return "edit";
	}
	
	/**
	 * 上传所选择的图片
	 * @return
	 * @throws Exception
	 */
	@Security
	public String uploadPicFile() throws Exception {
		
		String oriName = request.getParameter("oriName");								//原文件名
		
		String id = StringUtil.null2String(uploadPic.getId());							//得到文件id值
		if(!id.equals("")) {															//如果不为空表示之前有上传过文件，那么需要先删除之前的文件
			uploadPic = uploadPicService.getUploadPicById(id);
			File delFile = new File(uploadPic.getSavePath() + "/" + uploadPic.getSerName());
			if(delFile.exists()) {
				delFile.delete();
			}
		}
		
		/**
		 * 上传文件步骤
		 */
		String exeName = oriName.substring(oriName.lastIndexOf("."));					//.扩展名
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");				//日期格式
		String serName = sdf.format(new Date()) + exeName;								//服务器文件名
		String savePath = SysCache.getInstance().getSystemConfig().getPicSavePath();	//文件保存路径
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");						//日期格式
		savePath = savePath + "/" + sdf1.format(new Date());
		File savefile = new File(new File(savePath), serName);							//要上传到服务器上的文件
		if (!savefile.getParentFile().exists()) {										//检查是否有该路径的文件夹存在，如果没有那么创建文件夹               
			savefile.getParentFile().mkdirs();   
		}
		FileUtils.copyFile(picFile, savefile);											//上传文件
		
		/**
		 * 保存数据步骤
		 */
		uploadPic.setOriName(oriName);
		uploadPic.setSerName(serName);
		uploadPic.setSavePath(savePath);
		uploadPic.setUploadTime(new Date());
		uploadPic.setUploadUserId(getCurUser().getUserId());				
		
		uploadPic = uploadPicService.saveUploadPic(uploadPic);
		String saveId = uploadPic.getId();																//得到保存后的id值
		
		/**
		 * js逻辑
		 * 1.回显上传后的图片
		 * 2.关闭窗口
		 */
		String closeWinMethod = request.getParameter("closeWinMethod");					//执行关闭窗口的方法名
		String imgId = request.getParameter("imgId");
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>parent.showPic('"+saveId+"','"+imgId+"');parent."+closeWinMethod+"();</script>");
		out.close();
		out.flush();
		return null;
	}
	
	/**
	 * 读取图片
	 * @return
	 * @throws Exception
	 */
	@Security
	public String getPic() throws Exception {
		String id = request.getParameter("id");
		uploadPic = uploadPicService.getUploadPicById(id);
		
		File showFile = null;
		if(uploadPic != null) {
			showFile = new File(uploadPic.getSavePath() + "/" + uploadPic.getSerName());
		} else {
			showFile = new File(request.getRealPath("") + "/images/uppic.jpg");
		}
		
		InputStream in = new FileInputStream(showFile);
		OutputStream os = response.getOutputStream();
    	
		if(in != null && os != null) {
			byte[] b = new byte[1024];
			int len;
			while ((len = in.read(b)) > 0){
				os.write(b, 0, len);
			}
			in.close();
			os.flush();
			os.close();
		}		
		return null;
	}
	
	/**
	 * 清除图片
	 * @return
	 * @throws Exception
	 */
	@Security
	public String clearPic() throws Exception {
		String id = request.getParameter("id");
		if(!id.equals("")) {
			uploadPic = uploadPicService.getUploadPicById(id);
			if(uploadPic != null) {
				File delFile = new File(uploadPic.getSavePath() + "/" + uploadPic.getSerName());
				if(delFile.exists()) {
					delFile.delete();
				}
				uploadPicService.delUploadPic(uploadPic);
			}			
		}		
		return null;
	}
	
	@Security
	public String delPicFile() throws Exception {
		String picFileId = request.getParameter("picFileId");
		if(!picFileId.equals("")) {
			uploadPic = uploadPicService.getUploadPicById(picFileId);
			if(uploadPic != null) {
				File delFile = new File(uploadPic.getSavePath() + "/" + uploadPic.getSerName());
				if(delFile.exists()) {
					delFile.delete();
				}
				uploadPicService.delUploadPic(uploadPic);
			}			
		}
		
		String imgId = request.getParameter("imgId");
		String closeWinMethod = request.getParameter("closeWinMethod");
		
		PrintWriter out = response.getWriter();
		out.print("<script language='javascript'>parent.showNoPicFile('"+imgId+"');parent."+closeWinMethod+"();</script>");
		out.close();
		out.flush();
		
		return null;
	}

}
