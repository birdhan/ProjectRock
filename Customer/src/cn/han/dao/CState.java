package cn.han.dao;

import java.util.List;

import cn.han.pojo.Customer_state;

public interface CState {

	
	public List<Customer_state> findlist();
	
	public void delete01(String id);
	
	public void addone(Customer_state customer_state);
	
	
	
	
	
}
