package com.vckadam.oopdesign.NorthWind.dao.ordersbycustomer;

import java.io.IOException;
import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.OrdersByCustomer;

public interface OrdersByCustomerDao {
	List<OrdersByCustomer> getOrdersByCustomer()throws IOException;
}
