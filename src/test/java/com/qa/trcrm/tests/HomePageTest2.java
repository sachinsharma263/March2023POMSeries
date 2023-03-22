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

public class HomePageTest2 {

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
		homePage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		

	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String title = homePage.getHomePageTitle();
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifyHomePageHeaderTest() {
		Assert.assertEquals(homePage.verifyHomePageHeader(), AppConstants.HOME_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void verifyLoggedInUserTest() {
		Assert.assertEquals(homePage.verifyLoggedInUser(), prop.getProperty("accountname"));
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
