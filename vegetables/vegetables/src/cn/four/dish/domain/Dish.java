package cn.four.dish.domain;

public class Dish {
	private String did;
	private String dishname;
	private String title;
	private Double price;
	private String  type;
	private int heat;
	private String details;
	private String picture;
	private String animation;
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public String getDishname() {
		return dishname;
	}
	public void setDishname(String dishname) {
		this.dishname = dishname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getHeat() {
		return heat;
	}
	public void setHeat(int heat) {
		this.heat = heat;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getAnimation() {
		return animation;
	}
	public void setAnimation(String animation) {
		this.animation = animation;
	}
	@Override
	public String toString() {
		return "Dish [did=" + did + ", dishname=" + dishname + ", title="
				+ title + ", price=" + price + ", type=" + type + ", heat="
				+ heat + ", details=" + details + ", picture=" + picture
				+ ", animation=" + animation + "]";
	}
	
	
	
}
