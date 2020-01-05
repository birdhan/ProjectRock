package com.cloud.base.test.other;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

public class HttpClientUploadFileTest {

	public void uploadFile(File file, String url) {
		if (!file.exists()) {
			return;
		}
		PostMethod postMethod = new PostMethod(url);
		try {
			// FilePart：用来上传文件的类
			FilePart fp = new FilePart("filedata", file);
			Part[] parts = { fp };

			// 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
			MultipartRequestEntity mre = new MultipartRequestEntity(parts,postMethod.getParams());
			postMethod.setRequestEntity(mre);
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 设置连接时间
			int status = client.executeMethod(postMethod);
			if (status == HttpStatus.SC_OK) {
				System.out.println(postMethod.getResponseBodyAsString());
			} else {
				System.out.println("fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放连接
			postMethod.releaseConnection();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpClientUploadFileTest test = new HttpClientUploadFileTest();
		test.uploadFile(new File("d:/1.gif"),"http://127.0.0.1:8888/Cloud3/demo/uploadFileTest.do");
	}

}
