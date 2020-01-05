package com.cloud.base.util;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * MySql数据库备份还原工具类
 * @author cloud7
 *
 */
public class MySqlDBUtil {

	/**
	 * 备份数据库
	 */
	public static void bakeup() {
		try {
			Runtime rt = Runtime.getRuntime();			
			Process child = rt.exec("D://software//MySQL Server 5.0//bin//mysqldump -h localhost -uroot -proot  cloud3");
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
}
