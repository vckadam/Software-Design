package com.vckadam.oopdesign.NorthWind.dao.customer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.NorthWind.model.Customer;
import com.vckadam.oopdesign.NorthWind.model.Order;

public class CustomerDaoImpl implements CustomerDao {

	List<Customer> customerList;
	Map<String,Customer> customerMap;
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\NorthWind\\dao\\customer\\customertext";
	
	public CustomerDaoImpl() throws IOException {
		customerList = new ArrayList<Customer>();
		loadList();
	}
	@Override
	public List<Customer> getAllCustomer() {
		return null;
	}

	private void loadList() throws IOException {
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
	    	Customer customer = new Customer(
	    			strA[0].substring(1,strA[0].length()-1),
	    			strA[1].substring(1,strA[1].length()-1),
	    			strA[2].substring(1,strA[2].length()-1),
	    			strA[3].substring(1,strA[3].length()-1),
	    			strA[4].substring(1,strA[4].length()-1),
	    			strA[5].substring(1,strA[5].length()-1),
	    			strA[6].equals("NULL")?null:strA[6].substring(1,strA[6].length()-1),
	    			strA[7].substring(1,strA[7].length()-1),
	    			strA[8].substring(1,strA[8].length()-1),
	    			strA[9].substring(1,strA[9].length()-1),
	    			strA[10].equals("NULL")?null:strA[10].substring(1,strA[10].length()-1));
	    	this.customerList.add(customer);
	    }
	    /*for(Customer cust: this.customerList) {
	    	System.out.println(cust.toString());
	    }*/
	}
	
	@Override
	public Map<String, Customer> getCustomerMap() {
		if(this.customerMap == null) loadMap();
		return this.customerMap;
	}
	
	private void loadMap() {
		this.customerMap = new HashMap<String, Customer>();
		for(Customer cust : this.customerList) {
			if(!this.customerMap.containsKey(cust.getCustomerId())){
				this.customerMap.put(cust.getCustomerId(), cust);
			}
		}
	}
}
