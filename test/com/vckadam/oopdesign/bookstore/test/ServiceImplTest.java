package com.vckadam.oopdesign.bookstore.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.bookstore.model.Book;
import com.vckadam.oopdesign.bookstore.model.BookLocation;
import com.vckadam.oopdesign.bookstore.model.BookOrder;
import com.vckadam.oopdesign.bookstore.model.LocsByBook;
import com.vckadam.oopdesign.bookstore.model.OrdersByLoc;
import com.vckadam.oopdesign.bookstore.service.Service;
import com.vckadam.oopdesign.bookstore.service.ServiceImpl;

public class ServiceImplTest {

	private Service service;
	@Before
	public void setUp() throws Exception {
		this.service = new ServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		this.service = null;
	}
	
	@Test 
	public void testGetOrdersByLocation_BasicScenario() {
		Object[] locs1 = {Arrays.asList(1L, 1L, 9L),Arrays.asList(2L, 1L, 1L)};
		Object[] locs2 = {Arrays.asList(3L, 2L, 3L)};
		Object[][] booksToLocs = {{1L,locs1},{2L,locs2}};
		List<LocsByBook> bookByLocs = prepListForTestGetOrdersByLocation(booksToLocs);
		Object[] orders = {Arrays.asList(1L,1L,1L),Arrays.asList(2L,1L,9L),Arrays.asList(3L,2L,1L),Arrays.asList(4L,2L,3L),Arrays.asList(5L,2L,1L)};
		List<BookOrder> bookOrders = prepBookOrdersForTestGetOrdersByLocation(orders);
		List<OrdersByLoc> ordersByLocList = this.service.getOrdersByLocation(bookByLocs, bookOrders);
		Map<Long,Set<Long>> locIdToOrderIdsActMap = prepActualMapForTestGetOrdersByLocation(ordersByLocList);
		Object[][] locIdToOrderIds = {{2L, Arrays.asList(1L)},{1L,Arrays.asList(2L)},{3L,Arrays.asList(3L,5L)}};
		Map<Long,Set<Long>> locIdToOrderIdsExpMap = prepExpectedMapForTestGetOrdersByLocation(locIdToOrderIds);
		assertEquals(locIdToOrderIds.length, ordersByLocList.size());
		assertEquals(locIdToOrderIdsExpMap, locIdToOrderIdsActMap);
	}
	
	private List<LocsByBook> prepListForTestGetOrdersByLocation(Object[][] booksToLocs) {
		List<LocsByBook> bookByLocsList = new ArrayList<>();
		for(Object[] ele : booksToLocs) {
			if(ele != null) {
				Long curBkId = (Long)ele[0];
				List<BookLocation> bookLocs = null;
				Book currBook = null;
				if(curBkId != null) {
					currBook = new Book(curBkId, null, 0.0);
				}					
				Object[] curLocs = (Object[])ele[1];
				if(curLocs != null) {
					for(Object listEle : curLocs) {
						BookLocation currBookLoc = null;
						@SuppressWarnings("unchecked")
						List<Long> currLocId = (List<Long>)listEle;
						if(currLocId != null) {
							currBookLoc = new BookLocation(currLocId.get(0), null, currLocId.get(1), currLocId.get(2));
							if(bookLocs == null) 
								bookLocs = new ArrayList<BookLocation>();
							bookLocs.add(currBookLoc);
						}
					}
				}
				bookByLocsList.add(new LocsByBook(currBook,bookLocs));
			}
		}
		return bookByLocsList;
	}
	
	@SuppressWarnings("unchecked")
	private List<BookOrder> prepBookOrdersForTestGetOrdersByLocation(Object[] orders) {
		List<BookOrder> bkOrders = new ArrayList<BookOrder>();
		for(Object ele : orders) {
			BookOrder currOrder = null;
			List<Long> currList;
			if((currList = (List<Long>)ele) != null) {
				currOrder = new BookOrder(currList.get(0), currList.get(1), 2L, currList.get(2));
			}
			bkOrders.add(currOrder);
		}
		return bkOrders;
	}
	
