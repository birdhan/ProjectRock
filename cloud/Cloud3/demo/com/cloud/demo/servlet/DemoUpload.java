package com.cloud.demo.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class DemoUpload extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DemoUpload() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = null;
		Map map = new HashMap();
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		File directory = null;  
		List<FileItem> items = new ArrayList();
		try {
			items = upload.parseRequest(request);
			// 得到所有的文件
			Iterator<FileItem> it = items.iterator();
			while (it.hasNext()) {
				FileItem fItem = (FileItem) it.next();
				String fName = "";
				Object fValue = null;
				if (fItem.isFormField()) { // 普通文本框的值
					fName = fItem.getFieldName();
					fValue = fItem.getString("UTF-8");
					map.put(fName, fValue);
				} else { // 获取上传文件的值
					fName = fItem.getFieldName();
					fValue = fItem.getInputStream();
					//map.put(fName, fValue);
					String name = fItem.getName();
					if(name != null && !("".equals(name))) {
						name = name.substring(name.lastIndexOf(File.separator) + 1);
						String timestamp_Str = "/"+System.currentTimeMillis();
						directory = new File("d://aaa"+timestamp_Str+ File.separator);  
						directory.mkdirs();
						String filePath = ("d://aaa")+ timestamp_Str+ File.separator + name;
						//map.put(fName + "FilePath", filePath);
						InputStream is = fItem.getInputStream();
						FileOutputStream fos = new FileOutputStream(filePath);
						byte[] buffer = new byte[1024];
						while (is.read(buffer) > 0) {
							fos.write(buffer, 0, buffer.length);
						}
						fos.flush();
						fos.close();
						map.put(fName + "FileName", name);
					}
				}
			}
			System.out.println(map);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("读取http请求属性值出错!");
		}
		
		try {
			out = response.getWriter();
			out.print("{success:true, msg:'接收成功'}");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
