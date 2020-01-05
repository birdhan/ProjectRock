package com.cloud.base.test.other;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * httpclient上传文件
 */
public class MultipartEntityUtil {

	public static String postFile(File file, String url)
			throws ClientProtocolException, IOException {

		FileBody bin = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		if (file != null) {
			bin = new FileBody(file);
		}

		StringBody username = new StringBody("13696900475");
		StringBody password = new StringBody("13696900475");
		// new StringBody("汉字",Charset.forName("UTF-8")));

		MultipartEntity reqEntity = new MultipartEntity();
		reqEntity.addPart("username", username);
		reqEntity.addPart("password", password);
		reqEntity.addPart("data", bin);

		httppost.setEntity(reqEntity);
		System.out.println("执行: " + httppost.getRequestLine());

		HttpResponse response = httpclient.execute(httppost);
		System.out.println("statusCode is " + response.getStatusLine().getStatusCode());
		HttpEntity resEntity = response.getEntity();
		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());
		if (resEntity != null) {
			System.out.println("返回长度: " + resEntity.getContentLength());
			System.out.println("返回类型: " + resEntity.getContentType());
			InputStream in = resEntity.getContent();
			System.out.println("in is " + in);
			// System.out.println(IoStreamUtil.getStringFromInputStream(in));
		}
		if (resEntity != null) {
			resEntity.consumeContent();
		}
		return null;
	}

	public static void main(String[] args) throws ClientProtocolException,IOException {
		File file = new File("d:/1.gif");
		String url = "http://127.0.0.1:8888/Cloud3/demo/uploadFileTest.do";
		postFile(file, url);
	}

}
