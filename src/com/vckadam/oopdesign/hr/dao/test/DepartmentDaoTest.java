package com.vckadam.oopdesign.hr.dao.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.hr.dao.department.DepartmentDao;
import com.vckadam.oopdesign.hr.dao.department.DepartmentDaoImpl;
import com.vckadam.oopdesign.hr.model.Department;

public class DepartmentDaoTest {
	
	private DepartmentDao departmentDao;
	
	@Before
	public void beforeMethod() throws IOException {
		this.departmentDao = new DepartmentDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.departmentDao = null;
	}
	
	@Test
	public void getDepartmentListTest() {
		List<Department> departmentList = this.departmentDao.getDepartmentList();
		Set<Integer> actualSet = new HashSet<Integer>();
		for(Department department : departmentList) {
			actualSet.add(department.getDeparmentId());
		}
		
		Set<Integer> expectedSet = new HashSet<Integer>();
		for(int i = 10; i <= 270; i+=10) expectedSet.add(i);
		
		assertEquals(expectedSet.size(), departmentList.size());
		assertEquals(expectedSet, actualSet);
	}
}
