package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleutil;

	// 1. private page By locators : page objects

	private By EmailId = By.id("input-email");
	private By Password = By.id("input-password");
	private By LoginBtn = By.xpath("//input[@value='Login']");
	private By ForgotPasswordLink = By.linkText("Forgotten Password");
	private By RegisterLink = By.linkText("Register");
	private By logo = By.cssSelector("img.img-responsive");
	private By loginErrorMesg=By.cssSelector("div.alert.alert-danger.alert-dismissible");

	// 2.public page constructors
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	// 3.public page Actions
	public String getPageTitle() {
		String title = driver.getTitle();
		System.out.println("Title is" + title);
				return title;
	}

	public String getPageURL() {
		String url = driver.getCurrentUrl();
		return url;
	}

	public boolean isForgotPasswordExists() {	
		return eleutil.isElementDisplayed(ForgotPasswordLink);
		
	}
	public boolean isLogoDisplayed() {		
		return eleutil.isElementDisplayed(logo);
		
	}
	
	public AccountsPage doLogin(String email, String pwd) {
		
		eleutil.WaitforElementVisible(EmailId,AppConstants.SHORT_TIME_OUT);
		eleutil.doSendkeys(EmailId, email);
		eleutil.doSendkeys(Password, pwd);
//		driver.findElement(EmailId).sendKeys(email);
//		driver.findElement(Password).sendKeys(pwd);
		eleutil.doClick(LoginBtn);
//		driver.findElement(LoginBtn).click();
//        String AccPageTitle= driver.getTitle();
//        System.out.println("Account page title is"+AccPageTitle);
//        return AccPageTitle;
		
		 return new AccountsPage(driver);
	}
	
public boolean doInvalidLogin(String email, String pwd) {
		
		eleutil.WaitforElementVisible(EmailId,AppConstants.SHORT_TIME_OUT);
		eleutil.doSendkeys(EmailId, email);
		eleutil.doSendkeys(Password, pwd);
//		driver.findElement(EmailId).sendKeys(email);
//		driver.findElement(Password).sendKeys(pwd);
		eleutil.doClick(LoginBtn);
//		driver.findElement(LoginBtn).click();
//        String AccPageTitle= driver.getTitle();
//        System.out.println("Account page title is"+AccPageTitle);
//        return AccPageTitle;
		String errormesg=eleutil.WaitforElementVisible(loginErrorMesg, AppConstants.SHORT_TIME_OUT).getText();
		Log.error("Login error ---->"+errormesg);
		if(errormesg.contains(AppConstants.LOGIN_ERROR_MESG)) {
			return true;
		}else
			return false;
	}
	
	public RegistrationPage navigateToRegisterPage() {
		eleutil.doClick(RegisterLink);
		return new RegistrationPage(driver);
	}

}
