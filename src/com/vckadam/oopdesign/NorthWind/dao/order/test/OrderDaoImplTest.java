package com.vckadam.oopdesign.NorthWind.dao.order.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.dao.order.OrderDao;
import com.vckadam.oopdesign.NorthWind.dao.order.OrderDaoImpl;

public class OrderDaoImplTest {
	@Test
	public void companyPacedOrderInTest() throws IOException, NumberFormatException, ParseException {
		OrderDao orderDao = new OrderDaoImpl();
		Set<String> expected = new HashSet<String>();
		expected.add("Berglunds snabbkp");
		expected.add("Antonio Moreno Taquera");
		Set<String> actual = new HashSet<String>(orderDao.companyPacedOrderIn(1996));
		assertEquals(expected, actual);
	}
	
	@Test
	public void companyPacedOrderInTest2() throws IOException, NumberFormatException, ParseException {
		OrderDao orderDao = new OrderDaoImpl();
		Set<String> expected = new HashSet<String>();
		Set<String> actual = new HashSet<String>(orderDao.companyPacedOrderIn(1994));
		assertEquals(expected, actual);
	}
}
