package cn.han.dao;

import java.util.List;

import cn.han.pojo.Customer_address;

public interface Address {

	
	public List<Customer_address> findlist();
	
	public void delete01(String id);
	
	public void addone(Customer_address customer_address);
	
	public Integer countAddress();
	
	
	
}
