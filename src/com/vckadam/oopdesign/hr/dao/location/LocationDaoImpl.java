package com.vckadam.oopdesign.hr.dao.location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vckadam.oopdesign.hr.model.Location;

public class LocationDaoImpl implements LocationDao {

	List<Location> locationList;
	
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\hr\\dao\\location\\locationfile";
	
	public LocationDaoImpl() throws IOException {
		this.locationList = new ArrayList<Location>();
		loadList();
	}
	
	public void loadList() throws IOException {
		List<String> lines = new ArrayList<String>();
	    BufferedReader reader = null;
	    try {
	        reader = new BufferedReader(new FileReader(FILENAME));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            lines.add(line.substring(1,line.length()-2));
	        }
	    } finally {
	        reader.close();
	    }
	    for(String line : lines) {
	    	String[] strA = line.split(", ");
	    	Location location = new Location(
	    			Integer.valueOf(strA[0].substring(1,strA[0].length()-1)),
	    			strA[1].substring(1,strA[1].length()-1),
	    			strA[2].substring(1,strA[2].length()-1),
	    			strA[3].substring(1,strA[3].length()-1),
	    			strA[4].substring(1,strA[4].length()-1),
	    			strA[5].substring(1,strA[5].length()-1)
	    			);
	    	this.locationList.add(location);
	    }
	}
	
	@Override
	public List<Location> getLocationList() {
		return this.locationList;
	}

}
