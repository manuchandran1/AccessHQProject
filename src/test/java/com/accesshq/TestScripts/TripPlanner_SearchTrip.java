package com.accesshq.TestScripts;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import org.testng.asserts.SoftAssert;

import com.accesshq.Generic.Base;
import com.accesshq.PageFactory.TripPlannerPage;
import com.relevantcodes.extentreports.LogStatus;

public class TripPlanner_SearchTrip extends Base
{
	
	@Test(dataProvider = "DataProvider_TripPlanner", dataProviderClass = com.accesshq.Dataprovider.Dataprovider_Class.class)
	public void TestPlanner_SearchTripTest(HashMap<String, String> TestData) throws IOException 
	{
		// Assigning the Test Data from the Test Data Sheet to local variables

		String TestCaseID = TestData.get("TestCase_ID");
		String fromStation = TestData.get("FromStation");
		String toStation = TestData.get("ToStation");
		String ExpectedTitle_TripPlanner = TestData.get("ExpectedTitle_TripPlanner");
		
		//Assert for TestNG Report & extentTest for Extent Report
		SoftAssert sAssert = new SoftAssert();
		extentTest = extentReport.startTest(TestCaseID);
		
		// Log the start of the test execution in the Extent report
		extentTest.log(LogStatus.PASS, "Test Case ID : " + TestCaseID + ",   Status: Execution Started");
		
		//	STEP1 :'Trip Planner Page' : Title Validation
		String currentTitle = driver.getTitle();
		if(currentTitle.equalsIgnoreCase(ExpectedTitle_TripPlanner)) 
		{
			extentTest.log(LogStatus.PASS, " TripPlanner Page is Displayed");
		}
		else 
		{
			extentTest.log(LogStatus.FAIL, "Status = FAILED, Actual Title= '"+currentTitle+ "'  & Expected =  '"+ExpectedTitle_TripPlanner+"'");
			extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(utility_Object.CaptureScreenshot(driver, TestCaseID)));
			AssertJUnit.fail("STATUS: FAILED,  Page Title = "+currentTitle+"  & Expected Page Title = '"+ExpectedTitle_TripPlanner);
		}
		
		//	STEP2 :	Search for From Station and Select the From Station from the results
		TripPlannerPage page_TripPlanner = new TripPlannerPage(driver);
		page_TripPlanner.set_FromStation(fromStation);
		page_TripPlanner.select_FromStation();
			
		//	STEP3 :	Search for To Station and Select the To Station from the results
		page_TripPlanner.set_ToStation(toStation);
		page_TripPlanner.select_ToStation();
		
		//	STEP4 :	Click the Go Button
		page_TripPlanner.click_GoButton();
		
		//	STEP5 :	Verify if a list of Trips are displayed 
		int countOfTripResults = page_TripPlanner.getCountOfTripResults();
		
		if(countOfTripResults>1) 
		{
			extentTest.log(LogStatus.PASS, "STATUS : PASSED, List of Trips are displayed & the Count of Trips = "+countOfTripResults);
		}
		else if(countOfTripResults<=1) 
		{
			extentTest.log(LogStatus.FAIL, "STATUS: FAILED, The result displayed is not a list of trips & the Count of Trips = "+countOfTripResults);
			extentTest.log(LogStatus.FAIL,extentTest.addScreenCapture(utility_Object.CaptureScreenshot(driver, TestCaseID)));
			AssertJUnit.fail("STATUS: FAILED, The result displayed is not a list of trips & the Count of Trips = "+countOfTripResults);
		}
		
		sAssert.assertAll();
		
	}
	

}
