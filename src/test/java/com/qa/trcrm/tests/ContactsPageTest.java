package com.qa.trcrm.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.trcrm.base.BasePage;
import com.qa.trcrm.pages.ContactsPage;
import com.qa.trcrm.pages.HomePage;
import com.qa.trcrm.pages.LoginPage;
import com.qa.trcrm.utils.AppConstants;

public class ContactsPageTest {

	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.goToContactsPage();

	}

	@Test(priority = 1)
	public void verifyContactsPageTitleTest() {
		String title = contactsPage.getContactPageTitle();
		Assert.assertEquals(title, AppConstants.CONTACT_PAGE_TITLE);
	}

	@Test(priority = 2, dataProvider = "getTestdata")
	public void verifyAddPersonTest(String name, String email) {
		String personAddedMsg  = contactsPage.addPerson(name, email);
		Assert.assertEquals(personAddedMsg, AppConstants.CONTACTS_PERSON_ADDED);
	}

	@DataProvider
	public Object[][] getTestdata() {
		Object data[][] = { { "bhawna", "b@gmail.com" }, { "naveen", "n@gmail.com" } };

		return data;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
