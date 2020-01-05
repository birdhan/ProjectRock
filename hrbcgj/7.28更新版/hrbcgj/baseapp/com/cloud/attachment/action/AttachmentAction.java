package com.cloud.attachment.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.persistence.Transient;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Controller;

import com.cloud.attachment.model.Attachment;
import com.cloud.attachment.service.IAttachmentService;
import com.cloud.base.action.BaseAction;
import com.cloud.base.annotation.propertydesc.PropertyDesc;
import com.cloud.base.annotation.security.Security;
import com.cloud.base.importexcel.ExcelMap2Model;
import com.cloud.base.util.DateHelper;
import com.cloud.base.util.ExcelUtil;
import com.cloud.base.util.FileUtil;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.StringUtil;
import com.opensymphony.xwork2.ModelDriven;

/**
 * @author 丛宇滋
 * 创建时间：2014-04-18 15:24:16
 */
@SuppressWarnings("finally")
@Controller
public class AttachmentAction extends BaseAction implements ModelDriven<Object>{

	private Long PAGESIZE_CONSTANT = 10l;//显示记录数

	@Resource
	private IAttachmentService attachmentService;
	
	private File file;
	private String fileFileName;
    private String fileContentType;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}


	/**
	 * 将form转成model
	 */
	private Attachment attachment;
	public Attachment getModel() {
		if(attachment == null) {
			attachment = new Attachment();
		}
		return attachment;
	}

	/**
	 * 添加
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="add",privName="添加",roleType="PRIV")
	public String addAttachment() throws Exception {
		return "edit";
	}

	/**
	 * 查看详细
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="detail",privName="详细")
	public String detailAttachment() throws Exception {
		String id = request.getParameter("id");
		attachment = attachmentService.getAttachmentById(id);
		request.setAttribute("attachment", attachment);
		return "detail";
	}

	/**
	 * 编辑
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="edit",privName="编辑",roleType="PRIV")
	public String editAttachment() throws Exception {
		String id = request.getParameter("id");
		attachment = attachmentService.getAttachmentById(id);
		request.setAttribute("attachment", attachment);
		return "edit";
	}

	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="del",privName="删除",roleType="PRIV")
	public String delAttachmentById() throws IOException {
		String id = request.getParameter("id");
		try {
			attachment.setId(id);
			attachmentService.delAttachment(attachment);
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
	public String delAttachmentByIds() throws IOException {
		String ids = request.getParameter("ids");
		try {
			String[] ids_arr = ids.split(",");
			List idList = new ArrayList();
			for(String id : ids_arr) {
				idList.add(id);
			}
			attachmentService.delAttachmentBatch(idList,new String[0]);
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
	public String saveAttachment() throws Exception {
		attachmentService.saveAttachment(attachment);
		return "redirectSuccess";														//重定向至列表，避免页面再次刷新弹出页面对话框
	}

	/**
	 * 跳转成功页面
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="success",privName="跳转成功")
	public String success() throws Exception {
		request.setAttribute("listUrl", request.getContextPath() + "/attachment/searchAttachment.do");
		return "success";
	}

	/**
	 * 获取列表中的数据
	 * @return
	 * @throws Exception
	 */
	@Security(privKey="search",privName="列表查询",roleType="PRIV")
	public String searchAttachment() throws Exception {
		StringBuffer sqlWhere = new StringBuffer("");										//查询条件where
		String pageIndexName = new org.displaytag.util.ParamEncoder("attachmentList").		//attachmentList为jsp页面中<display:table> 的id值
				encodeParameterName(org.displaytag.tags.TableTagParameters.PARAMETER_PAGE);

		//查询过滤-start
		String isSearch = StringUtil.null2String(request.getParameter("isSearch"));		//查询标志
		if(isSearch.equals("yes")) {													//表示是查询
			if(!StringUtil.null2String(attachment.getOrgfilename()).equals("")) {
				sqlWhere.append(" and attachment.orgfilename='" + attachment.getOrgfilename() + "'");
			}
			request.setAttribute("attachment", attachment);
		} else {
			request.setAttribute("attachment", null);
		}

		//查询过滤-end

		//当前索引页
		final Long pageIndex = new Long(GenericValidator.isBlankOrNull(request.getParameter(pageIndexName)) ? 1 : (Long.parseLong(request.getParameter(pageIndexName))));
		Map map = attachmentService.searchAttachment(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());

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
	public String openAttachmentExport() throws Exception {
		String ids = request.getParameter("ids");
		Map fieldMap = new TreeMap();
		Field[] fieldArr = attachment.getClass().getDeclaredFields();					//得到定义的属性
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
				where.append(" and attachment.id in (" + inIds + ")");
			}
		}

		where.append("");																	//排序方式 order by user.createTime asc
		//过滤条件结束

		if(!StringUtil.null2String(columns).equals("")) {
			JSONArray ja = JSONArray.fromObject("[" + columns + "]");					//得到json数组
			List<Attachment> list = attachmentService.getAllDataByWhere(where.toString());			//得到要导出的数据
			List dataList = new ArrayList();												//封装成dataList数据

			Object[] tempTitle = new Object[ja.size()];										//表头
			for(int i=0;i<ja.size();i++) {
				JSONObject jo = (JSONObject)ja.get(i);
				tempTitle[i] = String.valueOf(jo.get("name"));
			}
			dataList.add(tempTitle);														//添加表头

			for(Attachment attachment : list) {
				Object[] tempModel = new Object[ja.size()];									//临时的model
				for(int i=0;i<ja.size();i++) {												//遍历所有选中的列内容
					JSONObject jo = (JSONObject)ja.get(i);
					Method m = attachment.getClass().getMethod("get"+StringUtil.replaceFirstStr2UpperCase(String.valueOf(jo.get("value"))), null);		//得到方法
					Object obj = m.invoke(attachment, null);								//调用方法后的返回值
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
	public String openAttachmentImport() throws Exception {
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
		Object[] obj = ExcelMap2Model.excleMap2Model(excelMap, Attachment.class);
		if(obj != null) {
			boolean flag = (Boolean)obj[0];														//验证标志：true为正确
			if(flag) {																			//表示flag值为true
				List<Attachment> saveList = (List)obj[1];									//取出保存对象
				if(saveList.size() != 0) {
					attachmentService.saveDataBatch(saveList);										//批量保存
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
		request.getSession().setAttribute("returnUrl", request.getContextPath()+"/attachment/searchAttachment.do");
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
	public String saveAttachment4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	try {
	 		jsonData = StringUtil.null2String(request.getParameter("jsonData"));
	 		attachment = (Attachment)JSONUtil.json2Object(jsonData, Attachment.class.getName());
	 		attachment = attachmentService.saveAttachment(attachment);
	 		code = 1;
	 		jsonData = JSONUtil.object2JSONObject(attachment).toString();
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
	public String editAttachment4Json() throws Exception {
	 	JSONObject root = new JSONObject();
	 	int code = 0;																		//默认失败
	 	String jsonData = "";
	 	try {
	 		String id = request.getParameter("id");
	 		attachment = attachmentService.getAttachmentById(id);
	 		jsonData = JSONUtil.object2JSONObject(attachment).toString();
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
	public String delAttachmentByIds4Json() throws Exception {
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
	 		attachmentService.delAttachmentBatch(idList,new String[0]);												//批量删除
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
	public String searchAttachment4Json() throws Exception {
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
	 		Map map = attachmentService.searchAttachment(pageIndex,PAGESIZE_CONSTANT,sqlWhere.toString());
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
	 * 弹出上传附件窗口
	 * @throws Exception
	 */
	@Security
	public String openAttachmentWin() throws Exception {
		String attachmentIds = request.getParameter("attachmentIds");					// 关联附件ID集合
		int attachmentNums = Integer.valueOf(request.getParameter("attachmentNums"));				// 允许上传附件数量
		List<Attachment> attachList = new ArrayList<Attachment>();
		if(attachmentIds.length() == 0) {									// 初始数据
			Attachment attachment = new Attachment();
			for(int i=0;i<attachmentNums;i++) {
				attachList.add(attachment);
			}
		} else {															// 查询数据库
			String[] attachmentIdsArr = attachmentIds.split(",");
			for(int i=0;i<attachmentIdsArr.length;i++) {
				attachList.add(attachmentService.getAttachmentById(attachmentIdsArr[i]));
			}
			
			// 补齐控件
			Attachment attachment = new Attachment();
			int curSize = attachList.size();
			for(int i=0;i<attachmentNums-curSize;i++) {
				attachList.add(attachment);
			}
		}
		request.setAttribute("attachList", attachList);
		
		String attachmentType = request.getParameter("attachmentType");					// 允许上传文件类型
		if(StringUtils.isBlank(attachmentType)) attachmentType = "*";
		request.setAttribute("attachmentType", attachmentType);
		
		String attachmentSize = request.getParameter("attachmentSize");					// 文件大小
		if(StringUtils.isBlank(attachmentSize)) request.setAttribute("attachmentSize", FileUtil.getSize(5*1024*1024));		// 默认5MB
		else request.setAttribute("attachmentSize", FileUtil.getSize(Integer.valueOf(attachmentSize)));
		
		request.setAttribute("fileSizeLimit", attachmentSize);							// 文件大小限制
		
		String funmodulename = request.getParameter("funmodulename");					// 模块名称
		request.setAttribute("funmodulename", funmodulename);
		
		String hiddenId = request.getParameter("hiddenId");								// 隐藏域ID值
		request.setAttribute("hiddenId", hiddenId);
		
		return "openAttachmentWin";
	}
	
	/**
	 * 上传文件
	 * @throws Exception
	 */
	@Security
	public void uploadFile() throws Exception {
		String path = request.getSession().getServletContext().getRealPath("/") + "attached";
		String serverFileName = System.currentTimeMillis() + new Random(9999).nextInt() + FileUtil.getExtendName(fileFileName);	// 服务器存储路径
		Date uploadTime = new Date();										// 图片上传时间
		String funmodulename = request.getParameter("funmodulename");	// 模块名称
		
		if(StringUtils.isBlank(funmodulename)) funmodulename = request.getParameter("funmodulename");
		File moduleFolder = new File(path + File.separator + "common" + File.separator + funmodulename + File.separator + DateHelper.yyyy_MM_dd.format(uploadTime));
		if(!moduleFolder.isDirectory()) moduleFolder.mkdirs();				// 若不是目录需要创建文件目录路径
		
		String savePath = moduleFolder.getPath() + File.separator + serverFileName;		// 文件在服务器的存储路径（全路径）
		
		String attachmentid = request.getParameter("attachmentid");						// 附件ID值
		Attachment attach = new Attachment();
		attach.setOrgfilename(fileFileName);
		attach.setServerfilename(serverFileName);
		attach.setFilesize(FileUtil.getSize(file.length()));
		attach.setSavepath(savePath);
		attach.setUploadtime(new Timestamp(uploadTime.getTime()));
		String relativepath = savePath.substring(savePath.indexOf("attached")).replaceAll("\\\\", "/");
		attach.setRelativepath(relativepath);
		if(StringUtils.isNotBlank(attachmentid)) {							// 修改
			attach.setId(attachmentid);
			Attachment orgAttachment = attachmentService.getAttachmentById(attachmentid);
			FileUtil.deleteFileByPath(orgAttachment.getSavepath());
			attachmentService.saveAttachment(attach);
		} else {															// 新增
			attachmentService.saveAttachment(attach);
			attachmentid = attach.getId();
		}
		
		// 回显信息
		JSONObject uploadFileInfo = new JSONObject();
		uploadFileInfo.put("code", "1");
		uploadFileInfo.put("orgfilename", fileFileName);					// 原文件名
		uploadFileInfo.put("serverfilename", serverFileName);				// 服务器存储的文件名称
		uploadFileInfo.put("savepath", savePath);							// 服务器存储路径
		uploadFileInfo.put("msg", "上传成功！");								
		uploadFileInfo.put("uploadtime" , DateHelper.yyyy_MM_dd_HHmmss.format(uploadTime));	// 上传时间
		uploadFileInfo.put("attachmentid", attachmentid);
		FileUtils.copyFile(file, new File(savePath));
		
		response.getWriter().print(uploadFileInfo);
	}
	
	/**
	 * 获取所有附件内容
	 * @throws Exception
	 */
	public void getAllAttachmentFiles() throws Exception {
		String ids = request.getParameter("ids");						// 多个文件内容
		String[] idsArr = ids.split(",");					// 分组
		
		JSONArray tempArray = new JSONArray();
		for(int i=0;i<idsArr.length;i++) {
			Attachment getAttachment = attachmentService.getAttachmentById(idsArr[i]);
			getAttachment.setUploadTimeStr(DateHelper.yyyy_MM_dd_HHmmss.format(getAttachment.getUploadtime()));
			tempArray.add(getAttachment);
		}
		
		JSONObject result = new JSONObject();
		result.put("code", "1");
		result.put("msg", "获取文件成功");
		result.put("attachmentDataArray", tempArray);
		response.getWriter().print(result);
	}
	
	/**
	 * 获取某一附件文件
	 * @throws Exception
	 */
	public void getAttachment() throws Exception {
		String id = request.getParameter("id");
		Attachment attach = attachmentService.getAttachmentById(id);
		if(attach == null) {return;}
		
		File showFile = new File(attach.getSavepath());
		if(!showFile.isFile()) {return;}
		try{
			response.setHeader("Content-Type", "audio/mpeg");
			int len_l = (int) showFile.length();
			byte[] buf = new byte[2048];
			FileInputStream fis = new FileInputStream(showFile);
			OutputStream out = response.getOutputStream();
			len_l = fis.read(buf);
			while (len_l != -1) {
				out.write(buf, 0, len_l);
				len_l = fis.read(buf);
			}
			out.flush();
			out.close();
			fis.close();
		}catch (Exception e){
			System.out.println(e);
		}
	}
	
	public void downLoadAttachment() throws Exception {
		
		String id = request.getParameter("id");
		Attachment attach = attachmentService.getAttachmentById(id);
		try{ 
			String templateFile = attach.getSavepath();
			File file = new File(templateFile);
			InputStream inStream = new FileInputStream(file);							// 文件的存放路径
			
			// 设置输出的格式
			response.reset();
			response.setContentType("application/x-msdownload;charset=GBK");
			String fileName = StringUtil.str2URLEncoder(file.getName());
			response.setHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes("UTF-8"), "GBK"));

			// 循环取出流中的数据
			ServletOutputStream os = response.getOutputStream();
			byte[] b = new byte[5120];
			int len;
			while ((len = inStream.read(b)) > 0){
				os.write(b, 0, len);
			}
			inStream.close();
			os.flush();
			os.close();
		} catch (FileNotFoundException e) {
			LoggerUtil.error(getClass(), "filePath: " + attach.getSavepath() + " 没有找到!!");			
		}
	}
	
	/**
	 * 附件管理删除
	 * @return
	 * @throws Exception
	 */
	public void delAttachmentFileById() throws Exception {
		String id = request.getParameter("id");
		Attachment attachment = attachmentService.getAttachmentById(id);
		FileUtil.deleteFileByPath(attachment.getSavepath());
		attachmentService.delAttachment(attachmentService.getAttachmentById(id));
		
		JSONObject deleteFileInfo = new JSONObject();
		deleteFileInfo.put("code", "1");
		deleteFileInfo.put("msg", "附件删除成功");
		response.getWriter().print(deleteFileInfo);
	}

}
