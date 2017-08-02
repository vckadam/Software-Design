package com.vckadam.oopdesign.NorthWind.test.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vckadam.oopdesign.NorthWind.model.Product;
import com.vckadam.oopdesign.NorthWind.model.ProductsBySupplier;
import com.vckadam.oopdesign.NorthWind.model.Supplier;
import com.vckadam.oopdesign.NorthWind.service.Service;
import com.vckadam.oopdesign.NorthWind.service.ServiceImpl;

public class ServiceImplTest {

	private Service service;
	@Before
	public void beforeMethod() throws IOException {
		this.service = new ServiceImpl();
	}
	
	@After
	public void afterMethod() {
		this.service = null;
	}
	
	@Test
	public void testGetProductsInSupplier_PositiveScenario() {
		int[] supIds = {1,2,3,4};
		List<Supplier> supList = prepareSupList_TestGetProductsInSupplier(supIds);
		int[][] prodToSupIds = {{1,1},{2,1},{3,2},{4,4},{5,4}};
		List<Product> prodList = prepareProdList_TestGetProductsInSupplier(prodToSupIds);
		List<ProductsBySupplier> actualProdBySupList = this.service.getProductsInSupplier(prodList, supList);
		Map<Integer,List<Integer>> actualSupToProdsMap = prepareActualMap_TestGetProductsInSupplier(actualProdBySupList);
		
		int[][] expectedSupToProdIds = {{1,1},{1,2},{2,3},{4,4},{4,5}};
		Map<Integer,List<Integer>> expectedSupToProdsMap = prepareExpectedMap_TestGetProductsInSupplier(expectedSupToProdIds);
		
		assertEquals(expectedSupToProdsMap.size(), actualProdBySupList.size());
		assertEquals(expectedSupToProdsMap, actualSupToProdsMap);
	}
	
	public List<Supplier> prepareSupList_TestGetProductsInSupplier(int[] supIds) {
		List<Supplier> supList = new ArrayList<Supplier>();
		for(int supId : supIds) {
			supList.add(new Supplier(supId, null, null, null, null,
						null,null,null,null,null,null));
		}
		return supList;
	}
	
	public List<Product> prepareProdList_TestGetProductsInSupplier(int[][] prodToSupIds) {
		List<Product> prodList = new ArrayList<Product>();
		for(int[] prodToSupId : prodToSupIds) {
			prodList.add(new Product(prodToSupId[0], null, prodToSupId[1], 0, null, 
			0.0,0,0,0,true));
		}
		return prodList;
	}
	
	public Map<Integer,List<Integer>> prepareActualMap_TestGetProductsInSupplier(List<ProductsBySupplier> prodBySupList) {
		Map<Integer,List<Integer>> supIdToProdIds = new HashMap<>();
		for(ProductsBySupplier prodBySup : prodBySupList) {
			int currSupId = prodBySup.getSupplier().getSupplierId();
			List<Product> currProds = prodBySup.getProducts();
			if(!supIdToProdIds.containsKey(currSupId)) supIdToProdIds.put(currSupId, new ArrayList<Integer>());
			for(Product prod : currProds) {
				supIdToProdIds.get(currSupId).add(prod.getProductId());
			}
		}
		return supIdToProdIds;
	}
	
	public Map<Integer,List<Integer>> prepareExpectedMap_TestGetProductsInSupplier(int[][] supToProdIds) {
		Map<Integer,List<Integer>> supIdToProdIds = new HashMap<>();
		for(int[] supToProdId : supToProdIds) {
			if(!supIdToProdIds.containsKey(supToProdId[0])) 
				supIdToProdIds.put(supToProdId[0], new ArrayList<Integer>());
			supIdToProdIds.get(supToProdId[0]).add(supToProdId[1]);
		}
		return supIdToProdIds;
	}
	
	/** One Supplier available for given category.*/
	@Test
	public void sellProductInCategoryTest1() throws IOException {
		List<String> expected = new ArrayList<String>(Arrays.asList("Exotic Liquids"));
		List<Supplier> suppliers = this.service.sellProductInCategory("Beverages");
		List<String> actual = new ArrayList<String>();
		for(Supplier supplier : suppliers) {
			actual.add(supplier.getCompanyName());
		}
		assertEquals(expected, actual);
	}
	
	/** No Supplier available for given Category. */
	@Test
	public void sellProductInCategoryTest2() throws IOException {
		List<String> expected = new ArrayList<String>();
		List<Supplier> suppliers = this.service.sellProductInCategory("Not in list");
		List<String> actual = new ArrayList<String>();
		for(Supplier supplier : suppliers) {
			actual.add(supplier.getCompanyName());
		}
		assertEquals(expected, actual);
	}
	
	/** Multiple Supplier available for given Category. */
	@Test
	public void sellProductInCategoryTest3() throws IOException {
		Set<String> expected = new HashSet<String>();
		expected.add("Exotic Liquids");
		expected.add("New Orleans Cajun Delights");
		expected.add("Grandma Kelly's Homestead");
		List<Supplier> suppliers = this.service.sellProductInCategory("Condiments");
		Set<String> actual = new HashSet<String>();
		for(Supplier supplier : suppliers) {
			actual.add(supplier.getCompanyName());
		}
		assertEquals(expected, actual);
	}
}
