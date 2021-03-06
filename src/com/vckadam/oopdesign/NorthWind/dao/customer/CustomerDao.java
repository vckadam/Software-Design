package com.vckadam.oopdesign.NorthWind.dao.customer;

import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.NorthWind.model.Customer;

public interface CustomerDao {
	public List<Customer> getAllCustomer();
	public Map<String,Customer> getCustomerMap();
}
