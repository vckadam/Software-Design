package com.vckadam.oopdesign.hr.model;

public class ExpensiveDeptInCoun {
	private Country country;
	private Department deptment;
	public ExpensiveDeptInCoun(Country country, Department deptment) {
		super();
		this.country = country;
		this.deptment = deptment;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Department getDeptment() {
		return deptment;
	}
	public void setDeptment(Department deptment) {
		this.deptment = deptment;
	}
	
}
