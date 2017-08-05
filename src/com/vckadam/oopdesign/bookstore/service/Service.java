package com.vckadam.oopdesign.bookstore.service;

import java.util.List;

import com.vckadam.oopdesign.bookstore.model.Book;
import com.vckadam.oopdesign.bookstore.model.BookLocation;
import com.vckadam.oopdesign.bookstore.model.BookOrder;
import com.vckadam.oopdesign.bookstore.model.LocsByBook;
import com.vckadam.oopdesign.bookstore.model.OrdersByLoc;

public interface Service {
	List<LocsByBook> getBooksByLocation(List<Book> books, List<BookLocation> locs);
	List<OrdersByLoc> getOrdersByLocation(List<LocsByBook> booksByLoc, List<BookOrder> orders);
	
}
