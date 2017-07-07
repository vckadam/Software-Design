package com.vckadam.oopdesign.NorthWind.dao.order;

import java.io.IOException;
import java.util.List;

public interface OrderDao {
	public List<String> companyPacedOrderIn(int year) throws IOException;
}
