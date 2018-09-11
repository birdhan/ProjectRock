package cn.han.pojo;

public class Customer_state {

	private String id;
	
	private String customerstate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerstate() {
		return customerstate;
	}

	public void setCustomerstate(String customerstate) {
		this.customerstate = customerstate;
	}

	@Override
	public String toString() {
		return "Customer_state [id=" + id + ", customerstate=" + customerstate + "]";
	}
	
	
}
