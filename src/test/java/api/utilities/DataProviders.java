package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	/**
	  Here we have the methods with DataProvider annotations which will get the data from the excel 
	  sheet through XLUtility methods and pass to test case
	  
	  DataProviders stores the data in 2D arrays
	 */
	
	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException{
		
		String path = System.getProperty("user.dir") + "//test-data//testData.xlsx"; 
		XLUtility xl = new XLUtility(path);
		
		int rowNum = xl.getRowCount("Sheet1");
		int cellNum = xl.getCellCount("Sheet1", 1);
		
		// we store data in 2D array
		String[][] data = new String[rowNum][cellNum];
		
		// i we take 1 so that it does not read 0th index row which has titles
		for(int i = 1; i<=rowNum; i++) {
			
			for(int j=0; j<cellNum; j++) {
				
				// i-1 bcoz we want store data in index position starting from 0 in 2D array
				data[i-1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		
		return data;
	}
	
	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException{
		
		String path = System.getProperty("user.dir") + "//test-data//testData.xlsx"; 
		XLUtility xl = new XLUtility(path);
		
		int rowNum = xl.getRowCount("Sheet1");
		
		// we store data in 1D array
		String[] data = new String[rowNum];
		
		// i we take 1 so that it does not read 0th index row which has titles
		for(int i = 1; i<=rowNum; i++) {
							
				// i-1 bcoz we want store data in index position starting from 0 in 1D array
				data[i-1] = xl.getCellData("Sheet1", i, 1); // we took cell value as 1 bcoz in out excel sheet the username is in column 2
			}
		
		return data;
	}


}
