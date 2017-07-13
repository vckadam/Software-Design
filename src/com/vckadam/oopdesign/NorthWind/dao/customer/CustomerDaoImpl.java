package com.vckadam.oopdesign.NorthWind.dao.customer;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.NorthWind.model.Customer;


public class CustomerDaoImpl implements CustomerDao {

	List<Customer> customerList;
	Map<String,Customer> customerMap;
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\NorthWind\\dao\\customer\\customertext";
	
	public CustomerDaoImpl() throws IOException {
		this.customerMap = new HashMap<String, Customer>();
		this.customerList = new ArrayList<Customer>();
		loadList();
		loadMap();
	}
	
	@Override
	public List<Customer> getAllCustomer() {
		return null;
	}
	

	private void loadList() throws IOException {
		List<String> lines = new ArrayList<String>();
	    BufferedReader reader = null;
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
	
	
	
	private void loadMap() {
		for(Customer cust : this.customerList) {
			if(!this.customerMap.containsKey(cust.getCustomerId())){
				this.customerMap.put(cust.getCustomerId(), cust);
			}
		}
		//System.out.println(this.customerList.toString());
	}
	@Override
	public Map<String, Customer> getCustomerMap() {
		return this.customerMap;
	}
}
