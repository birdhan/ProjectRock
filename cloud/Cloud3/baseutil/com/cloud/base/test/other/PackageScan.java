package com.cloud.base.test.other;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import sun.print.resources.serviceui;

import com.cloud.base.annotation.propertydesc.PropertyDesc;
import com.cloud.base.annotation.security.Security;

public class PackageScan {
	public static List<String> getClassName(String packageName) {
		List<String> classNames = new ArrayList<String>();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			String resourceName = packageName.replaceAll("\\.", "/");
			URL url = loader.getResource(resourceName);
			File urlFile = new File(url.toURI());
			File[] files = urlFile.listFiles();
			for (File f : files) {
				getClassName(packageName, f, classNames);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return classNames;
	}

	private static void getClassName(String packageName, File packageFile,List<String> list) {
		if (packageFile.isFile()) {
			if(packageFile.getName().indexOf(".class") > 0 && packageName.indexOf("action") > 0) {		//过滤出所有action层的action文件
				list.add(packageName + "."+ packageFile.getName().replace(".class", ""));
			}
		} else {
			File[] files = packageFile.listFiles();
			String tmPackageName = packageName + "." + packageFile.getName();
			for (File f : files) {
				getClassName(tmPackageName, f, list);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		List<String> list = PackageScan.getClassName("com.cloud");
		for (String className : list) {
			System.out.println("=====================================");
			System.out.println("类名："+className);
			String moduleName = className.substring(0, className.indexOf(".action"));
			moduleName = moduleName.substring(moduleName.lastIndexOf(".")+1);
			System.out.println("模块："+moduleName);
			Object object = Class.forName(className).newInstance();												//实例对象
			Method[] method_arr = object.getClass().getDeclaredMethods();
			for(Method method : method_arr) {
				System.out.println("\n方法："+method.getName());
				if(method.getAnnotation(Security.class) != null) {
					System.out.println("URL："+"/"+moduleName+"/"+method.getName()+".do");
					System.out.println("名称："+method.getAnnotation(Security.class).privName());
					System.out.println("类型："+method.getAnnotation(Security.class).roleType());
					System.out.println("编号："+method.getAnnotation(Security.class).privKey()+"\n");
				} else {
					System.out.println("名称：");
					System.out.println("编号：\n");
				}				
			}
			System.out.println();
		}
//		String aaa = "/aaaaaa/";
//		String abc = aaa.substring(aaa.indexOf("/")+1, aaa.lastIndexOf("/"));
//		System.out.println(abc);

	}

}
