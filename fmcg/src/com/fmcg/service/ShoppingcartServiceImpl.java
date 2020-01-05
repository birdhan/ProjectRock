package com.fmcg.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fmcg.dao.ShoppingcartMapper;
import com.fmcg.pojo.Shoppingcart;

@Service
public class ShoppingcartServiceImpl implements ShoppingcartService {

	@Resource
	private ShoppingcartMapper shopping;
	
	@Override
	public void addshopping(Shoppingcart shoping) {
		shopping.addshopping(shoping);;
		
	}

	@Override
	public void updatestatusById(String id, String status) {
		shopping.updatestatusById(id, status);
		
	}

	@Override
	public void updatesnumberById(String id, String number, String spay) {
		shopping.updatesnumberById(id, number, spay);
		
	}

	@Override
	public List<Shoppingcart> showshoppingByAll() {
		// TODO Auto-generated method stub
		List<Shoppingcart> showshoppingByAll = shopping.showshoppingByAll();
		return showshoppingByAll;
	}

	@Override
	public List<Shoppingcart> findshoppingcartById(String id) {
		// TODO Auto-generated method stub
		List<Shoppingcart> findshoppingcartById = shopping.findshoppingcartById(id);
		return findshoppingcartById;
	}

	@Override
	public List<Shoppingcart> findAllByType(String type,String uid) {
		// TODO Auto-generated method stub
		return shopping.findAllByType(type,uid);
	}

}
