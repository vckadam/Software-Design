package com.vckadam.oopdesign.NorthWind.model;

import java.util.List;

public class ProductsBySupplier {
	private Supplier supplier;
	private List<Product> products;
	public ProductsBySupplier(Supplier supplier, List<Product> products) {
		super();
		this.supplier = supplier;
		this.products = products;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
