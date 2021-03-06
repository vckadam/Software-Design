package com.vckadam.oopdesign.NorthWind.service;

import java.io.IOException;
import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.Category;
import com.vckadam.oopdesign.NorthWind.model.Product;
import com.vckadam.oopdesign.NorthWind.model.ProductsBySupplier;
import com.vckadam.oopdesign.NorthWind.model.ProductsInCat;
import com.vckadam.oopdesign.NorthWind.model.Supplier;

public interface Service {
	public List<Supplier> sellProductInCategory(String categoryName) throws IOException;
	public List<ProductsBySupplier> getProductsInSupplier();
	public List<ProductsBySupplier> getProductsInSupplier(List<Product> products, List<Supplier> supplier);
	public List<ProductsInCat> getProductsInCategory();
	public List<ProductsInCat> getProductsInCategory(List<Category> cats, List<Product> prods);
}
