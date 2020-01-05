package com.cloud.base.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();			//权限角色集合
		System.out.println("username:"+username);
		
		if (username.equals("admin")) {
			GrantedAuthority admin = new GrantedAuthorityImpl("ROLE_ADMIN");
			auths.add(admin);
		}
		User user = new User("admin", "123", true, true, true, true, auths);
		return user;
	}

}