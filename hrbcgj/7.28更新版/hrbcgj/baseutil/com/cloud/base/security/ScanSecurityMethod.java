package com.cloud.base.security;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cloud.base.annotation.security.Security;
import com.cloud.base.test.other.PackageScan;
import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.StringUtil;
import com.cloud.menumanager.dao.MenuDaoHibernate;
import com.cloud.menumanager.model.Menu;
import com.cloud.menupriv.dao.MenuPrivDaoHibernate;
import com.cloud.menupriv.model.MenuPriv;

/**
 * 扫描带有Security注解的action，找到其方法
 * @author cloud7
 *
 */
public class ScanSecurityMethod {
	
	/**
	 * 扫描入口方法
	 * @param packageName
	 */
	public static void entrance() {
		List<String> classList = PackageScan.getClassName("com");
		MenuDaoHibernate mdh = (MenuDaoHibernate)SpringContextHolder.getApplicationContext().getBean("menuDaoHibernate");
		List<Menu> menuList = mdh.getAllDataByWhere(" and menu.menuType='1'");									//得到所有菜单
		
		JdbcTemplate jdbc = (JdbcTemplate)ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
		jdbc.update("DELETE FROM SYSTEM_MENU_PRIV");
		
		for (String className : classList) {												//得到某个类
			System.out.println("className:" + className);
			if(className.indexOf(".action") == -1) continue;
			String moduleName = className.substring(0, className.indexOf(".action"));
			moduleName = moduleName.substring(moduleName.lastIndexOf(".")+1);				//得到模块名
			Object object = null;
			try {
				System.out.println(className);
				object = Class.forName(className).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}																				//实例对象
			
			List<MenuPriv> saveList = new ArrayList<MenuPriv>();
			for(Menu menu : menuList) {
				String menuUrl = menu.getMenuUrl();
				if(!StringUtil.null2String(menuUrl).equals("")) {
					if(menuUrl.indexOf("/") != -1) {
						String menuModule = menuUrl.substring(menuUrl.indexOf("/")+1, menuUrl.lastIndexOf("/"));	//得到某菜单的模块
						if(menuModule.equalsIgnoreCase(moduleName)) {
							if(object != null) {
								Method[] method_arr = object.getClass().getDeclaredMethods();
								if(method_arr != null) {
									for(Method method : method_arr) {
										if(method.getAnnotation(Security.class) != null) {							//表示带有security注解的方法
											String url = "/"+moduleName+"/"+method.getName()+".do";
											String privName = method.getAnnotation(Security.class).privName();
											String roleType = method.getAnnotation(Security.class).roleType();
											String privKey = method.getAnnotation(Security.class).privKey();
											
											if(roleType.equalsIgnoreCase("priv")) {
												MenuPriv mp = new MenuPriv();
												mp.setPrivName(privName);
												mp.setPrivNo(privKey);
												mp.setUrl(url);
												mp.setLinkMenuId(menu.getId());
												saveList.add(mp);
											}
											System.out.println("URL："+url + ",名称："+privName+",类型："+roleType+",编号："+privKey);
										} 			
									}
								}							
								break;
							}							
						}
					}
				}
			}
			if(saveList.size() != 0) {				
				MenuPrivDaoHibernate mpdh = (MenuPrivDaoHibernate)SpringContextHolder.getApplicationContext().getBean("menuPrivDaoHibernate");
				mpdh.saveDataBatch(saveList);
			}
		}
	}

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
}
