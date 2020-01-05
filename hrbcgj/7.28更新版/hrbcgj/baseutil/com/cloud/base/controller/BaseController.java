/**   
* Licensed under the Apache License, Version 2.0 (the "License");
*/
package com.cloud.base.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.cloud.base.util.JSONUtil;

/**  
 * <p>@Description: MVC 基类，所有的业务Controller必须继承此类</p> 
 */
@Controller
public class BaseController extends SimpleFormController {

//	@Override
//	protected void initServletContext(ServletContext servletContext) {
//		logger.info("---initServletContext---");
//		
//	}
	
	@InitBinder
	protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception { 
//		  SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
//		  SimpleDateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd HH");
//		  SimpleDateFormat fmt3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		  SimpleDateFormat fmt4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		  SimpleDateFormat fmt5 = new SimpleDateFormat("yyyy");
//		  SimpleDateFormat fmt6 = new SimpleDateFormat("yyyy-MM");
		  
//	      CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
//	      CustomDateEditor dateEditor2 = new CustomDateEditor(fmt2, true);
//	      CustomDateEditor dateEditor3 = new CustomDateEditor(fmt3, true);
	      CustomDateEditor dateEditor4 = new CustomDateEditor(fmt4, true);
//	      CustomDateEditor dateEditor5 = new CustomDateEditor(fmt5, true);
//	      CustomDateEditor dateEditor6 = new CustomDateEditor(fmt6, true);
	      
//	      binder.registerCustomEditor(Date.class, dateEditor);
//	      binder.registerCustomEditor(Date.class, dateEditor2);
//	      binder.registerCustomEditor(Date.class, dateEditor3);
	      binder.registerCustomEditor(Date.class, dateEditor4);
//	      binder.registerCustomEditor(Date.class, dateEditor5);
//	      binder.registerCustomEditor(Date.class, dateEditor6);
	      
	      super.initBinder(request, binder); 
	}

	public void printJSON(HttpServletResponse response, String jsonString) {
		response.setHeader("Cache-Control", "nocache");
		response.setHeader("Pragma", "nocache");
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;

		try {
			out = response.getWriter();
			out.write(jsonString);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
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
	 * 获取总页数
	 * @param total
	 * @param pageSize
	 * @return
	 */
	public int getTotalPageNum(int total , long pageSize) {
		int resultSize = (int) (total / pageSize);
		if(resultSize == 0) resultSize = 1;
		else {
			if(pageSize == 1) return total;
			resultSize = (int) (total % pageSize);
			if(resultSize > 0) resultSize = (int) (total / pageSize) +1;
		}
		return resultSize;
	}

}
