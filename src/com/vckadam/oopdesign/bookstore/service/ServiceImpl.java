package com.vckadam.oopdesign.bookstore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vckadam.oopdesign.bookstore.model.Book;
import com.vckadam.oopdesign.bookstore.model.BookLocation;
import com.vckadam.oopdesign.bookstore.model.BookOrder;
import com.vckadam.oopdesign.bookstore.model.LocsByBook;
import com.vckadam.oopdesign.bookstore.model.OrdersByLoc;

public class ServiceImpl implements Service{

	@Override
	public List<LocsByBook> getBooksByLocation(List<Book> books, List<BookLocation> locs) {
		if(books == null || locs == null || books.size() == 0 || locs.size() == 0) 
			throw new IllegalArgumentException("Illegal Exception: BookList = "+books+" Location List = "+locs);
		List<Book> tempBooks = new ArrayList<>(books);
		List<BookLocation> tempLocs = new ArrayList<>(locs);
		Map<Long,List<BookLocation>> bookIdToLocsMap = new HashMap<>();
		Set<String> bookLocSet = new HashSet<>();
		Set<Long> bookSet = new HashSet<>();
		List<LocsByBook> locsByBook = new ArrayList<>();
		for(BookLocation loc : tempLocs) {
			String strId; 
			if(loc != null && !bookLocSet.contains((strId = loc.getBookId()+"#"+loc.getLocId()))) {
				bookLocSet.add(strId);
				long bookId;
				if(!bookIdToLocsMap.containsKey((bookId = loc.getBookId()))) {
					bookIdToLocsMap.put(bookId, new ArrayList<BookLocation>());
				}
				bookIdToLocsMap.get(bookId).add(loc);
			}
		}
		for(Book book : tempBooks) {
			long bookId;
			if(book != null && !bookSet.contains((bookId = book.getBookId()))) {
				bookSet.add(bookId);
				List<BookLocation> bcLocs = bookIdToLocsMap.get(bookId);
				if(bcLocs != null) {
					locsByBook.add(new LocsByBook(book, bcLocs));
				}
			}
		}
		return locsByBook;
	}


	@Override
	public List<OrdersByLoc> getOrdersByLocation(List<LocsByBook> booksByLoc, List<BookOrder> orders) {
		// TODO Auto-generated method stub
		return null;
	}

}
