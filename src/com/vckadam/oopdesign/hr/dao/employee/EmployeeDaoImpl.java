package com.vckadam.oopdesign.hr.dao.employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.vckadam.oopdesign.hr.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	List<Employee> employeeList;
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\hr\\dao\\employee\\employeefile";
	
	public EmployeeDaoImpl() throws NumberFormatException, IOException, ParseException {
		this.employeeList = new ArrayList<Employee>();
		loadList();
	}
	
	public void loadList() throws IOException, NumberFormatException, ParseException {
		List<String> lines = new ArrayList<String>();
	    BufferedReader reader = null;
	    SimpleDateFormat parser=new SimpleDateFormat("yyyy-mm-dd");
	    try {
	        reader = new BufferedReader(new FileReader(FILENAME));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            lines.add(line.substring(1,line.length()-2));
	        }
	    } finally {
	        reader.close();
	    }
	    for(String line : lines) {
	    	String[] strA = line.split(", ");
	    	Employee employee = new Employee(	
	    			Integer.valueOf(strA[0].substring(1,strA[0].length()-1)),
	    			strA[1].substring(1,strA[1].length()-1),
	    			strA[2].substring(1,strA[2].length()-1),
	    			strA[3].substring(1,strA[3].length()-1),
	    			strA[4].substring(1,strA[4].length()-1),
	    			parser.parse(strA[5].substring(1,strA[5].length()-1)),
	    			strA[6].substring(1,strA[6].length()-1),
	    			Double.valueOf(strA[7].substring(1,strA[7].length()-1)),
	    			Double.valueOf(strA[8].substring(1,strA[8].length()-1)),
	    			Integer.valueOf(strA[9].substring(1,strA[9].length()-1)),
	    			Integer.valueOf(strA[10].substring(1,strA[10].length()-1)));
	    	this.employeeList.add(employee);
	    }
	}

	@Override
	public List<Employee> getEmployeeList() {
		return this.employeeList;
	}
	
}
