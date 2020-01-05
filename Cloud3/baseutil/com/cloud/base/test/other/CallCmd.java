package com.cloud.base.test.other;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CallCmd {

	/**
	 * 直接捕获输出信息
	 */
	private static void readFmConsole() {
		try {
			Process process = Runtime.getRuntime().exec("C:/Users/cloud7/Desktop/bake.bat"); // 调用bat文件
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String str = reader.readLine();
			while (str != null) {
				System.out.println(str);
				str = reader.readLine();
				System.out.println("0");
			}
			System.gc();
			System.out.println("1");
			reader.close();
			System.out.println("2");
			process.destroy();
			System.out.println("3");
			reader = null;
			process = null;
			reader.close();			
			System.out.println("执行结束");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
//		new CallCmd().readFmConsole();
		System.out.println(CallCmd.class.getResource("/").getPath().replace("WEB-INF/classes/", "sql/bake.bat"));
	}
}
