package com.vckadam.oopdesign.NorthWind.dao.ordersbycustomer;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.NorthWind.dao.customer.CustomerDao;
import com.vckadam.oopdesign.NorthWind.dao.customer.CustomerDaoImpl;
import com.vckadam.oopdesign.NorthWind.dao.order.OrderDao;
import com.vckadam.oopdesign.NorthWind.dao.order.OrderDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Customer;
import com.vckadam.oopdesign.NorthWind.model.Order;
import com.vckadam.oopdesign.NorthWind.model.OrdersByCustomer;

public class OrdersByCustomerDaoImpl implements OrdersByCustomerDao {

	Map<String, List<Order>> ordersByCustomerMap;
	
	public OrdersByCustomerDaoImpl() throws NumberFormatException, IOException, ParseException {
		this.ordersByCustomerMap = new HashMap<String, List<Order>>();
		loadMap();
	}
	
	private void loadMap() throws NumberFormatException, IOException, ParseException {
		OrderDao orderDao = new OrderDaoImpl();
		List<Order> orders = orderDao.getOrderList();
		for(Order order : orders) {
			String customerId = order.getCustomerId();
			if(!this.ordersByCustomerMap.containsKey(customerId))
				this.ordersByCustomerMap.put(customerId, new ArrayList<Order>());
			this.ordersByCustomerMap.get(customerId).add(order);
		}
	}
	
	@Override
	public List<OrdersByCustomer> getOrdersByCustomer() throws IOException {
		List<OrdersByCustomer> ordersByCustomerList = new ArrayList<OrdersByCustomer>();
		CustomerDao customerDao = new CustomerDaoImpl();
		for(String customerId : this.ordersByCustomerMap.keySet()) {
			Customer customer = customerDao.getCustomerMap().get(customerId);
			List<Order> orders = this.ordersByCustomerMap.get(customerId);
			ordersByCustomerList.add(new OrdersByCustomer(customer, orders));
		}
		return ordersByCustomerList;
	}

}
