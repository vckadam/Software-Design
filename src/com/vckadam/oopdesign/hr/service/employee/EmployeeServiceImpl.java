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
import com.vckadam.oopdesign.hr.model.Job;
import com.vckadam.oopdesign.hr.model.Location;
import com.vckadam.oopdesign.hr.model.Manager;
import com.vckadam.oopdesign.hr.model.MinSalGradeEmp;

public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDao employeeDao;
	private DepartmentDao departmentDao;
	private LocationDao locationDao;
	private JobDao jobDao;
	
	public EmployeeServiceImpl() throws NumberFormatException, IOException, ParseException {
		this.employeeDao = new EmployeeDaoImpl();
		this.departmentDao = new DepartmentDaoImpl();
		this.locationDao = new LocationDaoImpl();
		this.jobDao = new JobDaoImpl();
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

	@Override
	public List<Employee> deptInCountry(String country) {
		List<Location> locs = this.locationDao.getLocByCountry(country); 
		if(locs != null) {
			List<Employee> employeeList = new ArrayList<Employee>();
			for(Location loc : locs) {
				List<Department> depts = this.departmentDao.getDepartmentByLocation(loc.getLocationId());
				if(depts != null) {
					for(Department dept : depts) {
						List<Employee> emps = this.employeeDao.employeesByDepartment(dept.getDeparmentId());
						if(emps != null) {
							employeeList.addAll(emps);
						}
					}
				}
			}
			return employeeList;
		}
		return null;
	}

	@Override
	public List<Manager> getAllMangers() {
		List<Employee> employeeList = this.employeeDao.getEmployeeList();
		Map<Integer,List<Employee>> manMap = new HashMap<Integer,List<Employee>>();
		List<Manager> managerList = new ArrayList<Manager>();
		for(Employee emp : employeeList) {
			if(!manMap.containsKey(emp.getManagerId())) manMap.put(emp.getManagerId(),new ArrayList<Employee>());
			manMap.get(emp.getManagerId()).add(emp);
		}
		for(Integer key : manMap.keySet()) {
			Employee emp = this.employeeDao.getEmployeeById(key);
			if(emp != null) {
				List<Employee> team = manMap.get(key);
				Manager manager = new Manager(emp,team); 
				managerList.add(manager);
			}
		}
		return managerList;
	}
	
	public List<MinSalGradeEmp> getEmpWithMinSalInJob() {
		List<Employee> emps = this.employeeDao.getEmployeeList();
		List<Job> jobs = this.jobDao.getJobList();
		return this.getEmployeeMinSal(emps, jobs);
	}
	
	public List<MinSalGradeEmp> getEmployeeMinSal(List<Employee> empList, List<Job> jobList) {
		Map<String, MinSalGradeEmp> minSalEmp = new HashMap<>();
		for(Job job : jobList) {
			if(job != null) {
				if(!minSalEmp.containsKey(job.getJobId())) {
					minSalEmp.put(job.getJobId(), new MinSalGradeEmp(job,new ArrayList<Employee>()));
				}
			}
		}
		for(Employee emp : empList) {
			if(emp != null) {
				String empJobId = emp.getJobId();
				if(empJobId != null) {
					if(minSalEmp.containsKey(empJobId)) {
						MinSalGradeEmp jobCatEmp = minSalEmp.get(empJobId);
						if(emp.getSalary() == jobCatEmp.getJob().getMinSalary()) {
							jobCatEmp.getEmps().add(emp);
						}
					}
				}
			}
		}
		List<MinSalGradeEmp> ret = new ArrayList<>();
		for(String key : minSalEmp.keySet()) {
			ret.add(minSalEmp.get(key));
		}
		return ret;
	}

}
