package com.vckadam.oopdesign.hr.service.employee;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.vckadam.oopdesign.hr.dao.department.DepartmentDao;
import com.vckadam.oopdesign.hr.dao.department.DepartmentDaoImpl;
import com.vckadam.oopdesign.hr.dao.employee.EmployeeDao;
import com.vckadam.oopdesign.hr.dao.employee.EmployeeDaoImpl;
import com.vckadam.oopdesign.hr.dao.location.LocationDao;
import com.vckadam.oopdesign.hr.dao.location.LocationDaoImpl;
import com.vckadam.oopdesign.hr.model.Department;
import com.vckadam.oopdesign.hr.model.Employee;
import com.vckadam.oopdesign.hr.model.Location;

public class EmployeeServiceImpl implements EmployeeService {

	@Override
	public List<Employee> getemployeeWorkIn(String city) throws IOException, NumberFormatException, ParseException {
		List<Employee> employees = new ArrayList<Employee>();
		LocationDao locationDao = new LocationDaoImpl();
		Location location = locationDao.getLocationWithCity(city);
		if(location == null) return null;
		DepartmentDao departmentDao = new DepartmentDaoImpl();
		List<Department> departments = departmentDao.getDepartmentByLocation(location.getLocationId());
		if(departments == null) return null;
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		for(Department department : departments) {
			List<Employee> employeesIndept = employeeDao.employeesByDepartment(department.getDeparmentId());
			if(employeesIndept == null) continue;
			employees.addAll(employeesIndept);
		}
		return employees;
	}

	@Override
	public List<Employee> getEmployeeJoinAfter(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