	private Map<Long,Set<Long>> prepActualMapForTestGetOrdersByLocation(List<OrdersByLoc> ordersByLocList) {
		Map<Long,Set<Long>> LocIdToOrdIds = new HashMap<>();
		for(OrdersByLoc ele : ordersByLocList) {
			BookLocation currLoc = ele.getLoc();
			List<BookOrder> currOrders = ele.getOrders();
			Set<Long> orderIds = new HashSet<Long>();
			for(BookOrder bkOrd : currOrders) {
				orderIds.add(bkOrd.getOrderId());
			}
			LocIdToOrdIds.put(currLoc.getLocId(), orderIds);
		}
		return LocIdToOrdIds;
	}
	
	Map<Long,Set<Long>> prepExpectedMapForTestGetOrdersByLocation(Object[][] locIdToOrderIds) {
		Map<Long,Set<Long>> locIdToOrdIds = new HashMap<>();
		for(Object[] ele : locIdToOrderIds) {
			Long locId = (Long)ele[0];
			@SuppressWarnings("unchecked")
			List<Long> orderIds = (List<Long>)ele[1];
			Set<Long> orderIdSet = new HashSet<>();
			for(Long orderId : orderIds) orderIdSet.add(orderId);
			locIdToOrdIds.put(locId, orderIdSet);
		}
		return locIdToOrdIds;
	}
	
	/*************************************************************/

	@Test
	public void testGetBooksByLocation_BasicScenario() {
		Long[] bookIds = {1L ,2L,3L};
		List<Book> books = prepBooksForTestGetBooksByLocation(bookIds);
		long[][] locToBookIds = {{1,1},{2,1},{3,1},{1,2},{3,3}};
		List<BookLocation> locs = prepLocsForTestGetBooksByLocation(locToBookIds);
		List<LocsByBook> locsByBook = this.service.getBooksByLocation(books, locs);
		Map<Long,List<Long>> actualBkToLocsMap = prepActualBkToLocsMap(locsByBook);
		
		Object[][] expectedBkToLocs = {{1L,Arrays.asList(1L,2L,3L)},{2L,Arrays.asList(1L)},{3L,Arrays.asList(3L)}};
		Map<Long,List<Long>> expectedBkToLocMap = prepExpectedBkToLocsMap(expectedBkToLocs);
		
		assertEquals(expectedBkToLocMap.size(), locsByBook.size());
		assertEquals(expectedBkToLocMap, actualBkToLocsMap);
	}
	
	@Test
	public void testGetBooksByLocation_BookWithoutLoc() {
		Long[] bookIds = {1L ,2L,3L, 4L}; //4L without location
		List<Book> books = prepBooksForTestGetBooksByLocation(bookIds);
		long[][] locToBookIds = {{1,1},{2,1},{3,1},{1,2},{3,3}};
		List<BookLocation> locs = prepLocsForTestGetBooksByLocation(locToBookIds);
		List<LocsByBook> locsByBook = this.service.getBooksByLocation(books, locs);
		Map<Long,List<Long>> actualBkToLocsMap = prepActualBkToLocsMap(locsByBook);
		
		Object[][] expectedBkToLocs = {{1L,Arrays.asList(1L,2L,3L)},{2L,Arrays.asList(1L)},{3L,Arrays.asList(3L)}};
		Map<Long,List<Long>> expectedBkToLocMap = prepExpectedBkToLocsMap(expectedBkToLocs);
		
		assertEquals(expectedBkToLocMap.size(), locsByBook.size());
		assertEquals(expectedBkToLocMap, actualBkToLocsMap);
	}
	
