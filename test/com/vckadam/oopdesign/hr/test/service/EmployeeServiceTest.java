package com.vckadam.oopdesign.hr.test.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.hr.model.Department;
import com.vckadam.oopdesign.hr.model.Employee;
import com.vckadam.oopdesign.hr.model.EmployeeInCity;
import com.vckadam.oopdesign.hr.model.Job;
import com.vckadam.oopdesign.hr.model.Location;
import com.vckadam.oopdesign.hr.model.Manager;
import com.vckadam.oopdesign.hr.model.MinSalGradeEmp;
import com.vckadam.oopdesign.hr.service.employee.EmployeeService;
import com.vckadam.oopdesign.hr.service.employee.EmployeeServiceImpl;

public class EmployeeServiceTest {
	
	private EmployeeService employeeService;
	
	@Before
	public void beforeMethod() throws NumberFormatException, IOException, ParseException {
		this.employeeService = new EmployeeServiceImpl();
	}
	
	@After
	public void afterMethod() {
		this.employeeService = null;
	}
	
	
	@Test
	public void testMoreThanAvgSalInCity_PositiveScenario() {
		int[] locsId = {1,2};
		int[][] empsIdWithSalLocId1 = {{1,100},{2,200},{3,300}};
		int[][] empsIdWithSalLocId2 = {{4,400},{5,500}};
		List<EmployeeInCity> empsInCity = prepareEmpInCity_testMoreThanAvgSalInCity(locsId, empsIdWithSalLocId1, empsIdWithSalLocId2);
		List<EmployeeInCity> actualEmpsMoreThanAvg = this.employeeService.moreThanAvgSalInCity(empsInCity);
		
		Map<Integer,List<Integer>> actualEmpsMoreThanAvgMap = prepareActualMap_testMoreThanAvgSalInCity(actualEmpsMoreThanAvg);
		
		int[][] expectedLocIdToEmp = {{1,3},{2,5}};
		Map<Integer,List<Integer>> expectedEmpsMoreThanAvgMap = prepareExpectedMap_testMoreThanAvgSalInCity(expectedLocIdToEmp);
		
		assertEquals(expectedEmpsMoreThanAvgMap.size(), actualEmpsMoreThanAvg.size());
		assertEquals(expectedEmpsMoreThanAvgMap, actualEmpsMoreThanAvgMap);
	}
	
	private Map<Integer,List<Integer>> prepareExpectedMap_testMoreThanAvgSalInCity(int[][] locIdToEmpIds) {
		Map<Integer,List<Integer>> locIdToEmpIdMap = new HashMap<>();
		for(int[] locIdToEmpId : locIdToEmpIds) {
			if(!locIdToEmpIdMap.containsKey(locIdToEmpId[0])) locIdToEmpIdMap.put(locIdToEmpId[0], new ArrayList<Integer>());
			locIdToEmpIdMap.get(locIdToEmpId[0]).add(locIdToEmpId[1]);
		}
		return locIdToEmpIdMap;
	}
	
