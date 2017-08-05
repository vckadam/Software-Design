package com.vckadam.oopdesign.bookstore.model;

import java.util.List;


public class OrdersByLoc {
	private BookLocation loc;
	private List<BookOrder> orders;
	public OrdersByLoc(BookLocation loc, List<BookOrder> orders) {
		super();
		this.loc = loc;
		this.orders = orders;
	}
	public BookLocation getLoc() {
		return loc;
	}
	public void setLoc(BookLocation loc) {
		this.loc = loc;
	}
	public List<BookOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<BookOrder> orders) {
		this.orders = orders;
	}
	
}
