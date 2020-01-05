package com.fmcg.dao;

import java.util.List;

import com.fmcg.pojo.User;

public interface UserMapper {

	public void registeruser(User user);
	
	public User findyanzheng(String name,String password);
	
	public List<User> showuser(); 
	
	public List<User> showuserById(String id);
	
	public void updateuserById(User user);
	
	public void updatebalance(User user);
	
	public void updateByIdbalance(String id,String balance);
	
	
}
