package com.vckadam.oopdesign.hr.model;

public class Country {
	private int regionId;
	private String countryId, countryName;
	public Country(String countryId, String countryName, int regionId) {
		super();
		this.countryId = countryId;
		this.regionId = regionId;
		this.countryName = countryName;
	}
	public String getCountryId() {
		return countryId;
	}
	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	
}
