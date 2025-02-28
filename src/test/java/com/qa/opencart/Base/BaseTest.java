package com.qa.opencart.Base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	
	protected SoftAssert softassert;
	
	protected LoginPage loginpage;
	protected AccountsPage accpage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registrationPage;
	protected Properties prop;

	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setup(@Optional("chrome")  String browserName,String browserversion,String testname) {
		df = new DriverFactory();
		prop = df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserversion);
			prop.setProperty("testname", testname);
		}

		driver = df.initdriver(prop);
		loginpage = new LoginPage(driver);
		softassert = new SoftAssert();
	}

	@AfterTest
	public void Teardown() {
		driver.quit();
	}

}
