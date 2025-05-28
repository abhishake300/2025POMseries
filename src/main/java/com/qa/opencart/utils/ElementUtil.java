package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsutil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsutil=new JavaScriptUtil(driver);

	}
	
	private void checkElementHighlight(WebElement element) {
		if(Boolean.parseBoolean(DriverFactory.isHighlight)) {
			jsutil.flash(element);	
		}		
	}

	public WebElement getElement(By locator) {
		WebElement ele= driver.findElement(locator);
		checkElementHighlight(ele);
		return ele;

	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);

	}

	public void doSendkeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);

	}
	
	public void doSendkeys(WebElement element, String value) {

		element.clear();
		element.sendKeys(value);

	}

	public void doClick(By locator) {

		getElement(locator).click();

	}
	
	public void doActionsCick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).build().perform();
	}

	public String getTextofElement(By locator) {

		return getElement(locator).getText();
	}

	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not Displayed"+locator);
			return false;
		}
	}

	public List<String> getLinksElementText(By locator) {
		List<String> textList = new ArrayList<String>();
		List<WebElement> elelist = getElements(locator);
		for (WebElement e : elelist) {
			String text = e.getText();
			textList.add(text);
		}
		return textList;
	}

	public int totalNumberOfLinks(By locator) {
		System.out.println("total links count for: " + locator + " is: " + getElements(locator).size());
		return getElements(locator).size();

	}

	// *******************SELECT based Dropdown utils************************
	public void doSelectDropdownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropdownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropdownByVisibleText(By locator, String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public List<WebElement> getSelectDropdownOptions(By locator) {
		Select select = new Select(getElement(locator));
		return select.getOptions();

	}

	public int getTotalSelectDropdownOptions(By locator) {
		return getSelectDropdownOptions(locator).size();

	}

	public List<String> getTextOfSelectDropdownOptions(By locator) {
		List<String> TextList = new ArrayList<String>();
		List<WebElement> OptionsList = getSelectDropdownOptions(locator);
		for (WebElement e : OptionsList) {
			String text = e.getText();
			TextList.add(text);

		}

		return TextList;
	}

	public boolean verifyTheOptionPresentInSelectDD(By locator, String searchValue) {
		List<String> textList = getTextOfSelectDropdownOptions(locator);
		boolean IsSearchValuePresent = false;
		for (String k : textList) {

			if (k.equals(searchValue)) {
				IsSearchValuePresent = true;
				System.out.println(searchValue + ":  found");
				break;
			}

		}
		return IsSearchValuePresent;
	}

	// *******************************Search and click on
	// suggestion***************************

	public void doSearchAndClickOnSuggestion(By searchTermLocator, String enterSearchTerm, By suggListLocator,
			String suggName) {
		getElement(searchTermLocator).sendKeys(enterSearchTerm);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<WebElement> suggestionList = getElements(suggListLocator);

		System.out.println("Total number of results :" + suggestionList.size());
		boolean isClickedflag = false;
		for (WebElement e : suggestionList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggName)) {
				e.click();
				isClickedflag = true;
				break;
			}

		}
		if (isClickedflag != true) {
			System.out.println("The given Suggestion Name is not present in list: " + suggName);
		}
	}
//******************************waitFor UTILS******************************	
	public  WebElement WaitforElementPresent(By locator, int timeOut) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 WebElement ele= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		 checkElementHighlight(ele);
		 return ele;
		
	}
	public  WebElement WaitforElementVisible(By locator, int timeOut) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 WebElement ele=  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		 checkElementHighlight(ele);
		 return ele;
		
	}
	
	public  List<WebElement> WaitforElementSVisible(By locator, int timeOut) {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		 return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
	}
	
}
