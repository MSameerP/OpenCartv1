package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws Exception{
		
		String path = "./testData//TestData.xlsx"; //taking xl file from testData
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating object for Excelutility
		
		int totalrows = xlutil.getRowCount("OPenCart");
		int totalcolms = xlutil.getCellCount("OpenCart", 1);
		
		String loginData[][] = new String[totalrows][totalcolms];
		
		for(int i =1;i<=totalrows;i++) {
			for(int j=0;j<totalcolms;j++) {
				loginData[i-1][j] = xlutil.getCellData("OpenCart", i, j); 
			}
		}
		
		return loginData; //returning two dimensional array
		
	}
	
	//DataProvider 2
	
	//DataProvider 3

}
