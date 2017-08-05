package com.vckadam.oopdesign.bookstore.model;

public class Book {
	private final long bookId;
	private String bookName;
	private double price;
	public Book(long bookId, String bookName, double price) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.price = price;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getBookId() {
		return bookId;
	}
}
