package com.vckadam.oopdesign.hr.dao.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.hr.dao.country.CountryDao;
import com.vckadam.oopdesign.hr.dao.country.CountryDaoImpl;
import com.vckadam.oopdesign.hr.model.Country;

public class CountryDaoTest {
	
	private CountryDao countryDao;
	
	@Before
	public void beforeMethod() throws IOException {
		this.countryDao = new CountryDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.countryDao = null;
	}
	
	@Test
	public void getCountryListTest() {
		List<Country> countryList = this.countryDao.getCountryList();
		Set<String> actualSet = new HashSet<String>();
		for(Country country : countryList) {
			actualSet.add(country.getCountryId());
		}
		Set<String> expectedSet = new HashSet<String>();
		expectedSet.add("AR"); expectedSet.add("AU"); expectedSet.add("BE");
		expectedSet.add("BR"); expectedSet.add("CA"); expectedSet.add("CH");
		expectedSet.add("CN"); expectedSet.add("DE"); expectedSet.add("DK"); 
		expectedSet.add("EG"); expectedSet.add("FR"); expectedSet.add("HK");
		expectedSet.add("IL"); expectedSet.add("IN"); expectedSet.add("IT");
		expectedSet.add("JP"); expectedSet.add("KW"); expectedSet.add("MX");
		expectedSet.add("NG"); expectedSet.add("NL"); expectedSet.add("SG");
		expectedSet.add("UK"); expectedSet.add("US"); expectedSet.add("ZM");
		expectedSet.add("ZW");
		assertEquals(expectedSet.size(), countryList.size());
		assertEquals(expectedSet, actualSet);
	}
}
