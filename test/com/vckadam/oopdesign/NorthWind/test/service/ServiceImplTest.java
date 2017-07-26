package com.vckadam.oopdesign.NorthWind.test.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.model.Supplier;
import com.vckadam.oopdesign.NorthWind.service.Service;
import com.vckadam.oopdesign.NorthWind.service.ServiceImpl;

public class ServiceImplTest {

	private Service service;
	@Before
	public void beforeMethod() {
		this.service = new ServiceImpl();
	}
	
	@After
	public void afterMethod() {
		this.service = null;
	}
	
	/** One Supplier available for given category.*/
	@Test
	public void sellProductInCategoryTest1() throws IOException {
		List<String> expected = new ArrayList<String>(Arrays.asList("Exotic Liquids"));
		List<Supplier> suppliers = this.service.sellProductInCategory("Beverages");
		List<String> actual = new ArrayList<String>();
		for(Supplier supplier : suppliers) {
			actual.add(supplier.getCompanyName());
		}
		assertEquals(expected, actual);
	}
	
	/** No Supplier available for given Category. */
	@Test
	public void sellProductInCategoryTest2() throws IOException {
		List<String> expected = new ArrayList<String>();
		List<Supplier> suppliers = this.service.sellProductInCategory("Not in list");
		List<String> actual = new ArrayList<String>();
		for(Supplier supplier : suppliers) {
			actual.add(supplier.getCompanyName());
		}
		assertEquals(expected, actual);
	}
	
	/** Multiple Supplier available for given Category. */
	@Test
	public void sellProductInCategoryTest3() throws IOException {
		Set<String> expected = new HashSet<String>();
		expected.add("Exotic Liquids");
		expected.add("New Orleans Cajun Delights");
		expected.add("Grandma Kelly's Homestead");
		List<Supplier> suppliers = this.service.sellProductInCategory("Condiments");
		Set<String> actual = new HashSet<String>();
		for(Supplier supplier : suppliers) {
			actual.add(supplier.getCompanyName());
		}
		assertEquals(expected, actual);
	}
}
