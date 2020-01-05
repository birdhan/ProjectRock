package com.cloud.base.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.jdbc.core.JdbcTemplate;

import com.cloud.base.util.ApplicationContextHolder;
import com.cloud.base.util.LoggerUtil;
import com.cloud.base.util.SpringContextHolder;
import com.cloud.login.model.SuperAdmin;
import com.cloud.menupriv.dao.MenuPrivDaoHibernate;
import com.cloud.menupriv.model.MenuPriv;
import com.cloud.systemuser.model.SystemUser;

/**
 * 检查登录人是否有权限访问该请求
 * @author cloud-black
 *
 */
public class CheckRequestPrivFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		
		String lastRequestURI = (String)request.getSession().getAttribute("lastRequestURI");
		
		SystemUser su = (SystemUser)request.getSession().getAttribute("user");
		String userId = su.getUserId();														//登录人的登录帐号
		
		SuperAdmin sa = (SuperAdmin)ApplicationContextHolder.getInstance().getBean("superAdmin");
		
		if(sa.getMap().get(userId) == null) {												//如果登录人不是超级管理员
			JdbcTemplate jdbc = (JdbcTemplate)ApplicationContextHolder.getInstance().getBean("jdbcTemplate");
			MenuPrivDaoHibernate mpdh = (MenuPrivDaoHibernate)SpringContextHolder.getApplicationContext().getBean("menuPrivDaoHibernate");
			
			List<MenuPriv> linkMenuIdList = mpdh.getAllDataByWhere(" and menuPriv.url='"+request.getRequestURI().replace(request.getContextPath(), "")+"'");
			
			if(linkMenuIdList != null && linkMenuIdList.size() != 0) {						//取当前URL下的菜单ID，根本菜单id去寻找角色id
				String linkMenuId = linkMenuIdList.get(0).getLinkMenuId();
				String privNo = linkMenuIdList.get(0).getPrivNo();							//得到权限编码
				List<Map<String,Object>> roleIdList = jdbc.queryForList("select linkroleid,privno " +
						"from system_rolemenupriv where linkmenuid='"+linkMenuId+"'");												//得到某菜单所关联的角色id
				
				List<Map<String,Object>> roleUserList =  jdbc.queryForList("select linkroleid from SYSTEM_ROLEUSER where userid='"+userId+"'");	//得到当前人所在的角色id
				
				boolean flag = false;
				for(Map<String,Object> mr : roleIdList) {									//遍历菜单角色关系取出角色ID
					String lri = (String)mr.get("linkroleid");
					String privno_role = (String)mr.get("privno");
					for(Map<String,Object> ru : roleUserList) {								//遍历人员角色关系取出角色ID
						String uri = (String)ru.get("linkroleid");
						if(lri.equals(uri)) {
							if(privno_role.equals(privNo)) {								//请求的URL权限编码与角色中的编码一致
								flag = true;
								break;
							}							
						}
					}
				}
				
				if(flag == false) {
					LoggerUtil.info(getClass(), "您没有权限访问该URL"+request.getContextPath());
					response.getWriter().print("<script language='javascript'>alert('1:您没有权限访问该URL！');parent.document.getElementById('right_iframe').src='"+lastRequestURI+"'</script>");
					return;
				}
			} 
			
		} else if(!sa.getMap().get(userId).equals("yes")) {									//不是超级管理员
			LoggerUtil.info(getClass(), "您没有权限访问该URL"+request.getContextPath());
			response.getWriter().print("<script language='javascript'>alert('2:您没有权限访问该URL！');parent.document.getElementById('right_iframe').src='"+lastRequestURI+"'</script>");
			return;
		} 
		
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
