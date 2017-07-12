package com.vckadam.oopdesign.NorthWind.dao.productsbysupplier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.NorthWind.dao.product.ProductDao;
import com.vckadam.oopdesign.NorthWind.dao.product.ProductDaoImpl;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDao;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Product;
import com.vckadam.oopdesign.NorthWind.model.ProductsBySupplier;
import com.vckadam.oopdesign.NorthWind.model.Supplier;

public class ProductsBySupplierDaoImpl implements ProductsBySupplierDao{

	Map<Integer,List<Product>> productMap;
	
	public ProductsBySupplierDaoImpl() throws IOException {
		productMap = new HashMap<Integer,List<Product>>();
		loadMap();
	}
	
	@Override
	public List<Product> getProducts(Supplier supplier) {
		Integer supplierId = supplier.getSupplierId();
		List<Product> emptyList = new ArrayList<Product>();
		if(!this.productMap.containsKey(supplierId)) return emptyList;
		else return this.productMap.get(supplierId);
	}
	
	private void loadMap() throws IOException {
		ProductDao productDao = new ProductDaoImpl();
		List<Product> allProducts = productDao.getAllProducts();
		for(Product product : allProducts) {
			int supplierId = product.getSupplierId();
			if(!this.productMap.containsKey(supplierId)) 
				this.productMap.put(supplierId, new ArrayList<Product>());
			this.productMap.get(supplierId).add(product);
		}
		
	}

	@Override
	public List<ProductsBySupplier> getAllSupplierProdcts() throws IOException {
		List<ProductsBySupplier> ret = new ArrayList<ProductsBySupplier>();
		SupplierDao supplierDao = new SupplierDaoImpl();
		for(Integer supplierId : this.productMap.keySet()) {
			Supplier supplier = supplierDao.getSupplierById(supplierId);
			ProductsBySupplier productBySupplier = new ProductsBySupplier(supplier,
					this.productMap.get(supplierId));
		    ret.add(productBySupplier);	
		}
		return ret;
	}

}