	private Map<Integer,List<Integer>> prepareActualMap_testMoreThanAvgSalInCity(List<EmployeeInCity> empsInCity) {
		Map<Integer,List<Integer>> locIdToEmpIdMap = new HashMap<>();
		for(EmployeeInCity empInCity : empsInCity) {
			int locId = empInCity.getLocation().getLocationId();
			if(!locIdToEmpIdMap.containsKey(locId)) locIdToEmpIdMap.put(locId, new ArrayList<Integer>());
			List<Employee> emps = empInCity.getEmployeeList();
			for(Employee emp : emps) {
				locIdToEmpIdMap.get(locId).add(emp.getEmpId());
			}
		}
		return locIdToEmpIdMap;
	}
	public List<EmployeeInCity> prepareEmpInCity_testMoreThanAvgSalInCity(int[] locsId, int[][] emps1, int[][] emps2) {
		List<EmployeeInCity> empsInCity = new ArrayList<>();
		for(int locId : locsId) {
			Location loc = new Location(locId, null,null,null,null,null);
			empsInCity.add(new EmployeeInCity(loc,new ArrayList<Employee>()));
		}
		
		for(int[] emp : emps1) {
			Employee employee = new Employee(emp[0], null,null,null,null,null, 
					null, emp[1], 0.0,0,0);
			empsInCity.get(0).getEmployeeList().add(employee);
		}
		
		for(int[] emp : emps2) {
			Employee employee = new Employee(emp[0], null,null,null,null,null, 
					null, emp[1], 0.0,0,0);
			empsInCity.get(1).getEmployeeList().add(employee);
		}
		
		return empsInCity;
	}
	
	
	@Test
	public void testGetMoreThanAvg_PositiveScenario() {
		int[][] empIdWithSal = {{1,100},{2,200},{3,300},{4,400},{5,500}};
		List<Employee> emps = prepareEmpList_testGetMoreThanAvg(empIdWithSal);
		List<Employee> actualEmpList = this.employeeService.getMoreThanAvg(emps, 250);
		Set<Integer> actualEmpSet = prepareActualSet_testGetMoreThanAvg(actualEmpList);
		
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(3,4,5));
		Set<Integer> expectedEmpSet = new HashSet<Integer>(expected);
		
