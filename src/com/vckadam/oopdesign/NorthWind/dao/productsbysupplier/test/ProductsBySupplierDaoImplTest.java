package com.vckadam.oopdesign.NorthWind.dao.productsbysupplier.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.dao.productsbysupplier.ProductsBySupplierDao;
import com.vckadam.oopdesign.NorthWind.dao.productsbysupplier.ProductsBySupplierDaoImpl;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDao;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Product;
import com.vckadam.oopdesign.NorthWind.model.ProductsBySupplier;
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
	
	@Test
	public void getAllSupplierProdctsTest1() throws IOException{
		List<ProductsBySupplier> supplierProducts = this.productBySupplierDao.getAllSupplierProdcts();
		Set<Set<Integer>> actual = new HashSet<Set<Integer>>();
		for(ProductsBySupplier supplierProduct: supplierProducts) {
			List<Product> products = supplierProduct.getProducts();
			Set<Integer> current = new HashSet<Integer>();
			for(Product product : products) {
				current.add(product.getProductId());
			}
			actual.add(current);
		}
		
		Set<Set<Integer>> expected = new HashSet<Set<Integer>>();
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		Set<Integer> set3 = new HashSet<Integer>();
		Set<Integer> set4 = new HashSet<Integer>();
		set1.add(1); set1.add(2); set1.add(3);
		set2.add(4); set2.add(5); 
		set3.add(6); set3.add(7); set3.add(8);
		set4.add(9); set4.add(10);
		expected.add(set1);
		expected.add(set2);
		expected.add(set3);
		expected.add(set4);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void getAllSupplierProdctsTest2() throws IOException{
		List<ProductsBySupplier> supplierProducts = this.productBySupplierDao.getAllSupplierProdcts();
		
		Map<Integer, Set<Integer>> actualMap = new HashMap<Integer, Set<Integer>>();
		for(ProductsBySupplier supplierProduct: supplierProducts) {
			List<Product> products = supplierProduct.getProducts();
			Set<Integer> productIds = new HashSet<Integer>();
			for(Product product : products) {
				productIds.add(product.getProductId());
			}
			actualMap.put(supplierProduct.getSupplier().getSupplierId(),
					productIds);
		}
		
		Map<Integer,Set<Integer>> expectedMap = new HashMap<>();		
		expectedMap.put(1, new HashSet<Integer>(Arrays.asList(1,2,3)));
		expectedMap.put(2, new HashSet<Integer>(Arrays.asList(4,5)));
		expectedMap.put(3, new HashSet<Integer>(Arrays.asList(6,7,8)));
		expectedMap.put(4, new HashSet<Integer>(Arrays.asList(9,10)));
		
		
		assertEquals(expectedMap, actualMap);
	}
}
