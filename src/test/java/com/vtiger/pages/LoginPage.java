package com.vtiger.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.vtiger.common.GenericMethods;

public class LoginPage {
	
	private WebDriver driver;
	public GenericMethods gm;
	public ExtentTest log;
	
	public  LoginPage(WebDriver driver,ExtentTest log)
	{
		this.driver = driver;
		this.log = log;
		PageFactory.initElements(driver, this);
		gm = new GenericMethods(driver,log);
	}
	
	
	@FindBy(name="user_name")
	WebElement tb_username;
	
	@FindBy(name="user_password")
	WebElement tb_password;
	
	@FindBy(name="Login")
	WebElement btn_login;
	
	@FindBy(xpath="//img[@src='include/images/vtiger-crm.gif123']")
	WebElement img_logo;
	
	
	
	//By tb_username = By.name("user_name");
	//By tb_password = By.name("user_password");
	//By btn_login = By.name("Login");
	//By img_logo = By.xpath("//img[@src='include/images/vtiger-crm.gif']");
	
	
	
	public void login(String userid, String pwd)
	{
		
		setUsername(userid);
		setPassword(pwd);
		clickLogin();
		
	}
	
	public void setUsername(String userid)
	{
		gm.enterValue(tb_username, userid,"Username");
	}
	
	public void setPassword(String pwd)
	{
		gm.enterValue(tb_password, pwd,"Password");
	}
	
	public void clickLogin()
	{
		gm.clickElement(btn_login,"login button");
	}
	
	public boolean UserNameFiledEditable()
	{			
		return gm.elementEnable(tb_username,"UserName");		
	}
	
	
	public boolean LogoDisplay()
	{		
		return gm.elementDisplay(img_logo,"Logo");
	}
	
	
	

}
