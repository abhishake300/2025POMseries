package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.listeners.ExtentReportListener;
import com.qa.opencart.pages.AccountsPage;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
//@Listeners({ExtentReportListener.class})
public class LoginPageTest extends BaseTest{

	@Severity(SeverityLevel.CRITICAL)
	@Description("Get the title of the page")
	@Feature("f 100: login page")
	@Test
	public void getpageTitleTest() {
		String ActualTitle = loginpage.getPageTitle();
		Assert.assertEquals(ActualTitle,AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Severity(SeverityLevel.NORMAL)
	@Description("Get the URL of the page")
	@Feature("f 101: login page")
	@Test
	public void getpageURLTest() {
		String ActualUrl = loginpage.getPageURL();
		Assert.assertTrue(ActualUrl.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
    
	@Severity(SeverityLevel.NORMAL)
	@Description("Check whether the forgot password link exist on the page")
	@Feature("f 101: login page")
	@Test	
	public void isForgotPwdLinkExistTest() {
		boolean flag =loginpage.isForgotPasswordExists();
		Assert.assertTrue(flag);
	}
	
	@Severity(SeverityLevel.TRIVIAL)
	@Description("Check whether the logo exist on the page")
	@Feature("f 101: login page")
	@Test	
	public void islogoExistTest() {
		boolean flag =loginpage.isLogoDisplayed();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=Integer.MAX_VALUE)
	@Severity(SeverityLevel.CRITICAL)
	@Description("verify the login feature of username{0}")
	@Feature("f 101: login page")
	public void dologinTest() {
	AccountsPage accpage=loginpage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	Assert.assertEquals(accpage.getAccountsPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
//		Assert.assertEquals(AcctpageTitle,AppConstants.ACCOUNTS_PAGE_TITLE );
	}

}
