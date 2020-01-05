package com.word.pojo;

public class Contrastlibrary {

	private String id;
	private String name;
	private String wordurl;
	private String wfid;
	private String uid;
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
	public String getWordurl() {
		return wordurl;
	}
	public void setWordurl(String wordurl) {
		this.wordurl = wordurl;
	}
	public String getWfid() {
		return wfid;
	}
	public void setWfid(String wfid) {
		this.wfid = wfid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Contrastlibrary [id=" + id + ", name=" + name + ", wordurl=" + wordurl + ", wfid=" + wfid + ", uid="
				+ uid + "]";
	}

	
}
