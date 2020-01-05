package com.cloud.sendemail.message.webservice;

import java.io.StringReader;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.digester.Digester;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.cloud.base.cache.SysCache;
import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.AxisWebServiceUtil;
import com.cloud.base.util.PropertyFileUtil;
import com.cloud.callwebservice.util.CallWSUtil;
import com.cloud.sendemail.message.service.MailSenderInfo;
import com.cloud.sendemail.message.service.SimpleMailSender;
import com.cloud.sendemail.model.EmailConfig;
import com.cloud.sendemail.model.EmailXML;

public class EmailWebService {

	private static final Logger logger = Logger.getLogger(EmailWebService.class);
	
	/**
	 * 接收xml串，解析xml后发送邮件
	 * 
	 * @param xmlDoc
	 * @return
	 */
	public String sendEmail(String xmlDoc) {
		String returnValue = "";
		try {
			EmailXML eXML = convert2EmailXMLFromXMLDoc(xmlDoc);
			
			EmailConfig ec = SysCache.getInstance().getEmailConfig();
			
			 //这个类主要是设置邮件   
			MailSenderInfo mailInfo = new MailSenderInfo();    
			mailInfo.setMailServerHost(ec.getServerHost()); 	   
			mailInfo.setMailServerPort(ec.getServerPort());    
			mailInfo.setValidate(true);    
			mailInfo.setUserName(ec.getFromAddress());    
			mailInfo.setPassword(ec.getPwd());  
			mailInfo.setFromAddress(ec.getFromAddress());    
			mailInfo.setToAddress(eXML.getToAddress());
			mailInfo.setSubject(eXML.getSubject());    
			mailInfo.setContent(eXML.getContent()); 
	     
			//这个类主要来发送邮件
			SimpleMailSender sms = new SimpleMailSender();   
			sms.sendHtmlMail(mailInfo);//发送html格式   
			returnValue = "1";
			
			logger.info(URLDecoder.decode(xmlDoc, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
			returnValue = "2.Send Email Failed!";
			logger.info(xmlDoc);
			logger.info(e.toString());
			return returnValue;
		} 

		return returnValue;
	}
	
	public EmailXML convert2EmailXMLFromXMLDoc(String xmlDoc) {
		EmailXML eXML = null;
		try {
			Digester digester = new Digester();
			digester.setValidating(false);
			// 当遇到<SystemConfig>标签时生成SystemConfig对象
			digester.addObjectCreate("root/email", EmailXML.class);
			
			digester.addBeanPropertySetter("root/email/toAddress", "toAddress");
			digester.addBeanPropertySetter("root/email/subject", "subject");
			digester.addBeanPropertySetter("root/email/content", "content");
		
			StringReader xmlReader = new StringReader(URLDecoder.decode(xmlDoc, "UTF-8"));
			eXML = (EmailXML)digester.parse(xmlReader);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return eXML;
	}
	
	public EmailXML dom4jParseXML(String xml) {
		EmailXML eXML = new EmailXML();
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			Element email = root.element("email");
			String toAddress = email.element("toAddress").getText();
			String subject = email.element("subject").getText();
			String content = email.element("content").asXML().replace("<content>", "").replace("</content>", "");
			eXML.setToAddress(toAddress);
			eXML.setContent(content);
			eXML.setSubject(subject);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return eXML;
	}
	
	public static void main(String[] args) throws Exception{
		
		StringBuffer xmlDoc = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xmlDoc.append("<root>\n");
		xmlDoc.append("<email>\n");
		xmlDoc.append("<toAddress>"+URLEncoder.encode("514628014@qq.com", "UTF-8")+"</toAddress>\n");
		xmlDoc.append("<subject>"+URLEncoder.encode("tessfdfs", "UTF-8")+"</subject>\n");
		xmlDoc.append("<content>"+URLEncoder.encode("测试", "UTF-8")+"</content>\n");
		xmlDoc.append("</email>\n");
		xmlDoc.append("</root>");
		
		//本地调用
		EmailWebService ews = new EmailWebService();
		System.out.println(ews.sendEmail(xmlDoc.toString()));
		
		//webservice调用
		String aa = AxisWebServiceUtil.callInvoke("http://127.0.0.1/Cloud3/axisservices/EmailWS?wsdl", "sendEmail", new Object[]{xmlDoc.toString()});
		System.out.println(aa);
	}
}
