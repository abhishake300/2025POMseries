package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private Map<String,String> ProductMap;
	
	
	private By ProductHeader= By.tagName("h1");
	private By ProductMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By ProductPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	
	
	
	

	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}
	
	
	
	public String getProductHeader() {
		String ProductHeaderVal= eleutil.WaitforElementVisible(ProductHeader, AppConstants.SHORT_TIME_OUT).getText();
		System.out.println("The product name is "+ProductHeaderVal);
		return ProductHeaderVal;
	}
	
	private void getProductMetaData() {
		List<WebElement> MetaList= eleutil.WaitforElementSVisible(ProductMetaData, AppConstants.SHORT_TIME_OUT);
			
		for(WebElement meta: MetaList) {
			String metaText= meta.getText();
			String[] metaData =metaText.split(":");
			String metaKey= metaData[0].trim();
			String metaValue=metaData[1].trim();
			ProductMap.put(metaKey, metaValue);
		} }
		
		private void getProductPricedata() {
			List<WebElement> PriceList= eleutil.getElements(ProductPriceData);
			String Price=PriceList.get(0).getText();
			System.out.println("price"+Price);
			String exTaxPrice= PriceList.get(1).getText().split(":")[1].trim();
			System.out.println("exTaxPrice"+exTaxPrice);
			ProductMap.put("ProductPrice",Price);
			ProductMap.put("ExTaxPrice",exTaxPrice);

	} 
		
		public Map<String, String> getProductData() {
//			ProductMap = new TreeMap<String, String>();	-- alphabetic order
//			ProductMap = new LinkedHashMap<String, String>();	-- order of link
			ProductMap = new HashMap<String, String>();	
			ProductMap.put("ProductHeader", getProductHeader());
			getProductMetaData();
			getProductPricedata();
			System.out.println("Product Map Data is"+ProductMap);
			return ProductMap;
			
		}

}