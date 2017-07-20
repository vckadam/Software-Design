package com.vckadam.oopdesign.hr.dao.employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.hr.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	List<Employee> employeeList;
	Map<Integer, List<Employee>> employeesByDepartment;
	Map<String, List<Employee>> employeeOrder;
	Map<Integer, Integer> noOfEmpInDept;
	
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\hr\\dao\\employee\\employeefile";
	
	public EmployeeDaoImpl() throws NumberFormatException, IOException, ParseException {
		this.employeeList = new ArrayList<Employee>();
		this.employeesByDepartment = new HashMap<Integer,List<Employee>>();
		this.employeeOrder = new HashMap<String,List<Employee>>();
		this.noOfEmpInDept = new HashMap<Integer,Integer>();
		loadList();
		loadMap();
	}
	
	public void loadList() throws IOException, NumberFormatException, ParseException {
		List<String> lines = new ArrayList<String>();
	    BufferedReader reader = null;
	    SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
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
	
	public void loadMap() {
		for(Employee employee : this.employeeList) {
			if(!this.employeesByDepartment.containsKey(employee.getDepartmentId())) {
				this.employeesByDepartment.put(employee.getDepartmentId(), new ArrayList<Employee>());
			}
			this.employeesByDepartment.get(employee.getDepartmentId()).add(employee);
			this.noOfEmpInDept.put(employee.getDepartmentId(), 
					this.noOfEmpInDept.getOrDefault(employee.getDepartmentId(),0)+1);
		}
		List<Employee> employeeList = new ArrayList<Employee>(this.employeeList);
		Comparator<Employee> comp = new Comparator<Employee>() {
			public int compare(Employee e1, Employee e2) {
				int ret = e1.getHireDate().compareTo(e2.getHireDate());
				return ret;
			}
		};
		Collections.sort(employeeList, comp);
		
		for(int i = 0; i < employeeList.size(); i++) {
			List<Employee> tempList = new ArrayList<Employee>();
			Employee currEmployee = employeeList.get(i);
			for(int j = i; j < employeeList.size(); j++) {
				Employee emp = employeeList.get(j);
				if(currEmployee.getHireDate().compareTo(emp.getHireDate()) >= 0) continue;
				tempList.add(emp);
			}
			this.employeeOrder.put(currEmployee.getLastName(), tempList);
		}
		
	}

	@Override
	public List<Employee> getEmployeeList() {
		return this.employeeList;
	}

	@Override
	public List<Employee> employeesByDepartment(Integer departmentId) {
		if(!this.employeesByDepartment.containsKey(departmentId)) return null;
		return this.employeesByDepartment.get(departmentId);
	}

	@Override
	public List<Employee> getEmployeeJoinAfter(String name) {
		if(!this.employeeOrder.containsKey(name)) return null;
		return this.employeeOrder.get(name);
	}

	@Override
	public int numberOfEmployeeInDept(int deptNo) {
		if(!this.noOfEmpInDept.containsKey(deptNo)) return 0;
		return this.noOfEmpInDept.get(deptNo);
	}
	
}
