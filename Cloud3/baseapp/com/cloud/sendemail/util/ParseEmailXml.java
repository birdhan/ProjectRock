package com.cloud.sendemail.util;

import java.net.URLDecoder;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.cloud.sendemail.model.EmailXML;

public class ParseEmailXml {

	public static EmailXML parse(String xmlDoc) {
		EmailXML ex = new EmailXML();
		try {
			Document doc = DocumentHelper.parseText(xmlDoc); 
			Element ele_root = doc.getRootElement();
			List<Element> root_List = ele_root.elements();
			if(root_List != null && root_List.size() !=0) {
				for(int i=0;i<root_List.size();i++) {
					Element ele_root_2 = root_List.get(i);
					if(ele_root_2.getName().toLowerCase().equals("email")) {	//得到email
						List<Element> ele_root_2_list = ele_root_2.elements();
						for(int j=0;j<ele_root_2_list.size();j++) {
							Element ele_root_3 = ele_root_2_list.get(j);
							if(ele_root_3.getName().equals("toAddress")) {
								ex.setToAddress(URLDecoder.decode(ele_root_3.getText(),"UTF-8"));
							} else if(ele_root_3.getName().equals("subject")) {
								ex.setSubject(URLDecoder.decode(ele_root_3.getText(),"UTF-8"));
							} else if(ele_root_3.getName().equals("content")) {
								ex.setContent(URLDecoder.decode(ele_root_3.getText(),"UTF-8"));
							}
						}
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ex;
	}
}
