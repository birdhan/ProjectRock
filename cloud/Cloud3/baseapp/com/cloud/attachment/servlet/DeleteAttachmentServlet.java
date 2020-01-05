package com.cloud.attachment.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.cloud.attachment.service.AttachmentServiceImpl;
import com.cloud.base.util.JSONUtil;
import com.cloud.base.util.SpringContextHolder;


public class DeleteAttachmentServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public DeleteAttachmentServlet() {
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
		AttachmentServiceImpl asi = (AttachmentServiceImpl)SpringContextHolder.getApplicationContext().getBean("attachmentServiceImpl");
		String param = request.getParameter("param");								//得到参数
		JSONArray array = JSONArray.fromObject(param);								//转json数组
		List<String> idArrList = new ArrayList<String>();
		List<String> filePathsList = new ArrayList<String>();
		
		for(int i=0;i<array.size();i++){
			JSONObject obj = (JSONObject)array.get(i);
			idArrList.add(obj.getString("id"));
			filePathsList.add(obj.getString("filePath"));
		}	
		String filePaths[] = new String [filePathsList.size()];
		asi.delAttachmentBatch(idArrList, filePathsList.toArray(filePaths));
		//request.getRequestDispatcher("/ListAttachmentServlet").forward(request, response);
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
