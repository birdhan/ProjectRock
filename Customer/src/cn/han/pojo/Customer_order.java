package cn.han.pojo;



public class Customer_order {

	private String id;
	
	private String user;
	
	private String tel;
	
	private String contractnumber;
	
	private String ordernumber;
	
	private String title;
	
	private String money;
	
	private String paymentdate;
	
	private String paymentstate;
	
	private String details;
	
	private String uid;
	
	private Customer_user customer_user;

	public Customer_user getCustomer_user() {
		return customer_user;
	}

	public void setCustomer_user(Customer_user customer_user) {
		this.customer_user = customer_user;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getContractnumber() {
		return contractnumber;
	}

	public void setContractnumber(String contractnumber) {
		this.contractnumber = contractnumber;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String  getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}

	public String getPaymentstate() {
		return paymentstate;
	}

	public void setPaymentstate(String paymentstate) {
		this.paymentstate = paymentstate;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "Customer_order [id=" + id + ", user=" + user + ", tel=" + tel + ", contractnumber=" + contractnumber
				+ ", ordernumber=" + ordernumber + ", title=" + title + ", money=" + money + ", paymentdate="
				+ paymentdate + ", paymentstate=" + paymentstate + ", details=" + details + ", uid=" + uid + "]";
	}
	
	
}
