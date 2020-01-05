package com.cloud.base.test.other;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.cloud.base.util.XMLModelUtil;
import com.cloud.base.util.StringUtil;
import com.cloud.demo.model.Site;

public class CreateXMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");		
		Element sites = root.addElement("sites");

		for (int i = 0; i < 3; i++) {
			Element site = sites.addElement("site");
			site.addAttribute("no", "001");
			site.addAttribute("name", "abcdefg");
		}		
		String xml = StringUtil.formatXml(root.asXML());
		System.out.println(xml);
		
//		Object[] paramArr = {xml};
//		AxisWebServiceUtil.callInvoke("http://192.168.138.162:8888/Cloud3/axisservices/DemoService?wsdl", "insertDemoData", paramArr);
//		CXFWebserviceUtil.callInvoke("http://192.168.138.162:8888/Cloud3/cxfservices/DemoService?wsdl", "insertDemoData", paramArr);
		
//		Digester digester = new Digester();
//		digester.setValidating(false);
//		
//		digester.addObjectCreate("root/sites/", Sites.class);
//		digester.addObjectCreate("root/sites/site", Site.class);
//		
//		digester.addSetProperties("root/sites/site/");
//		
////		digester.addSetProperties("root/sites/site/","no","no");
////		digester.addSetProperties("root/sites/site/","name","name");
//		
//		digester.addSetNext("root/sites/site/", "addSite");
//		
//		StringReader xmlReader = new StringReader(xml);		
//		Sites sites0 = (Sites)digester.parse(xmlReader);
//		
//		List list = sites0.getList();
//		for(int i=0;i<list.size();i++) {
//			Site s = (Site)list.get(i);
//			System.out.println("no:"+s.getNo()+",name:"+s.getName());
//		}
		
		System.out.println("====================================");
		
		List l = new ArrayList();
		for(int i=0;i<2;i++) {
			Site s = new Site();
			s.setNo("no"+i);
			s.setName("name"+i);
			l.add(s);
		}
		String finalxml = XMLModelUtil.toXML4Attribute(l);
		System.out.println("finalxml:");
		System.out.println(finalxml);
	}
}
