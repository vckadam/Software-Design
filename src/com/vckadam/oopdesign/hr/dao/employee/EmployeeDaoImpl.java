package com.vckadam.oopdesign.hr.dao.employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.hr.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	List<Employee> employeeList;
	Map<Integer, List<Employee>> employeesByDepartment;
	Map<String, List<Employee>> employeeOrder;
	Map<Integer, Integer> noOfEmpInDept;
	Map<Integer, Employee> employeeByEmpId;
	List<Employee> employeeByAge;
	List<Employee> empOrdBySal;
	Map<String,Integer> ordIndMap;
	
	private static final String FILENAME = "C:\\Users\\kadam\\workspace\\SoftwareDesign\\src\\com\\vckadam\\oopdesign\\hr\\dao\\employee\\employeefile";
	
	public EmployeeDaoImpl() throws NumberFormatException, IOException, ParseException {
		this.employeeList = new ArrayList<Employee>();
		this.employeesByDepartment = new HashMap<Integer,List<Employee>>();
		this.employeeOrder = new HashMap<String,List<Employee>>();
		this.noOfEmpInDept = new HashMap<Integer,Integer>();
		this.employeeByEmpId = new HashMap<Integer,Employee>();
		loadList();
		loadMap();
		employeeByAgeSetup();
		this.empOrdBySal = new ArrayList<Employee>(this.employeeList);
		this.ordIndMap = new HashMap<String,Integer>();
		loadOrdIndMap();
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
	    	this.employeeByEmpId.put(employee.getEmpId(),employee);
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

	@Override
	public Map<String, Double> avgSalaryByJob() {
		Map<String,Double> avgSalaryMap = new HashMap<String,Double>();
		Map<String,double[]> countMap = new HashMap<String,double[]>();
		for(Employee employee : this.employeeList) {
			if(!countMap.containsKey(employee.getJobId())) countMap.put(employee.getJobId(), new double[2]);
			double[] currCount = countMap.get(employee.getJobId());
			currCount[0] += employee.getSalary();
			currCount[1]++;
		}
		for(String key : countMap.keySet()) {
			double[] currCount = countMap.get(key);
			avgSalaryMap.put(key, currCount[0]/currCount[1]);
		}
		return avgSalaryMap;
	}

	@Override
	public Employee getEmployeeById(int empId) {
		if(this.employeeByEmpId.containsKey(empId)) {
			return this.employeeByEmpId.get(empId);
		}
		return null;
	}
	
	public void employeeByAgeSetup() {
		this.employeeByAge = new ArrayList<Employee>(this.employeeList);
		Comparator<Employee> comp = new Comparator<Employee>() {
			public int compare(Employee e1, Employee e2) {
				return e1.getHireDate().getDate() - e2.getHireDate().getDate();
			}
		};
		Collections.sort(this.employeeByAge, comp);
	}
	
	public List<Employee> getEmpeWithExp(int year) {
		int left = 0, right = this.employeeByAge.size()-1;
		Date currDate = new Date();
		if(currDate.getDate() - this.employeeByAge.get(left).getHireDate().getDate() > year) 
			return this.employeeByAge;
		
		if(currDate.getDate() - this.employeeByAge.get(right).getHireDate().getDate() < year) 
			return null;
		
		while(left < right) {
			int mid = left + (right - left) / 2;
			Date empHireDate = this.employeeByAge.get(mid).getHireDate();
			if(currDate.getDate() - empHireDate.getDate() >= year) right = mid;
			else left = mid + 1;
		
		}
		
		return this.employeeByAge.subList(left, this.employeeByAge.size());
		
	}
	
	private void loadOrdIndMap() {
		Comparator<Employee> comp = new Comparator<Employee>() {

			@Override
			public int compare(Employee o1, Employee o2) {
				if(o1.getSalary() <= o2.getSalary()) return -1;
				else return 1;
			}
			
		};
		Collections.sort(this.empOrdBySal, comp);
		int ind = 0;
		for(Employee emp : this.empOrdBySal) {
			if(!this.ordIndMap.containsKey(emp.getLastName())) {
				this.ordIndMap.put(emp.getLastName(), ind++);
			}
		}
	}

	@Override
	public List<Employee> getEmpMoreSal(String lastName) {
		if(this.ordIndMap.containsKey(lastName)) {
			int ind = this.ordIndMap.get(lastName);
			DecimalFormat format = new DecimalFormat(".##");
			double empSal = Double.valueOf(format.format(this.empOrdBySal.get(ind).getSalary()));
			for(; ind < this.empOrdBySal.size(); ind++) 
				if(empSal != Double.valueOf(new Double(format.format(this.empOrdBySal.get(ind).getSalary()))))
					break;
			return this.empOrdBySal.subList(ind, this.empOrdBySal.size());  
		}
		else 
			return null;
	}
	
}