	@Test
	public void testGetBooksByLocation_LocIdWithInvalidBookId() {
		Long[] bookIds = {1L ,2L,3L, 4L}; //4L without location
		List<Book> books = prepBooksForTestGetBooksByLocation(bookIds);
		long[][] locToBookIds = {{1,1},{2,1},{3,1},{1,2},{3,3},{3,6}}; //bookId with 6 doen't exist.
		List<BookLocation> locs = prepLocsForTestGetBooksByLocation(locToBookIds);
		List<LocsByBook> locsByBook = this.service.getBooksByLocation(books, locs);
		Map<Long,List<Long>> actualBkToLocsMap = prepActualBkToLocsMap(locsByBook);
		
		Object[][] expectedBkToLocs = {{1L,Arrays.asList(1L,2L,3L)},{2L,Arrays.asList(1L)},{3L,Arrays.asList(3L)}};
		Map<Long,List<Long>> expectedBkToLocMap = prepExpectedBkToLocsMap(expectedBkToLocs);
		
		assertEquals(expectedBkToLocMap.size(), locsByBook.size());
		assertEquals(expectedBkToLocMap, actualBkToLocsMap);
	}
	
	@Test
	public void testGetBooksByLocation_DuplicateBookId() {
		Long[] bookIds = {1L ,2L,3L, 3L}; //3L duplicate book Id 
		List<Book> books = prepBooksForTestGetBooksByLocation(bookIds);
		long[][] locToBookIds = {{1,1},{2,1},{3,1},{1,2},{3,3}};
		List<BookLocation> locs = prepLocsForTestGetBooksByLocation(locToBookIds);
		List<LocsByBook> locsByBook = this.service.getBooksByLocation(books, locs);
		Map<Long,List<Long>> actualBkToLocsMap = prepActualBkToLocsMap(locsByBook);
		
		Object[][] expectedBkToLocs = {{1L,Arrays.asList(1L,2L,3L)},{2L,Arrays.asList(1L)},{3L,Arrays.asList(3L)}};
		Map<Long,List<Long>> expectedBkToLocMap = prepExpectedBkToLocsMap(expectedBkToLocs);
		
		assertEquals(expectedBkToLocMap.size(), locsByBook.size());
		assertEquals(expectedBkToLocMap, actualBkToLocsMap);
	}
	
	@Test
	public void testGetBooksByLocation_DuplicateLocToBookId() {
		Long[] bookIds = {1L ,2L,3L, 3L}; //3L duplicate book Id 
		List<Book> books = prepBooksForTestGetBooksByLocation(bookIds);
		long[][] locToBookIds = {{1,1},{2,1},{3,1},{1,2},{3,3},{3,3}}; //3,3 locTobookId duplicate
		List<BookLocation> locs = prepLocsForTestGetBooksByLocation(locToBookIds);
		List<LocsByBook> locsByBook = this.service.getBooksByLocation(books, locs);
		Map<Long,List<Long>> actualBkToLocsMap = prepActualBkToLocsMap(locsByBook);
		
		Object[][] expectedBkToLocs = {{1L,Arrays.asList(1L,2L,3L)},{2L,Arrays.asList(1L)},{3L,Arrays.asList(3L)}};
		Map<Long,List<Long>> expectedBkToLocMap = prepExpectedBkToLocsMap(expectedBkToLocs);
		
		assertEquals(expectedBkToLocMap.size(), locsByBook.size());
		assertEquals(expectedBkToLocMap, actualBkToLocsMap);
	}
	
	@Test
	public void testGetBooksByLocation_NullBookId() {
		Long[] bookIds = {null,1L, null,2L,null,3L}; 
		List<Book> books = prepBooksForTestGetBooksByLocation(bookIds);
		long[][] locToBookIds = {{1,1},{2,1},{3,1},{1,2},{3,3}};
		List<BookLocation> locs = prepLocsForTestGetBooksByLocation(locToBookIds);
		List<LocsByBook> locsByBook = this.service.getBooksByLocation(books, locs);
		Map<Long,List<Long>> actualBkToLocsMap = prepActualBkToLocsMap(locsByBook);
		
		Object[][] expectedBkToLocs = {{1L,Arrays.asList(1L,2L,3L)},{2L,Arrays.asList(1L)},{3L,Arrays.asList(3L)}};
		Map<Long,List<Long>> expectedBkToLocMap = prepExpectedBkToLocsMap(expectedBkToLocs);
		
		assertEquals(expectedBkToLocMap.size(), locsByBook.size());
		assertEquals(expectedBkToLocMap, actualBkToLocsMap);
	}
	
