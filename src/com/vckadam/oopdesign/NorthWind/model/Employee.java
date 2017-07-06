package com.vckadam.oopdesign.NorthWind.model;

import java.util.Date;

public class Employee {
	private final int employeeId;
	private int reportsTo;
	private double salary;
	private String firstName, lastName, title, titleOfCourtesy,
	               address, city, region, postalCode, country, homePhone,
	               extension, photoPath;
	private Date birthDate, hireDate;
	public Employee(int employeeId, int reportsTo, double salary, String firstName, String lastName, String title,
			String address, String city, String postalCode, String country, String homePhone, Date birthDate,
			Date hireDate) {
		super();
		this.employeeId = employeeId;
		this.reportsTo = reportsTo;
		this.salary = salary;
		this.firstName = firstName;
		this.lastName = lastName;
		this.title = title;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.country = country;
		this.homePhone = homePhone;
		this.birthDate = birthDate;
		this.hireDate = hireDate;
	}
	public int getReportsTo() {
		return reportsTo;
	}
	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleOfCourtesy() {
		return titleOfCourtesy;
	}
	public void setTitleOfCourtesy(String titleOfCourtesy) {
		this.titleOfCourtesy = titleOfCourtesy;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	
}
