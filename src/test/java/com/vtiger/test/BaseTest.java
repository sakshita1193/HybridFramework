package com.vtiger.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public static Properties prop;
	public List<Map<String,String>> LoginData;
	public List<Map<String,String>> LeadData;
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest log;
	
	
	
	@BeforeSuite
	public void setupPrecondions()
	{
		readproperties();		
		createReport();
	}
	
	public void StartApp()
	{		
		if(prop.getProperty("browser").equals("chrome"))
		{
		WebDriverManager.chromedriver().setup();
		driver  = new ChromeDriver();
		}
		else if(prop.getProperty("browser").equals("firefox"))
		{
		WebDriverManager.firefoxdriver().setup();
		driver  = new FirefoxDriver();
		}
		else if(prop.getProperty("browser").equals("edge"))
		{
		WebDriverManager.edgedriver().setup();
		driver  = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get(prop.getProperty("AppUrl"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("GlobalTimeout"))));
		
		
	}
	
	public void tearDown()
	{
		driver.quit();
	}
	
	
	public void readproperties() 
	{
		try
		{
		prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/setting.properties");
		prop.load(fis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<Map<String,String>> readTestData(String Sheet)
	{
		Connection connection =null;
		Recordset recordset = null;
		List<Map<String,String>> AllMap = null;
		try
		{
		Fillo fillo=new Fillo();
		connection=fillo.getConnection(System.getProperty("user.dir")+"/src/test/resources/TestData/Data.xlsx");
		String strQuery="Select * from "+Sheet;
		recordset=connection.executeQuery(strQuery);
		List<String> lst = recordset.getFieldNames();
		AllMap = new ArrayList<Map<String,String>>();
		
		while(recordset.next()){
		//System.out.println(recordset.getField("Details"));
		Map<String,String> map = new HashMap<String,String>();
		 for(int i=0;i<=lst.size()-1;i++)
		 {
			 //System.out.println(lst.get(i));
			 map.put(lst.get(i), recordset.getField(lst.get(i)));
		 }
		 AllMap.add(map);
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		recordset.close();
		connection.close();
		}
		return AllMap;
	}
	
	public void createReport() 
	{
		DateFormat f = new SimpleDateFormat("yyyyMMddhhmmss");
		Date d = new Date();
		String str = f.format(d);
		
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/src/test/java/com/vtiger/report/vTigerCrm"+str+".html");
    	// Create an object of Extent Reports
		extent = new ExtentReports();  
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host Name", "Automation Test Hub");
		    	extent.setSystemInfo("Environment", "Test");
		extent.setSystemInfo("User Name", "Rajesh U");
		htmlReporter.config().setDocumentTitle("Title of the Report Comes here "); 
		            // Name of the report
		htmlReporter.config().setReportName("Name of the Report Comes here "); 
		            // Dark Theme
		htmlReporter.config().setTheme(Theme.STANDARD); 
	}
	

}
