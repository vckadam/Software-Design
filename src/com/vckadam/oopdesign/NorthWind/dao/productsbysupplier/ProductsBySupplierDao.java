package com.vckadam.oopdesign.NorthWind.dao.productsbysupplier;

import java.io.IOException;
import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.Product;
import com.vckadam.oopdesign.NorthWind.model.ProductsBySupplier;
import com.vckadam.oopdesign.NorthWind.model.Supplier;

public interface ProductsBySupplierDao {
	public List<Product> getProducts(Supplier supplier);
	public List<ProductsBySupplier> getAllSupplierProdcts()throws IOException ;
	
}
