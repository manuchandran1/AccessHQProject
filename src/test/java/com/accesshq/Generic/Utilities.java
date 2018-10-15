package com.accesshq.Generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities 
{
	// Method for Reading the properties file to get the URL & Browser Type
	public String ReadingProperties(String sKey) throws IOException 
	{
		FileInputStream fis = new FileInputStream("FrameworkProperties.properties");
		Properties prop = new Properties();
		
		prop.load(fis);
		return prop.getProperty(sKey);
		
	}
	
	//Method for taking screenshot and it returns the path of the '.png' file.
	public String CaptureScreenshot(WebDriver driver, String TestCaseID) throws IOException 
	{
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
		String str = sf.format(date)+".png";
		
		TakesScreenshot screenshot = (TakesScreenshot)driver;
		File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotAs, new File(System.getProperty("user.dir")+"/TestScreenshots/" + TestCaseID +"-"+ str));
		String path = System.getProperty("user.dir")+"/TestScreenshots/" + TestCaseID +"-"+ str;
		return path;
	}
}
