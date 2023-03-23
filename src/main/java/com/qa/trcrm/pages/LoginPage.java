package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pojo.Credentials;
import com.qa.trcrm.utils.ElementUtil;
import com.qa.trcrm.utils.JavaScriptUtil;

public class LoginPage extends BasePage {

	By email = By.id("_username");
	By password = By.id("_password");
	By loginBtn = By.xpath("//input[@type='submit']");

	By signUpNowLink = By.linkText("Sign Up Now2");

	By error = By.id("error");
	By random=By.id("random");

	WebDriver driver;
	ElementUtil util;
	JavaScriptUtil jsUtil;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(driver);
		jsUtil = new JavaScriptUtil(driver);
	}

	public String getLoginPageTitle() {
		return util.doGetTitle();
	}

	public boolean verifySignUpLink() {
		return util.doIsDisplayed(signUpNowLink);
	}

	public HomePage doLogin(String emailId, String pass) {
		util.doClear(email);
		util.doActionSendkeys(email, emailId);
		util.doClear(password);
		util.doActionSendkeys(password, pass);
		util.doClick(loginBtn);

		return new HomePage(driver);
	}

	public HomePage doLogin(Credentials credential) {
		util.doClear(email);
		util.doSendKeys(email, credential.getEmailId());
		util.doClear(password);
		util.doSendKeys(password, credential.getPassword());
		util.doClick(loginBtn);

		return new HomePage(driver);
	}

	public boolean errorMsg() {

		return util.doIsDisplayed(error);
	}

}
