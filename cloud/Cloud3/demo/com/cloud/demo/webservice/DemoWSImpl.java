package com.cloud.demo.webservice;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.HTTPConstants;
import org.apache.commons.digester.Digester;
import org.xml.sax.SAXException;

import com.cloud.base.util.CXFWebServiceUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.demo.model.Site;
import com.cloud.demo.model.Sites;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;

public class DemoWSImpl implements IDemoWS {

	@Override
	public String testDemoWS() {		
		return "this is testDemoWs";
	}

	@Override
	public String insertDemoData(String xml) {
		
		//利用Digester来解析xml
		Digester digester = new Digester();
		digester.setValidating(false);
		
		digester.addObjectCreate("root/sites/", Sites.class);
		digester.addObjectCreate("root/sites/site", Site.class);
		
		digester.addSetProperties("root/sites/site/");
		
//		digester.addSetProperties("root/sites/site/","no","no");
//		digester.addSetProperties("root/sites/site/","name","name");
		
		digester.addSetNext("root/sites/site/", "addSite");
		
		StringReader xmlReader = new StringReader(xml);		
		Sites sites;
		try {
			sites = (Sites)digester.parse(xmlReader);
			List list = sites.getList();
			for(int i=0;i<list.size();i++) {
				Site s = (Site)list.get(i);
				System.out.println("no:"+s.getNo()+",name:"+s.getName());
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}	
		
		return "1";
	}
	
	public synchronized String InsertPassInfo(String xml) {
//		try {
//			xml = new String(Base64.decode(xml),"UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Base64DecodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	//BASE64解密
		System.out.println(xml);
//		MessageContext mc = MessageContext.getCurrentContext();
//		HttpServletResponse response = (HttpServletResponse)mc.getProperty(HTTPConstants.MC_HTTP_SERVLETRESPONSE);		
		return "1";
	}

}
