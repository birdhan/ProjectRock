package com.fmcg.pojo;

public class Kuaixiao {
	private String id;
	private String kname;
	private String kprice;
	private String ktype;
	private String kpic;
	private String kpoint;
	private String kdetails;
	private String kdate;
	private Integer kcheck;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	public String getKprice() {
		return kprice;
	}
	public void setKprice(String kprice) {
		this.kprice = kprice;
	}
	public String getKtype() {
		return ktype;
	}
	public void setKtype(String ktype) {
		this.ktype = ktype;
	}
	public String getKpic() {
		return kpic;
	}
	public void setKpic(String kpic) {
		this.kpic = kpic;
	}
	public String getKpoint() {
		return kpoint;
	}
	public void setKpoint(String kpoint) {
		this.kpoint = kpoint;
	}
	public String getKdetails() {
		return kdetails;
	}
	public void setKdetails(String kdetails) {
		this.kdetails = kdetails;
	}
	public String getKdate() {
		return kdate;
	}
	public void setKdate(String kdate) {
		this.kdate = kdate;
	}
	public Integer getKcheck() {
		return kcheck;
	}
	public void setKcheck(Integer kcheck) {
		this.kcheck = kcheck;
	}
	@Override
	public String toString() {
		return "Kuaixiao [id=" + id + ", kname=" + kname + ", kprice=" + kprice + ", ktype=" + ktype + ", kpic=" + kpic
				+ ", kpoint=" + kpoint + ", kdetails=" + kdetails + ", kdate=" + kdate + ", kcheck=" + kcheck + "]";
	}
	
	
	
}
