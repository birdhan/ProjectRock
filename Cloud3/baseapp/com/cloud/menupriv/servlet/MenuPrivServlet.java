package com.cloud.menupriv.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cloud.base.cache.SysCache;
import com.cloud.base.security.MyInvocationSecurityMetadataSource;
import com.cloud.base.security.ScanSecurityMethod;
import com.cloud.base.util.ApplicationContextHolder;

public class MenuPrivServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MenuPrivServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
		System.out.println("=====初始化菜单权限=====");
		ScanSecurityMethod.entrance();											//初始化菜单权限
		JdbcTemplate jdbc = (JdbcTemplate)ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
		
		
		String dataBaseType = SysCache.getInstance().getDataBase();				//得到数据库类型
		
		if(dataBaseType.equalsIgnoreCase("oracle")) {
			/**
			 * oracle方式
			 */
			String delNotPrivSql = "delete system_rolemenupriv where id in (select id from system_rolemenupriv sr where sr.linkmenuid||sr.privno not in (select smp.linkmenuid||smp.privno from system_menu_priv smp where linkmenuid=sr.linkmenuid and privno=sr.privno))";
			jdbc.update(delNotPrivSql);												//撤消不存在于菜单权限表中的权限
			System.out.println("删除不存在于菜单权限表中的权限_sql:"+delNotPrivSql);
		} else if(dataBaseType.equalsIgnoreCase("mysql")) {
			
			/**
			 * mysql方式
			 */
			String querySql = "select id from system_rolemenupriv where id in " +
					"(select id from system_rolemenupriv sr where sr.linkmenuid+sr.privno not in " +
					"(select smp.linkmenuid+smp.privno from system_menu_priv smp where linkmenuid=sr.linkmenuid and privno=sr.privno))";
			List idsList = jdbc.queryForList(querySql);
			if(idsList != null && idsList.size() != 0) {
				StringBuffer idsBuffer = new StringBuffer("");
				for (int i = 0; i < idsList.size(); i++) {
					Map m = (Map)idsList.get(i);
					idsBuffer.append("'"+m.get("id")+"',");
				}
				String ids = idsBuffer.toString();
				if(!ids.equals("")) {
					ids = ids.substring(0,ids.length()-1);
				}
				
				String delSql = "delete from system_rolemenupriv where id in ("+ids+")";
				jdbc.update(delSql);
				System.out.println("删除不存在于菜单权限表中的权限_sql:"+delSql);
			}
		}
		
		
		
		new MyInvocationSecurityMetadataSource();								//重新加载资源关系	
	}

}
