package cn.it.ssh.service.impl;

import java.util.List;

import cn.it.ssh.dao.UserDao;
import cn.it.ssh.entity.users;
import cn.it.ssh.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save(users user) {
		this.userDao.saveUser(user);

	}

	@Override
	public List<users> fingByName(String username,String password) {
		
		return this.userDao.findUserName(username, password);
	}


}
