package com.vckadam.oopdesign.hr.service.department;

import java.util.List;

import com.vckadam.oopdesign.hr.model.Country;
import com.vckadam.oopdesign.hr.model.Department;
import com.vckadam.oopdesign.hr.model.DepartmentsByCountry;
import com.vckadam.oopdesign.hr.model.Employee;
import com.vckadam.oopdesign.hr.model.ExpensiveDeptInCoun;
import com.vckadam.oopdesign.hr.model.Location;

public interface DepartmentService {
	public List<DepartmentsByCountry> getAllDeptByCoun();
	public List<DepartmentsByCountry> getAllDeptByCoun(List<Country> couns, List<Department> depts, List<Location> locs);
	public List<ExpensiveDeptInCoun> getExpensiveDeptInCountry();
	public List<ExpensiveDeptInCoun> getExpensiveDeptInCountry(List<DepartmentsByCountry> deptsInCoun, List<Employee> empList);
}
