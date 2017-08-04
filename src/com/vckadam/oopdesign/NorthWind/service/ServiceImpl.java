package com.vckadam.oopdesign.NorthWind.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vckadam.oopdesign.NorthWind.dao.category.CategoryDao;
import com.vckadam.oopdesign.NorthWind.dao.category.CategoryDaoImpl;
import com.vckadam.oopdesign.NorthWind.dao.product.ProductDao;
import com.vckadam.oopdesign.NorthWind.dao.product.ProductDaoImpl;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDao;
import com.vckadam.oopdesign.NorthWind.dao.supplier.SupplierDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Category;
import com.vckadam.oopdesign.NorthWind.model.Product;
import com.vckadam.oopdesign.NorthWind.model.ProductsBySupplier;
import com.vckadam.oopdesign.NorthWind.model.ProductsInCat;
import com.vckadam.oopdesign.NorthWind.model.Supplier;

public class ServiceImpl implements Service {

	SupplierDao supplierDao;
	ProductDao productDao;
	private CategoryDao catDao;
	
	public ServiceImpl() throws IOException {
		this.supplierDao = new SupplierDaoImpl();
		this.productDao = new ProductDaoImpl();
		this.catDao = new CategoryDaoImpl();
	}
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

	@Override
	public List<ProductsBySupplier> getProductsInSupplier() {
		List<Product> products = this.productDao.getAllProducts();
		List<Supplier> suppliers = this.supplierDao.getSupplierList();
		return getProductsInSupplier(products,suppliers);
	}

	@Override
	public List<ProductsBySupplier> getProductsInSupplier(List<Product> products, List<Supplier> suppliers) {
		if (products==null || suppliers==null || products.size()==0 || suppliers.size()==0)
			throw new IllegalArgumentException("Illegal Argument Products: "+products+" supplier: "+suppliers);
		List<Product> tempProducts = new ArrayList<>(products);
		List<Supplier> tempSuppliers = new ArrayList<>(suppliers);
		Map<Integer,List<Product>> supIdToProdMap = new HashMap<>();
		Map<Integer,Supplier> supIdToSupMap = new HashMap<>();
		List<ProductsBySupplier> prodsBySupList = new ArrayList<>();
		Set<Integer> supSet = new HashSet<>();
		Set<Integer> prodSet = new HashSet<>();
		for(Supplier sup : tempSuppliers) {
			if(sup != null && !supSet.contains(sup.getSupplierId())) {
				if(!supIdToSupMap.containsKey(sup.getSupplierId()))
					supIdToSupMap.put(sup.getSupplierId(), sup);
				supSet.add(sup.getSupplierId());
			}
		}
		for(Product prod : tempProducts) {
			if(prod != null && !prodSet.contains(prod.getProductId())) {
				int supId = prod.getSupplierId();
				if(!supIdToProdMap.containsKey(supId)) 
					supIdToProdMap.put(supId, new ArrayList<Product>());
				supIdToProdMap.get(supId).add(prod);
				prodSet.add(prod.getProductId());
			}
		}
		for(Integer key : supIdToProdMap.keySet()) {
			Supplier currSup = supIdToSupMap.get(key);
			List<Product> currProds = supIdToProdMap.get(key);
			if(currSup != null && currProds != null) {
				prodsBySupList.add(new ProductsBySupplier(currSup,currProds));
			}
		}
		return prodsBySupList;
	}
	@Override
	public List<ProductsInCat> getProductsInCategory() {
		List<Category> cats = this.catDao.getAllCategories();
		List<Product> prods = this.productDao.getAllProducts();
		return getProductsInCategory(cats,prods);
	}
	@Override
	public List<ProductsInCat> getProductsInCategory(List<Category> cats, List<Product> prods) {
		if(cats == null || prods == null || cats.size() == 0 || prods.size() == 0) 
			throw new IllegalArgumentException("Illegal Argument category List: "+cats+" Produce List: "+prods);
		List<Product> tempProds = new ArrayList<Product>(prods);
		List<Category> tempCats = new ArrayList<Category>(cats);
		List<ProductsInCat> prodsToCatId = new ArrayList<>();
		Map<Integer,List<Product>> catIdToProdsMap = new HashMap<>();
		Set<Integer> prodSet = new HashSet<>();
		Set<Integer> catSet = new HashSet<>();
		for(Product prod : tempProds) {
			if(prod != null && !prodSet.contains(prod.getProductId())) {
				prodSet.add(prod.getProductId());
				int currCatId = prod.getCategoryId();
				if(!catIdToProdsMap.containsKey(currCatId)) catIdToProdsMap.put(currCatId, new ArrayList<Product>());
				catIdToProdsMap.get(currCatId).add(prod);
			}
		}
		for(Category cat : tempCats) {
			if(cat != null && catIdToProdsMap.containsKey(cat.getCategoryId())) {
				if(!catSet.contains(cat.getCategoryId())) {
					prodsToCatId.add(new ProductsInCat(cat,catIdToProdsMap.get(cat.getCategoryId())));
					catSet.add(cat.getCategoryId());
				}
			}
		}
		return prodsToCatId;
	}

}
