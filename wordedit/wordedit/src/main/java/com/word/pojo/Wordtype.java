package com.word.pojo;

public class Wordtype {

	private String id;
	private String name;
	private String wtype;
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
	public String getWtype() {
		return wtype;
	}
	public void setWtype(String wtype) {
		this.wtype = wtype;
	}
	@Override
	public String toString() {
		return "Wordtype [id=" + id + ", name=" + name + ", wtype=" + wtype + "]";
	}
	
}
