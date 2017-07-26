package com.vckadam.oopdesign.taxitrip.test.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.taxitrip.dao.trip.TripDao;
import com.vckadam.oopdesign.taxitrip.dao.trip.TripDaoImpl;
import com.vckadam.oopdesign.taxitrip.model.Trip;

public class TripDaoTest {

	private TripDao tripDao;
	
	
	@Before
	public void setUp() throws Exception {
		this.tripDao = new TripDaoImpl();
	}

	@After
	public void tearDown() throws Exception {
		this.tripDao = null;
	}

	@Test
	public void testGetAllTrips() {
		List<Trip> actualList = this.tripDao.getAllTrips();
		Set<Integer> actualSet = new HashSet<Integer>();
		for(Trip trip : actualList) {
			actualSet.add(trip.getId());
		}
		
		Set<Integer> expectedSet = new HashSet<Integer>();
		for(int i = 1; i <= 10; i++) expectedSet.add(i);
		assertEquals(10, actualList.size());
		assertEquals(expectedSet , actualSet);
	}

}
