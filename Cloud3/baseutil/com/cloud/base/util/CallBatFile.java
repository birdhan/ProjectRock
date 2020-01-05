package com.cloud.base.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;

/**
 * 调用执行bat批处理文件工具类
 * @author cloud7
 *
 */
public class CallBatFile {

	/**
	 * 执行批处理方法
	 * @param batFilePath
	 */
	public static void call(String batFilePath) {
		try {			
			final CountDownLatch threadSignal = new CountDownLatch(2);//初始化countDown			
			final Process pro = Runtime.getRuntime().exec(batFilePath);
			pro.getOutputStream().close();
			
			Runnable errThread = new Runnable() {
				@Override
				public void run() {
					try {
						InputStream is = pro.getErrorStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line = "";
						StringBuilder sb = new StringBuilder();
						while((line = br.readLine()) != null) {
							sb.append(line);
						}
						LoggerUtil.info(getClass(),"信息 : " + sb.toString());
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					threadSignal.countDown();//线程结束时计数器减1 
				}
			};
			
			new Thread(errThread).start();
			
			Runnable inputThread = new Runnable() {
				@Override
				public void run() {
					try {
						InputStream is = pro.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line = "";
						StringBuilder sb = new StringBuilder();
						while((line = br.readLine()) != null) {
							sb.append(line);
						}
						LoggerUtil.info(getClass(),"文件 : " + sb.toString());
						
						is.close();
						isr.close();
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					threadSignal.countDown();//线程结束时计数器减1 
				}
			};
			
			new Thread(inputThread).start();
			try {
				threadSignal.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 用法
	 * @param args
	 */
	public static void main(String[] args) {
		String batPath = "传入你的bat文件所在的路径";
		CallBatFile.call(batPath);
	}
}
