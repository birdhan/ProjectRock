package com.cloud.base.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/**
 * http模拟客户端请求
 * @author cloud-black
 *
 */
public class HttpUtil {

	/**
	 * 请求
	 * @param url
	 * @return
	 */
	public static String postExecute(String url) {
		return postExecute(url,null);
	}
	
	/**
	 * post请求
	 * @param url：请求地址
	 * @param params：传入参数
	 * @return
	 */
	public static String postExecute(String url , NameValuePair[] params) {
		String result = "";
		try {
			HttpClient client = new HttpClient();											//创建客户端
	        PostMethod post = new PostMethod(url);											//创建请求方法
	        post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");	//设置编码格式
	        if(params != null) {															//传入参数是否为空情况
		        post.setRequestBody(params);
	        }	         
	        client.executeMethod(post);														//执行
	        result = post.getResponseBodyAsString();										//得到返回流
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return result;
		}
	}
}
