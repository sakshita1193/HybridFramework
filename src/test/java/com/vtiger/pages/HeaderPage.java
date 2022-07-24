package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.GenericMethods;

public class HeaderPage {
	
	public WebDriver driver;
	public GenericMethods gm;
	public ExtentTest log;
	
	public  HeaderPage(WebDriver driver,ExtentTest log)
	{
		this.driver = driver;
		this.log = log;
		PageFactory.initElements(driver, this);
		gm = new GenericMethods(driver,log);
	}
	
	
	@FindBy(linkText="Logout")
	WebElement lnk_logout;
	
	@FindBy(linkText="New Lead")
	WebElement lnk_NewLead;
	
	@FindBy(linkText="Leads")
	WebElement lnk_Leads;
	
	@FindBy(linkText="My Account")
	WebElement lnk_MyAccount;
	
	@FindBy(linkText="New Account")
	WebElement lnk_NewAccount;
	
	@FindBy(linkText="Home")
	WebElement lnk_Home;
	
	public void cliclHome()
	{
		gm.clickElement(lnk_Home,"Link Home");
	}
	
	public void cliclNewAccount()
	{
		gm.clickElement(lnk_NewAccount,"Link New Account");
	}
	
	public void cliclMyAccount()
	{
		gm.clickElement(lnk_MyAccount,"Link My Account");
	}
	
	public void cliclLogout()
	{
		gm.clickElement(lnk_logout,"Link Logout");
	}
	
	public void cliclNewLead()
	{
		gm.clickElement(lnk_NewLead,"Link New Lead");
	}

}
