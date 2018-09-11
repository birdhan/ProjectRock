package cn.han.dao;

import cn.han.pojo.Customer_regist;

public interface Register {

	public Customer_regist findByone();
	
	public void updateRe(String username,String password);
	
}
