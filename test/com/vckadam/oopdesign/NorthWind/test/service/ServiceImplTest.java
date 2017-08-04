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

import com.vckadam.oopdesign.NorthWind.model.Category;
import com.vckadam.oopdesign.NorthWind.model.Product;
import com.vckadam.oopdesign.NorthWind.model.ProductsBySupplier;
import com.vckadam.oopdesign.NorthWind.model.ProductsInCat;
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
	public void testGetProductsInCategory_PositiveScenario() {
		int[] catIds = {1,2,3,4};
		List<Category> cats = prepCatListForTestGetProductsInCategory(catIds);
		int[][] prodIdToCatId = {{1,1},{2,1},{3,3},{4,4},{5,2},{6,2},{7,3}};
		List<Product> prods = prepProdListForTestGetProductsInCategory(prodIdToCatId);
		List<ProductsInCat> actualCatToProd = this.service.getProductsInCategory(cats, prods);
		Map<Integer,List<Integer>> actualCatIdToProdIdsMap = prepActualMapTestGetProductsInCategory(actualCatToProd);
		
		Object[][] expectedCatIdToProdIds = {{1,Arrays.asList(1,2)},{2,Arrays.asList(5,6)},{3,Arrays.asList(3,7)},{4,Arrays.asList(4)}};
		Map<Integer,List<Integer>> expectedCatIdToProdIdsMap = prepExpectedMapTestGetProductsInCategory(expectedCatIdToProdIds);
		assertEquals(expectedCatIdToProdIdsMap.size(), actualCatToProd.size());
		assertEquals(expectedCatIdToProdIdsMap, actualCatIdToProdIdsMap);
		
	}
	
	@Test
	public void testGetProductsInCategory_NullProducts() {
		int[] catIds = {1,2,3,4};
		List<Category> cats = prepCatListForTestGetProductsInCategory(catIds);
		int[][] prodIdToCatId = {{1,1},{2,1},null,{3,3},null,{4,4},{5,2},{6,2},{7,3}};
		List<Product> prods = prepProdListForTestGetProductsInCategory(prodIdToCatId);
		List<ProductsInCat> actualCatToProd = this.service.getProductsInCategory(cats, prods);
		Map<Integer,List<Integer>> actualCatIdToProdIdsMap = prepActualMapTestGetProductsInCategory(actualCatToProd);
		
		Object[][] expectedCatIdToProdIds = {{1,Arrays.asList(1,2)},{2,Arrays.asList(5,6)},{3,Arrays.asList(3,7)},{4,Arrays.asList(4)}};
		Map<Integer,List<Integer>> expectedCatIdToProdIdsMap = prepExpectedMapTestGetProductsInCategory(expectedCatIdToProdIds);
		assertEquals(expectedCatIdToProdIdsMap.size(), actualCatToProd.size());
		assertEquals(expectedCatIdToProdIdsMap, actualCatIdToProdIdsMap);
		
	}
	
	@Test
	public void testGetProductsInCategory_ExtraCategories() {
		int[] catIds = {1,2,3,4,5,6,7,8}; //No products in cat 5,6,7,8
		List<Category> cats = prepCatListForTestGetProductsInCategory(catIds);
		int[][] prodIdToCatId = {{1,1},{2,1},null,{3,3},null,{4,4},{5,2},{6,2},{7,3}};
		List<Product> prods = prepProdListForTestGetProductsInCategory(prodIdToCatId);
		List<ProductsInCat> actualCatToProd = this.service.getProductsInCategory(cats, prods);
		Map<Integer,List<Integer>> actualCatIdToProdIdsMap = prepActualMapTestGetProductsInCategory(actualCatToProd);
		
		Object[][] expectedCatIdToProdIds = {{1,Arrays.asList(1,2)},{2,Arrays.asList(5,6)},{3,Arrays.asList(3,7)},{4,Arrays.asList(4)}};
		Map<Integer,List<Integer>> expectedCatIdToProdIdsMap = prepExpectedMapTestGetProductsInCategory(expectedCatIdToProdIds);
		assertEquals(expectedCatIdToProdIdsMap.size(), actualCatToProd.size());
		assertEquals(expectedCatIdToProdIdsMap, actualCatIdToProdIdsMap);
		
	}
	
	@Test
	public void testGetProductsInCategory_ProductsWithInvalidCat() {
		int[] catIds = {1,2,3,4};
		List<Category> cats = prepCatListForTestGetProductsInCategory(catIds);
		int[][] prodIdToCatId = {{1,1},{2,1},null,{3,3},null,{4,4},{5,2},{6,2},{7,3},{8,9},{9,10}}; // 9 and 10 not valid category.
		List<Product> prods = prepProdListForTestGetProductsInCategory(prodIdToCatId);
		List<ProductsInCat> actualCatToProd = this.service.getProductsInCategory(cats, prods);
		Map<Integer,List<Integer>> actualCatIdToProdIdsMap = prepActualMapTestGetProductsInCategory(actualCatToProd);
		
		Object[][] expectedCatIdToProdIds = {{1,Arrays.asList(1,2)},{2,Arrays.asList(5,6)},{3,Arrays.asList(3,7)},{4,Arrays.asList(4)}};
		Map<Integer,List<Integer>> expectedCatIdToProdIdsMap = prepExpectedMapTestGetProductsInCategory(expectedCatIdToProdIds);
		assertEquals(expectedCatIdToProdIdsMap.size(), actualCatToProd.size());
		assertEquals(expectedCatIdToProdIdsMap, actualCatIdToProdIdsMap);
		
	}
	
	@Test
	public void testGetProductsInCategory_DuplicateCategory() {
		int[] catIds = {1,2,3,4,4};
		List<Category> cats = prepCatListForTestGetProductsInCategory(catIds);
		int[][] prodIdToCatId = {{1,1},{2,1},null,{3,3},null,{4,4},{5,2},{6,2},{7,3}};
		List<Product> prods = prepProdListForTestGetProductsInCategory(prodIdToCatId);
		List<ProductsInCat> actualCatToProd = this.service.getProductsInCategory(cats, prods);
		Map<Integer,List<Integer>> actualCatIdToProdIdsMap = prepActualMapTestGetProductsInCategory(actualCatToProd);
		
		Object[][] expectedCatIdToProdIds = {{1,Arrays.asList(1,2)},{2,Arrays.asList(5,6)},{3,Arrays.asList(3,7)},{4,Arrays.asList(4)}};
		Map<Integer,List<Integer>> expectedCatIdToProdIdsMap = prepExpectedMapTestGetProductsInCategory(expectedCatIdToProdIds);
		assertEquals(expectedCatIdToProdIdsMap.size(), actualCatToProd.size());
		assertEquals(expectedCatIdToProdIdsMap, actualCatIdToProdIdsMap);
		
	}
	
	@Test
	public void testGetProductsInCategory_DuplicateProducts() {
		int[] catIds = {1,2,3,4};
		List<Category> cats = prepCatListForTestGetProductsInCategory(catIds);
		int[][] prodIdToCatId = {{1,1},{2,1},null,{3,3},null,{4,4},{5,2},{6,2},{7,3},{7,3},{3,3}};
		List<Product> prods = prepProdListForTestGetProductsInCategory(prodIdToCatId);
		List<ProductsInCat> actualCatToProd = this.service.getProductsInCategory(cats, prods);
		Map<Integer,List<Integer>> actualCatIdToProdIdsMap = prepActualMapTestGetProductsInCategory(actualCatToProd);
		
		Object[][] expectedCatIdToProdIds = {{1,Arrays.asList(1,2)},{2,Arrays.asList(5,6)},{3,Arrays.asList(3,7)},{4,Arrays.asList(4)}};
		Map<Integer,List<Integer>> expectedCatIdToProdIdsMap = prepExpectedMapTestGetProductsInCategory(expectedCatIdToProdIds);
		assertEquals(expectedCatIdToProdIdsMap.size(), actualCatToProd.size());
		assertEquals(expectedCatIdToProdIdsMap, actualCatIdToProdIdsMap);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetProductsInCategory_NullCatList() {
		int[][] prodIdToCatId = {{1,1},{2,1},null,{3,3},null,{4,4},{5,2},{6,2},{7,3},{7,3},{3,3}};
		List<Product> prods = prepProdListForTestGetProductsInCategory(prodIdToCatId);
		this.service.getProductsInCategory(null, prods);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetProductsInCategory_NullProdList() {
		this.service.getProductsInCategory(null, null);
	}
	
	private List<Category> prepCatListForTestGetProductsInCategory(int[] catIds) {
		List<Category> cats = new ArrayList<Category>();
		for(int cat : catIds) {
			cats.add(new Category(cat, null, null));
		}
		return cats;
	}
	
	private List<Product> prepProdListForTestGetProductsInCategory(int[][] prodIds) {
		List<Product> prods = new ArrayList<Product>();
		for(int[] prodId : prodIds) {
			if(prodId != null) prods.add(new Product(prodId[0], null, 0, prodId[1], null, 0.0, 0,0,0,true));
			else prods.add(null);
		}
		return prods;
	}
	
	private Map<Integer,List<Integer>> prepActualMapTestGetProductsInCategory(List<ProductsInCat> catToProds) {
		Map<Integer,List<Integer>> catToProdsMap = new HashMap<>();
		for(ProductsInCat ele : catToProds) {
			catToProdsMap.put(ele.getCategory().getCategoryId(), new ArrayList<Integer>());
			for(Product prod : ele.getProducts()) {
				catToProdsMap.get(ele.getCategory().getCategoryId()).add(prod.getProductId());
			}
		}
		return catToProdsMap;
	}
	
	@SuppressWarnings("unchecked")
	private Map<Integer,List<Integer>> prepExpectedMapTestGetProductsInCategory(Object[][] catToProds) {
		Map<Integer,List<Integer>> catToProdsMap = new HashMap<>();
		for(Object[] ele : catToProds) {
			catToProdsMap.put((int)ele[0],(List<Integer>)ele[1]);
		}
		return catToProdsMap;
	}
	
	/*----------------------------------------------------------------*/
	
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
