package com.cloud.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.cloud.base.util.SpringContextHolder;
import com.cloud.base.util.StringUtil;
import com.cloud.rolemanager.dao.RoleDaoJDBC;

/** 
 * 
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * 
 * 
 */
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    public MyInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }

    public  void loadResourceDefine() {
    	
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        resourceMap.clear();
        
        RoleDaoJDBC rdJdbc = (RoleDaoJDBC)SpringContextHolder.getApplicationContext().getBean("roleDaoJDBC");
        
        List<Map<String, Object>> allRoleList = rdJdbc.getAllRoles();
        if(allRoleList != null && allRoleList.size() != 0) {
        	for(Map map : allRoleList) {													//遍历所有角色
        		String id = StringUtil.null2String((String)map.get("id"));
        		String name = StringUtil.null2String((String)map.get("name"));
        		
        		Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
                ConfigAttribute ca = new SecurityConfig("ROLE_ADMIN");
                atts.add(ca);
        		List<Map<String, Object>> role_menuUrlList = rdJdbc.getRoleMenuUrl(id);
        		if(role_menuUrlList != null && role_menuUrlList.size() != 0) {
        			for(Map rm : role_menuUrlList) {
        				String menuurl = StringUtil.null2String((String)rm.get("menuurl"));	//得到对应菜单的url
        				resourceMap.put(menuurl, atts);
        			}
        		}
        	}
        }
        System.out.println(resourceMap.toString());
    }

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String url = ((FilterInvocation)object).getRequestUrl();		
        Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            String resURL = ite.next();
            if (resURL.equals(url)) {
            	System.out.println("getKey_value:"+resourceMap.get(resURL));
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
    
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

}