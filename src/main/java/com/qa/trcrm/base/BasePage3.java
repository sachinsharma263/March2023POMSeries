package com.qa.trcrm.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage3 {

	WebDriver driver;
	Properties prop;
	OptionsManager options;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialized the webdriver on the basis of browser name
	 * 
	 * @param prop
	 * @return driver
	 */
	public WebDriver init_driver(Properties prop) {

		String browserName = prop.getProperty("browser");
		options = new OptionsManager(prop);

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(options.getChromeOptions()));
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(options.getFireFoxOptions()));

		} else if (browserName.equalsIgnoreCase("safari")) {
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tlDriver.set(new SafariDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
		} else {

			System.out.println(browserName + " not found");
			try {
				throw new Exception("NoSuchBrowserException");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		return getDriver();
	}

	public WebDriver init_driver(Properties prop, String browserName) {

		switch (browserName) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(options.getChromeOptions()));
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			tlDriver.set(new FirefoxDriver(options.getFireFoxOptions()));
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			tlDriver.set(new EdgeDriver());
			break;
		case "safari":
			WebDriverManager.getInstance(SafariDriver.class).setup();
			tlDriver.set(new SafariDriver());
			break;
		default:
			System.out.println(browserName + " not found");
			try {
				throw new Exception("NoSuchBrowserException");

			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			break;
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		return getDriver();
	}

	/**
	 * This method is use to get the property file
	 * 
	 * @return prop
	 */
	public Properties init_prop() {
		prop = new Properties();
		String env = System.getProperty("env");
		String path;

		if (env == null) {
			env="qa";
		}
		switch (env) {
		case "qa":
			path = "./src/main/java/com/qa/trcrm/config/config_qa.properties";
			break;
		case "prod":
			path = "./src/main/java/com/qa/trcrm/config/config_prod.properties";
			break;

		case "stg":
			path = "./src/main/java/com/qa/trcrm/config/config.properties";
			break;
		default:
			path = "./src/main/java/com/qa/trcrm/config/config_qa.properties";
			break;
		}
		try {
			FileInputStream fis = new FileInputStream(
					new File(path));
			prop.load(fis);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("config file not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;

	}

	/**
	 * Taking screenshot util
	 * 
	 * @return path
	 */
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		// String
		// path=System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		String path = System.getProperty("user.dir") + "/screenshots/" + getDateTime() + ".png";

		File dest = new File(path);
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	public String getDateTime() {
		Date date = new Date();
		SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyhhmma");

		return formatDate.format(date);
	}
}
