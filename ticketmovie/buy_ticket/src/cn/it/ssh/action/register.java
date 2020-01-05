package cn.it.ssh.action;



import com.opensymphony.xwork2.ActionSupport;

import cn.it.ssh.entity.users;
import cn.it.ssh.service.UserService;

public class register extends ActionSupport{
	
	

	private users user;
	private UserService service;
	
	public users getUser() {
		return user;
	}

	public void setUser(users user) {
		this.user = user;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public String execute() throws Exception {
		System.out.println(getUser());
	    this.service.save(user);
	   
	
	return SUCCESS;
	}
	
}
