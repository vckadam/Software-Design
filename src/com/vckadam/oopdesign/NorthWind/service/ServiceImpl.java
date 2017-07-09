package com.vckadam.oopdesign.NorthWind.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vckadam.oopdesign.NorthWind.dao.category.CategoryDao;
import com.vckadam.oopdesign.NorthWind.dao.category.CategoryDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Category;
import com.vckadam.oopdesign.NorthWind.model.Customer;

public class ServiceImpl implements Service {

	@Override
	public List<Customer> sellProductInCategory(String categoryName) throws IOException {
		List<Customer> customerList = new ArrayList<Customer>();
		if(categoryName == null || categoryName.length() == 0) return customerList;
		CategoryDao categoryDao = new CategoryDaoImpl();
		Category category = categoryDao.getCategory(categoryName);
		if(category == null) return customerList;
		int categoryId = category.getCategoryId();
		
		return null;
	}

}
