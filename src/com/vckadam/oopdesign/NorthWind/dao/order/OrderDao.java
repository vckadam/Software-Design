package com.vckadam.oopdesign.NorthWind.dao.order;

import java.io.IOException;
import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.Order;

public interface OrderDao {
	public List<String> companyPacedOrderIn(int year) throws IOException;
	public List<Order> sortFromExpensiveToCheapest();
	public List<Order> getOrderList();
}
