package com.vckadam.oopdesign.hr.model;

public class Department {
	private int deparmentId, managerId, locationId;
	private String departmentName;
	public Department(int deparmentId, String departmentName, int managerId, int locationId) {
		super();
		this.deparmentId = deparmentId;
		this.managerId = managerId;
		this.locationId = locationId;
		this.departmentName = departmentName;
	}
	public int getDeparmentId() {
		return deparmentId;
	}
	public void setDeparmentId(int deparmentId) {
		this.deparmentId = deparmentId;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getLocationId() {
		return locationId;
	}
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
}
