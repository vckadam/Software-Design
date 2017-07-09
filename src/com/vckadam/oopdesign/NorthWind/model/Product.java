package com.vckadam.oopdesign.NorthWind.model;

public class Product {
	private final int productId;
	private int supplierId, categoryId, unitsInStock, unitsOnOrder, reorderLevel;
	private String productName, quantityPerUnit;
	private double unitPrice;
	private boolean discontinued;
	public Product(int productId, String productName, int supplierId, int categoryId, String quantityPerUnit, 
			double unitPrice, int unitsInStock, int unitsOnOrder, int reorderLevel,
			   boolean discontinued) {
		super();
		this.productId = productId;
		this.supplierId = supplierId;
		this.categoryId = categoryId;
		this.unitsInStock = unitsInStock;
		this.unitsOnOrder = unitsOnOrder;
		this.reorderLevel = reorderLevel;
		this.productName = productName;
		this.quantityPerUnit = quantityPerUnit;
		this.unitPrice = unitPrice;
		this.discontinued = discontinued;
	}
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(int unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public int getUnitsOnOrder() {
		return unitsOnOrder;
	}
	public void setUnitsOnOrder(int unitsOnOrder) {
		this.unitsOnOrder = unitsOnOrder;
	}
	public int getReorderLevel() {
		return reorderLevel;
	}
	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQuantityPerUnit() {
		return quantityPerUnit;
	}
	public void setQuantityPerUnit(String quantityPerUnit) {
		this.quantityPerUnit = quantityPerUnit;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public boolean isDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}
	public int getProductId() {
		return productId;
	}
	public String toString() {
		return this.productId+" "+this.productName+" "+this.supplierId+" "+this.categoryId+" "+
	    this.quantityPerUnit+" "+this.unitPrice+" "+this.unitsInStock+" "+
	    this.unitsOnOrder+" "+this.reorderLevel+" "+this.discontinued;
	}
}
