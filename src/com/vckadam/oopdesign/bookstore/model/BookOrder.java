package com.vckadam.oopdesign.bookstore.model;

public class BookOrder {
	private final long orderId;
	private long bookId, customerId;
	private long quantity;
	public BookOrder(long orderId, long bookId, long customerId, long quantity) {
		super();
		this.orderId = orderId;
		this.bookId = bookId;
		this.customerId = customerId;
		this.quantity = quantity;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	public long getOrderId() {
		return orderId;
	}
}
