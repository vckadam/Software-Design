package com.vckadam.oopdesign.hr.model;

import java.util.List;

public class DepartmentsByCountry {
	private Country country;
	private List<Department> depts;
	public DepartmentsByCountry(Country country, List<Department> depts) {
		super();
		this.country = country;
		this.depts = depts;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public List<Department> getDepts() {
		return depts;
	}
	public void setDepts(List<Department> depts) {
		this.depts = depts;
	}
	public String toString() {
		return this.getCountry().getCountryId();
	}
}