	@Test
	public void testGetBooksByLocation_NullLocToBookId() {
		Long[] bookIds = {null,1L, null,2L,null,3L}; 
		List<Book> books = prepBooksForTestGetBooksByLocation(bookIds);
		long[][] locToBookIds = {null,{1,1},{2,1},null,{3,1},null,{1,2},{3,3}};
		List<BookLocation> locs = prepLocsForTestGetBooksByLocation(locToBookIds);
		List<LocsByBook> locsByBook = this.service.getBooksByLocation(books, locs);
		Map<Long,List<Long>> actualBkToLocsMap = prepActualBkToLocsMap(locsByBook);
		
		Object[][] expectedBkToLocs = {{1L,Arrays.asList(1L,2L,3L)},{2L,Arrays.asList(1L)},{3L,Arrays.asList(3L)}};
		Map<Long,List<Long>> expectedBkToLocMap = prepExpectedBkToLocsMap(expectedBkToLocs);
		
		assertEquals(expectedBkToLocMap.size(), locsByBook.size());
		assertEquals(expectedBkToLocMap, actualBkToLocsMap);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetBooksByLocation_NullBookList() {
		List<Book> books = null;
		long[][] locToBookIds = {null,{1,1},{2,1},null,{3,1},null,{1,2},{3,3}};
		List<BookLocation> locs = prepLocsForTestGetBooksByLocation(locToBookIds);
		this.service.getBooksByLocation(books, locs);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetBooksByLocation_NullLocList() {
		List<Book> books = null;
		List<BookLocation> locs = null;
		this.service.getBooksByLocation(books, locs);
	}
	
	private List<Book> prepBooksForTestGetBooksByLocation(Long[] bookIds) {
		List<Book> books = new ArrayList<>();
		for(Long bookId : bookIds) {
			if(bookId != null) books.add(new Book(bookId.longValue(), null, 0.0));
			else books.add(null);
		}
		return books;
	}
	
	private List<BookLocation> prepLocsForTestGetBooksByLocation(long[][] locToBookIds) {
		List<BookLocation> bkLocs = new ArrayList<>();
		for(long[] locToBookId : locToBookIds){
			if(locToBookId != null) {
				bkLocs.add(new BookLocation(locToBookId[0], null, locToBookId[1], 0));
			} else bkLocs.add(null);
		}
		return bkLocs;
	}

	private Map<Long,List<Long>> prepActualBkToLocsMap(List<LocsByBook> locsByBook) {
		Map<Long,List<Long>> bkToLocsMap = new HashMap<>();
		for(LocsByBook ele : locsByBook) {
			long bookId = ele.getBook().getBookId();
			List<Long> bookLocs = new ArrayList<>();
			List<BookLocation> locs = ele.getBookLocs();
			for(BookLocation loc : locs) {
				bookLocs.add(loc.getLocId());
			}
			bkToLocsMap.put(bookId, bookLocs);
		}
		return bkToLocsMap;
	}
	
	@SuppressWarnings("unchecked")
	private Map<Long,List<Long>> prepExpectedBkToLocsMap(Object[][] bkToLocs) {
		Map<Long,List<Long>> bkToLocsMap = new HashMap<>();
		for(Object[] ele : bkToLocs) {
			/*List<Long> locIds = new ArrayList<Long>();
			for(Integer id : (List<Integer>)ele[1]) locIds.add((long)id);*/
			bkToLocsMap.put((Long) ele[0], (List<Long>)ele[1]);
		}
		return bkToLocsMap;
	}
	
}
