package com.vckadam.oopdesign.hr.service.employee;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.vckadam.oopdesign.hr.model.Employee;

public interface EmployeeService {
	public List<Employee> getemployeeWorkIn(String city) throws IOException, NumberFormatException, ParseException;
	public List<Employee> getEmployeeJoinAfter(String name) throws NumberFormatException, IOException, ParseException;
	public int getEmployeesInDept(String name) throws IOException, NumberFormatException, ParseException ;
}
