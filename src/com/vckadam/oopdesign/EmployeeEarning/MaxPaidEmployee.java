package com.vckadam.oopdesign.EmployeeEarning;

public class MaxPaidEmployee {
	private String department, employee;
	private int salary;
	public MaxPaidEmployee(String dept, String emp, int sal) {
		this.department = dept;
		this.employee = emp;
		this.salary = sal;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String toString() {
		if(department != null && employee != null) {
			return department +", "+employee+", "+String.valueOf(salary);
		} else return null;
	}
}
