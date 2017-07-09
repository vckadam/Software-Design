package com.vckadam.oopdesign.NorthWind.service;

import java.io.IOException;
import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.Customer;

public interface Service {
	public List<Customer> sellProductInCategory(String categoryName) throws IOException;
}
