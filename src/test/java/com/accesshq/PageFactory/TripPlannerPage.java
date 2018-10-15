package com.accesshq.PageFactory;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TripPlannerPage 
{
	//	Page Factory Constructor
	public TripPlannerPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	
	// Page Elements
	@FindBy(id = "search-input-From")
	private WebElement textBox_FromStation;
	
	@FindBy(xpath = "//div[@id='suggestions-From']//div[contains(text(),'North Sydney Station')]")
	WebElement searchResult_FromStation;
	
	@FindBy(id = "search-input-To")
	private WebElement textBox_ToStation;
	
	@FindBy(xpath = "//div[@id='suggestions-To']//div[contains(text(),'Town Hall Station')]")
	private WebElement searchResult_ToStation;
	
	@FindBy(id = "search-button")
	private WebElement button_Go;
	
	@FindBy(tagName = "trip-summary")
	private List<WebElement> resultTrips_All; 
	
	
	//	Methods to Access the Page Elements
	public void set_FromStation(String fromStation) 
	{
		textBox_FromStation.sendKeys(fromStation);
	}
	
	public void select_FromStation() 
	{
		searchResult_FromStation.click();
	}
	
	public void set_ToStation(String toStation) 
	{
		textBox_ToStation.sendKeys(toStation);
	}
	
	public void select_ToStation() 
	{
		searchResult_ToStation.click();
	}
	
	public void click_GoButton() 
	{
		button_Go.click();
	}
	
	public int getCountOfTripResults() 
	{
		return resultTrips_All.size();
	}
	
}
