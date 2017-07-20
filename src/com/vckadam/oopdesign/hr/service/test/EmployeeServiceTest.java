package com.vckadam.oopdesign.hr.service.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.hr.model.Employee;
import com.vckadam.oopdesign.hr.service.employee.EmployeeService;
import com.vckadam.oopdesign.hr.service.employee.EmployeeServiceImpl;

public class EmployeeServiceTest {
	
	private EmployeeService employeeService;
	
	@Before
	public void beforeMethod() {
		this.employeeService = new EmployeeServiceImpl();
	}
	
	@After
	public void afterMethod() {
		this.employeeService = null;
	}
	
	/** If one or more employees work in given city.*/
	@Test
	public void getEmployeeWorkInTest1() throws NumberFormatException, IOException, ParseException {
		List<Employee> employees = this.employeeService.getemployeeWorkIn("London");
		Set<Integer> actualSet = new HashSet<Integer>();
		for(Employee employee : employees) {
			actualSet.add(employee.getEmpId());
		}
		
		Set<Integer> expectedSet = new HashSet<Integer>();
		expectedSet.add(203);
		
		assertEquals(expectedSet.size(), employees.size());
		assertEquals(expectedSet, actualSet);
	}
	
	/** No employees work in given city.*/
	@Test
	public void getEmployeeWorkInTest2() throws NumberFormatException, IOException, ParseException {
		assertEquals(null, this.employeeService.getemployeeWorkIn("Bombay"));
	}
	
	@Test
	public void getEmployeesInDeptTest1() throws NumberFormatException, IOException, ParseException {
		assertEquals(3, this.employeeService.getEmployeesInDept("Executive"));
	}
}
