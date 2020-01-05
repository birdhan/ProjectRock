package com.cloud.base.test.other;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class TestMysql {

	public static void main(String[] args) {
//		System.out.println("开始备份...");
		backup();
//		System.out.println("备份成功...");
//
//		System.out.println("开始还原...");
//		//load1();
//		System.out.println("还原成功...");
		
		System.out.println(TestMysql.class.getResource("../../").getPath());
	}

	public static void backup() {
		try {
			Runtime rt = Runtime.getRuntime();
			
			Process child = rt.exec("D://software//MySQL Server 5.0//bin//mysqldump -h localhost -uroot -proot cloud3");
			InputStream in = child.getInputStream();
			InputStreamReader xx = new InputStreamReader(in, "utf-8");

			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(xx);
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			FileOutputStream fout = new FileOutputStream("c:/test.sql");
			OutputStreamWriter writer = new OutputStreamWriter(fout, "utf-8");
			writer.write(outStr);
			writer.flush();
			in.close();
			xx.close();
			br.close();
			writer.close();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void load1() {
		try {
			String fPath = "c:/test.sql";
			Runtime rt = Runtime.getRuntime();

			Process child = rt.exec("D://software//MySQL Server 5.0//bin//mysql.exe -ucloud3 -pcloud3 cloudwork ");
			OutputStream out = child.getOutputStream();
			String inStr;
			StringBuffer sb = new StringBuffer("");
			String outStr;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fPath), "utf8"));
			while ((inStr = br.readLine()) != null) {
				sb.append(inStr + "\r\n");
			}
			outStr = sb.toString();
			OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
			writer.write(outStr);
			writer.flush();
			out.close();
			br.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
