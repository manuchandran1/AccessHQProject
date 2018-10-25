package com.accesshq.Generic;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Base 
{
	
	public WebDriver driver;
	public ExtentReports extentReport;
	public ExtentTest extentTest;
	
	// Object created for using the methods for Fetching the URL from properties file, Taking Screenshots for report etc.
	public Utilities utility_Object = new Utilities();
	
	// The below method is for initializing the Extent Report before all the tests. 
	// @BeforeSuite annotation is used because a single report will record all the tests.
	@BeforeSuite
	public void extentReport_Generator() 
	{
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String date_string = df.format(date);		
		extentReport = new ExtentReports(System.getProperty("user.dir")+"/TestReport/Report-"+ date_string +".html", false);	
	}
	
	// Launching the browser and Application
	@BeforeMethod
	public void LaunchAppliation() throws IOException
	{
		
		String browserType = utility_Object.ReadingProperties("BrowserType");
		
		if(browserType.equalsIgnoreCase("chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/chromedriver");
			driver = new ChromeDriver(); 
		}
		
		else if(browserType.equalsIgnoreCase("safari")) 
		{
			driver = new SafariDriver(); 
		}

		
		driver.get(utility_Object.ReadingProperties("URL"));
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6000, TimeUnit.SECONDS);
	}
	
	
	//Method for closing the browser and Flushing the status of the test to the report. 
	@AfterMethod
	public void TearDown() 
	{
		driver.quit();
		extentReport.endTest(extentTest);
		extentReport.flush();
		
	}
}
