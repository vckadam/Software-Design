package com.vckadam.oopdesign.NorthWind.dao.order.test;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.dao.order.OrderDao;
import com.vckadam.oopdesign.NorthWind.dao.order.OrderDaoImpl;

public class OrderDaoImplTest {
	@Test
	public void test1() throws IOException, NumberFormatException, ParseException {
		OrderDao orderDao = new OrderDaoImpl();
	}
}
