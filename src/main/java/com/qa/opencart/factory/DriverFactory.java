package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;

	Properties prop;

	OptionsManager optionsmanager;

	public static String isHighlight;

	// ThreadLocal is used to give the driver local copy to each driver so that
	// parallel tests are not effected
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * This is used to initialize the driver on basis of given browsername
	 * 
	 * @param BrowserName
	 * @return driver
	 */
	public WebDriver initdriver(Properties prop) {

		optionsmanager = new OptionsManager(prop);

		String BrowserName = prop.getProperty("browser");
		System.out.println("The Browser passed is  :" + BrowserName);

		isHighlight = prop.getProperty("highlight");

		switch (BrowserName.toLowerCase().trim()) {
		case "chrome":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remotedriver("chrome");
			} else {
				// run it in local browser
				// driver = new ChromeDriver(optionsmanager.getChromeOptions());
				tldriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
			}
			break;

		case "firefox":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remotedriver("firefox");
			} else {
				// driver = new FirefoxDriver(optionsmanager.getFirefoxOptions());
				tldriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
			}
			break;
			
		case "edge":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remotedriver("edge");
			} else {
				// driver = new EdgeDriver(optionsmanager.getEdgeOptions());
				tldriver.set(new EdgeDriver(optionsmanager.getEdgeOptions()));
			}
			break;
			
		default:
			System.out.println(AppError.INVALID_BROWSER_MESG + BrowserName + "is invalid");
			throw new BrowserException(AppError.INVALID_BROWSER_MESG);

		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	/*
	 * REMOTE WEBDRIVER CLASS To connect to selenium grid and run on docker
	 */
	private void init_remotedriver(String BrowserName) {
		System.out.println("Running on selenium grid" + BrowserName);

		try {

			switch (BrowserName.toLowerCase().trim()) {
			case "chrome":
				tldriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getChromeOptions()));
				break;
			case "firefox":
				tldriver.set(
						new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getFirefoxOptions()));
				break;
			case "edge":
				tldriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsmanager.getEdgeOptions()));
				break;

			default:
				System.out.println("please pass the right remote browser" + BrowserName);
				throw new BrowserException("INVALID BROWSER NAME");

			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This is used to return the driver using Thread local concept
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * This method is used to initialize the properties from the config file
	 * 
	 * @return
	 */

	// mvn clean install -Denv="qa"
	public Properties initProp() {
		prop = new Properties();
		FileInputStream fi = null;

		String envName = System.getProperty("env");
		System.out.println("Running test on environment  :" + envName);

		try {

			if (envName == null) {
				fi = new FileInputStream("./src/test/resources/config/config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					fi = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "production":
					fi = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("please pass the right env name" + envName);
					throw new FrameworkException("INVALID ENVIRONMENT");

				}
			}

			prop.load(fi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * take screenshot
	 */

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
