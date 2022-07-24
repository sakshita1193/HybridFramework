package com.vtiger.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

public class GenericMethods {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public ExtentTest log;
	
	public GenericMethods(WebDriver driver,ExtentTest log)
	{
		this.driver = driver;
		this.log= log;
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	
	public void enterValue(WebElement elm, String val,String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		elm.isEnabled();
		log.info("checked element is enabled");
		elm.clear();
		log.info("cleared existing data");
		elm.sendKeys(val);
		if(elm.getAttribute("value").equals(val))
		{
			System.out.println("PASSED");
			log.pass(val+" has been entered successfully in textbox "+msg);
		}
		else
		{
			System.out.println("FAILED");
			log.fail(val+" did not enter successfully in textbox "+msg+" <span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");
		}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			log.fail(val+" did not enter successfully in textbox "+msg+" due to error "+e.getMessage()+" <span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");
		}
	}
	
	
	public void clickElement(WebElement elm,String msg)
	{
		try
		{
		wait.until(ExpectedConditions.elementToBeClickable(elm));
		elm.click();		
		System.out.println("PASSED");	
		log.pass(msg+" element clicked successfully");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			log.fail(msg+" element did not click due to error "+e.getMessage()+" <span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");
		}
	}
	
	public boolean elementDisplay(WebElement elm,String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		log.pass(msg+" element displayed successfully");
		return elm.isDisplayed();				
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			log.fail(msg+" element did not found due to error "+e.getMessage()+" <span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");
			return false;
		}
	}
	
	
	public boolean elementEnable(WebElement elm,String msg)
	{
		try
		{
		wait.until(ExpectedConditions.visibilityOf(elm));
		log.pass(msg+" element enabled");
		return elm.isEnabled();				
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			log.fail(msg+" element did not found due to error "+e.getMessage()+" <span class='label end-time'><a href='"+getScreenshot()+"'>Screenshot</a></span>");
			return false;
		}
	}
	
	public String getScreenshot()  {
		//below line is just to append the date format with the screenshot name to avoid duplicate names		
	    String destination=null;
		try
		{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		//after execution, you could see a folder "FailedTestsScreenshots" under src folder
		destination = System.getProperty("user.dir") + "/src/test/java/com/vtiger/report/screenshot/"+dateName+".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//Returns the captured file path
		return destination;
	}
	

}
