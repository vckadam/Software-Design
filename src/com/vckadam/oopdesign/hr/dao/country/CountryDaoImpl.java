package com.vckadam.oopdesign.hr.dao.country;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vckadam.oopdesign.hr.model.Country;

public class CountryDaoImpl implements CountryDao {
	
	List<Country> countryList;
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\hr\\dao\\country\\countryfile";
	
	public CountryDaoImpl() throws IOException {
		this.countryList = new ArrayList<Country>();
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
	    	Country country = new Country(
	    			strA[0].substring(1,strA[0].length()-1),
	    			strA[1].substring(1,strA[1].length()-1),
	    			Integer.valueOf(strA[2].substring(1,strA[2].length()-1))
	    			);
	    	this.countryList.add(country);
	    }
	}
	
	@Override
	public List<Country> getCountryList() {
		return this.countryList;
	}

}
