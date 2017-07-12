package com.vckadam.oopdesign.NorthWind.dao.productsbysupplier.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.dao.productsbysupplier.ProductsBySupplierDao;
import com.vckadam.oopdesign.NorthWind.dao.productsbysupplier.ProductsBySupplierDaoImpl;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDao;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Product;
import com.vckadam.oopdesign.NorthWind.model.Supplier;

public class ProductsBySupplierDaoImplTest {
	
	private ProductsBySupplierDao productBySupplierDao;
	
	@Before
	public void beforeMethod() throws IOException {
		this.productBySupplierDao = new ProductsBySupplierDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.productBySupplierDao = null;
	}
	
	@Test
	public void getProductsTest1() throws IOException {
		
		Set<Integer> expectedProductIds = new HashSet<Integer>();
		expectedProductIds.add(1); 
		expectedProductIds.add(2);
		expectedProductIds.add(3);
		
		Set<Integer> actualProductIds = new HashSet<Integer>();
		SupplierDao supplierDao = new SupplierDaoImpl();
		Supplier supplier = supplierDao.getSupplierById(1);
		List<Product> products = this.productBySupplierDao.getProducts(supplier);
		for(Product product : products) {
			actualProductIds.add(product.getProductId());
		}
		
		assertEquals(expectedProductIds.size(), products.size());
		assertEquals(expectedProductIds, actualProductIds);
		
	}
	
	@Test
	public void getProductsTest2() throws IOException {
		
		Set<Integer> expectedProductIds = new HashSet<Integer>();
		expectedProductIds.add(9); 
		expectedProductIds.add(10);
		
		Set<Integer> actualProductIds = new HashSet<Integer>();
		SupplierDao supplierDao = new SupplierDaoImpl();
		Supplier supplier = supplierDao.getSupplierById(4);
		List<Product> products = this.productBySupplierDao.getProducts(supplier);
		for(Product product : products) {
			actualProductIds.add(product.getProductId());
		}
		
		assertEquals(expectedProductIds.size(), products.size());
		assertEquals(expectedProductIds, actualProductIds);
		
	}
}
