package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;

public class HomePage2 extends BasePage {

	By homePage = By.xpath("//span[text()='Homepage']");
	By loggedInUser = By.xpath("//span[text()=' sachin sharma']");

	WebDriver driver;

	public HomePage2(WebDriver driver) {
		this.driver = driver;
	}

	public String getHomePageTitle() {
		return driver.getTitle();
	}
	public String verifyHomePageHeader() {
		return driver.findElement(homePage).getText();
	}
	public String verifyLoggedInUser() {
		return driver.findElement(loggedInUser).getText();
	}
}
