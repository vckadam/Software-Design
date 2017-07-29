package com.vckadam.oopdesign.hr.service.employee;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.hr.model.Employee;
import com.vckadam.oopdesign.hr.model.Manager;

public interface EmployeeService {
	public List<Employee> getemployeeWorkIn(String city) throws IOException, NumberFormatException, ParseException;
	public List<Employee> getEmployeeJoinAfter(String name) throws NumberFormatException, IOException, ParseException;
	public int getEmployeesInDept(String name) throws IOException, NumberFormatException, ParseException ;
	public Map<String,Double> avgSalaryByJob() throws NumberFormatException, IOException, ParseException ;
	public List<Employee> managerWithExperience(int year);
	public List<Employee> deptInCountry(String country);
	public List<Manager> getAllMangers();
}
