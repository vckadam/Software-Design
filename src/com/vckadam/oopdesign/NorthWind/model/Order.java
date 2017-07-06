package com.vckadam.oopdesign.NorthWind.model;

import java.util.Date;

public class Order {
	private final int orderId;
	private int employeeId, shipVia;
	private double freight;
	private String customerId, shipName, shitAddress, shipCity, shitRegion,
	               shipPostalCode, shipCountry;
	private Date orderDate, requiredDate, shippedDate;
	public Order(final int orderId, 
			      final int employeeId, final int shipVia, 
			      final String customerId, final Date orderDate,
			      final Date requiredDate) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.employeeId = employeeId;
		this.shipVia = shipVia;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getShipVia() {
		return shipVia;
	}
	public void setShipVia(int shipVia) {
		this.shipVia = shipVia;
	}
	public double getFreight() {
		return freight;
	}
	public void setFreight(double freight) {
		this.freight = freight;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getShitAddress() {
		return shitAddress;
	}
	public void setShitAddress(String shitAddress) {
		this.shitAddress = shitAddress;
	}
	public String getShipCity() {
		return shipCity;
	}
	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}
	public String getShitRegion() {
		return shitRegion;
	}
	public void setShitRegion(String shitRegion) {
		this.shitRegion = shitRegion;
	}
	public String getShipPostalCode() {
		return shipPostalCode;
	}
	public void setShipPostalCode(String shipPostalCode) {
		this.shipPostalCode = shipPostalCode;
	}
	public String getShipCountry() {
		return shipCountry;
	}
	public void setShipCountry(String shipCountry) {
		this.shipCountry = shipCountry;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Date getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	public Date getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}
	public int getOrderId() {
		return orderId;
	}
	
}
