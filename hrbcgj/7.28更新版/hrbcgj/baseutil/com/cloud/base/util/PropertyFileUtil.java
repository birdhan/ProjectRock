package com.cloud.base.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.lowagie.text.pdf.codec.Base64.OutputStream;

public class PropertyFileUtil {
	public static String getValue(String fileName, String key){
		Properties prop = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(new FileInputStream(fileName));
			prop.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String value = prop.getProperty(key);
		return value;
	}

	public static void writeProperties(String fileName, String key, String value)
			throws IOException {
		Properties prop = new Properties();
		  try {
		  File file = new File(fileName);
		  if (!file.exists())
		  file.createNewFile();
		  InputStream fis = new FileInputStream(file);
		  prop.load(fis);
		  fis.close();
		  FileOutputStream fos = new FileOutputStream(fileName);
		  prop.setProperty(key, value);
		  prop.store(fos, "");
		  fos.close();
		  } catch (IOException e) {
		   e.printStackTrace();
		  
		  }
 

	}
}
