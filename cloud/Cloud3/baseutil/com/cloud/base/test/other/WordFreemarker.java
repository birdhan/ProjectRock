package com.cloud.base.test.other;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import sun.misc.BASE64Encoder;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class WordFreemarker {

	public void exportSimpleWord() throws Exception {
		// 要填充的数据, 注意map的key要和word中${xxx}的xxx一致
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("title", "标题");
		dataMap.put("name", "中国");
		dataMap.put("sex", "男");
		dataMap.put("age", "100");
		dataMap.put("contentVal", "内容");
		dataMap.put("createTime", "2014-05-14 17:07:11");
		dataMap.put("picpath", getImageStr());

		// Configuration用于读取ftl文件
		Configuration configuration = new Configuration();
		configuration.setDefaultEncoding("utf-8");

		/*
		 * 以下是两种指定ftl文件所在目录路径的方式, 注意这两种方式都是 指定ftl文件所在目录的路径,而不是ftl文件的路径
		 */
		// 指定路径的第一种方式(根据某个类的相对路径指定)
		// configuration.setClassForTemplateLoading(this.getClass(),"");

		// 指定路径的第二种方式,我的路径是C:/a.ftl
		configuration.setDirectoryForTemplateLoading(new File("c:/ftl"));

		// 输出文档路径及名称
		File outFile = new File("D:/test.doc");

		// 以utf-8的编码读取ftl文件
		Template t = configuration.getTemplate("a.ftl", "utf-8");
		Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
		t.process(dataMap, out);
		out.close();
	}

	public String getImageStr() {
		String imgFile = "c:/123.jpg";
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws Exception {
		// TODO Auto-generated method stub
		WordFreemarker wf = new WordFreemarker();
		wf.exportSimpleWord();
	}

}
