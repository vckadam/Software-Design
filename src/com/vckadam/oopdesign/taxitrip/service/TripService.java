package com.vckadam.oopdesign.taxitrip.service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.taxitrip.dao.trip.TripDao;
import com.vckadam.oopdesign.taxitrip.dao.trip.TripDaoImpl;
import com.vckadam.oopdesign.taxitrip.dao.user.UserDao;
import com.vckadam.oopdesign.taxitrip.dao.user.UserDaoImpl;
import com.vckadam.oopdesign.taxitrip.model.Trip;
import com.vckadam.oopdesign.taxitrip.model.User;

public class TripService {
	private TripDao tripDao;
	private UserDao userDao;
	
	public TripService() throws IOException {
		this.tripDao = new TripDaoImpl();
		this.userDao = new UserDaoImpl();
	}
	
	public Map<String,Double> getCancellationRate() {
		Map<String,Double> ret = new HashMap<String,Double>();
		Map<String,Integer> count = new HashMap<String,Integer>();
		List<Trip> tripList = this.tripDao.getAllTrips();
		for(Trip trip : tripList) {
			User user = this.userDao.getUserById(trip.getClientId());
			if(user != null && !user.isBanned()) {
				ret.put(trip.getRequestAt(), ret.getOrDefault(trip.getRequestAt(), 0.0)+1);
				if(trip.getStatus().substring(0,9).equals("cancelled")) {
					count.put(trip.getRequestAt(), count.getOrDefault(trip.getRequestAt(), 0)+1);
				}
			}
		}
		DecimalFormat formate = new DecimalFormat(".##");
		for(String key : ret.keySet()) {
			if(count.containsKey(key)) 
				ret.put(key, Double.valueOf(formate.format(count.get(key)/ret.get(key))));
			else ret.put(key, 0.0);
		}
		
		return ret;
	}
	
}
