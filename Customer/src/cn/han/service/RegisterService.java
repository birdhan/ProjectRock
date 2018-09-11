package cn.han.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.han.dao.Register;
import cn.han.pojo.Customer_regist;

@Service
public class RegisterService {

	@Resource
	private Register register;
	
	public Customer_regist findByOne() {
		
		return register.findByone();
		
	}
	
	public void updateOne(String users,String pwd) {
		register.updateRe(users, pwd);
	}
}
