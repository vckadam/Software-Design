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
}
