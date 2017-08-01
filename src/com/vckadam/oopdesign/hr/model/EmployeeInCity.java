package com.vckadam.oopdesign.hr.model;

import java.util.List;

public class EmployeeInCity {
	private Location location;
	private List<Employee> employeeList;
	public EmployeeInCity(Location location, List<Employee> employeeList) {
		super();
		this.location = location;
		this.employeeList = employeeList;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
}
