package com.drugs.pojo;

public class Drugs {

	
	private int id;
	private String dname;
	private String companyname;
	private String jianshu;
	private String detais;
	private String type;
	private String companyadd;
	private String dprice;
	private String usetype;
	private String pictureurl;
	private int eid;
	private String stock;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getJianshu() {
		return jianshu;
	}
	public void setJianshu(String jianshu) {
		this.jianshu = jianshu;
	}
	public String getDetais() {
		return detais;
	}
	public void setDetais(String detais) {
		this.detais = detais;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCompanyadd() {
		return companyadd;
	}
	public void setCompanyadd(String companyadd) {
		this.companyadd = companyadd;
	}
	public String getDprice() {
		return dprice;
	}
	public void setDprice(String dprice) {
		this.dprice = dprice;
	}
	public String getUsetype() {
		return usetype;
	}
	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Drugs [id=" + id + ", dname=" + dname + ", companyname=" + companyname + ", jianshu=" + jianshu
				+ ", detais=" + detais + ", type=" + type + ", companyadd=" + companyadd + ", dprice=" + dprice
				+ ", usetype=" + usetype + ", pictureurl=" + pictureurl + ", eid=" + eid + ", stock=" + stock
				+ ", status=" + status + "]";
	}
	
	
	
	
	
}
