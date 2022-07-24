package com.vtiger.test;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;



public class LoginTest extends BaseTest {
	
	
	
	@BeforeClass
	public void launchApp()
	{
		LoginData = readTestData("Login");
		LeadData = readTestData("Leads");
		StartApp();
		
	}
	
	@AfterClass
	public void closeApp()
	{
		tearDown();
	}
	
	
	@Test
	public void validLogin()
	{
		log = extent.createTest("validLogin");
		LoginPage lp = new LoginPage(driver,log);
		lp.LogoDisplay();
		lp.login(LoginData.get(0).get("Userid"), LoginData.get(0).get("Password"));
		extent.flush();
	}

}
