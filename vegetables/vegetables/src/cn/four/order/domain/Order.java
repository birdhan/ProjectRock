package cn.four.order.domain;

public class Order {
	private String oid;
	private String uid;
	private String did;
	private String status;
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", uid=" + uid + ", did=" + did
				+ ", status=" + status + "]";
	}
	
}
