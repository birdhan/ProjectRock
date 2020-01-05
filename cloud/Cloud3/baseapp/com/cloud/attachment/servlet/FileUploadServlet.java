package com.cloud.attachment.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cloud.attachment.dao.AttachmentDaoHibernate;
import com.cloud.attachment.model.Attachment;
import com.cloud.base.attachment.PropertiesUtil;
import com.cloud.base.attachment.ResponseUtils;
import com.cloud.base.attachment.UUIDUtil;
import com.cloud.base.util.SpringContextHolder;

/**
 * 附件上传处理 servlet
 * 
 * @author eoms-wudan
 * 
 */

public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int UPLOAD_SUCCSSS = 0; // "上传文件成功,

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("==========文件上传开始===============");
		AttachmentDaoHibernate adh = (AttachmentDaoHibernate)SpringContextHolder.getApplicationContext().getBean("attachmentDaoHibernate");
		JSONObject obj = new JSONObject();
		//获取参数
		String contextPath = request.getParameter("contextPath");
		if(contextPath == null || "".equals(contextPath)){
			contextPath = "/public";
		}
		//设置请求格式头
		response.setContentType("text/html; charset=UTF-8");
		//获得输出
		PrintWriter out = response.getWriter();
		//取得上传根路径
		Properties p = new PropertiesUtil().loadProperty();
		String basePath = p.getProperty("basePath");
		//创建文件夹
		//一级文件夹 为工程文根
		String firstFolder = contextPath.substring(1,contextPath.length());
		System.out.println("一级文件夹："+firstFolder);
		//定义日期格式
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
		//二级文件夹 为当前日期
		String secondFolder = sdf1.format(new Date(System.currentTimeMillis()));
		System.out.println("二级文件夹："+secondFolder);
		//如果文件夹不存在 则创建
		File mkr = new File(basePath + File.separatorChar +firstFolder + File.separatorChar + secondFolder);
		if(!mkr.exists()){
			mkr.mkdirs();
		}
		// 上传操作
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		try{
			List items = upload.parseRequest(request);
			if (null != items) {
				Iterator itr = items.iterator();
				while (itr.hasNext()) {
					FileItem item = (FileItem) itr.next();
					if (item.isFormField()) {
						continue;
					} else {
						//修改文件名
						String newFileName = UUIDUtil.getUUID();
						String oldFileName = item.getName();
						newFileName += oldFileName.substring(oldFileName.lastIndexOf("."));
						String savePath = File.separatorChar + firstFolder + File.separatorChar + secondFolder + File.separatorChar + newFileName;
						//写入文件到本地磁盘
						File savedFile = new File(mkr, newFileName);
						item.write(savedFile);
						
						//保存文件信息
						Attachment att = saveInfo(request, item, savePath, oldFileName.substring(oldFileName.lastIndexOf(".")), getFileSize(savedFile));
						adh.saveAttachment(att);
						//返回信息
						String newFilePath = att.getFilePath().replace("\\", "\\\\");
						String root = basePath.replace("\\", "\\\\");
						System.out.println(att.getFileName());
						if("问题反馈截图.jpg".equals(att.getFileName())){
							obj.put("path", newFilePath);
							ResponseUtils.renderJson(response, obj.toString());
						}else{
							out.print("{status:" + this.UPLOAD_SUCCSSS+ ",id:'"+ att.getId()+"',name:'"+ att.getFileName()+"',path:'"+ newFilePath + "',root:'"+root+"',viewRoot:'"+root+"'}");
						}
					}
				}
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			out.flush();
			out.close();
		}
	}
	/**
	 * 整合上传文件的信息
	 * @param request 请求
	 * @param item 文件对象
	 * @param path 保存的路径
	 * @param type 文件类型
	 * @return
	 */
	private Attachment saveInfo(HttpServletRequest request, FileItem item , String path, String type, long size){
		Attachment att = new Attachment();
		
		String fileId = UUIDUtil.getUUID();
		String fileName = item.getName();
		String filePath = path;
		long fileSize = size;
		String fileType = type;
		String userId = request.getParameter("userId");
		String contextPath = request.getParameter("contextPath");
		Date uploadTime = new Date(System.currentTimeMillis());
		
		att.setFileName(fileName);
		att.setFilePath(filePath);
		att.setFileSize(fileSize);
		att.setFileType(fileType);
		att.setUploadUserId(userId == null?"未知":userId);
		att.setUploadTime(uploadTime);
		att.setContextPath(contextPath);
		
		return att;
	}
	/**
	 * 获取上传完成的文件大小
	 * @param file
	 * @return
	 */
	private long getFileSize(File file){
		long size = file.length();
		return size;
	}
}
