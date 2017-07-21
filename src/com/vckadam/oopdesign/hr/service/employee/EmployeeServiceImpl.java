package com.vckadam.oopdesign.hr.service.employee;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.hr.dao.department.DepartmentDao;
import com.vckadam.oopdesign.hr.dao.department.DepartmentDaoImpl;
import com.vckadam.oopdesign.hr.dao.employee.EmployeeDao;
import com.vckadam.oopdesign.hr.dao.employee.EmployeeDaoImpl;
import com.vckadam.oopdesign.hr.dao.job.JobDao;
import com.vckadam.oopdesign.hr.dao.job.JobDaoImpl;
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
	public List<Employee> getEmployeeJoinAfter(String name) throws NumberFormatException, IOException, ParseException {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		return employeeDao.getEmployeeJoinAfter(name);
	}

	@Override
	public int getEmployeesInDept(String name) throws IOException, NumberFormatException, ParseException {
		DepartmentDao deptDao = new DepartmentDaoImpl();
		Department dept = deptDao.getDepartmentByName(name);
		if(dept == null) return 0;
		EmployeeDao empDao = new EmployeeDaoImpl();
		return empDao.numberOfEmployeeInDept(dept.getDeparmentId());
	}

	@Override
	public Map<String, Double> avgSalaryByJob() throws NumberFormatException, IOException, ParseException {
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		Map<String, Double> avgByIdMap = employeeDao.avgSalaryByJob();
		JobDao jobDao = new JobDaoImpl();
		Map<String, Double> avgByTitleMap = new HashMap<String, Double>();
		for(String jobId : avgByIdMap.keySet()) {
			String jobTitle = jobDao.getJobById(jobId).getJobTitle();
			avgByTitleMap.put(jobTitle, avgByIdMap.get(jobId));
		}
		return avgByTitleMap;
	}

}
