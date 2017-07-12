package com.vckadam.oopdesign.NorthWind.dao.product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.NorthWind.model.Product;

public class ProductDaoImpl implements ProductDao {

	private List<Product> productList;
	private Map<Integer,List<Product>> productCatMap;
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\NorthWind\\dao\\product\\producttext";
	
	public ProductDaoImpl() throws IOException {
		this.productList = new ArrayList<Product>();
		this.productCatMap = new HashMap<Integer,List<Product>>();
		loadList();
		loadMap();
	}
	
	@Override
	public List<Product> getProductInCategory(int categoryId) {
		List<Product> productList = new ArrayList<Product>();
		if(!this.productCatMap.containsKey(categoryId)) return productList;
		return this.productCatMap.get(categoryId);
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
	    	//System.out.println(Arrays.toString(strA));
	    	Product product = new Product(
	    			Integer.valueOf(strA[0]),
	    			strA[1].substring(1,strA[1].length()-1),
	    			Integer.valueOf(strA[2]),
	    			Integer.valueOf(strA[3]),
	    			strA[4].substring(1,strA[4].length()-1),
	    			Double.valueOf(strA[5].substring(1,strA[5].length()-1)),
	    			Integer.valueOf(strA[6]),
	    			Integer.valueOf(strA[7]),
	    			Integer.valueOf(strA[8]),
	    			!(strA[9].substring(2,strA[9].length()-1).equals("0")));
	    	this.productList.add(product);
	    }
	    /*for(Product or: this.productList) {
	    	System.out.println(or.toString());
	    }*/
	}
	
	private void loadMap() {
		for(Product product : this.productList) {
			int key = product.getCategoryId();
			if(!this.productCatMap.containsKey(key)) 
				this.productCatMap.put(key, new ArrayList<Product>());
			this.productCatMap.get(key).add(product);
		}
	}
	
	public List<Product> getAllProducts() {
		return this.productList;
	}
}
