package com.vckadam.oopdesign.NorthWind.dao.category;

import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.Category;

public interface CategoryDao {
	public Category getCategory(String categoryName);
	public List<Category> getAllCategories();
}
