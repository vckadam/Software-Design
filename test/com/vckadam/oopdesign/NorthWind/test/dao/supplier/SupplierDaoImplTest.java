package com.vckadam.oopdesign.NorthWind.test.dao.supplier;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDao;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Supplier;

public class SupplierDaoImplTest {
	
	SupplierDao supplierDao;
	
	@Before
	public void beforeMethod() throws IOException {
		this.supplierDao = new SupplierDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.supplierDao = null;
	}
	
	@Test
	public void sortByCountryAndNameTest() {
		List<Supplier> supplierList = this.supplierDao.getSupplierList();
		List<Supplier> expected = new ArrayList<Supplier>();
		expected.add(supplierList.get(2)); 
		expected.add(supplierList.get(1)); 
		expected.add(supplierList.get(0)); 
		expected.add(supplierList.get(4));
		expected.add(supplierList.get(3));
		List<Supplier> actual = this.supplierDao.sortByCountryAndName();
		assertEquals(expected, actual);
	}
}
