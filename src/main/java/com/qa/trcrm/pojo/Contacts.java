package com.qa.trcrm.pojo;

public class Contacts {

	String name;
	String emailId;
	public Contacts(String name, String emailId) {
		this.name = name;
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
}
