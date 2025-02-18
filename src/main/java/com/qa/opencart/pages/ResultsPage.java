package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	private By SearchHeader= By.cssSelector("div#content h2");
	private By results=By.cssSelector("div.product-thumb");
	
	
	public ResultsPage(WebDriver driver) {
		this.driver=driver;
		eleutil = new ElementUtil(driver);
	}
	
	
	public String getSearchHeader() {
		String searchHeaderValue = eleutil.WaitforElementVisible(SearchHeader, AppConstants.SHORT_TIME_OUT).getText();
		return searchHeaderValue;
	}
	
	public int getSearchResultsCount() {
		int resultCount= eleutil.WaitforElementSVisible(results, AppConstants.MEDIUM_TIME_OUT).size();
		System.out.println("search result count is"+resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eleutil.doClick(By.linkText(productName)); //dynamic by locators
		return new ProductInfoPage(driver);
	}

}
