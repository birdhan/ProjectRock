package cn.it.ssh.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.it.ssh.dao.UserDao;
import cn.it.ssh.entity.users;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public void saveUser(users user) {
		this.getHibernateTemplate().save(user);

	}

	



	@Override
	public List<users> findUserName(String username, String password) {
		
		 String hql="from users where username='" + username + "' and password='"+ password + "'";
		 
		 List<users> userlist=this.getHibernateTemplate().find(hql);
		 
		 return userlist;
	}

}
