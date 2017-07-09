package com.vckadam.oopdesign.NorthWind.dao.category;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.NorthWind.model.Category;

public class CategoryDaoImpl implements CategoryDao {

	Map<String,Category> categoryMap;
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\NorthWind\\dao\\category\\categorytext";
	
	public CategoryDaoImpl() throws IOException {
		this.categoryMap = new HashMap<String,Category>();
		loadMap();
	}
	
	@Override
	public Category getCategory(String categoryName) {
		if(!this.categoryMap.containsKey(categoryName)) return null;
		return this.categoryMap.get(categoryName);
	}
	
	private void loadMap() throws IOException {
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
	    	Category category = new Category(	
	    			Integer.valueOf(strA[0]),
	    			strA[1].substring(1,strA[1].length()-1),
	    			strA[2].substring(1,strA[2].length()-1));		
	    	this.categoryMap.put(category.getCategoryName(), category);
	    }
	    /*for(String key : this.categoryMap.keySet()) {
	    	System.out.println(this.categoryMap.get(key).toString());
	    }*/
	    /*for(Customer cust: this.customerList) {
	    	System.out.println(cust.toString());
	    }*/
	}

}
