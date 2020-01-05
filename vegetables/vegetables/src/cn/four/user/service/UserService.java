package cn.four.user.service;

import cn.four.tools.util.CommonUtils;
import cn.four.user.dao.UserDao;
import cn.four.user.domain.User;

public class UserService {
	private UserDao userdao=new UserDao();
	public void regist(User user){
		/**
		 * 1.补齐数据
		 */
		user.setUid(CommonUtils.uuid());
		/**
		 * 添加数据
		 */
		try {
			userdao.addUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User findUser(User user){
		
		return userdao.findUser(user.getUname(), user.getUpassword());
	}
	
}
