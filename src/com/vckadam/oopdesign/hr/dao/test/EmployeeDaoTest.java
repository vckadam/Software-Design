package com.vckadam.oopdesign.hr.dao.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
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
}
