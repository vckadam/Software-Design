package com.vckadam.oopdesign.hr.service.department;

import java.io.IOException;
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
import com.vckadam.oopdesign.hr.dao.location.LocationDao;
import com.vckadam.oopdesign.hr.dao.location.LocationDaoImpl;
import com.vckadam.oopdesign.hr.model.Country;
import com.vckadam.oopdesign.hr.model.Department;
import com.vckadam.oopdesign.hr.model.DepartmentsByCountry;
import com.vckadam.oopdesign.hr.model.Location;

public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentDao departmentDao;
	private LocationDao locationDao;
	private CountryDao countryDao;
	
	public DepartmentServiceImpl() throws IOException {
		this.departmentDao = new DepartmentDaoImpl();
		this.locationDao = new LocationDaoImpl();
		this.countryDao = new CountryDaoImpl();
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

}
