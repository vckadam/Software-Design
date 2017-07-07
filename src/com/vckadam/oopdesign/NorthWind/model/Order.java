package com.vckadam.oopdesign.NorthWind.model;

import java.util.Date;

public class Order {
	private final int orderId;
	private int employeeId, shipVia;
	private double freight;
	private String customerId, shipName, shipAddress, shipCity, shipRegion,
	               shipPostalCode, shipCountry;
	private Date orderDate, requiredDate, shippedDate;
	
	public Order(int orderId, String customerId, int employeeId, Date orderDate,  Date requiredDate, Date shippedDate,
			int shipVia, double freight, String shipName,
			String shipAddress, String shipCity, String shipRegion, String shipPostalCode, String shipCountry) {
		super();
		this.orderId = orderId;
		this.employeeId = employeeId;
		this.shipVia = shipVia;
		this.freight = freight;
		this.customerId = customerId;
		this.shipName = shipName;
		this.shipAddress = shipAddress;
		this.shipCity = shipCity;
		this.shipRegion = shipRegion;
		this.shipPostalCode = shipPostalCode;
		this.shipCountry = shipCountry;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
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
	public String getshipAddress() {
		return shipAddress;
	}
	public void setshipAddress(String shipAddress) {
		this.shipAddress = shipAddress;
	}
	public String getShipCity() {
		return shipCity;
	}
	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}
	public String getshipRegion() {
		return shipRegion;
	}
	public void setshipRegion(String shipRegion) {
		this.shipRegion = shipRegion;
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
	public String toString() {
		return this.orderId+" "+this.customerId+" "+this.employeeId+" "+this.orderDate+
				" "+this.requiredDate+" "+this.shippedDate+" "+this.shipVia+" "+
				this.freight+" "+this.shipName+" "+this.shipAddress+" "+
				this.shipCity+" "+this.shipRegion+" "+this.shipPostalCode+" "+
				this.shipCountry;
	}
	
}
