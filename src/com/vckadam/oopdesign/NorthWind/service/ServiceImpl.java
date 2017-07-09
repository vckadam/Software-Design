package com.vckadam.oopdesign.NorthWind.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.vckadam.oopdesign.NorthWind.dao.category.CategoryDao;
import com.vckadam.oopdesign.NorthWind.dao.category.CategoryDaoImpl;
import com.vckadam.oopdesign.NorthWind.dao.product.ProductDao;
import com.vckadam.oopdesign.NorthWind.dao.product.ProductDaoImpl;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDao;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Category;
import com.vckadam.oopdesign.NorthWind.model.Product;
import com.vckadam.oopdesign.NorthWind.model.Supplier;

public class ServiceImpl implements Service {

	@Override
	public List<Supplier> sellProductInCategory(String categoryName) throws IOException {
		List<Supplier> supplierList = new ArrayList<Supplier>();
		if(categoryName == null || categoryName.length() == 0) return supplierList;
		CategoryDao categoryDao = new CategoryDaoImpl();
		Category category = categoryDao.getCategory(categoryName);
		if(category == null) return supplierList;
		int categoryId = category.getCategoryId();
		ProductDao productDao = new ProductDaoImpl();
		List<Product> productList = productDao.getProductInCategory(categoryId);
		if(productList == null || productList.size() == 0) return supplierList;
		Set<Supplier> suppliers = new HashSet<Supplier>();
		SupplierDao supplierDao = new SupplierDaoImpl();
		for(Product product: productList) {
			int supplierId = product.getSupplierId();
			Supplier supplier = supplierDao.getSupplierById(supplierId);
			if(!suppliers.contains(supplier)) {
				suppliers.add(supplier);
				supplierList.add(supplier);
			}
		}
		return supplierList;
	}

}
