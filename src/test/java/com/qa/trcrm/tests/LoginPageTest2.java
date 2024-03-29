package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.HomePage2;
import com.qa.trcrm.pages.LoginPage2;
import com.qa.trcrm.utils.AppConstants;

public class LoginPageTest2 {

	BasePage basePage;
	Properties prop;
	WebDriver driver;
	LoginPage2 loginPage;
	HomePage2 homePage;

	@BeforeTest
	@Parameters("browser")
	public void setUp(String browser) {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop,browser);
		loginPage = new LoginPage2(driver);

	}

	@Test(priority = 1)
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifySignUpNowLinkTest() {
		Assert.assertTrue(loginPage.verifySignUpLink());
	}

	@Test(priority = 3)
	public void loginTest() {
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.verifyHomePageHeader(), AppConstants.HOME_PAGE_HEADER);
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
