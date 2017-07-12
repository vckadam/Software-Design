package com.vckadam.oopdesign.NorthWind.dao.productsbysupplier;

import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.Product;
import com.vckadam.oopdesign.NorthWind.model.Supplier;

public interface ProductsBySupplierDao {
	public List<Product> getProducts(Supplier supplier);
	
}
