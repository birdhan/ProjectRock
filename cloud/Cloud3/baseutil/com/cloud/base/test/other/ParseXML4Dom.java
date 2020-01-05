package com.cloud.base.test.other;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ParseXML4Dom {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		String xml = "<?xml version='1.0' encoding='UTF-8'?>"+
				"<root>"+
				"<email>"+
				"<toAddress>514628014@qq.com</toAddress>"+
				"<subject>您有一些需要处理的数据，请及时处理！</subject>"+
				"<content><table><tr><td>序号</td><td>姓名</td><td>身份证号</td><td>电话</td><td>借款日期</td><td>借款周期</td><td>借款利率</td></tr><tr><td>1</td><td>崔云鹏</td><td>230821198707244039</td><td>15945676378</td><td>15945676378</td><td>12</td><td>0.03</td></tr></table><table><tr><td>序号</td><td>姓名</td><td>身份证号</td><td>电话</td><td>借款日期</td><td>借款周期</td><td>借款利率</td></tr><tr><td>1</td><td>崔云鹏</td><td>230821198707244039</td><td>15945676378</td><td>15945676378</td><td>12</td><td>0.03</td></tr></table></content>"+
				"</email>"+
				"</root>";
		Document doc = DocumentHelper.parseText(xml);
		Element root = doc.getRootElement();
		Element email = root.element("email");
		Element content = email.element("content");
		System.out.println(content.asXML());
		
	}

}
