package com.drugs.pojo;

public class Dorder {

	private int id;
	private String dname;
	private String dtype;
	private String dprice;
	private String dstatus;
	private int uid;
	private int did;
	private String uname;
	private String uaddress;
	private String utel;
	private String yunnumber;
	private int eid;
	private String odate;
	private String loan;
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
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public String getDprice() {
		return dprice;
	}
	public void setDprice(String dprice) {
		this.dprice = dprice;
	}
	public String getDstatus() {
		return dstatus;
	}
	public void setDstatus(String dstatus) {
		this.dstatus = dstatus;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUaddress() {
		return uaddress;
	}
	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getYunnumber() {
		return yunnumber;
	}
	public void setYunnumber(String yunnumber) {
		this.yunnumber = yunnumber;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getOdate() {
		return odate;
	}
	public void setOdate(String odate) {
		this.odate = odate;
	}
	public String getLoan() {
		return loan;
	}
	public void setLoan(String loan) {
		this.loan = loan;
	}
	@Override
	public String toString() {
		return "Dorder [id=" + id + ", dname=" + dname + ", dtype=" + dtype + ", dprice=" + dprice + ", dstatus="
				+ dstatus + ", uid=" + uid + ", did=" + did + ", uname=" + uname + ", uaddress=" + uaddress + ", utel="
				+ utel + ", yunnumber=" + yunnumber + ", eid=" + eid + ", odate=" + odate + ", loan=" + loan + "]";
	}
	
	
}
