package cn.han.pojo;

public class Customer_regist {

	private String id;
	
	private String user;
	
	private String pwd;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	@Override
	public String toString() {
		return "Customer_regist [id=" + id + ", user=" + user + ", pwd=" + pwd + "]";
	}
	
	
}
