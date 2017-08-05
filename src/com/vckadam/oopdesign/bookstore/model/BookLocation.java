package com.vckadam.oopdesign.bookstore.model;

public class BookLocation {
	private final long locId;
	private String locName;
	private long bookId, stock;
	public BookLocation(long locId, String locName, long bookId, long stock) {
		super();
		this.locId = locId;
		this.locName = locName;
		this.bookId = bookId;
		this.stock = stock;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public long getStock() {
		return stock;
	}
	public void setStock(long stock) {
		this.stock = stock;
	}
	public long getLocId() {
		return locId;
	}
	
}
