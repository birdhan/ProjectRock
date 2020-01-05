package com.cloud.sqllog.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.PropertyFileUtil;

public class InitJdbMonitorConfig {
	public static boolean doc2XmlFile(Document document, String filename) {
		boolean flag = true;
		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(filename));
			transformer.transform(source, result);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	public static Document load(String filename) {
		Document document = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(filename));
			document.normalize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	public static void xmlUpdateDemo() {
		LoggerUtil.info(InitJdbMonitorConfig.class,"====初始化jdbcmonitorconfig.xml文件====");
		String jdbmonitor_filePath = InitJdbMonitorConfig.class.getResource("/").getPath() + "jdbmonitorconfig.xml";
		String properties_filePath = InitJdbMonitorConfig.class.getResource("/").getPath() + "config-database.properties";
		Document document = load(jdbmonitor_filePath);
		Node root = document.getDocumentElement();

		if(root.hasChildNodes()) {
			NodeList ftpnodes = root.getChildNodes();
			for (int i = 0; i < ftpnodes.getLength(); i++) {
				NodeList ftplist = ftpnodes.item(i).getChildNodes();
				for (int k = 0; k < ftplist.getLength(); k++) {
					Node subnode = ftplist.item(k);
					if(subnode.getNodeName().equalsIgnoreCase("Listener")) {
						String method = subnode.getAttributes().getNamedItem("method").getNodeValue();
						if(method.equalsIgnoreCase("database")) {												//表示数据库方式，那么需要修改数据库的相关url信息
							String driverUrl = PropertyFileUtil.getValue(properties_filePath, "driverUrl");
							driverUrl = driverUrl.substring(driverUrl.indexOf(":url=")+":url=".length());
							String user = PropertyFileUtil.getValue(properties_filePath, "user");
							String password = PropertyFileUtil.getValue(properties_filePath, "password");
							//dburl=jdbc:oracle:thin:@127.0.0.1:1521:orcl;user=cloud3;password=cloud3;logtable=T_Log_SQLLog
							String dburl = "dburl="+driverUrl+";user="+user+";password="+password+";logtable=T_Log_SQLLog";
							subnode.getAttributes().getNamedItem("arg").setNodeValue(dburl);
						}
					}
					if(subnode.getNodeName().equalsIgnoreCase("JdbcDriver")) {									//初始化驱动
						String dbDriver = PropertyFileUtil.getValue(properties_filePath, "dbDriver");
						subnode.getAttributes().getNamedItem("class").setNodeValue(dbDriver);
					}
				}
			}
		}

		doc2XmlFile(document, jdbmonitor_filePath);
	}

}
