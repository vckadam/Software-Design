package com.vckadam.oopdesign.taxitrip.model;

public class User {

	private final int id;
	private boolean banned;
	private String role;
	public User(int id, boolean banned, String role) {
		super();
		this.id = id;
		this.banned = banned;
		this.role = role;
	}
	public boolean isBanned() {
		return banned;
	}
	public void setBanned(boolean banned) {
		this.banned = banned;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public String toString() {
		return this.id+" "+this.isBanned()+" "+this.role;
	}
	
}
