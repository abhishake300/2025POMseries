package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.utils.CSVUtil;

public class LoginPageNegative extends BaseTest {
    @DataProvider
	public Object[][] invalidLoginData() {
    	return CSVUtil.csvData("login");
		
	}
	
	
	@Test(dataProvider = "invalidLoginData")
	public void invalidLoginTest(String UN, String PW) {
		Assert.assertTrue(loginpage.doInvalidLogin(UN,PW));
	}
}
