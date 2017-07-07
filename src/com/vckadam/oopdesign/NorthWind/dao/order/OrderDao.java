package com.vckadam.oopdesign.NorthWind.dao.order;

import java.util.List;

public interface OrderDao {
	public List<String> companyPacedOrderIn(int year);
}
