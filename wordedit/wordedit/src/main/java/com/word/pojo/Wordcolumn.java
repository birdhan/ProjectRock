package com.word.pojo;

public class Wordcolumn {

	private String id;
	private String cname;
	private String cgrade;
	private String fatherid;
	private String fathername;
	private String columntype;
	private String uid;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCgrade() {
		return cgrade;
	}
	public void setCgrade(String cgrade) {
		this.cgrade = cgrade;
	}
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid;
	}
	public String getFathername() {
		return fathername;
	}
	public void setFathername(String fathername) {
		this.fathername = fathername;
	}
	public String getColumntype() {
		return columntype;
	}
	public void setColumntype(String columntype) {
		this.columntype = columntype;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Wordcolumn [id=" + id + ", cname=" + cname + ", cgrade=" + cgrade + ", fatherid=" + fatherid
				+ ", fathername=" + fathername + ", columntype=" + columntype + ", uid=" + uid + "]";
	}
	
	
	
}
