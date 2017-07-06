package com.vckadam.oopdesign.EmployeeEarning;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.Test;

public class OperationTest {
	
	/** Maximum salary Employee department exists. */
	@Test
	public void getMaxPaidEmployeeTest() {
		Operation op = new Operation();
		List<Employee> empList = prepareEmployeeList();
		List<Department> deptList = prepareDeptList();
		
		Set<String> expected = new HashSet<String>();
		expected.add("Sales"+", "+"Henry"+", "+"80000");
		expected.add("IT"+", "+"Max"+", "+"90000");
		
		List<MaxPaidEmployee> ret = op.getMaxPaidEmployee(empList, deptList);
		Set<String> actual = new HashSet<String>();
		for(MaxPaidEmployee emp : ret) {
			actual.add(emp.toString());
		}
		
		assertEquals(expected, actual);
	}
	
	/** Maximum salary Employee department exists with employee with not department. */
	@Test
	public void getMaxPaidEmployeeTest2() {
		Operation op = new Operation();
		List<Employee> empList = prepareEmployeeList2();
		List<Department> deptList = prepareDeptList2();
		
		Set<String> expected = new HashSet<String>();
		expected.add("Sales"+", "+"Henry"+", "+"80000");
		expected.add("IT"+", "+"Joe"+", "+"70000");
		
		List<MaxPaidEmployee> ret = op.getMaxPaidEmployee(empList, deptList);
		Set<String> actual = new HashSet<String>();
		for(MaxPaidEmployee emp : ret) {
			actual.add(emp.toString());
		}
		assertEquals(expected, actual);
	}
	
	/** Maximum salary Employee department doen't exist. */
	@Test
	public void getMaxPaidEmployeeTest3() {
		Operation op = new Operation();
		List<Employee> empList = prepareEmployeeList3();
		List<Department> deptList = prepareDeptList3();
		List<MaxPaidEmployee> ret = op.getMaxPaidEmployee(empList, deptList);
		assertEquals(new ArrayList<MaxPaidEmployee>(), ret);
	}
	
	public List<Employee> prepareEmployeeList() {
		List<Employee> emps = new ArrayList<Employee>();
		Employee emp1 = new Employee(1, "Joe", 70000, 1);
		Employee emp2 = new Employee(2, "Henry", 80000, 2);
		Employee emp3 = new Employee(3, "Sam", 60000, 2);
		Employee emp4 = new Employee(4, "Max", 90000, 1);
		emps.add(emp1); emps.add(emp2); emps.add(emp3); emps.add(emp4);
		return emps;
	}
	
	public List<Department> prepareDeptList() {
		List<Department> depts = new ArrayList<Department>();
		Department dept1 = new Department(1, "IT");
		Department dept2 = new Department(2, "Sales");
		depts.add(dept1); depts.add(dept2);
		return depts;
	}
	
	public List<Employee> prepareEmployeeList2() {
		List<Employee> emps = new ArrayList<Employee>();
		Employee emp1 = new Employee(1, "Joe", 70000, 1);
		Employee emp2 = new Employee(2, "Henry", 80000, 2);
		Employee emp3 = new Employee(3, "Sam", 60000, 2);
		Employee emp4 = new Employee(4, "Max", 90000, null);
		emps.add(emp1); emps.add(emp2); emps.add(emp3); emps.add(emp4);
		return emps;
	}
	
	public List<Department> prepareDeptList2() {
		List<Department> depts = new ArrayList<Department>();
		Department dept1 = new Department(1, "IT");
		Department dept2 = new Department(2, "Sales");
		depts.add(dept1); depts.add(dept2);
		return depts;
	}
	
	public List<Employee> prepareEmployeeList3() {
		List<Employee> emps = new ArrayList<Employee>();
		Employee emp1 = new Employee(1, "Joe", 70000, 1);
		Employee emp2 = new Employee(2, "Henry", 80000, 2);
		Employee emp3 = new Employee(3, "Sam", 60000, 2);
		Employee emp4 = new Employee(4, "Max", 90000, null);
		emps.add(emp1); emps.add(emp2); emps.add(emp3); emps.add(emp4);
		return emps;
	}
	
	public List<Department> prepareDeptList3() {
		List<Department> depts = new ArrayList<Department>();
		Department dept1 = new Department(3, "IT");
		Department dept2 = new Department(4, "Sales");
		depts.add(dept1); depts.add(dept2);
		return depts;
	}
}
