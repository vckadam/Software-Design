package com.vckadam.oopdesign.hr.dao.department;

import java.util.List;

import com.vckadam.oopdesign.hr.model.Department;

public interface DepartmentDao {
	public List<Department> getDepartmentList();
	public List<Department> getDepartmentByLocation(int locationId);
	public Department getDepartmentByName(String name);
}
