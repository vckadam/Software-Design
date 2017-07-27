package com.vckadam.oopdesign.hr.test.dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.hr.dao.location.LocationDao;
import com.vckadam.oopdesign.hr.dao.location.LocationDaoImpl;
import com.vckadam.oopdesign.hr.model.Location;

public class LocationDaoImplTest {
	
	private LocationDao locationDao;
	
	@Before
	public void beforeMethod() throws IOException {
		this.locationDao = new LocationDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.locationDao = null;
	}
	
	@Test
	public void getLocationListTest() {
		List<Location> locationList = this.locationDao.getLocationList();
		Set<Integer> actualSet = new HashSet<Integer>();
		for(Location location : locationList) {
			actualSet.add(location.getLocationId());
		}
		
		Set<Integer> expectedSet = new HashSet<Integer>();
		for(int i = 10; i <= 32; i++) {
			expectedSet.add(i*100);
		}
		assertEquals(expectedSet.size(), locationList.size());
		assertEquals(expectedSet, actualSet);
	}
	
	@Test
	public void getLocationWithCityTest1() {
		Location location = this.locationDao.getLocationWithCity("London");
		assertEquals(2400, location.getLocationId());
	}
	
	@Test
	public void getLocationWithCityTest2() {
		assertEquals(null, this.locationDao.getLocationWithCity("Vadodara"));
	}
	
	@Test
	public void getLocByCountryTest() {
		List<Location> actualList = this.locationDao.getLocByCountry("US");
		Set<Integer> actualSet = null;
		if(actualList != null)
			actualSet= prepareActualSet(actualList);
		
		Set<Integer> expectedSet = new HashSet<Integer>(Arrays.asList(1400,1500,1600,1700));
		assertEquals(4, actualList.size());
		assertEquals(expectedSet, actualSet);
	}
	
	private Set<Integer> prepareActualSet(List<Location> list) {
		Set<Integer> set = new HashSet<Integer>();
		for(Location loc : list) {
			set.add(loc.getLocationId());
		}
		return set;
	}
}
