package com.vckadam.oopdesign.bookstore.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
		if(booksByLoc == null || orders == null)
			throw new IllegalArgumentException("Illegal Argument");
		
		List<OrdersByLoc> ordersByLocList = new ArrayList<OrdersByLoc>();
		if(booksByLoc.size() == 0 || orders.size() == 0) 
			return ordersByLocList;
		
		Map<String,OrdersByLoc> ordersByLocMap = new HashMap<>();
		Map<Long,List<BookOrder>> bookIdToOrdersMap = new HashMap<>();
		Map<Long,List<BookLocation>> bookIdToLocsMap = new HashMap<>(); 
		List<LocsByBook> tempBooksByLoc = new ArrayList<>(booksByLoc);
		List<BookOrder> tempOrders = new ArrayList<>(orders);
		
		for(LocsByBook ele : tempBooksByLoc) {
			if(ele != null) {
				List<BookLocation> currLocs;
				if((currLocs = ele.getBookLocs()) != null) {
					for(BookLocation currLoc : currLocs) {
						if(currLoc != null) {
							putInMap(bookIdToLocsMap,ele.getBook().getBookId(),currLoc);
						}
					}
				}
			}
		}
		
		for(BookOrder order : tempOrders) {
			if(order != null) {
				putInMap(bookIdToOrdersMap, order.getBookId(),order);
			}
		}
		
		for(Long bookId : bookIdToOrdersMap.keySet()) {
			List<BookOrder> bookOrders = bookIdToOrdersMap.get(bookId);
			if(bookOrders != null) {
				Collections.sort(bookOrders,(a,b)->(a.getQuantity() <= b.getQuantity())?-1:1);
				List<BookLocation> bookLocs = bookIdToLocsMap.get(bookId);
				if(bookLocs != null) {
					TreeMap<Long,List<BookLocation>> stocksInLoc = new TreeMap<>();
					for(BookLocation ele : bookLocs) {
						if(ele != null) {
							putInMap(stocksInLoc, ele.getStock(), ele);
						}
					}
					for(BookOrder currOrd : bookOrders) {
						if(currOrd != null) {
							Long currLocKey = stocksInLoc.ceilingKey(currOrd.getQuantity());
							if(currLocKey != null) {
								List<BookLocation> currLocList = stocksInLoc.get(currLocKey);
								int listSize;
								if(currLocList != null && (listSize = currLocList.size()) > 0) {
									BookLocation currLoc = currLocList.get(listSize-1);
									String currKey = currLoc.getLocId()+"#"+currLoc.getBookId();
									if(!ordersByLocMap.containsKey(currKey))
										ordersByLocMap.put(currKey, new OrdersByLoc(currLoc, new ArrayList<BookOrder>()));
									ordersByLocMap.get(currKey).getOrders().add(currOrd);
									Long extra = currLoc.getStock()-currOrd.getQuantity();
									currLoc.setStock(extra);
									currLocList.remove(listSize-1);
									if(currLocList.size() == 0)
										stocksInLoc.remove(currLocKey);
									if(extra > 0)  {
										putInMap(stocksInLoc, extra, currLoc);
									}
								}
							}
						}
					}
				}
			}
		}
		
		for(String ele : ordersByLocMap.keySet()) {
			OrdersByLoc currEle;
			if(ele != null && (currEle=ordersByLocMap.get(ele)).getOrders().size() > 0) {
				ordersByLocList.add(currEle);
			}
		}
		return ordersByLocList;
	}
	
	private <K,V> void putInMap(Map<K,List<V>> map, K key, V val) {
		if(map != null) {
			if(!map.containsKey(key)) 
				map.put(key, new ArrayList<V>());
			map.get(key).add(val);
		}
	}

}
