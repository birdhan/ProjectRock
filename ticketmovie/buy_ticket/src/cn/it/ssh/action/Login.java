package cn.it.ssh.action;



import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.it.ssh.entity.users;
import cn.it.ssh.service.UserService;

public class Login extends ActionSupport{

	private String username;
	private String password;
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private UserService service;

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}
private String result="";
	public String execute() throws Exception {
		List<users> listuser=this.service.fingByName(username, password);
	    System.out.println(listuser);
	    /*System.out.println(listuser.get(0).getUsername());
	    System.out.println(listuser.get(0).getPassword());*/
	    if(listuser.isEmpty()) {
	    	result="error";
	    }else {
	    	int id=listuser.get(0).getId();
	    	String uname=listuser.get(0).getUsername();
	    	String usex=listuser.get(0).getSex();
	    	int uage=listuser.get(0).getId();
	    	String utel=listuser.get(0).getTel();
	    	System.out.println(id);
	    	HttpSession session = ServletActionContext.getRequest().getSession();
	    	session.setAttribute("user_id", id);
	    	session.setAttribute("name", uname);
	    	session.setAttribute("sex", usex);
	    	session.setAttribute("age", uage);
	    	session.setAttribute("phone", utel);
	    	System.out.println(session.getAttribute("user_id"));
	 
	    	result="success";
	    	
	    }
	    
	   
	
	     return result;
	}
	
}
