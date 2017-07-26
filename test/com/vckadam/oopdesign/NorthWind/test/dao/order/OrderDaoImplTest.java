package com.vckadam.oopdesign.NorthWind.test.dao.order;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.dao.order.OrderDao;
import com.vckadam.oopdesign.NorthWind.dao.order.OrderDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Order;

public class OrderDaoImplTest {
	
	OrderDao orderDao;
	
	@Before
	public void beforeMethod() throws NumberFormatException, IOException, ParseException {
		this.orderDao = new OrderDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.orderDao = null;
	}
		
	@Test
	public void companyPacedOrderInTest() throws IOException, NumberFormatException, ParseException {
		Set<String> expected = new HashSet<String>();
		expected.add("Berglunds snabbkp");
		expected.add("Antonio Moreno Taquera");
		Set<String> actual = new HashSet<String>(orderDao.companyPacedOrderIn(1996));
		assertEquals(expected, actual);
	}
	
	@Test
	public void companyPacedOrderInTest2() throws IOException, NumberFormatException, ParseException {
		Set<String> expected = new HashSet<String>();
		Set<String> actual = new HashSet<String>(orderDao.companyPacedOrderIn(1994));
		assertEquals(expected, actual);
	}
	
	@Test
	public void sortFromExpensiveToCheapestTest1() {
		List<Order> orderList = this.orderDao.getOrderList();
		List<Order> expected = new ArrayList<Order>();
		expected.add(orderList.get(2));
		expected.add(orderList.get(4));
		expected.add(orderList.get(3));  
		expected.add(orderList.get(0));
		expected.add(orderList.get(1));
		List<Order> actual = this.orderDao.sortFromExpensiveToCheapest();
		assertEquals(expected, actual);
	}
}
