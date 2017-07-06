package EmployeeEarning;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Operation {
	private Map<Integer,PriorityQueue<Employee>> mostPaidEmployeeMap;
	private Map<Integer,Department> deptMap;
	public Operation() {
		this.mostPaidEmployeeMap = new HashMap<Integer,PriorityQueue<Employee>>();
		this.deptMap = new HashMap<Integer,Department>();
	}
	public List<MaxPaidEmployee> getMaxPaidEmployee(List<Employee> emps, List<Department> depts) {
		List<MaxPaidEmployee> maxPaidEmpList = new ArrayList<MaxPaidEmployee>();
		if(emps == null || emps.size() == 0
				|| depts == null || depts.size() == 0) return maxPaidEmpList;
		Comparator<Employee> comp = new Comparator<Employee>() {
			public int compare(Employee emp1, Employee emp2) {
				return emp2.getSalary() - emp1.getSalary();
			}
		};
		for(Department dept : depts) {
			if(dept != null && !mostPaidEmployeeMap.containsKey(dept.getId())) {
				mostPaidEmployeeMap.put(dept.getId(), new PriorityQueue<Employee>(comp));
			}
			if(dept != null && !deptMap.containsKey(dept)) {
				deptMap.put(dept.getId(), dept);
			}
		}
		for(Employee emp : emps) {
			if(emp != null) {
				Integer key = emp.getDepartmentId();
				if(key != null && mostPaidEmployeeMap.containsKey(key)) {
					mostPaidEmployeeMap.get(key).add(emp);
				}
			}
		}
		
		for(Integer key : mostPaidEmployeeMap.keySet()) {
			PriorityQueue<Employee> val = mostPaidEmployeeMap.get(key);
			if(val != null && val.size() > 0) {
				Employee emp = val.peek();
				Department dep = deptMap.get(emp.getDepartmentId());
				MaxPaidEmployee maxPaidEmp = new MaxPaidEmployee(dep.getName(),emp.getName(),emp.getSalary());
				maxPaidEmpList.add(maxPaidEmp);
			}
		}
		return maxPaidEmpList;
	}
					
}
