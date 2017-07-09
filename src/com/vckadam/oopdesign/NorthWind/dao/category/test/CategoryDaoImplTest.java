package com.vckadam.oopdesign.NorthWind.dao.category.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.dao.category.CategoryDao;
import com.vckadam.oopdesign.NorthWind.dao.category.CategoryDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Category;

public class CategoryDaoImplTest {
	
	CategoryDao categoryDao;
	
	@Before
	public void beforeMethod() throws IOException {
		this.categoryDao = new CategoryDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.categoryDao = null;
	}
	
	@Test
	public void getCategoryTest1() {
		int expected = 1;
		Category actual = this.categoryDao.getCategory("Beverages");
		assertEquals(expected, actual.getCategoryId());
	}
}
