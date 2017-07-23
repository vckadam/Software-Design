package com.vckadam.oopdesign.hr.service.employee;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

	private EmployeeDao employeeDao;
	private DepartmentDao departmentDao;
	
	public EmployeeServiceImpl() throws NumberFormatException, IOException, ParseException {
		this.employeeDao = new EmployeeDaoImpl();
		this.departmentDao = new DepartmentDaoImpl();
	}
	
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

	@Override
	public List<Employee> managerWithExperience(int year) {
		List<Employee> empList = this.employeeDao.getEmpeWithExp(year);
		if(empList != null) {
			Set<Integer> empSet =new HashSet<Integer>();
			for(Employee emp : empList) {
				empSet.add(emp.getEmpId());
			}
			List<Employee> managersWithExp = new ArrayList<Employee>();
			List<Department> depts = this.departmentDao.getDepartmentList();
			for(Department dept : depts) {
				if(empSet.contains(dept.getManagerId())) {
					managersWithExp.add(this.employeeDao.getEmployeeById(dept.getManagerId()));
				}
			}
			return managersWithExp;
		}
		
		return null;
	}

}
