package com.fmcg.dao;

import java.util.List;

import com.fmcg.pojo.Shoppingcart;

public interface ShoppingcartMapper {
	
	public void addshopping(Shoppingcart shoping);
	
	public void updatestatusById(String id,String status);
	
	public void updatesnumberById(String id,String number,String spay);
	
	public List<Shoppingcart> showshoppingByAll();
	
	public List<Shoppingcart> findshoppingcartById(String id);
	
	public List<Shoppingcart> findAllByType(String type,String uid);
}
