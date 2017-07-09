package com.vckadam.oopdesign.NorthWind.service;

import java.io.IOException;
import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.Supplier;

public interface Service {
	public List<Supplier> sellProductInCategory(String categoryName) throws IOException;
}
