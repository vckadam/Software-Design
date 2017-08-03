package com.vckadam.oopdesign.hr.service.department;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vckadam.oopdesign.hr.dao.country.CountryDao;
import com.vckadam.oopdesign.hr.dao.country.CountryDaoImpl;
import com.vckadam.oopdesign.hr.dao.department.DepartmentDao;
import com.vckadam.oopdesign.hr.dao.department.DepartmentDaoImpl;
import com.vckadam.oopdesign.hr.dao.employee.EmployeeDao;
import com.vckadam.oopdesign.hr.dao.employee.EmployeeDaoImpl;
import com.vckadam.oopdesign.hr.dao.location.LocationDao;
import com.vckadam.oopdesign.hr.dao.location.LocationDaoImpl;
import com.vckadam.oopdesign.hr.model.Country;
import com.vckadam.oopdesign.hr.model.Department;
import com.vckadam.oopdesign.hr.model.DepartmentsByCountry;
import com.vckadam.oopdesign.hr.model.Employee;
import com.vckadam.oopdesign.hr.model.ExpensiveDeptInCoun;
import com.vckadam.oopdesign.hr.model.Location;

public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDao departmentDao;
	private LocationDao locationDao;
	private CountryDao countryDao;
	private EmployeeDao employeeDao;
	
	public DepartmentServiceImpl() throws IOException, NumberFormatException, ParseException {
		this.departmentDao = new DepartmentDaoImpl();
		this.locationDao = new LocationDaoImpl();
		this.countryDao = new CountryDaoImpl();
		this.employeeDao = new EmployeeDaoImpl();
	}
	@Override
	public List<DepartmentsByCountry> getAllDeptByCoun() {
		List<Country> counsList = this.countryDao.getCountryList();
		List<Department> deptsList = this.departmentDao.getDepartmentList();
		List<Location> locsList = this.locationDao.getLocationList();
		return getAllDeptByCoun(counsList,deptsList,locsList);
	}

	@Override
	public List<DepartmentsByCountry> getAllDeptByCoun(List<Country> couns, List<Department> depts,
			List<Location> locs) {
		if(couns == null || depts == null || locs == null || couns.size() == 0 || depts.size() == 0 ||
		   locs.size() == 0) 
			throw new IllegalArgumentException("Country List: "+couns+" Department List: "+depts+" Location List: "+locs);
		List<Country> tempCouns = new ArrayList<>(couns);
		List<Department> tempDepts = new ArrayList<>(depts);
		List<Location> tempLocs = new ArrayList<>(locs);
		Map<String,List<Location>> counIdToLocsMap = new HashMap<>();
		Map<Integer,List<Department>> locIdToDepts = new HashMap<>();
		Map<String,List<Department>> counIdtoDepts = new HashMap<>();
		Map<String,Country> counMap = new HashMap<>();
		Set<String> locAndCounId = new HashSet<String>();
		Set<String> deptAndLocId = new HashSet<String>();
		List<DepartmentsByCountry> deptsByCoun = new ArrayList<>();
		for(Location loc : tempLocs) {
			if(loc != null) {
				String counId = loc.getCountryId();
				if(counId != null && !locAndCounId.contains(counId+"#"+loc.getLocationId())) {
					if(!counIdToLocsMap.containsKey(counId)) counIdToLocsMap.put(counId, new ArrayList<Location>());
					counIdToLocsMap.get(counId).add(loc);
					locAndCounId.add(counId+"#"+loc.getLocationId());
				}
			}
		} 
		for(Department dept : tempDepts) {
			if(dept != null) {
				int locId = dept.getLocationId();
				if(!deptAndLocId.contains(dept.getDeparmentId()+"#"+locId)) {
					deptAndLocId.add(dept.getDeparmentId()+"#"+locId);
					if(!locIdToDepts.containsKey(locId)) locIdToDepts.put(locId, new ArrayList<Department>());
					locIdToDepts.get(locId).add(dept);
				}
			}
		}
		for(Country coun : tempCouns) {
			if(coun != null && !counMap.containsKey(coun.getCountryId())) 
				counMap.put(coun.getCountryId(), coun);
		}
		for(String counId : counIdToLocsMap.keySet()) {
			Country currCoun = counMap.get(counId);
			if(currCoun != null) {
				List<Location> currLocs = counIdToLocsMap.get(counId);
				if(currLocs != null && currLocs.size() > 0) {
					for(Location currLoc : currLocs) {
						if(currLoc != null) {
							List<Department> currDepts = locIdToDepts.get(currLoc.getLocationId());
							if(currDepts != null && currDepts.size() > 0) {
								if(!counIdtoDepts.containsKey(counId)) counIdtoDepts.put(counId, new ArrayList<Department>());
								counIdtoDepts.get(counId).addAll(currDepts);
							}
						}
					}
				}
			}
		}
		for(String counId : counIdtoDepts.keySet()) {
			Country currCoun = counMap.get(counId);
			if(currCoun != null) {
				List<Department> currDepts = counIdtoDepts.get(counId);
				if(currDepts != null && currDepts.size() > 0) {
					deptsByCoun.add(new DepartmentsByCountry(currCoun, currDepts));
				}
			}
		}
		return deptsByCoun;
	}
	@Override
	public List<ExpensiveDeptInCoun> getExpensiveDeptInCountry() {
		List<DepartmentsByCountry> deptsByCoun = this.getAllDeptByCoun();
		List<Employee> emps = this.employeeDao.getEmployeeList();
		return getExpensiveDeptInCountry(deptsByCoun, emps);
	}
	@Override
	public List<ExpensiveDeptInCoun> getExpensiveDeptInCountry(List<DepartmentsByCountry> deptsInCoun,
			List<Employee> empList) {
		if(deptsInCoun == null || empList == null || deptsInCoun.size() == 0 || empList.size() == 0) 
			throw new IllegalArgumentException("Illegal Argument DepartmentsInCountry: "+deptsInCoun+" Employee List: "+empList);
		List<DepartmentsByCountry> tempDeptsIncoun = new ArrayList<>(deptsInCoun);
		List<Employee> tempEmpList = new ArrayList<>(empList);
		Map<Integer,Double> deptSalMap = new HashMap<>();
		Set<Integer> empIdSet = new HashSet<>();
		Set<String> counIdSet = new HashSet<>();
		List<ExpensiveDeptInCoun> expDeptInCounList = new ArrayList<ExpensiveDeptInCoun>();
		for(Employee emp : tempEmpList) {
			if(emp != null && !empIdSet.contains(emp.getEmpId())) {
				deptSalMap.put(emp.getDepartmentId(),deptSalMap.getOrDefault(emp.getDepartmentId(),0.0)+emp.getSalary());
			}
		}
		for(DepartmentsByCountry deptInCoun : tempDeptsIncoun) {
			if(deptInCoun != null) {
				Country currCon = deptInCoun.getCountry();
				if(currCon != null && !counIdSet.contains(currCon.getCountryId())) {
					counIdSet.add(currCon.getCountryId());
					List<Department> currDepts = deptInCoun.getDepts();
					if(currDepts != null && currDepts.size() > 0) {
						Double maxSal = -Double.MAX_VALUE;
						Department maxDept = null;
						for(Department dept : currDepts) {
							if(dept != null) {
								Double currDeptSal = deptSalMap.get(dept.getDeparmentId());
								if(currDeptSal != null) {
									if(currDeptSal > maxSal) {
										maxSal = currDeptSal;
										maxDept = dept;
									}
								}
							}
						}
						if(maxDept != null) {
							expDeptInCounList.add(new ExpensiveDeptInCoun(currCon,maxDept));
						}
					}
				}
			}
		}
		return expDeptInCounList;
	}

}
