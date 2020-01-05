package com.word.pojo;

public class Wordfile {

	private String id;
	private String wname;
	private String picurl;
	private Integer readnumber;
	private String wordtype;
	private String pngurl;
	private String worddate;
	private String wauthor;
	private String fathercolumn;
	private String uid;
	private String annotation;
	private String htmlurl;
	private String wordurl;
	private String serveraddress;
	
	public String getServeraddress() {
		return serveraddress;
	}
	public void setServeraddress(String serveraddress) {
		this.serveraddress = serveraddress;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWname() {
		return wname;
	}
	public void setWname(String wname) {
		this.wname = wname;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public Integer getReadnumber() {
		return readnumber;
	}
	public void setReadnumber(Integer readnumber) {
		this.readnumber = readnumber;
	}
	public String getWordtype() {
		return wordtype;
	}
	public void setWordtype(String wordtype) {
		this.wordtype = wordtype;
	}
	public String getPngurl() {
		return pngurl;
	}
	public void setPngurl(String pngurl) {
		this.pngurl = pngurl;
	}
	public String getWorddate() {
		return worddate;
	}
	public void setWorddate(String worddate) {
		this.worddate = worddate;
	}
	public String getWauthor() {
		return wauthor;
	}
	public void setWauthor(String wauthor) {
		this.wauthor = wauthor;
	}
	public String getFathercolumn() {
		return fathercolumn;
	}
	public void setFathercolumn(String fathercolumn) {
		this.fathercolumn = fathercolumn;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getAnnotation() {
		return annotation;
	}
	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	public String getHtmlurl() {
		return htmlurl;
	}
	public void setHtmlurl(String htmlurl) {
		this.htmlurl = htmlurl;
	}
	public String getWordurl() {
		return wordurl;
	}
	public void setWordurl(String wordurl) {
		this.wordurl = wordurl;
	}
	@Override
	public String toString() {
		return "Wordfile [id=" + id + ", wname=" + wname + ", picurl=" + picurl + ", readnumber=" + readnumber
				+ ", wordtype=" + wordtype + ", pngurl=" + pngurl + ", worddate=" + worddate + ", wauthor=" + wauthor
				+ ", fathercolumn=" + fathercolumn + ", uid=" + uid + ", annotation=" + annotation + ", htmlurl="
				+ htmlurl + ", wordurl=" + wordurl + "]";
	}
	
	
}
