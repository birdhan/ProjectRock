package cn.han.dao;

import java.util.List;

import cn.han.pojo.Customer_user;

public interface User {

	
	public List<Customer_user> findAll();
	
	public List<Customer_user> findBySome(Customer_user users);
	
	public void deleteUser(String id);
	
	public void addUser(Customer_user users);
	
	public void updateUser(Customer_user cusUser);
	
	public Integer count();
	
	public List<Customer_user> findByType(String customertype);
	
	public List<Customer_user> findByState(String customerstate);
	
	public List<Customer_user> findByfenye(Integer row);
}
