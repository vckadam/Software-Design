package com.vckadam.oopdesign.NorthWind.dao.supplier;

import java.util.List;

import com.vckadam.oopdesign.NorthWind.model.Supplier;

public interface SupplierDao {
	public List<Supplier> sortByCountryAndName();
	public List<Supplier> getSupplierList();
}
