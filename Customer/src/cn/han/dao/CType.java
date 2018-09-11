package cn.han.dao;

import java.util.List;

import cn.han.pojo.Customer_type;

public interface CType {

	
	public List<Customer_type> findlist();
	
	public void delete01(String id);
	
	public void addone(Customer_type customer_type);
	
	
	
	
	
}
