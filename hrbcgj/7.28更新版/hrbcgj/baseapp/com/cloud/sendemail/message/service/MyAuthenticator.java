package com.cloud.sendemail.message.service;

import javax.mail.*;

public class MyAuthenticator extends Authenticator {
	String userName = null;
	String password = null;

	public MyAuthenticator() {
	}

	public MyAuthenticator(String username, String password) {
		this.userName = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, password);
	}

	public static void main(String[] args){   
        //这个类主要是设置邮件   
		MailSenderInfo mailInfo = new MailSenderInfo();    
		mailInfo.setMailServerHost("smtp.qq.com"); 	//peizhi   
		mailInfo.setMailServerPort("25");    //peizhi
		mailInfo.setValidate(true);    
		mailInfo.setUserName("514628014@qq.com");   //peizhi 
		mailInfo.setPassword("19870609Cyp");//您的邮箱密码  //peizhi  
		mailInfo.setFromAddress("514628014@qq.com");    //peizhi
		mailInfo.setToAddress("514628014@qq.com");    //chuanru
		mailInfo.setSubject("审核任务提示");    //chuanru
		mailInfo.setContent("您有新的任务需要审核，请查收！"); //chuanru
     
		//这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();   
		//sms.sendTextMail(mailInfo);//发送文体格式    
		sms.sendHtmlMail(mailInfo);//发送html格式   
		System.out.println("succese:");
		
   }}
