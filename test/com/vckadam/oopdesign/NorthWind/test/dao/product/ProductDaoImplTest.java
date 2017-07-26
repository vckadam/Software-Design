package com.vckadam.oopdesign.NorthWind.test.dao.product;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.dao.product.ProductDao;
import com.vckadam.oopdesign.NorthWind.dao.product.ProductDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Product;

public class ProductDaoImplTest {
	
	private ProductDao productDao;
	
	@Before
	public void beforeMethod() throws IOException {
		this.productDao = new ProductDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.productDao = null;
	}
	
	@Test
	public void getProductInCategoryTest() {
		Set<Integer> expected = new HashSet<Integer>(Arrays.asList(1,2));
		List<Product> products = this.productDao.getProductInCategory(1);
		Set<Integer> actual = new HashSet<Integer>();
		for(Product product : products) {
			actual.add(product.getProductId());
		}
		assertEquals(expected,actual);
	}
}
