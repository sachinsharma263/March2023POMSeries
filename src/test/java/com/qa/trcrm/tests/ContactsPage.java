package com.qa.trcrm.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactsPage {

	WebDriver driver;
	By addPerson = By.xpath("//button[@class='hidden-xs hidden-sm btn btn-danger mr5 ng-scope ng-binding']");
	By name = By.name("name");
	By email = By.id("email0");
	By saveButton = By.xpath("//button[@class='btn btn-primary btn-large ng-binding']");
	By personAdded = By.xpath("//span[text()='Person added.']");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
	}

}
