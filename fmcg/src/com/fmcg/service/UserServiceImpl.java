package com.fmcg.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fmcg.dao.UserMapper;
import com.fmcg.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userdao;
	
	@Override
	public void registeruser(User user) {
		userdao.registeruser(user);
		
	}

	@Override
	public User findyanzheng(String name, String password) {
		// TODO Auto-generated method stub
		return userdao.findyanzheng(name, password);
	}

	@Override
	public List<User> showuser() {
		// TODO Auto-generated method stub
		List<User> showuser = userdao.showuser();
		return showuser;
	}

	@Override
	public List<User> showuserById(String id) {
		// TODO Auto-generated method stub
		List<User> showuserById = userdao.showuserById(id);
		return showuserById;
	}

	@Override
	public void updateuserById(User user) {
		// TODO Auto-generated method stub
		userdao.updateuserById(user);
		
	}

	@Override
	public void updatebalance(User user) {
		// TODO Auto-generated method stub
		userdao.updatebalance(user);
	}

	@Override
	public void updateByIdbalance(String id, String balance) {
		userdao.updateByIdbalance(id, balance);
		
	}

	
}
