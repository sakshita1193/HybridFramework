package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.aventstack.extentreports.ExtentTest;

public class HomePage extends HeaderPage {

	public HomePage(WebDriver driver,ExtentTest log) {
		super(driver,log);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//*[contains(text(),'My Upcoming and Pending Activities')]")
	WebElement lbl_MyUpcomingPendingActivities;
	
	@FindBy(xpath="//map[@name='pipeline']")
	WebElement Map_pipeline;
	
	
	public boolean checkPendingActivities()
	{
		return gm.elementDisplay(lbl_MyUpcomingPendingActivities,"My Pending Activities block");
	}
	
	public boolean checkMyPipelineDashboard()
	{
		return gm.elementDisplay(Map_pipeline,"My Pipeline graph");
	}

}
