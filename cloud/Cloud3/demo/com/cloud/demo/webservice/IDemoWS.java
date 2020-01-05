package com.cloud.demo.webservice;

import javax.jws.WebService;

@WebService
public interface IDemoWS {

	public String testDemoWS();
	
	/**
	 * 插入数据库
	 * @param xml
	 * @return
	 */
	public String insertDemoData(String xml);
	
	public String InsertPassInfo(String xml); 
}
