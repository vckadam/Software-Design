package com.vckadam.oopdesign.hr.dao.department;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.vckadam.oopdesign.hr.model.Department;

public class DepartmentDaoImpl implements DepartmentDao {

	List<Department> departmentList;
	
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\hr\\dao\\department\\departmentfile";
	
	public DepartmentDaoImpl() throws IOException {
		this.departmentList = new ArrayList<Department>();
		loadList();
	}
	
	public void loadList() throws IOException {
		List<String> lines = new ArrayList<String>();
	    BufferedReader reader = null;
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
	    	Department department = new Department(
	    			Integer.valueOf(strA[0].substring(1,strA[0].length()-1)),
	    			strA[1].substring(1,strA[1].length()-1),
	    			Integer.valueOf(strA[2].substring(1,strA[2].length()-1)),
	    			Integer.valueOf(strA[3].substring(1,strA[3].length()-1))
	    			);
	    	this.departmentList.add(department);
	    }
	}
	
	@Override
	public List<Department> getDepartmentList() {
		return this.departmentList;
	}

}
