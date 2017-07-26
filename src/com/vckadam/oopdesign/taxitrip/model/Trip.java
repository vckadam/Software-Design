package com.vckadam.oopdesign.taxitrip.model;

public class Trip {

	private final int id;
	private int clientId, driverId, cityId;
	private String status, requestAt;
	public Trip(int id, int clientId, int driverId, int cityId, String status, String requestAt) {
		super();
		this.id = id;
		this.clientId = clientId;
		this.driverId = driverId;
		this.cityId = cityId;
		this.status = status;
		this.requestAt = requestAt;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRequestAt() {
		return requestAt;
	}
	public void setRequestAt(String requestAt) {
		this.requestAt = requestAt;
	}
	public int getId() {
		return id;
	}
	
	
}
