package com.vckadam.oopdesign.hr.dao.employee;

import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.hr.model.Employee;

public interface EmployeeDao {
	public List<Employee> getEmployeeList();
	public List<Employee> getEmployeeJoinAfter(String name);
	public List<Employee> employeesByDepartment(Integer departmentId);
	public int numberOfEmployeeInDept(int deptId);
	public Map<String,Double> avgSalaryByJob();
	public Employee getEmployeeById(int empId);
	public List<Employee> getEmpeWithExp(int year);
	public List<Employee> getEmpMoreSal(String lastName);
}
