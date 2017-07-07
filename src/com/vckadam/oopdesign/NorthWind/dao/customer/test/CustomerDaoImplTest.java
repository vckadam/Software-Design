package com.vckadam.oopdesign.NorthWind.dao.customer.test;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.dao.customer.CustomerDao;
import com.vckadam.oopdesign.NorthWind.dao.customer.CustomerDaoImpl;
import com.vckadam.oopdesign.NorthWind.dao.order.OrderDao;
import com.vckadam.oopdesign.NorthWind.dao.order.OrderDaoImpl;

public class CustomerDaoImplTest {
	@Test
	public void test1() throws IOException, NumberFormatException, ParseException {
		CustomerDao customerDao = new CustomerDaoImpl();
	}
}
