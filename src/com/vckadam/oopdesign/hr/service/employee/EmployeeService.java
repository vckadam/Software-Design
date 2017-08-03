package com.vckadam.oopdesign.hr.service.employee;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.vckadam.oopdesign.hr.model.Department;
import com.vckadam.oopdesign.hr.model.Employee;
import com.vckadam.oopdesign.hr.model.EmployeeInCity;
import com.vckadam.oopdesign.hr.model.Job;
import com.vckadam.oopdesign.hr.model.Location;
import com.vckadam.oopdesign.hr.model.Manager;
import com.vckadam.oopdesign.hr.model.MinSalGradeEmp;

public interface EmployeeService {
	public List<Employee> getemployeeWorkIn(String city) throws IOException, NumberFormatException, ParseException;
	public List<Employee> getEmployeeJoinAfter(String name) throws NumberFormatException, IOException, ParseException;
	public int getEmployeesInDept(String name) throws IOException, NumberFormatException, ParseException ;
	public Map<String,Double> avgSalaryByJob() throws NumberFormatException, IOException, ParseException ;
	public List<Employee> managerWithExperience(int year);
	public List<Employee> deptInCountry(String country);
	public List<Manager> getAllMangers();
	public List<MinSalGradeEmp> getEmployeeMinSal(List<Employee> empList, List<Job> jobList);
	public List<EmployeeInCity> getEmployeeInCity(List<Employee> empList, List<Location> location, List<Department> depts);
	public List<EmployeeInCity> getEmployeeInCityOnData();
	public List<EmployeeInCity> moreThanAvgSalInCity();
	public List<EmployeeInCity> moreThanAvgSalInCity(List<EmployeeInCity> empList);
	public List<Employee> getMoreThanAvg(List<Employee> tempEmpList, double avgSal);
	
}
   