package com.cloud.base.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.cloud.base.util.StringUtil;
import com.cloud.sendemail.message.webservice.EmailWebService;

public class SendMailJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		StringBuffer xmlDoc = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xmlDoc.append("<root>\n");
		xmlDoc.append("<email>\n");
		xmlDoc.append("<toAddress>"+StringUtil.str2URLDecoder("514628014@qq.com")+"</toAddress>\n");
		xmlDoc.append("<subject>"+StringUtil.str2URLDecoder("test！")+"</subject>\n");
		xmlDoc.append("<content>"+StringUtil.str2URLDecoder("测试隋唐英雄第四部！")+"</content>\n");
		xmlDoc.append("</email>\n");
		xmlDoc.append("</root>");
		
		//本地调用
		EmailWebService ews = new EmailWebService();
		System.out.println(ews.sendEmail(xmlDoc.toString()));
	}
	
	public static void main(String[] args) {
		StringBuffer xmlDoc = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xmlDoc.append("<root>\n");
		xmlDoc.append("<email>\n");
		xmlDoc.append("<toAddress>"+StringUtil.str2URLDecoder("514628014@qq.com")+"</toAddress>\n");
		xmlDoc.append("<subject>"+StringUtil.str2URLDecoder("test！")+"</subject>\n");
		xmlDoc.append("<content>"+StringUtil.str2URLDecoder("测试隋唐英雄第四部！")+"</content>\n");
		xmlDoc.append("</email>\n");
		xmlDoc.append("</root>");
		
		//本地调用
		EmailWebService ews = new EmailWebService();
		System.out.println(ews.sendEmail(xmlDoc.toString()));
	}
	
}
