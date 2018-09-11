package cn.han.pojo;

public class Customer_type {

	private String id;
	
	private String customertype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	@Override
	public String toString() {
		return "Customer_type [id=" + id + ", customertype=" + customertype + "]";
	}
	
	
}
