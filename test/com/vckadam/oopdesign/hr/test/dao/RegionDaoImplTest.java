package com.vckadam.oopdesign.hr.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.hr.dao.region.RegionDao;
import com.vckadam.oopdesign.hr.dao.region.RegionDaoImpl;
import com.vckadam.oopdesign.hr.model.Region;

public class RegionDaoImplTest {
	
	private RegionDao regionDao;
	
	@Before
	public void beforeMethod() {
		this.regionDao = new RegionDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.regionDao = null;
	}
	
	@Test
	public void getRegionListTest() {
		List<Region> regionList = this.regionDao.getRegionList();
		Set<Integer> actualSet = new HashSet<Integer>();
		for(Region region : regionList) {
			actualSet.add(region.getRegionId());
		}
		Set<Integer> expectedSet = new HashSet<Integer>(Arrays.asList(1,2,3,4));
		
		assertEquals(expectedSet.size(), regionList.size());
		assertEquals(expectedSet, actualSet);
	}
}
