package com.vckadam.oopdesign.NorthWind.dao.supplier;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.NorthWind.model.Supplier;

public class SupplierDaoImpl implements SupplierDao {
	
	private List<Supplier> supplierList;
	private Map<Integer,Supplier> supplierMap;
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\NorthWind\\dao\\supplier\\suppliertext";
	
	public SupplierDaoImpl() throws IOException {
		this.supplierList = new ArrayList<Supplier>();
		this.supplierMap = new HashMap<Integer, Supplier>();
		loadSuppliers();
	}
	
	private void loadSuppliers() throws IOException {
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
	    	Supplier supplier = new Supplier(
	    			Integer.valueOf(strA[0]),
	    			strA[1].substring(1,strA[1].length()-1),
	    			strA[2].substring(1,strA[2].length()-1),
	    			strA[3].substring(1,strA[3].length()-1),
	    			strA[4].substring(1,strA[4].length()-1),
	    			strA[5].substring(1,strA[5].length()-1),
	    			strA[6].equals("NULL")?null:strA[6].substring(1,strA[6].length()-1),
	    			strA[7].substring(1,strA[7].length()-1),
	    			strA[8].substring(1,strA[8].length()-1),
	    			strA[9].substring(1,strA[9].length()-1),
	    			strA[10].equals("NULL")?null:strA[10].substring(1,strA[10].length()-1)
	    			);
	    	this.supplierList.add(supplier);
	    	this.supplierMap.put(supplier.getSupplierId(), supplier);
	    }
	    /*for(Supplier sup: this.supplierList) {
    	System.out.println(sup.toString());
        }*/
	}

	@Override
	public List<Supplier> sortByCountryAndName() {
		List<Supplier> ret = new ArrayList<Supplier>(this.supplierList);
		Comparator<Supplier> countryComp = new Comparator<Supplier>() {
			public int compare(Supplier sup1, Supplier sup2) {
				return sup2.getCountry().compareTo(sup1.getCountry());
			}
		};
		Comparator<Supplier> nameComp = new Comparator<Supplier>() {
			public int compare(Supplier sup1, Supplier sup2) {
				if(sup1.getCountry().equals(sup2.getCountry())){
					return sup1.getCompanyName().compareTo(sup2.getCompanyName());
				}
				return 0;
			}
		};
		Collections.sort(ret, countryComp);
		Collections.sort(ret, nameComp);
		return ret;
	}

	@Override
	public List<Supplier> getSupplierList() {
		return this.supplierList;
	}

	@Override
	public Supplier getSupplierById(Integer supplierId) {
		if(!this.supplierMap.containsKey(supplierId)) return null;
		return this.supplierMap.get(supplierId);
	}
}
