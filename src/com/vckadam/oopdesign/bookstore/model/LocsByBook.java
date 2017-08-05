package com.vckadam.oopdesign.bookstore.model;

import java.util.List;

public class LocsByBook {
	private Book book;
	private List<BookLocation> bookLocs;
	public LocsByBook(Book book, List<BookLocation> bookLocs) {
		super();
		this.book = book;
		this.bookLocs = bookLocs;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public List<BookLocation> getBookLocs() {
		return bookLocs;
	}
	public void setBookLocs(List<BookLocation> bookLocs) {
		this.bookLocs = bookLocs;
	}
	
	
}
