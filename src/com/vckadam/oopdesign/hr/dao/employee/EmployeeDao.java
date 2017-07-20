package com.vckadam.oopdesign.hr.dao.employee;

import java.util.List;

import com.vckadam.oopdesign.hr.model.Employee;

public interface EmployeeDao {
	public List<Employee> getEmployeeList();
	public List<Employee> getEmployeeJoinAfter(String name);
	public List<Employee> employeesByDepartment(Integer departmentId);
}
