package com.fmcg.pojo;

public class Shoppingcart {
	private String id;
	private String kid;
	private String kname;
	private String kpicture;
	private Integer snumber;
	private String kpic;
	private String spay;
	private String status;
	private String sdate;
	private String ktype;
	private String uname;
	private String uid;
	private String utel;
	private String uaddress;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKid() {
		return kid;
	}
	public void setKid(String kid) {
		this.kid = kid;
	}
	public String getKname() {
		return kname;
	}
	public void setKname(String kname) {
		this.kname = kname;
	}
	public String getKpicture() {
		return kpicture;
	}
	public void setKpicture(String kpicture) {
		this.kpicture = kpicture;
	}
	public Integer getSnumber() {
		return snumber;
	}
	public void setSnumber(Integer snumber) {
		this.snumber = snumber;
	}
	public String getKpic() {
		return kpic;
	}
	public void setKpic(String kpic) {
		this.kpic = kpic;
	}
	public String getSpay() {
		return spay;
	}
	public void setSpay(String spay) {
		this.spay = spay;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getKtype() {
		return ktype;
	}
	public void setKtype(String ktype) {
		this.ktype = ktype;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	@Override
	public String toString() {
		return "Shoppingcart [id=" + id + ", kid=" + kid + ", kname=" + kname + ", kpicture=" + kpicture + ", snumber="
				+ snumber + ", kpic=" + kpic + ", spay=" + spay + ", status=" + status + ", sdate=" + sdate + ", ktype="
				+ ktype + ", uname=" + uname + ", uid=" + uid + ", utel=" + utel + ", uaddress=" + uaddress + "]";
	}
	
	
	
}
