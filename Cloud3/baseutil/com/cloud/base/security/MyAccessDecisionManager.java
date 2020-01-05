package com.cloud.base.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MyAccessDecisionManager implements AccessDecisionManager {

	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			return;
		}
		
		System.out.println("aaa:"+authentication.getAuthorities());
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while (ite.hasNext()) {
			ConfigAttribute ca = ite.next();
			String needRole = ((SecurityConfig) ca).getAttribute();
			System.out.println("needRole:"+needRole);
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				System.out.println(ga.getAuthority());
				if ("ROLE_ANONYMOUS".equals(ga.getAuthority())) { 
					return;
				}
			}
		}
		throw new AccessDeniedException("no right");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
