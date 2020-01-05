package com.cloud.base.test.other;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class GetPicTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
//	public String testPic4Http() throws Exception {
//		String urlStr = "http://s0.hao123img.com/res/r/image/2014-05-06/71158d2c7dbbe1533690bef07cfffbb7.jpg";							//拼写url地址串
//		HttpClient client = new HttpClient();
//        GetMethod get = new GetMethod(urlStr);		        
//        client.executeMethod(get);
//        int code = get.getStatusCode();
//        if(code == 200) {
//        	InputStream in = get.getResponseBodyAsStream();        	
//			OutputStream os = response.getOutputStream();							//输出流
//        	
//			if(in != null && os != null) {
//				byte[] b = new byte[1024];
//				int len;
//				while ((len = in.read(b)) > 0){
//					os.write(b, 0, len);
//				}
//				in.close();
//				os.flush();
//				os.close();
//			}	
//        }
//		return null;
//	}
	
	
//	public String testPic4File() throws Exception {
//    	InputStream in = new FileInputStream(new File("c:/123.jpg"));
//    	OutputStream os = response.getOutputStream();	
//    	
//		if(in != null && os != null) {
//			byte[] b = new byte[1024];
//			int len;
//			while ((len = in.read(b)) > 0){
//				os.write(b, 0, len);
//			}
//			in.close();
//			os.flush();
//			os.close();
//		}	
//		return null;
//	}

}
