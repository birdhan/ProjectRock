package com.cloud.base.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.ExcelUtil;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.systemuser.model.SystemUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *  所有的action都继承本类，该类为其它action类初始化了request,response,application三个对象。
 *  实现ServletRequestAware、ServletContextAware、ServletResponseAware接口
 * @author cuiyp
 *
 */
public class BaseAction extends ActionSupport implements ServletRequestAware , ServletContextAware , ServletResponseAware{
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected ServletContext application;
	
	private File importExcelFileAll;													//导入excel文件
	public File getImportExcelFileAll() {
		return importExcelFileAll;
	}

	public void setImportExcelFileAll(File importExcelFileAll) {
		this.importExcelFileAll = importExcelFileAll;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		request.setAttribute("sc", SysCache.getInstance().getSystemConfig());			//系统个性配置
	}
	
	public void setServletContext(ServletContext application) {
		this.application = application;
	}
	
	public void setServletResponse(HttpServletResponse response) {		
		this.response = response;
		response.setHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");	//解决跨域
	}
	
	/**
	 * 将后台查询到的数据map转成要在列表展现的json串
	 * @param map
	 * @return
	 */
	public JSONObject mapResult2JSON(Map map) {
		int total = Integer.valueOf(String.valueOf(map.get("total")));					//总数
		List resultList = (List)map.get("result");										//结果数据集
		JSONArray dataJSONAray = JSONUtil.list2JSONArray(resultList);					//数据json串		
		JSONObject root =new JSONObject();
		root.put("Rows", dataJSONAray);													//结果数据json串
		root.put("Total", total);														//总记录数		
		return root;
	}
	
	/**
	 * Excel文件模板下载
	 * @return
	 * @throws Exception
	 * by cuiyp 2012-08-25
	 */
	public String downLoadFile() throws Exception {
		
		String filePath = request.getParameter("filePath");
		try{ 
			String templateFile = request.getSession().getServletContext().getRealPath("\\") + filePath;
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
			LoggerUtil.error(getClass(), "filePath: "+filePath+" 没有找到!!");			
		}
		return null;
	}
	
	/**
	 * excel导入数据的时候首先要将文件上传到服务器，然后得到数据之后将文件删除（卸磨杀驴）
	 * 然后通过module的模块名，重定向到该模块下的action类的importData方法
	 * @return
	 * @throws Exception
	 * cuiyp 2012-08-23
	 */
	public String importExcel() throws Exception {
		String module = request.getParameter("module");													//获取模块名
		String realpath = request.getSession().getServletContext().getRealPath("/upload/excelImport");	//要上传的路径         
		if (importExcelFileAll != null) {           
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");							//日期格式
			File savefile = new File(new File(realpath), sdf.format(new Date())+".xls");				//要上传到服务器上的文件           
			if (!savefile.getParentFile().exists()) {													//检查是否有该路径的文件夹存在，如果没有那么创建文件夹               
				savefile.getParentFile().mkdirs();   
			}         
			FileUtils.copyFile(importExcelFileAll, savefile);											//上传文件
			
			//读取文件的路径
			FileInputStream is = new FileInputStream(savefile);											//获取服务器端的文件流
			Map m = ExcelUtil.ReadExcelToMap(is);														//**这一步是主要的----得到excel所有内容**
			
			is.close();																					//关闭文件流方便删除
			savefile.delete();																			//删除文件
			
			request.getSession().setAttribute("excelMap", m);											//将m值设置到session里
			response.sendRedirect(request.getContextPath()+"/" + module + "/importData.do");			//重定向，跳到相应的action的方法里执行数据插入功能
		}    
		return null;
	}
	
	/**
	 * 设置excel导入失败的集合list
	 * @param failedList
	 */
	public void setExcelFailedList(List failedList) {
		JSONArray array = new JSONArray();
		for(int i=0;i<failedList.size();i++) {
			JSONObject object = new JSONObject();
			String[] messageObject = (String[])failedList.get(i);
			object.put("excelRowNum", messageObject[0]);												//excel行号
			object.put("message", messageObject[1]);
			array.add(object);
		}
		JSONObject root = new JSONObject();
		root.put("Rows", array);																		//结果数据json串
		request.getSession().setAttribute("failedListSize", failedList.size());		
		request.getSession().setAttribute("failedList", root);
	}
	
	/**
	 * 得到当前登录人
	 * @return
	 */
	public SystemUser getCurUser() {
		SystemUser su = (SystemUser)request.getSession().getAttribute("user");
		return su;
	}
	
	/**
	 * 普通java类中可得到当前登录人
	 * @return
	 */
	public static SystemUser curUser() {
		ActionContext ac = ActionContext.getContext(); 
		HttpServletRequest request = (HttpServletRequest)ac.get(ServletActionContext.HTTP_REQUEST);
		SystemUser su = (SystemUser)request.getSession().getAttribute("user");
		return su;
	}
}
