package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
//1. private elements for this page
	private WebDriver driver;
	private ElementUtil eleutil;
	private By logoutLink = By.linkText("Logout");
	private By headers =By.cssSelector("div#content h2");
	private By SearchBtn = By.name("search");
	private By SearchIcon = By.cssSelector("div#search button");
	
	
//	2. public constructor for this page
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleutil= new ElementUtil(driver);
		
	}
	
	
//3. public actions
	
	public String getAccountsPageTitle() {
		String title = driver.getTitle();
		System.out.println("Accounts page Title is" + title);
				return title;
	}
	
	public boolean isLogoutLinkExists() {
		
		return eleutil.isElementDisplayed(logoutLink);
	}
	
	public List<String> AccountPageHeaders() {
		List<WebElement> headerlist= eleutil.WaitforElementSVisible(headers, AppConstants.MEDIUM_TIME_OUT);
	    List<String> HeaderStringList = new ArrayList<String>();
	    
	    for(WebElement e: headerlist) {
	    	String header= e.getText();
	    	HeaderStringList.add(header);
	    }
	    return HeaderStringList;
	}
	
	public int getTotalAccountPageHeadersCount() {
		List<WebElement> headerlist= eleutil.WaitforElementSVisible(headers, AppConstants.MEDIUM_TIME_OUT);
		return headerlist.size();
	}
	
	public ResultsPage doSearch(String SearchKey) {
		WebElement SearchResultrow =eleutil.WaitforElementVisible(SearchBtn,AppConstants.MEDIUM_TIME_OUT);
		eleutil.doSendkeys(SearchResultrow, SearchKey);
		eleutil.doClick(SearchIcon);
		return new ResultsPage(driver);
	}
	

}
