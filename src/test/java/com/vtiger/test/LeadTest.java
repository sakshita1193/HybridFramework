package com.vtiger.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.vtiger.pages.HomePage;
import com.vtiger.pages.LeadPage;
import com.vtiger.pages.LoginPage;

public class LeadTest extends BaseTest {
	
	@BeforeClass
	public void launchApp()
	{
		LeadData = readTestData("Leads");
		StartApp();
	}
	
	@AfterClass
	public void closeApp()
	{
		tearDown();
	}
	
	@Test
	public void CreateLead()
	{
		log = extent.createTest("CreateLead");
		LoginPage lp = new LoginPage(driver,log);
		lp.login(LeadData.get(0).get("Userid"), LeadData.get(0).get("Password"));
		HomePage hp = new HomePage(driver,log);
		hp.cliclNewLead();
		LeadPage ldp = new LeadPage(driver,log);
		ldp.createleadwithmandatoryfields(LeadData.get(0).get("LastName"), LeadData.get(0).get("Company"));
		ldp.cliclLogout();
		extent.flush();
	}

}
