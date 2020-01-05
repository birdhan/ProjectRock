package cn.it.ssh.service;

import java.util.List;

import cn.it.ssh.entity.users;

public interface UserService {

	public void save(users user);
	
	
	public List<users> fingByName(String username,String password);
	
	
}
