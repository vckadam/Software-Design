package com.vckadam.oopdesign.hr.dao.location;

import java.util.List;

import com.vckadam.oopdesign.hr.model.Location;

public interface LocationDao {
	public List<Location> getLocationList();
	public Location getLocationWithCity(String city);
	public List<Location> getLocByCountry(String countryId);
}
