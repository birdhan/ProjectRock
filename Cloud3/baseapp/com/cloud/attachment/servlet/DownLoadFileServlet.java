package com.cloud.attachment.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cloud.attachment.dao.AttachmentDaoHibernate;
import com.cloud.attachment.model.Attachment;
import com.cloud.base.attachment.PropertiesUtil;
import com.cloud.base.util.SpringContextHolder;

/**
 * 附加下载 servlet
 * @author eoms-wudan
 *
 */
public class DownLoadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownLoadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AttachmentDaoHibernate adh = (AttachmentDaoHibernate)SpringContextHolder.getApplicationContext().getBean("attachmentDaoHibernate");
		request.setCharacterEncoding("utf8");
		String id = request.getParameter("id");
		Attachment att1 = new Attachment();
		if(id != null && !"".equals(id)){
			System.out.println(id);
			att1 = adh.getAttachmentById(id);
		}
		String f1 = att1.getFileName();//request.getParameter("fileName");
		System.out.println(f1);
		//String f2 = new String(f1.getBytes("iso-8859-1"),"utf-8");
		String filePath = att1.getFilePath();//request.getParameter("path");
		Properties p = new PropertiesUtil().loadProperty();
		String basePath = p.getProperty("basePath");
		String path = basePath + filePath;
		System.out.println(path);
		response.reset(); 
        response.setCharacterEncoding("utf-8");
        response.setContentType(getContentType(f1));
        response.setHeader("Content-Disposition", "attachment; filename="+new String(f1.getBytes("gbk"),"iso-8859-1"));
        
        OutputStream output = null;
        InputStream fis = null;
        try
        {
            output  = response.getOutputStream();
            File file = new File(path);
            fis = new FileInputStream(file);
            byte[] b = new byte[1024];
            int i = 0;
            while((i = fis.read(b))!=-1)
            {
                output.write(b, 0, i);
            }
            output.flush();
            response.flushBuffer();
            
        }
        catch(Exception e)
        {
            System.out.println("Error!");
            System.out.println("filePath"+filePath);
            e.printStackTrace();
        }
        finally
        {
            if(fis != null)
            {
                fis.close();
                fis = null;
            }
            if(output != null)
            {
                output.close();
                output = null;
            }
        }
	}
	private String getContentType(String fileName) {
		   String fileNameTmp = fileName.toLowerCase();
		   String ret = "";
		   if (fileNameTmp.endsWith("txt")) {
		    ret = "text/plain";
		   }
		   if (fileNameTmp.endsWith("gif")) {
		    ret = "image/gif";
		   }
		   if (fileNameTmp.endsWith("jpg")) {
		    ret = "image/jpeg";
		   }
		   if (fileNameTmp.endsWith("jpeg")) {
		    ret = "image/jpeg";
		   }
		   if (fileNameTmp.endsWith("jpe")) {
		    ret = "image/jpeg";
		   }
		   if (fileNameTmp.endsWith("zip")) {
		    ret = "application/zip";
		   }
		   if (fileNameTmp.endsWith("rar")) {
		    ret = "application/rar";
		   }
		   if (fileNameTmp.endsWith("doc")) {
		    ret = "application/msword";
		   }
		   if (fileNameTmp.endsWith("ppt")) {
		    ret = "application/vnd.ms-powerpoint";
		   }
		   if (fileNameTmp.endsWith("xls")) {
		    ret = "application/vnd.ms-excel";
		   }
		   if (fileNameTmp.endsWith("html")) {
		    ret = "text/html";
		   }
		   if (fileNameTmp.endsWith("htm")) {
		    ret = "text/html";
		   }
		   if (fileNameTmp.endsWith("tif")) {
		    ret = "image/tiff";
		   }
		   if (fileNameTmp.endsWith("tiff")) {
		    ret = "image/tiff";
		   }
		   if (fileNameTmp.endsWith("pdf")) {
		    ret = "application/pdf";
		   }
		   return ret;
		}

}
