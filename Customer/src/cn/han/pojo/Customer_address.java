package cn.han.pojo;

public class Customer_address {

	private String id;
	private String customeraddress;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomeraddress() {
		return customeraddress;
	}
	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}
	@Override
	public String toString() {
		return "Customer_address [id=" + id + ", customeraddress=" + customeraddress + "]";
	}
	
	
	
	
	
}
