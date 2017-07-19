package com.vckadam.oopdesign.hr.dao.region;

import java.util.ArrayList;
import java.util.List;

import com.vckadam.oopdesign.hr.model.Region;

public class RegionDaoImpl implements RegionDao {

	private List<Region> regionList;
	
	public RegionDaoImpl() {
		this.regionList = new ArrayList<Region>();
		Region region1 = new Region(1, "Europe");
		Region region2 = new Region(2, "Americas");
		Region region3 = new Region(3, "Asia");
		Region region4 = new Region(4, "Middle East and Africa");
		this.regionList.add(region1);
		this.regionList.add(region2);
		this.regionList.add(region3);
		this.regionList.add(region4);
	}
	
	@Override
	public List<Region> getRegionList() {
		return this.regionList;
	}

}
