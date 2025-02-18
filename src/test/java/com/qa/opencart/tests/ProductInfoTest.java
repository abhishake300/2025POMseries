package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Base.BaseTest;

public class ProductInfoTest extends BaseTest {
	
	
	@BeforeClass
	
public void ProductInfoSetup() {
		
		accpage= loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	
	@Test
	
	public void ProductHeaderTest() {
		resultsPage= accpage.doSearch("macbook");
	productInfoPage=resultsPage.selectProduct("MacBook Pro");
	String ProductHeaderVal= productInfoPage.getProductHeader();
	Assert.assertEquals(ProductHeaderVal, "MacBook Pro");
		
		
	}
	
	@Test
	public void ProductInfoDataTest() {
		resultsPage= accpage.doSearch("macbook");
		productInfoPage=resultsPage.selectProduct("MacBook Pro");
		Map<String, String> ProductFullData=  productInfoPage.getProductData();
		
		softassert.assertEquals(ProductFullData.get("Reward Points"), "800");
		softassert.assertAll();
		
	}

}
