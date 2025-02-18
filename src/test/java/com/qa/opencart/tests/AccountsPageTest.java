package com.qa.opencart.tests;



import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	
	public void AccSetup() {
		accpage =loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}

	
	@Test
	public void getAccountspageTitleTest() {
		String accTitle = accpage.getAccountsPageTitle();
		Assert.assertEquals(accTitle,AppConstants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test	
	public void isLogoutLinkExistTest() {
		boolean flag =accpage.isLogoutLinkExists();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void AccountPageHeaderTest() {
		
		int actualCount=accpage.getTotalAccountPageHeadersCount();
		Assert.assertEquals(actualCount,AppConstants.ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	
	public void AccountsPageHeadersTest() {
	   List<String>actualheadersList=  accpage.AccountPageHeaders();
	   Assert.assertEquals(actualheadersList, AppConstants.EXPECTED_ACCOUNT_HEADERS_LIST);
	}
	
	
	@DataProvider
	
	public Object [][] searchTermCombination() {
		
	return new Object[][]{	
		{"macbook",3},
		{"iMac",1},
		{"samsung",2}
		
	}; }
	
	

	@Test(dataProvider="searchTermCombination")
	public void doSearchCountTest(String SearchTerm, int ResultCount) {
		resultsPage= accpage.doSearch(SearchTerm);
		Assert.assertEquals(resultsPage.getSearchResultsCount(), ResultCount); 
	}
	
	@DataProvider
	public Object [][] searchTermProductData() {
			
		return new Object[][]{	
			{"macbook","MacBook"},
			{"macbook","MacBook Air"},
			{"macbook","MacBook Pro"},
			{"iMac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"}
			
		};}


	@Test(dataProvider ="searchTermProductData")
	public void doSearchResultTest(String SearchTerm, String SelectProduct) {
		resultsPage= accpage.doSearch("macbook");
		productInfoPage= resultsPage.selectProduct("MacBook Pro");
	Assert.assertEquals(productInfoPage.getProductHeader(),"MacBook Pro");	
	}
}

