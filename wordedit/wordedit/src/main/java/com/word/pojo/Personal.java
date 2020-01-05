package com.word.pojo;

public class Personal {

	private String id;
	private String name;
	private String pwd;
	private String utype;
	private String pstatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUtype() {
		return utype;
	}
	public void setUtype(String utype) {
		this.utype = utype;
	}
	public String getPstatus() {
		return pstatus;
	}
	public void setPstatus(String pstatus) {
		this.pstatus = pstatus;
	}
	@Override
	public String toString() {
		return "Personal [id=" + id + ", name=" + name + ", pwd=" + pwd + ", utype=" + utype + ", pstatus=" + pstatus
				+ "]";
	}
	
}
