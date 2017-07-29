package com.vckadam.oopdesign.hr.test.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.hr.model.Employee;
import com.vckadam.oopdesign.hr.model.Manager;
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
	
	@Test
	public void deptInCountryTest1() {
		List<Employee> emps = this.employeeService.deptInCountry("US");
		Set<String> actualSet = prepareSet_deptInCountryTest(emps);
		
		String[] firstNames = {"Neena",
				"Lex",
				"Bruce",
				"David",
				"Valli",
				"Diana",
				"Nancy",
				"Daniel",
				"John",
				"Ismael",
				"Jose Manuel",
				"Luis",
				"Den",
				"Alexander",
				"Shelli",
				"Sigal",
				"Guy",
				"Karen",
				"Matthew",
				"Adam",
				"Payam",
				"Shanta",
				"Kevin",
				"Julia",
				"Irene",
				"James",
				"Steven",
				"Laura",
				"Mozhe",
				"James",
				"TJ",
				"Jason",
				"Michael",
				"Ki",
				"Hazel",
				"Renske",
				"Stephen",
				"Joshua",
				"Trenna",
				"Curtis",
				"Randall",
				"Peter",
				"John",
				"Karen",
				"Winston",
				"Jean",
				"Martha",
				"Girard",
				"Nandita",
				"Alexis",
				"Julia",
				"Anthony",
				"Kelly",
				"Jennifer",
				"Timothy",
				"Randall",
				"Sarah",
				"Britney",
				"Samuel",
				"Vance",
				"Alana",
				"Kevin",
				"Donald",
				"Douglas",
				"Jennifer",
				"Michael",
				"Shelley",
				"William" };

		
		Set<String> expectedSet = prepareExpectedSet_deptInCountryTest(firstNames);
		
		assertEquals(firstNames.length, emps.size());
		assertEquals(expectedSet, actualSet);
	}
	
	
	
	
	private Set<String> prepareSet_deptInCountryTest(List<Employee> emps) {
		Set<String> set = new HashSet<String>();
		for(Employee emp : emps) 
			set.add(emp.getFirstName());
		return set;
	}
	
	private Set<String> prepareExpectedSet_deptInCountryTest(String[] strA) {
		Set<String> set = new HashSet<String>();
		for(String str : strA) set.add(str);
		return set;
	}
	
	@Test
	public void testGetAllMangers_OneEmployeeInTeam() {
		List<Manager> managerList = this.employeeService.getAllMangers();
		Set<Integer> actualTeam = new HashSet<Integer>();
		for(Manager manager : managerList) {
			if(manager.getManager().getEmpId() ==  102) {
				List<Employee> team = manager.getTeam();
				for(Employee emp : team) actualTeam.add(emp.getEmpId());
			}
		}
		
		Set<Integer> expectedTeam = new HashSet<Integer>(Arrays.asList(103));
		assertEquals(expectedTeam,actualTeam);
	}
	
	@Test
	public void testGetAllMangers_multipleEmployeesInTeam() {
		List<Manager> managerList = this.employeeService.getAllMangers();
		Set<Integer> actualTeam = new HashSet<Integer>();
		for(Manager manager : managerList) {
			if(manager.getManager().getEmpId() ==  101) {
				List<Employee> team = manager.getTeam();
				for(Employee emp : team) actualTeam.add(emp.getEmpId());
			}
		}
		
		Set<Integer> expectedTeam = new HashSet<Integer>(Arrays.asList(200, 203, 204, 205, 108));
		assertEquals(expectedTeam,actualTeam);
	}
	
}
