package com.vckadam.oopdesign.hr.test.dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.hr.dao.employee.EmployeeDao;
import com.vckadam.oopdesign.hr.dao.employee.EmployeeDaoImpl;
import com.vckadam.oopdesign.hr.model.Employee;

public class EmployeeDaoTest {
	
	private EmployeeDao employeeDao;
	
	@Before
	public void beforeMethod() throws NumberFormatException, IOException, ParseException {
		this.employeeDao = new EmployeeDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.employeeDao = null;
	}
	
	@Test
	public void loadListTest() {
		List<Employee> employeeList = this.employeeDao.getEmployeeList();
		Set<Integer> actualSet = new HashSet<Integer>();
		for(Employee emp : employeeList) {
			actualSet.add(emp.getEmpId());
		}
		Set<Integer> expectedSet = new HashSet<Integer>();
		for(int i = 100; i <= 206; i++) expectedSet.add(i);
		assertEquals(expectedSet.size(), employeeList.size());
		assertEquals(expectedSet, actualSet);
		assertEquals(employeeList.get(employeeList.size()-1).getDepartmentId(), 110);
		
	}
	
	/** If employees exist for given department.*/
	@Test
	public void getEmployeesByDepartmentTest1() {
		List<Employee> employees = this.employeeDao.employeesByDepartment(90);
		Set<Integer> actualSet = new HashSet<Integer>();
		for(Employee employee : employees) {
			actualSet.add(employee.getEmpId());
		}
		
		Set<Integer> expectedSet = new HashSet<Integer>();
		expectedSet.add(100);  expectedSet.add(101); expectedSet.add(102);
		
		assertEquals(expectedSet.size(), employees.size());
		assertEquals(expectedSet, actualSet);
	}
	
	/** If only one employee exist for given department.*/
	@Test
	public void getEmployeesByDepartmentTest2() {
		List<Employee> employees = this.employeeDao.employeesByDepartment(0);
		Set<Integer> actualSet = new HashSet<Integer>();
		for(Employee employee : employees) {
			actualSet.add(employee.getEmpId());
		}
		
		Set<Integer> expectedSet = new HashSet<Integer>();
		expectedSet.add(178); 
		
		assertEquals(expectedSet.size(), employees.size());
		assertEquals(expectedSet, actualSet);
	}
	
	/** If employees don't exist for given department.*/
	@Test
	public void getEmployeesByDepartmentTest3() {
		assertEquals(null, this.employeeDao.employeesByDepartment(-1));
	}
	
	@Test
	public void getEmployeeJoinAfterTest1() {
		List<Employee> employeeList = this.employeeDao.getEmployeeJoinAfter("Higgins");
		Set<String> actualSet = new HashSet<String>();
		for(Employee emp : employeeList) {
			actualSet.add(emp.getLastName());
		}
		
		Set<String> expectedSet = new HashSet<String>();
		expectedSet.add("Gietz");
		
		assertEquals(expectedSet.size(), employeeList.size());
		assertEquals(expectedSet, actualSet);
	}
	
	@Test
	public void getEmployeeJoinAfterTest2() {
		List<Employee> employeeList = this.employeeDao.getEmployeeJoinAfter("Jones");
		Set<String> actualSet = new HashSet<String>();
		for(Employee emp : employeeList) {
			actualSet.add(emp.getLastName());
		}
		
		Set<String> expectedSet = new HashSet<String>();
		expectedSet.add("Walsh"); expectedSet.add("Feeney"); expectedSet.add("OConnell");
		expectedSet.add("Grant"); expectedSet.add("Whalen"); expectedSet.add("Hartstein");
		expectedSet.add("Fay"); expectedSet.add("Mavris"); expectedSet.add("Baer");
		expectedSet.add("Higgins"); expectedSet.add("Gietz");
		
		assertEquals(expectedSet.size(), employeeList.size());
		assertEquals(expectedSet, actualSet);
	}
	
	/** Employees exist in the department.*/
	@Test
	public void numberOfEmployeeInDeptTest1() {
		int actual = this.employeeDao.numberOfEmployeeInDept(90);
		assertEquals(3, actual);
	}
	
	/** Employees don't exist in the department.*/
	@Test
	public void numberOfEmployeeInDeptTest2() {
		int actual = this.employeeDao.numberOfEmployeeInDept(190);
		assertEquals(0, actual);
	}
	
	@Test
	public void avgSalaryByJobTest() {
		Map<String,Double> actualMap = this.employeeDao.avgSalaryByJob();
		Double actualAvg = actualMap.get("AD_VP");
		assertEquals(17000.00, actualAvg.doubleValue(),2);
	}
	
	@Test
	public void getEmployeeByIdTest() {
		Employee emp = this.employeeDao.getEmployeeById(100);
		assertEquals("Steven", emp.getFirstName());
	}
	
	@Test
	public void getEmpeWithExpTest1() {
		List<Employee> actualList = this.employeeDao.getEmpeWithExp(1);
		assertEquals(107, actualList.size());
	}
	
	@Test
	public void getEmpeWithExpTest2() {
		List<Employee> actualList = this.employeeDao.getEmpeWithExp(50);
		assertEquals(null, actualList);
	}
	
	@Test
	public void getEmpMoreSalTest() {
		List<Employee> actualList = this.employeeDao.getEmpMoreSal("Bull");
		Set<String> actualSet = getActualSetForGetEmpMoreSal(actualList);
		
		String[] result = {"Steven",
				"Neena",
				"Lex",
				"Alexander",
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
				"Matthew",
				"Adam",
				"Payam",
				"Shanta",
				"Kevin",
				"John",
				"Karen",
				"Alberto",
				"Gerald",
				"Eleni",
				"Peter",
				"David",
				"Peter",
				"Christopher",
				"Nanette",
				"Oliver",
				"Janette",
				"Patrick",
				"Allan",
				"Lindsey",
				"Louise",
				"Sarath",
				"Clara",
				"Danielle",
				"Mattea",
				"David",
				"Sundar",
				"Amit",
				"Lisa",
				"Harrison",
				"Tayler",
				"William",
				"Elizabeth",
				"Sundita",
				"Ellen",
				"Alyssa",
				"Jonathon",
				"Jack",
				"Kimberely",
				"Charles",
				"Nandita",
				"Jennifer",
				"Michael",
				"Pat",
				"Susan",
				"Hermann",
				"Shelley",
				"William"

};
		Set<String> expectedSet = getExpectedSetForGetEmpMoreSal(result);
		//assertEquals(result.length, actualList.size());
		assertEquals(expectedSet, actualSet);
	}
	
	public Set<String> getExpectedSetForGetEmpMoreSal(String[] strA) {
		Set<String> set = new HashSet<String>();
		for(String str : strA) set.add(str);
		return set;
	}
	
	public Set<String> getActualSetForGetEmpMoreSal(List<Employee> list) {
		Set<String> set  = new HashSet<String>();
		for(Employee emp : list) {
			set.add(emp.getFirstName());
		}
		return set;
	}
}
