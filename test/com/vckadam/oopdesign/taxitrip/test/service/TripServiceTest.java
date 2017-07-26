package com.vckadam.oopdesign.taxitrip.test.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.taxitrip.service.TripService;

public class TripServiceTest {

	private TripService tripService;
	
	@Before
	public void setUp() throws Exception {
		this.tripService = new TripService();
	}

	@After
	public void tearDown() throws Exception {
		this.tripService = null;
	}

	@Test
	public void testGetAllTrips() {
		String[] dates = {"2013-10-01","2013-10-02","2013-10-03"};
		double[] values = {0.33,0.00,0.50};
		Map<String,Double> expected = populateMap(dates,values);
		Map<String,Double> actual = this.tripService.getCancellationRate();
		assertEquals(expected, actual);
	}
	
	private Map<String,Double> populateMap(String[] dates, double[] values){
		Map<String, Double> ret = new HashMap<String,Double>();
		for(int i = 0; i < dates.length; i++) {
			ret.put(dates[i], values[i]);
		}
		return ret;
	}

}
