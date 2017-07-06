package com.vckadam.oopdesign.NorthWind.model;

public class Shipper {
	private final int shipperId;
	private String companyName, phone;
	public Shipper(int shipperId, String companyName, String phone) {
		super();
		this.shipperId = shipperId;
		this.companyName = companyName;
		this.phone = phone;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getShipperId() {
		return shipperId;
	}
	
}
