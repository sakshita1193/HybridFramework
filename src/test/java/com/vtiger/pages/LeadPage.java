package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

public class LeadPage extends HeaderPage {

	public LeadPage(WebDriver driver,ExtentTest log) {
		super(driver,log);		
	}
	
	@FindBy(name="lastname")
	WebElement tb_lastname;
	
	@FindBy(name="company")
	WebElement tb_company;
	
	@FindBy(name="button")
	WebElement tb_save;
	
	
	public void createleadwithmandatoryfields(String lname, String comp)
	{
		gm.enterValue(tb_lastname, lname,"Lastname");
		gm.enterValue(tb_company, comp,"Company");
		gm.clickElement(tb_save,"Save button");
	}
	

}
