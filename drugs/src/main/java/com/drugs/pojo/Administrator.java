package com.drugs.pojo;

public class Administrator {

	private int id;
	private String name;
	private String password;
	private String abalance;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbalance() {
		return abalance;
	}
	public void setAbalance(String abalance) {
		this.abalance = abalance;
	}
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", name=" + name + ", password=" + password + ", abalance=" + abalance + "]";
	}
	
	
}
