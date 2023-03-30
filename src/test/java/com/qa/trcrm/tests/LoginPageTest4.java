package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.HomePage;
import com.qa.trcrm.pages.HomePage2;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.pages.LoginPage2;
import com.qa.trcrm.pojo.Credentials;
import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.JiraPolicy;
import com.qa.trcrm.utils.Log;

public class LoginPageTest4 {

	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	Credentials credential;

	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		Log.info("base page launched");
		prop = basePage.init_prop();
		Log.error("prop init");
		driver = basePage.init_driver(prop);
		Log.debug("driver launched");
		loginPage = new LoginPage(driver);
		credential=new Credentials(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = 1, enabled = true,description = "verify Login Page Title is correct or not")
	public void verifyLoginPageTitleTest() {
		Log.info("getting Login page title");
		String title = loginPage.getLoginPageTitle();
		Log.info("Login page title is: " + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@JiraPolicy(logTicketReady = true)
	@Test(priority = 2, enabled = true, description = "verify SignUp Link is correct or not")
	public void verifySignUpNowLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink());
	}

	@Test(priority = 3, enabled = true, description = "verify login feature")
	public void loginTest() {
		homePage = loginPage.doLogin(credential);
		Assert.assertEquals(homePage.verifyHomePageHeader(), AppConstants.HOME_PAGE_HEADER);
	}

	@DataProvider
	public Object[][] getLoginInvalidData() {
		Object data[][] = { { "test@gmail.com", "test@123" }, { "test2@gmail.com", "test@123" } };
		return data;

	}

	@Test(dataProvider = "getLoginInvalidData", enabled = false)
	public void login_InvalidTestCases(String email, String pass) {
		loginPage.doLogin(email, pass);
		Assert.assertTrue(loginPage.errorMsg());
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
