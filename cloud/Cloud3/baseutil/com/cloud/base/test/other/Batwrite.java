package com.cloud.base.test.other;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CountDownLatch;

import com.cloud.base.util.LoggerUtil;

public class Batwrite {
	public static void WriteFile(String bat) {
		try {
			File FileName = new File("d://b.bat");
			FileOutputStream fileOut = new FileOutputStream(FileName);
			DataOutputStream fou = new DataOutputStream(fileOut);
			fou.write(bat.getBytes());
			fou.close();
		} catch (IOException e) {
			System.out.println("文件错误");
		}
	}
	
	public static void call() {
		try {
			final CountDownLatch threadSignal = new CountDownLatch(2);//初始化countDown
//			String batFilePath = Batwrite.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_bat/bake.bat");
//			String os = System.getProperty("os.name").toLowerCase();
//			if(os.indexOf("windows") != -1) {
//				batFilePath = Batwrite.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_bat/bake.bat");
//			} else if(os.indexOf("linux") != -1 || os.indexOf("unix") != -1) {
//				batFilePath = Batwrite.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bakeup_bat/bake.sh");
//			}
			
//			LoggerUtil.info(Batwrite.class, "准备备份数据库...");
			String batFilePath = "C:/Users/cloud7/Desktop/imp.bat";
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
						LoggerUtil.info(Batwrite.class,"信息 : " + sb.toString());
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
						LoggerUtil.info(Batwrite.class,"文件 : " + sb.toString());
						
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
//			LoggerUtil.info(Batwrite.class, "成功备份数据库...");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
//		String str = "exp cloud3/cloud3@orcl file=C:/Users/cloud7/Desktop/%date:~0,4%%date:~5,2%%date:~8,2%.dmp";
//		WriteFile(str);
//		try {
//			String command = "cmd.exe /c" + "start /min f:\\bat//b.bat";
//			Process child = Runtime.getRuntime().exec(command);
//		} catch (IOException e) {
//			System.out.println("文件错误");
//		}
		call();
	}
}
