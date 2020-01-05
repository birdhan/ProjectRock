package com.cloud.base.attachment;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	public  Properties loadProperty() {
		Properties prop=new Properties();
		try {
//			FileInputStream is=new FileInputStream("config.properties");
			String path  = this.getClass().getResource("/com/cloud/base/attachment/downPath.properties").getPath();
			InputStream is = new BufferedInputStream(new FileInputStream(path));

			//InputStream is=this.getClass().getResourceAsStream("/com/cloud/base/attachment/downPath.properties");
			prop.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
}
