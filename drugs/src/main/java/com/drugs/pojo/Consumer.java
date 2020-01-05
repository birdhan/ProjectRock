package com.drugs.pojo;

public class Consumer {

	private int id;
	private String uname;
	private String pwd;
	private String ubalance;
	private String tel;
	private String address;
	private String utype;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUbalance() {
		return ubalance;
	}
	public void setUbalance(String ubalance) {
		this.ubalance = ubalance;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	@Override
	public String toString() {
		return "Consumer [id=" + id + ", uname=" + uname + ", pwd=" + pwd + ", ubalance=" + ubalance + ", tel=" + tel
				+ ", address=" + address + ", utype=" + utype + "]";
	}
	
	
	
}
