package cn.han.dao;

import java.util.List;

import cn.han.pojo.Customer_order;

public interface Order {

	public List<Customer_order> findAll();
	
	public List<Customer_order> findBySome(Customer_order cuso);
	
	public List<Customer_order> findByPayment();
	
	public void delete(String id);
		
	public void updata(Customer_order customer_up);	
	
	public void addOrder(Customer_order customer_add);
	
	public Integer paycount1();
	
	public Integer paycount2();
	
}
