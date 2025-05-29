package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String[][]getData() throws IOException
	{
		String path="./testData/Opencart_logindata.xlsx"; //taking xl file from testData
		ExcelUtility xlutil=new ExcelUtility(path); //creating an object for XLutility
		
		int totalrows=xlutil.getRowcount("Sheet1");
		int totalcols=xlutil.getCellcount("Sheet1", 1);
	
	String logindata[][]=new String[totalrows][totalcols]; //created for 2 dimensional array which can store
	
	for(int i=1;i<=totalrows;i++)//1 
	{//read the data from xl sorting in 2 dimensional array
		for(int j=0;j<totalcols;j++) //0      i is rows j is col
		{
		logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j); //1,0
		}
	}
	return logindata;//returning two dimension array
	}
}
//We need to create all the dataprovider in one class.
//and we need to give different name to each data provider.
//DataProvider 2
//DataProvider 3
//DataProvider 4
//DataProvider 5
