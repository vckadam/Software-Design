package com.vckadam.oopdesign.NorthWind.test.dao.ordersbycustomer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.dao.ordersbycustomer.OrdersByCustomerDao;
import com.vckadam.oopdesign.NorthWind.dao.ordersbycustomer.OrdersByCustomerDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Order;
import com.vckadam.oopdesign.NorthWind.model.OrdersByCustomer;

public class OrdersByCustomerDaoImplTest {
	
	private OrdersByCustomerDao ordersByCustomerDao;
	
	@Before
	public void beforeMethod() throws NumberFormatException, IOException, ParseException {
		this.ordersByCustomerDao = new OrdersByCustomerDaoImpl();
	}
	
	@After
	public void afterMethod() {
		this.ordersByCustomerDao = null;
	}
	
	@Test
	public void getOrdersByCustomerTest1() throws IOException {
		List<OrdersByCustomer> orderByCustomerList = this.ordersByCustomerDao.getOrdersByCustomer();
		Map<String, Set<Integer>> actualMap = new HashMap<String, Set<Integer>>();
		for(OrdersByCustomer orderByCustomer: orderByCustomerList) {
			List<Order> orders = orderByCustomer.getOrders();
			String key = orderByCustomer.getCustomer().getCustomerId();
			actualMap.put(key, new HashSet<Integer>());
			for(Order order: orders) {
				actualMap.get(key).add(order.getOrderId());
			}			
		}
		
		Map<String, Set<Integer>> expectedMap = new HashMap<String, Set<Integer>>();
		expectedMap.put("VINET", new HashSet<Integer>(Arrays.asList(10248)));
		expectedMap.put("ALFKI", new HashSet<Integer>(Arrays.asList(10249, 10250)));
		expectedMap.put("AROUT", new HashSet<Integer>(Arrays.asList(10251)));
		expectedMap.put("SUPRD", new HashSet<Integer>(Arrays.asList(10252)));
		
		assertEquals(expectedMap, actualMap);
	}
}
