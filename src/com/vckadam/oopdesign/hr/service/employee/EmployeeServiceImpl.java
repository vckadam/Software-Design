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
import com.vckadam.oopdesign.hr.model.EmployeeInCity;
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
		if(empList == null || jobList == null) 
			throw new IllegalArgumentException("Invalid Input");
		List<Employee> currEmpList = new ArrayList<Employee>(empList); // Given arguments are "safe" now.
		List<Job> currJobList = new ArrayList<Job>(jobList);   // Caller is free to modify returned value.
		Map<String, MinSalGradeEmp> minSalEmp = new HashMap<>();
		for(Job job : currJobList) {
			if(job != null) {
				if(!minSalEmp.containsKey(job.getJobId())) {
					minSalEmp.put(job.getJobId(), new MinSalGradeEmp(job,new ArrayList<Employee>()));
				}
			}
		}
		for(Employee emp : currEmpList) {
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
			MinSalGradeEmp curr = minSalEmp.get(key);
			if(curr.getEmps().size() > 0) 
				ret.add(curr);
		}
		return ret;
	}

	@Override
	public List<EmployeeInCity> getEmployeeInCity(List<Employee> empList, List<Location> location, List<Department> depts) {
		if(empList == null || location == null || depts == null || empList.size() == 0 || location.size() == 0 || depts.size() == 0)
			throw new IllegalArgumentException("Illegal Argument empList: "+empList+" Location: "+location+" Departments: "+depts);
		Map<Integer,List<Employee>> empDeptMap = new HashMap<>();
		Map<Integer,List<Department>> deptLocMap = new HashMap<>();
		Map<Integer,List<Employee>> empLocMap = new HashMap<>();
		Map<Integer,Location> locMap = new HashMap<>();
		List<Employee> tempEmpList = new ArrayList<Employee>(empList);
		List<Location> tempLocList = new ArrayList<Location>(location);
		List<Department> tempDeptList = new ArrayList<Department>(depts);
		Set<String> empSet = new HashSet<String>();
		Set<String> deptSet = new HashSet<String>();
		for(Employee emp : tempEmpList) {
			if(emp != null) {
				Integer deptId = emp.getDepartmentId();
				if(!empSet.contains(emp.getEmpId()+"#"+deptId)) {
					if(!empDeptMap.containsKey(deptId)) empDeptMap.put(deptId, new ArrayList<Employee>());
					empDeptMap.get(deptId).add(emp);
					empSet.add(emp.getEmpId()+"#"+deptId);
				}
			}
		}
		for(Department dept : tempDeptList) {
			if(dept != null) {
				Integer locId = dept.getLocationId();
				if(!deptSet.contains(dept.getDeparmentId()+"#"+locId)) {
					if(!deptLocMap.containsKey(locId)) deptLocMap.put(locId, new ArrayList<Department>());
					deptLocMap.get(locId).add(dept);
					deptSet.add(dept.getDeparmentId()+"#"+locId);
				}
			}
		}
		for(Integer locId : deptLocMap.keySet()) {
			List<Department> currDepts = deptLocMap.get(locId);
			if(currDepts != null) {
				for(Department dept : currDepts) {
					if(dept != null) {
						Integer currDeptId = dept.getDeparmentId();
						List<Employee> currDeptEmp = empDeptMap.get(currDeptId);
						if(currDeptEmp != null) {
							if(!empLocMap.containsKey(locId)) empLocMap.put(locId, new ArrayList<Employee>());
							empLocMap.get(locId).addAll(currDeptEmp);
						}
					}
				}
			}
		}
		for(Location loc : tempLocList) {
			if(loc != null && !locMap.containsKey(loc.getLocationId())) locMap.put(loc.getLocationId(), loc);
		}
		
		List<EmployeeInCity> employeesInCityList = new ArrayList<EmployeeInCity>();
		for(Integer locId : empLocMap.keySet()) {
			Location currLoc = locMap.get(locId);
			if(currLoc != null) {
				List<Employee> locEmps = empLocMap.get(locId);
				if(locEmps.size() > 0) {
					EmployeeInCity currCity = new EmployeeInCity(currLoc, locEmps);
					employeesInCityList.add(currCity);
				}
			}
		}
		return employeesInCityList;
	}

	@Override
	public List<EmployeeInCity> getEmployeeInCityOnData() {
		return this.getEmployeeInCity(this.employeeDao.getEmployeeList(), this.locationDao.getLocationList(), this.departmentDao.getDepartmentList());
	}

	@Override
	public List<EmployeeInCity> moreThanAvgSalInCity() {
		List<EmployeeInCity> empList = getEmployeeInCityOnData();
		return moreThanAvgSalInCity(empList);
	}

	@Override
	public List<EmployeeInCity> moreThanAvgSalInCity(List<EmployeeInCity> empList) {
		if (empList==null || empList.size()==0)
			throw new IllegalArgumentException(expectionString(empList));
		List<EmployeeInCity> emps = new ArrayList<EmployeeInCity>();
		List<EmployeeInCity> tempEmpInCityList = new ArrayList<>(empList);
		for(EmployeeInCity empInCity : tempEmpInCityList) {
			if(empInCity != null && empInCity.getLocation() != null && empInCity.getEmployeeList() != null) {
				double cityAvgSal = 0;
				List<Employee> tempEmpList = empInCity.getEmployeeList();
				if(tempEmpList != null && tempEmpList.size() > 0) {
					for(Employee emp : tempEmpList) {
						cityAvgSal += emp.getSalary();
					}
					cityAvgSal /= tempEmpList.size();
					List<Employee> moreAvgEmps = getMoreThanAvg(tempEmpList, cityAvgSal);
					empInCity.setEmployeeList(moreAvgEmps);
					emps.add(empInCity);
				}
			}
			
		}
		return emps;
	}
	
	public List<Employee> getMoreThanAvg(List<Employee> tempEmpList, double avgSal) {
		int j = 0;
		for(int i = 0; i < tempEmpList.size(); i++) {
			if(tempEmpList.get(i).getSalary() > avgSal) swap(i, j++, tempEmpList);
		}
		for(int i = tempEmpList.size()-1; i >= j; i--) {
			tempEmpList.remove(tempEmpList.size()-1);
		}
		return tempEmpList;
	}
	
	public void swap(int i, int j, List<Employee> empList) {
		Employee emp1 = empList.get(i);
		empList.set(i, empList.get(j));
		empList.set(j, emp1);
	}
	
	private String expectionString(List<EmployeeInCity> empList) {
		return "Illegal Argumnet empList: "+empList;
	}

}
