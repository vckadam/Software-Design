package com.vckadam.oopdesign.taxitrip.dao.trip;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vckadam.oopdesign.taxitrip.model.Trip;

public class TripDaoImpl implements TripDao{

	private List<Trip> tripList;
	private static final String fileName = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\taxitrip\\dao\\trip\\tripdata";	
	public TripDaoImpl() throws IOException {
		this.tripList = new ArrayList<Trip>();
		loadList();
	}
	
	@Override
	public List<Trip> getAllTrips() {
		return this.tripList;
	}
	
	private void loadList() throws IOException {
		List<String> lines = new ArrayList<String>();
		String line = null;
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		while((line = br.readLine()) != null) {
			lines.add(line.substring(1,line.length()-1));
		}
		for(String currLine : lines) {
			String[] strA = currLine.split("\\|");
			Trip trip = new Trip(
					Integer.valueOf(strA[0].trim()),
					Integer.valueOf(strA[1].trim()),
					Integer.valueOf(strA[2].trim()),
					Integer.valueOf(strA[3].trim()),
					strA[4].trim(),
					strA[5].trim());
			this.tripList.add(trip);
		}
	}

}
