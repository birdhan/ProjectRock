package cn.han.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.han.dao.User;
import cn.han.pojo.Customer_user;

@Service
public class UserService {

	@Resource
	private User user;
//          查询所有
	public List<Customer_user> findAll(){
		
		return user.findAll();
	}
//	 条件查询
	public List<Customer_user> findBySome(Customer_user users){
	
		return user.findBySome(users);
		
	}
//	删除用户根据id
	public void delete(String id) {
		
		user.deleteUser(id);
	}
//	修改用户信息
	public void update(Customer_user cusUser) {
		user.updateUser(cusUser);
		
		
	}
//	添加用户
	public void addUser(Customer_user users) {
		
		user.addUser(users);
	}
	
	public Integer count() {
		
		return user.count();
	}
	
	public List<Customer_user> findByType(String type){
		
		return user.findByType(type);
	}
	
	public List<Customer_user> findByState(String status){
		
		return user.findByState(status);
	}
	
	public List<Customer_user> findByfenye(Integer row){
		
		
		return user.findByfenye(row);
	}
	
	
	public List<Customer_user> findbyex(){
		return user.findByexpoit();
	}
}
