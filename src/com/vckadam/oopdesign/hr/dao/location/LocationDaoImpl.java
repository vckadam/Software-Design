package com.vckadam.oopdesign.hr.dao.location;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.hr.model.Location;

public class LocationDaoImpl implements LocationDao {

	private List<Location> locationList;
	Map<String, Location> locationByCity;
	Map<String, List<Location>> locByCountryIdMap;
	
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\hr\\dao\\location\\locationfile";
	
	public LocationDaoImpl() throws IOException {
		this.locationList = new ArrayList<Location>();
		this.locationByCity = new HashMap<String, Location>();
		this.locByCountryIdMap = new HashMap<String, List<Location>>();
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
	    	this.locationByCity.put(location.getCity(), location);
	    	if(!this.locByCountryIdMap.containsKey(location.getCountryId()))
	    		this.locByCountryIdMap.put(location.getCountryId(), new ArrayList<Location>());
	    	this.locByCountryIdMap.get(location.getCountryId()).add(location);
	    }
	    //System.out.println(this.locByCountryIdMap.toString());
	}
	
	@Override
	public List<Location> getLocationList() {
		return this.locationList;
	}

	@Override
	public Location getLocationWithCity(String city) {
		if(!this.locationByCity.containsKey(city)) return null;
		return this.locationByCity.get(city);
	}

	@Override
	public List<Location> getLocByCountry(String countryId) {
		if(this.locByCountryIdMap.containsKey(countryId)) {
			return this.locByCountryIdMap.get(countryId);
		}
		return null;
	}

}