		assertEquals(expectedEmpSet.size(), actualEmpList.size());
		assertEquals(expectedEmpSet, actualEmpSet);
		
	}
	
	private List<Employee> prepareEmpList_testGetMoreThanAvg(int[][] empsIdWithSal) {
		List<Employee> emps = new ArrayList<Employee>();
		for(int[] emp : empsIdWithSal) {
			emps.add(new Employee(emp[0],  null, null, null,null,null, 
			null, emp[1], 0.0, 0, 0));
		}
		return emps;
	}
	
	private Set<Integer> prepareActualSet_testGetMoreThanAvg(List<Employee> empsList) {
		Set<Integer> empSet = new HashSet<>();
		for(Employee emp : empsList) {
			empSet.add(emp.getEmpId());
		}
		return empSet;
	}
	
	
	@Test
	public void testGetEmployeeInCity_PositiveScenario() {
		int[] locIds = {1,2,3};
		List<Location> locList = prepareLocList_TestGetEmployeeInCity(locIds);
		int[][] deptToLocIds = {{1,1},{2,1},{3,2},{4,3}};
		List<Department> deptList = prepareDeptList_TestGetEmployeeInCity(deptToLocIds);
		int[][] empToDeptIds = {{1,1},{2,2},{3,3},{4,4}};
		List<Employee> empList = prepareEmpList_TestGetEmployeeInCity(empToDeptIds);
		List<EmployeeInCity> empsInCity = this.employeeService.getEmployeeInCity(empList, locList, deptList);
		Map<Integer,List<Integer>> actualLocToEmpMap = prepareActualMap_TestGetEmployeeInCity(empsInCity);
		
		int[][] expectedLocToEmp = {{1,1},{1,2},{2,3},{3,4}};
		Map<Integer,List<Integer>> expectedLocToEmpMap = prepareExpectedMap_TestGetEmployeeInCity(expectedLocToEmp);
		
		assertEquals(expectedLocToEmpMap, actualLocToEmpMap);
	}
	
	@Test
	public void testGetEmployeeInCity_LocWithoutEmps() {
		int[] locIds = {1,2,3,5 }; //5 location is without employees
		List<Location> locList = prepareLocList_TestGetEmployeeInCity(locIds);
		int[][] deptToLocIds = {{1,1},{2,1},{3,2},{4,3}};
		List<Department> deptList = prepareDeptList_TestGetEmployeeInCity(deptToLocIds);
		int[][] empToDeptIds = {{1,1},{2,2},{3,3},{4,4}};
		List<Employee> empList = prepareEmpList_TestGetEmployeeInCity(empToDeptIds);
		List<EmployeeInCity> empsInCity = this.employeeService.getEmployeeInCity(empList, locList, deptList);
		Map<Integer,List<Integer>> actualLocToEmpMap = prepareActualMap_TestGetEmployeeInCity(empsInCity);
		
		int[][] expectedLocToEmp = {{1,1},{1,2},{2,3},{3,4}};
		Map<Integer,List<Integer>> expectedLocToEmpMap = prepareExpectedMap_TestGetEmployeeInCity(expectedLocToEmp);
		
		assertEquals(expectedLocToEmpMap, actualLocToEmpMap);
	}
	
	@Test
	public void testGetEmployeeInCity_DeptWithoutValidLoc() {
		int[] locIds = {1,2,3,};
		List<Location> locList = prepareLocList_TestGetEmployeeInCity(locIds);
		int[][] deptToLocIds = {{1,1},{2,1},{3,2},{4,3},{5,5}}; //5 locId is invalid.
		List<Department> deptList = prepareDeptList_TestGetEmployeeInCity(deptToLocIds);
		int[][] empToDeptIds = {{1,1},{2,2},{3,3},{4,4}};
		List<Employee> empList = prepareEmpList_TestGetEmployeeInCity(empToDeptIds);
		List<EmployeeInCity> empsInCity = this.employeeService.getEmployeeInCity(empList, locList, deptList);
		Map<Integer,List<Integer>> actualLocToEmpMap = prepareActualMap_TestGetEmployeeInCity(empsInCity);
		
		int[][] expectedLocToEmp = {{1,1},{1,2},{2,3},{3,4}};
		Map<Integer,List<Integer>> expectedLocToEmpMap = prepareExpectedMap_TestGetEmployeeInCity(expectedLocToEmp);
		
		assertEquals(expectedLocToEmpMap, actualLocToEmpMap);
	}
	
	@Test
	public void testGetEmployeeInCity_EmpyWithoutValidDept() {
		int[] locIds = {1,2,3};
		List<Location> locList = prepareLocList_TestGetEmployeeInCity(locIds);
		int[][] deptToLocIds = {{1,1},{2,1},{3,2},{4,3}};
		List<Department> deptList = prepareDeptList_TestGetEmployeeInCity(deptToLocIds);
		int[][] empToDeptIds = {{1,1},{2,2},{3,3},{4,4},{5,5}}; //5 deptId is invalid.
		List<Employee> empList = prepareEmpList_TestGetEmployeeInCity(empToDeptIds);
		List<EmployeeInCity> empsInCity = this.employeeService.getEmployeeInCity(empList, locList, deptList);
		Map<Integer,List<Integer>> actualLocToEmpMap = prepareActualMap_TestGetEmployeeInCity(empsInCity);
		
		int[][] expectedLocToEmp = {{1,1},{1,2},{2,3},{3,4}};
		Map<Integer,List<Integer>> expectedLocToEmpMap = prepareExpectedMap_TestGetEmployeeInCity(expectedLocToEmp);
		
		assertEquals(expectedLocToEmpMap, actualLocToEmpMap);
	}
	
	private List<Location> prepareLocList_TestGetEmployeeInCity(int[] locIds) {
		List<Location> locList = new ArrayList<Location>();
		for(int locId : locIds) {
			Location loc = new Location(locId, null, null, null, null, null);
			locList.add(loc);
		}
		return locList;
	}
	
	private List<Department> prepareDeptList_TestGetEmployeeInCity(int[][] deptIds) {
		List<Department> deptList = new ArrayList<Department>();
		for(int[] deptId : deptIds) {
			Department dept = new Department(deptId[0], null, 0, deptId[1]);
			deptList.add(dept);
		}
		return deptList;
	}
	
	private List<Employee> prepareEmpList_TestGetEmployeeInCity(int[][] empIds) {
		List<Employee> empList = new ArrayList<Employee>();
		for(int[] empId : empIds) {
			Employee emp = new Employee(empId[0],  null, null,null , null, null, 
					null, 0.0, 0.0, 0, empId[1]);
			empList.add(emp);
		}
		return empList;
	}
	
	private Map<Integer,List<Integer>> prepareActualMap_TestGetEmployeeInCity(List<EmployeeInCity> empsInCity){
		Map<Integer,List<Integer>> locToEmpMap = new HashMap<>();
		for(EmployeeInCity empInCity : empsInCity) {
			int locId = empInCity.getLocation().getLocationId();
			List<Employee> emps = empInCity.getEmployeeList();
			List<Integer> empIds = new ArrayList<Integer>();
			for(Employee emp : emps) {
				empIds.add(emp.getEmpId());
			}
			locToEmpMap.put(locId, empIds);
		}
		return locToEmpMap;
	}
	
	private Map<Integer, List<Integer>> prepareExpectedMap_TestGetEmployeeInCity(int[][] locIdToEmpIds) {
		Map<Integer,List<Integer>> locIdToEmpIdsMap = new HashMap<>();
		for(int[] locIdToEmpId : locIdToEmpIds) {
			if(!locIdToEmpIdsMap.containsKey(locIdToEmpId[0])) locIdToEmpIdsMap.put(locIdToEmpId[0], new ArrayList<Integer>());
			locIdToEmpIdsMap.get(locIdToEmpId[0]).add(locIdToEmpId[1]);
		}
		return locIdToEmpIdsMap;
	}
	
	/** If one or more employees work in given city.*/
	@Test
	public void getEmployeeWorkInTest1() throws NumberFormatException, IOException, ParseException {
		List<Employee> employees = this.employeeService.getemployeeWorkIn("London");
		Set<Integer> actualSet = new HashSet<Integer>();
		for(Employee employee : employees) {
			actualSet.add(employee.getEmpId());
		}
		
		Set<Integer> expectedSet = new HashSet<Integer>();
		expectedSet.add(203);
		
		assertEquals(expectedSet.size(), employees.size());
		assertEquals(expectedSet, actualSet);
	}
	
	/** No employees work in given city.*/
	@Test
	public void getEmployeeWorkInTest2() throws NumberFormatException, IOException, ParseException {
		assertEquals(null, this.employeeService.getemployeeWorkIn("Bombay"));
	}
	
	@Test
	public void getEmployeesInDeptTest1() throws NumberFormatException, IOException, ParseException {
		assertEquals(3, this.employeeService.getEmployeesInDept("Executive"));
	}
	
	@Test
	public void avgSalaryByJobTest() throws NumberFormatException, IOException, ParseException {
		Map<String, Double> actualMap = this.employeeService.avgSalaryByJob();
		Map<String, Double> expectedMap = new HashMap<String, Double>();
		expectedMap.put("Accountant",(double) 7920); 
		expectedMap.put("Marketing Manager",(double) 13000); 
		expectedMap.put("Purchasing Clerk",(double) 2780);
		expectedMap.put("Accounting Manager",(double) 12000); 
		expectedMap.put("Marketing Representative",(double) 6000); 
		expectedMap.put("Purchasing Manager",(double) 11000);
		expectedMap.put("Administration Assistant",(double) 4400); 
		expectedMap.put("President",(double) 24000); 
		expectedMap.put("Sales Manager",(double) 12200);
		expectedMap.put("Administration Vice President",(double) 17000 ); 
		expectedMap.put("Programmer",(double) 5760); 
		expectedMap.put("Sales Representative",(double) 8350);
		expectedMap.put("Finance Manager",(double) 12000); 
		expectedMap.put("Public Accountant",(double) 8300); 
		expectedMap.put("Shipping Clerk",(double) 3215);
		expectedMap.put("Human Resources Representative",(double) 6500); 
		expectedMap.put("Public Relations Representative",(double) 10000); 
		expectedMap.put("Stock Clerk",(double) 2785);
		expectedMap.put("Stock Manager",(double) 7280);
		assertEquals(expectedMap, actualMap);
		
	}
	
	
	
	@Test
	public void deptInCountryTest1() {
		List<Employee> emps = this.employeeService.deptInCountry("US");
		Set<String> actualSet = prepareSet_deptInCountryTest(emps);
		
		String[] firstNames = {"Neena",
				"Lex",
				"Bruce",
				"David",
				"Valli",
				"Diana",
				"Nancy",
				"Daniel",
				"John",
				"Ismael",
				"Jose Manuel",
				"Luis",
				"Den",
				"Alexander",
				"Shelli",
				"Sigal",
				"Guy",
				"Karen",
				"Matthew",
				"Adam",
				"Payam",
				"Shanta",
				"Kevin",
				"Julia",
				"Irene",
				"James",
				"Steven",
				"Laura",
				"Mozhe",
				"James",
				"TJ",
				"Jason",
				"Michael",
				"Ki",
				"Hazel",
				"Renske",
				"Stephen",
				"Joshua",
				"Trenna",
				"Curtis",
				"Randall",
				"Peter",
				"John",
				"Karen",
				"Winston",
				"Jean",
				"Martha",
				"Girard",
				"Nandita",
				"Alexis",
				"Julia",
				"Anthony",
				"Kelly",
				"Jennifer",
				"Timothy",
				"Randall",
				"Sarah",
				"Britney",
				"Samuel",
				"Vance",
				"Alana",
				"Kevin",
				"Donald",
				"Douglas",
				"Jennifer",
				"Michael",
				"Shelley",
				"William" };

		
		Set<String> expectedSet = prepareExpectedSet_deptInCountryTest(firstNames);
		
		assertEquals(firstNames.length, emps.size());
		assertEquals(expectedSet, actualSet);
	}
	
	
	
	
	private Set<String> prepareSet_deptInCountryTest(List<Employee> emps) {
		Set<String> set = new HashSet<String>();
		for(Employee emp : emps) 
			set.add(emp.getFirstName());
		return set;
	}
	
	private Set<String> prepareExpectedSet_deptInCountryTest(String[] strA) {
		Set<String> set = new HashSet<String>();
		for(String str : strA) set.add(str);
		return set;
	}
	
	@Test
	public void testGetAllMangers_OneEmployeeInTeam() {
		List<Manager> managerList = this.employeeService.getAllMangers();
		Set<Integer> actualTeam = prepareSet_TestGetAllMangers(managerList, 102);
		
		Set<Integer> expectedTeam = new HashSet<Integer>(Arrays.asList(103));
		assertEquals(expectedTeam,actualTeam);
	}
	
	@Test
	public void testGetAllMangers_multipleEmployeesInTeam() {
		List<Manager> managerList = this.employeeService.getAllMangers();
		Set<Integer> actualTeam = prepareSet_TestGetAllMangers(managerList, 101);
		
		Set<Integer> expectedTeam = new HashSet<Integer>(Arrays.asList(200, 203, 204, 205, 108));
		assertEquals(expectedTeam,actualTeam);
	}
	
	private Set<Integer> prepareSet_TestGetAllMangers(List<Manager> list, int managerId) {
		Set<Integer> ret = new HashSet<Integer>();
		for(Manager manager : list) {
			if(manager.getManager().getEmpId() ==  managerId) {
				List<Employee> team = manager.getTeam();
				for(Employee emp : team) ret.add(emp.getEmpId());
			}
		}
		return ret;	
	}
	
	@Test
	public void testGetEmployeeMinSal_PositiveScenario() {
		int[] employeeIds = {1, 2, 3, 4 , 5};
		double[] salary = {1000, 2000, 1000, 4000, 5000};
		String[] empJobIds = {"JobId1", "JobId2","JobId1",  "JobId2", "JobId2"};
		List<Employee> employeeList = prepareEmployeeList_testGetEmployeeMinSal(employeeIds,salary,empJobIds);
		
		String[] jobIds = {"JobId1", "JobId2"};
		double[] minSal = {1000, 2000};
		List<Job> jobList = prepareJobList_testGetEmployeeMinSal(jobIds,minSal);
		
		List<MinSalGradeEmp> actualList = this.employeeService.getEmployeeMinSal(employeeList, jobList);
		
		Map<String,List<Integer>> actuEmpIdsInJob = prepareActulMap_testGetEmployeeMinSal(actualList);
		//List<Integer> actualEmpsList = prepareEmpList_testGetEmployeeMinSal(actualList, "JobId1");
		
		//Set<Integer> expectedIdsInJob = new HashSet<Integer>(Arrays.asList(1,3));
		Map<String,List<Integer>> expectedIdsInJob = new HashMap<>();
		expectedIdsInJob.put("JobId1", new ArrayList<Integer>(Arrays.asList(1,3)));
		expectedIdsInJob.put("JobId2", new ArrayList<Integer>(Arrays.asList(2)));
		
		
		//assertEquals(2, actualEmpsList.size());
		assertEquals(expectedIdsInJob, actuEmpIdsInJob);
	}
	
	@Test
	public void testGetEmployeeMinSal_NoMinSalEmp() {
		int[] employeeIds = {1, 2, 3, 4 , 5};
		double[] salary = {1000, 2000, 1000, 4000, 5000};
		String[] empJobIds = {"JobId1", "JobId2","JobId1",  "JobId2", "JobId2"};
		List<Employee> employeeList = prepareEmployeeList_testGetEmployeeMinSal(employeeIds,salary,empJobIds);
		
		String[] jobIds = {"JobId1", "JobId2"};
		double[] minSal = {100, 200};
		List<Job> jobList = prepareJobList_testGetEmployeeMinSal(jobIds,minSal);
		
		List<MinSalGradeEmp> actualList = this.employeeService.getEmployeeMinSal(employeeList, jobList);
		
		Map<String,List<Integer>> actuEmpIdsInJob = prepareActulMap_testGetEmployeeMinSal(actualList);
		
		Map<String,List<Integer>> expectedIdsInJob = new HashMap<>();
		
		
		assertEquals(expectedIdsInJob, actuEmpIdsInJob);
	}
	
	@Test
	public void testGetEmployeeMinSal_NoEmpForJobId() {
		int[] employeeIds = {1, 2, 3, 4 , 5};
		double[] salary = {1000, 2000, 1000, 4000, 5000};
		String[] empJobIds = {"JobId1", "JobId2","JobId1",  "JobId2", "JobId2"};
		List<Employee> employeeList = prepareEmployeeList_testGetEmployeeMinSal(employeeIds,salary,empJobIds);
		
		String[] jobIds = {"JobId1", "JobId2", "JObId3"};
		double[] minSal = {1000, 200, 300};
		List<Job> jobList = prepareJobList_testGetEmployeeMinSal(jobIds,minSal);
		
		List<MinSalGradeEmp> actualList = this.employeeService.getEmployeeMinSal(employeeList, jobList);
		
		Map<String,List<Integer>> actuEmpIdsInJob = prepareActulMap_testGetEmployeeMinSal(actualList);
		
		Map<String,List<Integer>> expectedIdsInJob = new HashMap<>();
		expectedIdsInJob.put("JobId1", new ArrayList<Integer>(Arrays.asList(1,3)));
		
		
		assertEquals(expectedIdsInJob, actuEmpIdsInJob);
	}
	
	@Test
	public void testGetEmployeeMinSal_EmpWithNotValidJobId() {
		int[] employeeIds = {1, 2, 3, 4 , 5};
		double[] salary = {1000, 2000, 1000, 4000, 5000};
		String[] empJobIds = {"JobId1", "JobId2","JobId1",  "JobId2", "JobId4"};
		List<Employee> employeeList = prepareEmployeeList_testGetEmployeeMinSal(employeeIds,salary,empJobIds);
		
		String[] jobIds = {"JobId1", "JobId2", "JObId3"};
		double[] minSal = {1000, 200, 300};
		List<Job> jobList = prepareJobList_testGetEmployeeMinSal(jobIds,minSal);
		
		List<MinSalGradeEmp> actualList = this.employeeService.getEmployeeMinSal(employeeList, jobList);
		
		Map<String,List<Integer>> actuEmpIdsInJob = prepareActulMap_testGetEmployeeMinSal(actualList);
		
		Map<String,List<Integer>> expectedIdsInJob = new HashMap<>();
		expectedIdsInJob.put("JobId1", new ArrayList<Integer>(Arrays.asList(1,3)));
		
		
		assertEquals(expectedIdsInJob, actuEmpIdsInJob);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetEmployeeMinSal_NullEmpList() {
		int[] employeeIds = {1, 2, 3, 4 , 5};
		double[] salary = {1000, 2000, 1000, 4000, 5000};
		String[] empJobIds = {"JobId1", "JobId2","JobId1",  "JobId2", "JobId4"};
		List<Employee> employeeList = prepareEmployeeList_testGetEmployeeMinSal(employeeIds,salary,empJobIds);
		
		List<MinSalGradeEmp> actualList = this.employeeService.getEmployeeMinSal(employeeList, null);
		
		Map<String,List<Integer>> actuEmpIdsInJob = prepareActulMap_testGetEmployeeMinSal(actualList);
		
		Map<String,List<Integer>> expectedIdsInJob = new HashMap<>();
		expectedIdsInJob.put("JobId1", new ArrayList<Integer>(Arrays.asList(1,3)));
		
		
		assertEquals(expectedIdsInJob, actuEmpIdsInJob);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetEmployeeMinSal_NullEmpAndJobList() {
		
		List<MinSalGradeEmp> actualList = this.employeeService.getEmployeeMinSal(null, null);
		
		Map<String,List<Integer>> actuEmpIdsInJob = prepareActulMap_testGetEmployeeMinSal(actualList);
		
		Map<String,List<Integer>> expectedIdsInJob = new HashMap<>();
		expectedIdsInJob.put("JobId1", new ArrayList<Integer>(Arrays.asList(1,3)));
		
		
		assertEquals(expectedIdsInJob, actuEmpIdsInJob);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetEmployeeMinSal_NullJobList() {
		
		String[] jobIds = {"JobId1", "JobId2", "JObId3"};
		double[] minSal = {1000, 200, 300};
		List<Job> jobList = prepareJobList_testGetEmployeeMinSal(jobIds,minSal);
		
		List<MinSalGradeEmp> actualList = this.employeeService.getEmployeeMinSal(null, jobList);
		
		Map<String,List<Integer>> actuEmpIdsInJob = prepareActulMap_testGetEmployeeMinSal(actualList);
		
		Map<String,List<Integer>> expectedIdsInJob = new HashMap<>();
		expectedIdsInJob.put("JobId1", new ArrayList<Integer>(Arrays.asList(1,3)));
		
		
		assertEquals(expectedIdsInJob, actuEmpIdsInJob);
	}
	
	@Test
	public void testGetEmployeeMinSal_NullEmpInList() {
		int[] employeeIds = {1, 2, 3, 4 , 5};
		double[] salary = {1000, 2000, 1000, 4000, 5000};
		String[] empJobIds = {"JobId1", "JobId2","JobId1",  "JobId2", "JobId2"};
		List<Employee> employeeList = prepareEmployeeList_testGetEmployeeMinSal(employeeIds,salary,empJobIds);
		employeeList.add(null);
		
		String[] jobIds = {"JobId1", "JobId2"};
		double[] minSal = {1000, 200};
		List<Job> jobList = prepareJobList_testGetEmployeeMinSal(jobIds,minSal);
		
		List<MinSalGradeEmp> actualList = this.employeeService.getEmployeeMinSal(employeeList, jobList);
		
		Map<String,List<Integer>> actuEmpIdsInJob = prepareActulMap_testGetEmployeeMinSal(actualList);
		
		Map<String,List<Integer>> expectedIdsInJob = new HashMap<>();
		expectedIdsInJob.put("JobId1", new ArrayList<Integer>(Arrays.asList(1,3)));
		
		
		
		assertEquals(expectedIdsInJob, actuEmpIdsInJob);
	}
	
	@Test
	public void testGetEmployeeMinSal_NullJobInList() {
		int[] employeeIds = {1, 2, 3, 4 , 5};
		double[] salary = {1000, 2000, 1000, 4000, 5000};
		String[] empJobIds = {"JobId1", "JobId2","JobId1",  "JobId2", "JobId2"};
		List<Employee> employeeList = prepareEmployeeList_testGetEmployeeMinSal(employeeIds,salary,empJobIds);
		employeeList.add(null);
		
		String[] jobIds = {"JobId1", "JobId2"};
		double[] minSal = {1000, 200};
		List<Job> jobList = prepareJobList_testGetEmployeeMinSal(jobIds,minSal);
		jobList.add(null);
		
		List<MinSalGradeEmp> actualList = this.employeeService.getEmployeeMinSal(employeeList, jobList);
		
		Map<String,List<Integer>> actuEmpIdsInJob = prepareActulMap_testGetEmployeeMinSal(actualList);
		
		Map<String,List<Integer>> expectedIdsInJob = new HashMap<>();
		expectedIdsInJob.put("JobId1", new ArrayList<Integer>(Arrays.asList(1,3)));
		
		
		
		assertEquals(expectedIdsInJob, actuEmpIdsInJob);
	}
	
	private List<Employee> prepareEmployeeList_testGetEmployeeMinSal(int[] employeeIds, double[] salary, String[] empJobIds) {
		List<Employee> empList = new ArrayList<Employee>();
		for(int i = 0; i < employeeIds.length; i++) {
			empList.add(new Employee(employeeIds[i],  null, null, null, null, null,	empJobIds[i], salary[i], 0.0, 0, 0));
		}
		return empList;
	}
	
	private List<Job> prepareJobList_testGetEmployeeMinSal(String[] jobIds,double[] minSal){
		List<Job> jobList = new ArrayList<Job>();
		for(int i = 0; i < jobIds.length; i++) {
			jobList.add(new Job(jobIds[i], null, 0.0, minSal[i]));
		}
		return jobList;
	}
	
	private Set<Integer> prepareActulSet_testGetEmployeeMinSal(List<MinSalGradeEmp> actualList, String jobId) {
		Set<Integer> set = new HashSet<Integer>();
		for(MinSalGradeEmp jobGrp : actualList) {
			if(jobGrp != null && jobGrp.getJob().getJobId().equals(jobId)) {
				List<Employee> emps = jobGrp.getEmps();
				if(emps != null) {
					for(Employee emp : emps) {
						set.add(emp.getEmpId());
					}
				}
			}
		}
		return set;
	}
	
	private Map<String,List<Integer>> prepareActulMap_testGetEmployeeMinSal(List<MinSalGradeEmp> actualList) {
		Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
		for(MinSalGradeEmp jobGrp : actualList) {
			if(jobGrp != null) {
				List<Employee> emps = jobGrp.getEmps();
				List<Integer> empIdList = new ArrayList<Integer>();
				if(emps != null) {
					for(Employee emp : emps) {
						empIdList.add(emp.getEmpId());
					}
				}
				map.put(jobGrp.getJob().getJobId(), empIdList);
			}
		}
		return map;
	}
	
}
