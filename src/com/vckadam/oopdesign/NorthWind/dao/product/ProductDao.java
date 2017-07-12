package com.vckadam.oopdesign.NorthWind.dao.product;

import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.Product;

public interface ProductDao {
	List<Product> getProductInCategory(int categoryId);
	List<Product> getAllProducts();
}
