package cn.it.ssh.dao;

import java.util.List;

import cn.it.ssh.entity.users;

public interface UserDao {

	public void saveUser(users user);
	
	
	public List<users> findUserName(String username,String password);
}
