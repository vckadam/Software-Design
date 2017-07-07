package com.vckadam.oopdesign.NorthWind.dao.order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.NorthWind.dao.customer.CustomerDao;
import com.vckadam.oopdesign.NorthWind.dao.customer.CustomerDaoImpl;
import com.vckadam.oopdesign.NorthWind.model.Customer;
import com.vckadam.oopdesign.NorthWind.model.Order;

public class OrderDaoImpl implements OrderDao{

	private List<Order> orderList;
	private Map<Integer,List<Order>> ordersInyear;
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\NorthWind\\dao\\order\\ordertext";
	
	public OrderDaoImpl() throws IOException, NumberFormatException, ParseException {
		this.orderList = new ArrayList<Order>();
		this.ordersInyear = new HashMap<Integer,List<Order>>();
		loadList();
		populateMap();
	}
	@Override
	public List<String> companyPacedOrderIn(int year) throws IOException {
		List<String> ret = new ArrayList<String>();
		List<Order> orders = ordersInyear.get(year);
		//System.out.println(orders.toString());
		if(orders == null) return ret;
		CustomerDao customerDao = new CustomerDaoImpl();
		Map<String,Customer> customerMap = customerDao.getCustomerMap();
		//System.out.println(customerMap.toString());
		for(Order order : orders) {
			String key = order.getCustomerId();
			if(customerMap.containsKey(key)) {
				ret.add(customerMap.get(key).getCompanyName());
			}
		}
		return ret;
	}
	public void populateMap() {
		Calendar ca = Calendar.getInstance();
		for(Order order : orderList) {
			ca.setTime(order.getOrderDate());
			int key = ca.get(Calendar.YEAR);
			//System.out.println(key);
			if(!ordersInyear.containsKey(key)) {
				ordersInyear.put(key, new ArrayList<Order>());
			}
			ordersInyear.get(key).add(order);
		}
	}
	public void loadList() throws IOException, NumberFormatException, ParseException{
		List<String> lines = new ArrayList<String>();
	    BufferedReader reader = null;
	    SimpleDateFormat parser=new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
	    try {
	        reader = new BufferedReader(new FileReader(FILENAME));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            lines.add(line);
	        }
	    } finally {
	        reader.close();
	    }
	    for(String line : lines) {
	    	String[] strA = line.split(", ");
	    	//System.out.println(Arrays.toString(strA));
	    	Order order = new Order(
	    			Integer.valueOf(strA[0]),
	    			strA[1].substring(1,strA[1].length()-1),
	    			Integer.valueOf(strA[2]),
	    			parser.parse(strA[3].substring(1,strA[3].length()-1)),
	    			parser.parse(strA[4].substring(1,strA[4].length()-1)),
	    			parser.parse(strA[5].substring(1,strA[5].length()-1)),
	    			Integer.valueOf(strA[6]),
	    			Double.valueOf(strA[7].substring(1,strA[7].length()-1)),
	    			strA[8].substring(1,strA[8].length()-1),
	    			strA[9].substring(1,strA[9].length()-1),
	    			strA[10].substring(1,strA[10].length()-1),
	    			strA[11].equals("NULL")?null:strA[11].substring(1,strA[11].length()-1),
	    			strA[12].substring(1,strA[12].length()-1),
	    			strA[13].substring(1,strA[13].length()-1)
	    			);
	    	this.orderList.add(order);
	    }
	    /*for(Order or: orderList) {
	    	System.out.println(or.toString());
	    }*/
	}

}
