package com.qa.trcrm.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {

	WebDriver driver;
	Actions action;
	WebDriverWait wait;
	JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		action = new Actions(driver);
		wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT);
		jsUtil=new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locator) {
		WebElement element = null;
		try {
			element = driver.findElement(locator);
			jsUtil.flash(element);
			return element;
		} catch (Exception e) {
			System.out.println("some exception occured while creating the web element: " + locator);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return element;
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	public String doGetTitle() {
		return driver.getTitle();
	}

	public void doActionClick(By locator) {
		action.click(getElement(locator)).build().perform();
	}

	public void doActionSendkeys(By locator, String value) {
		action.sendKeys(getElement(locator), value).build().perform();
	}

	public void doMoveToElement(By locator) {
		action.moveToElement(getElement(locator)).build().perform();
	}
	public WebElement waitForPresenceOfElementLocated(By locator) {

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

		return getElement(locator);
	}
	public WebElement waitForVisibilityOfElement(By locator) {

		wait.until(ExpectedConditions.visibilityOf(getElement(locator)));

		return getElement(locator);
	}

	public String waitForPresenceOfTitle(String title) {
		wait.until(ExpectedConditions.titleIs(title));

		return doGetTitle();
	}

	public void doClear(By locator) {
		getElement(locator).clear();
	}
}
