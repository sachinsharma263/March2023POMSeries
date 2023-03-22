package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.ElementUtil;
import com.qa.trcrm.utils.JavaScriptUtil;

public class HomePage extends BasePage {

	By homePage = By.xpath("//span[text()='Homepage']");
	By loggedInUser = By.xpath("//span[text()=' sachin sharma']");

	WebDriver driver;
	ElementUtil util;
	JavaScriptUtil jsUtil;
	
	

	public HomePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		jsUtil=new JavaScriptUtil(driver);
	}

	public String getHomePageTitle() {
		return util.waitForPresenceOfTitle(AppConstants.HOME_PAGE_TITLE);
	}

	public String verifyHomePageHeader() {
		util.waitForPresenceOfElementLocated(homePage);
		return util.doGetText(homePage);
	}

	public String verifyLoggedInUser() {
		return util.doGetText(loggedInUser);
	}
}
