package com.vckadam.oopdesign.hr.test.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.hr.model.Country;
import com.vckadam.oopdesign.hr.model.Department;
import com.vckadam.oopdesign.hr.model.DepartmentsByCountry;
import com.vckadam.oopdesign.hr.model.Location;
import com.vckadam.oopdesign.hr.service.department.DepartmentService;
import com.vckadam.oopdesign.hr.service.department.DepartmentServiceImpl;

public class DepartmentServiceTest {

	private DepartmentService departmentService;
	@Before
	public void setUp() throws Exception {
		this.departmentService = new DepartmentServiceImpl();
	}

	@After
	public void tearDown() throws Exception {
		this.departmentService = null;
	}

	@Test
	public void testGetAllDeptByCoun_PositiveScenario() {
		String[] counIds = {"abc","bcd","def"};
		List<Country> counsList = prepCounListForTestGetAllDeptByCoun(counIds);
		Object[][] locIdToCounIds = {{1, "abc"},{2,"abc"},{3,"bcd"},{4,"def"}};
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},{3,2},{4,3},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(counsList, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test
	public void testGetAllDeptByCoun_DuplicateCountry() {
		String[] counIds = {"abc","bcd","def","def"};
		List<Country> counsList = prepCounListForTestGetAllDeptByCoun(counIds);
		Object[][] locIdToCounIds = {{1, "abc"},{2,"abc"},{3,"bcd"},{4,"def"}};
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},{3,2},{4,3},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(counsList, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test
	public void testGetAllDeptByCoun_UnnecessaryCountry() {
		String[] counIds = {"abc","bcd","def","def","mno"}; //No location in mno
		List<Country> counsList = prepCounListForTestGetAllDeptByCoun(counIds);
		Object[][] locIdToCounIds = {{1, "abc"},{2,"abc"},{3,"bcd"},{4,"def"}};
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},{3,2},{4,3},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(counsList, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test
	public void testGetAllDeptByCoun_UnnecessaryLocation() {
		String[] counIds = {"abc","bcd","def","def","mno"}; //No location in mno
		List<Country> counsList = prepCounListForTestGetAllDeptByCoun(counIds);
		Object[][] locIdToCounIds = {{1, "abc"},{2,"abc"},{3,"bcd"},{4,"def"},{5,"mno"}}; // no department for location 5
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},{3,2},{4,3},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(counsList, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test
	public void testGetAllDeptByCoun_UnnecessaryDepartment() {
		String[] counIds = {"abc","bcd","def","def","mno"}; //No location in mno
		List<Country> counsList = prepCounListForTestGetAllDeptByCoun(counIds);
		Object[][] locIdToCounIds = {{1, "abc"},{2,"abc"},{3,"bcd"},{4,"def"},{5,"mno"}}; // no department for location 5
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},{3,2},{4,3},{5,4},{6,7}}; // 6 dept is unnecessary since 7 is not valid location.
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(counsList, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test
	public void testGetAllDeptByCoun_DuplicateLocation() {
		String[] counIds = {"abc","bcd","def","def"};
		List<Country> counsList = prepCounListForTestGetAllDeptByCoun(counIds);
		Object[][] locIdToCounIds = {{1, "abc"},{2,"abc"},{3,"bcd"},{4,"def"},{4,"def"}};
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},{3,2},{4,3},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(counsList, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test
	public void testGetAllDeptByCoun_DuplicateDepartment() {
		String[] counIds = {"abc","bcd","def","def"};
		List<Country> counsList = prepCounListForTestGetAllDeptByCoun(counIds);
		Object[][] locIdToCounIds = {{1, "abc"},{2,"abc"},{3,"bcd"},{4,"def"},{4,"def"}};
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},{3,2},{4,3},{5,4},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(counsList, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test
	public void testGetAllDeptByCoun_NullCountry() {
		String[] counIds = {"abc","bcd",null,"def","def"};
		List<Country> counsList = prepCounListForTestGetAllDeptByCoun(counIds);
		Object[][] locIdToCounIds = {{1, "abc"},{2,"abc"},{3,"bcd"},{4,"def"},{4,"def"}};
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},{3,2},{4,3},{5,4},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(counsList, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test
	public void testGetAllDeptByCoun_NullLocation() {
		String[] counIds = {"abc","bcd",null,"def","def"};
		List<Country> counsList = prepCounListForTestGetAllDeptByCoun(counIds);
		Object[][] locIdToCounIds = {{1, "abc"},null,{2,"abc"},null,{3,"bcd"},{4,"def"},{4,"def"}};
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},{3,2},{4,3},{5,4},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(counsList, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test
	public void testGetAllDeptByCoun_NullDeptment() {
		String[] counIds = {"abc","bcd",null,"def","def"};
		List<Country> counsList = prepCounListForTestGetAllDeptByCoun(counIds);
		Object[][] locIdToCounIds = {{1, "abc"},null,{2,"abc"},null,{3,"bcd"},{4,"def"},{4,"def"}};
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},null,null,{3,2},{4,3},null,{5,4},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(counsList, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetAllDeptByCoun_NullCountryList() {
		Object[][] locIdToCounIds = {{1, "abc"},null,{2,"abc"},null,{3,"bcd"},{4,"def"},{4,"def"}};
		List<Location> locsList = prepLocListForTestGetAllDeptByCoun(locIdToCounIds);
		int[][] deptIdToLocIds = {{1,1},{2,1},null,null,{3,2},{4,3},null,{5,4},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(null, deptList, locsList);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetAllDeptByCoun_NullLocationList() {
		int[][] deptIdToLocIds = {{1,1},{2,1},null,null,{3,2},{4,3},null,{5,4},{5,4}};
		List<Department> deptList = prepDeptListForTestGetAllDeptByCoun(deptIdToLocIds);
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(null, deptList, null);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetAllDeptByCoun_NullDepartmentList() {
		List<DepartmentsByCountry> actualDeptsByCounList = this.departmentService.getAllDeptByCoun(null, null, null);
		Map<String,List<Integer>> actualCounIdToDeptIdsMap = prepActualMapForDepartmentsByCountry(actualDeptsByCounList);
		
		Object[][] expectedCounIdToDeptIds = {{"abc",Arrays.asList(1,2,3)},{"bcd",Arrays.asList(4)},{"def",Arrays.asList(5)}};
		Map<String,List<Integer>> expectedCounIdToDeptIdsMap = prepExpectedMapForDepartmentsByCountry(expectedCounIdToDeptIds);
		assertEquals(expectedCounIdToDeptIdsMap.size(), actualDeptsByCounList.size());
		assertEquals(expectedCounIdToDeptIdsMap, actualCounIdToDeptIdsMap);
		
	}
	
	private List<Country> prepCounListForTestGetAllDeptByCoun(String[] counIds) {
		List<Country> counList = new ArrayList<Country>();
		for(String counId : counIds) {
			if(counId != null) 
				counList.add(new Country(counId, null, 0));
			else 
				counList.add(null);
		}
		return counList;
	}
	
	private List<Location> prepLocListForTestGetAllDeptByCoun(Object[][] locIdToCounIds) {
		List<Location> locsList = new ArrayList<Location>();
		for(Object[] locIdToCounId : locIdToCounIds) {
			if(locIdToCounId != null) {
				locsList.add(new Location((int) locIdToCounId[0], null,null,null,null,(String)locIdToCounId[1]));
			} 
			else
				locsList.add(null);
		}
		return locsList;
	}
	
	private List<Department> prepDeptListForTestGetAllDeptByCoun(int[][] deptIdToLocIds) {
		List<Department> deptList = new ArrayList<Department>();
		for(int[] deptIdToLocId : deptIdToLocIds) {
			if(deptIdToLocId != null)
				deptList.add(new Department(deptIdToLocId[0], null,0, deptIdToLocId[1]));
			else
				deptList.add(null);
		}
		return deptList;
	}
	
	private Map<String,List<Integer>> prepActualMapForDepartmentsByCountry(List<DepartmentsByCountry> actualDeptsByCounList) {
		Map<String, List<Integer>> counIdToDeptIdsMap = new HashMap<>();
		for(DepartmentsByCountry deptsByCoun : actualDeptsByCounList) {
			Country coun = deptsByCoun.getCountry();
			if(!counIdToDeptIdsMap.containsKey(coun.getCountryId())) counIdToDeptIdsMap.put(coun.getCountryId(), new ArrayList<Integer>());
			List<Department> depts = deptsByCoun.getDepts();		
			for(Department dept : depts) {
				counIdToDeptIdsMap.get(coun.getCountryId()).add(dept.getDeparmentId());
			}
		}
		return counIdToDeptIdsMap;
	}
	
	private Map<String, List<Integer>> prepExpectedMapForDepartmentsByCountry(Object[][] counIdToDeptIds) {
		Map<String, List<Integer>> counIdToDeptIdsMap = new HashMap<>();
		for(Object[] counIdToDeptId : counIdToDeptIds) {
			String counId = (String)counIdToDeptId[0];
			@SuppressWarnings("unchecked")
			List<Integer> deptIds = (List<Integer>)counIdToDeptId[1];
			if(!counIdToDeptIdsMap.containsKey(counId)) counIdToDeptIdsMap.put(counId,deptIds);
		}
		return counIdToDeptIdsMap;
	}

}
