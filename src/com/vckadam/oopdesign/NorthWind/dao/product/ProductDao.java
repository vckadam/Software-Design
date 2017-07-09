package com.vckadam.oopdesign.NorthWind.dao.product;

import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.Product;

public interface ProductDao {
	public List<Product> getProductInCategory(int categoryId);
}
