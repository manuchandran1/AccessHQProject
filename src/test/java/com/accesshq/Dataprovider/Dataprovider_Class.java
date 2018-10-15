package com.accesshq.Dataprovider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.accesshq.Generic.ExcelReadWrite;



public class Dataprovider_Class 
{
	
	// This method Supplies the Data to the Test Script 'Test_BookFlight_Positive'.
	
	@DataProvider(name = "DataProvider_TripPlanner")
	public static Iterator<Object[]> getData_TripPlanner_SearchTrip() throws IOException
	{
		// Common Data Provider Logic is called with the Test Data Sheet Name and Test Script Name as arguments
		return commonDataProvider_Logic("TripPlanner","TripPlanner_SearchTrip");
	}
	
	
	/*	The below method accepts two arguments 1. SheetName of the TestData.xlsx, 2. Test Script Name of the test to be executed 
		This can be used as a common logic for all Data Provider methods.
		This reads the data from Test Data sheet saves it in HashMap and stores inside a Object Array and inside a List
		This method returns the Iterator of the Object Array.*/
	public static Iterator<Object[]> commonDataProvider_Logic(String SheetName, String TestScriptName) throws IOException
	{
		
		ExcelReadWrite excelReader = new ExcelReadWrite(System.getProperty("user.dir")+ "/TestData/TestData.xlsx");
		
		int rowCount = excelReader.getRow(SheetName);
		int columnCount = excelReader.getColumn(SheetName);
		
		List<Object[]> Arr_List = new ArrayList<Object[]>();

		for (int i=1;i<=rowCount;i++)
		{
			String Execute_Flag = excelReader.readCell(SheetName, i, 2);
			String Script_Name = excelReader.readCell(SheetName, i, 1);
			
			if ((Execute_Flag.equalsIgnoreCase("Y")) && (Script_Name.equalsIgnoreCase(TestScriptName)))
			{
				Object[] x = new Object[1];
				Map<String, String> dMap = new HashMap<String,String>();
				
				for (int j=0;j<columnCount;j++) 
				{
					
					String sKey = excelReader.readCell(SheetName, 0, j);
					String sValue = excelReader.readCell(SheetName, i, j);
					
					
					dMap.put(sKey, sValue);
					
					
				}// End of Inner For loop
				
				x[0] = dMap;
				Arr_List.add(x);
		
			} // end of if
			

		} // End of Outer For Loop
		
		return Arr_List.iterator();

	}


}

