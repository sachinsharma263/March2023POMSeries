package com.qa.trcrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.trcrm.utils.AppConstants;
import com.qa.trcrm.utils.ElementUtil;

public class ContactsPage {

	WebDriver driver;
	ElementUtil util;

	By contactsPageHeader = By.xpath("(//h2[@class='ng-binding'])[1]");

	By addPersonButton = By.xpath("//button[@class='hidden-xs hidden-sm btn btn-danger mr5 ng-scope ng-binding']");
	By name = By.name("name");
	By email = By.id("email0");
	By saveButton = By.xpath("//button[@class='btn btn-primary btn-large ng-binding']");
	By personAddedMsg = By.xpath("//span[text()='Person added.']");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		util=new ElementUtil(driver);
	}

	public String getContactPageTitle() {
		return util.waitForPresenceOfTitle(AppConstants.CONTACT_PAGE_TITLE);
	}

	public String getContactPageHeader() {
		return util.doGetText(contactsPageHeader);
	}
	public String addPerson(String firstName,String emailId) {
		util.waitForPresenceOfElementLocated(addPersonButton);
		util.doClick(addPersonButton);
		util.waitForPresenceOfElementLocated(name);
		util.doSendKeys(name, firstName);
		util.doSendKeys(email, emailId);
		
		util.doClick(saveButton);
		
		util.waitForPresenceOfElementLocated(personAddedMsg);
		
		return util.doGetText(personAddedMsg);
	}

}
