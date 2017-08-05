package com.vckadam.oopdesign.bookstore.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.bookstore.model.Book;
import com.vckadam.oopdesign.bookstore.model.BookLocation;
import com.vckadam.oopdesign.bookstore.model.LocsByBook;
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
			if(locToBookIds != null) {
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
