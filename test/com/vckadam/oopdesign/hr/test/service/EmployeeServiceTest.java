package com.vckadam.oopdesign.hr.test.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
	public void beforeMethod() throws NumberFormatException, IOException, ParseException {
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
	
	@Test
	public void avgSalaryByJobTest() throws NumberFormatException, IOException, ParseException {
		Map<String, Double> actualMap = this.employeeService.avgSalaryByJob();
		Map<String, Double> expectedMap = new HashMap<String, Double>();
		expectedMap.put("Accountant",(double) 7920); 
		expectedMap.put("Marketing Manager",(double) 13000); 
		expectedMap.put("Purchasing Clerk",(double) 2780);
		expectedMap.put("Accounting Manager",(double) 12000); 
		expectedMap.put("Marketing Representative",(double) 6000); 
		expectedMap.put("Purchasing Manager",(double) 11000);
		expectedMap.put("Administration Assistant",(double) 4400); 
		expectedMap.put("President",(double) 24000); 
		expectedMap.put("Sales Manager",(double) 12200);
		expectedMap.put("Administration Vice President",(double) 17000 ); 
		expectedMap.put("Programmer",(double) 5760); 
		expectedMap.put("Sales Representative",(double) 8350);
		expectedMap.put("Finance Manager",(double) 12000); 
		expectedMap.put("Public Accountant",(double) 8300); 
		expectedMap.put("Shipping Clerk",(double) 3215);
		expectedMap.put("Human Resources Representative",(double) 6500); 
		expectedMap.put("Public Relations Representative",(double) 10000); 
		expectedMap.put("Stock Clerk",(double) 2785);
		expectedMap.put("Stock Manager",(double) 7280);
		assertEquals(expectedMap, actualMap);
		
	}
	
	@Test
	public void managerWithExperienceTest1() {
		List<Employee> actualList = this.employeeService.managerWithExperience(15);
		assertEquals(11, actualList.size());
	}
}
