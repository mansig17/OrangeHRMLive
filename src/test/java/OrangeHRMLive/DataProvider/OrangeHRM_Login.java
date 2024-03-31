package OrangeHRMLive.DataProvider;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrangeHRM_Login extends OrangeHRM_BaseDataProvider

{
	
	@DataProvider(name="LoginDataProvider")
	
	public Object[][] Login_DataProvider() throws IOException
	{ 
		BaseDataProvider();// called the function to open the workbook and the sheet with test case names
		
		//ArrayList<String> testCaseNames =new ArrayList<String>();  
		//testCaseNames=getTestCaseNames(testCasesSheet,TestScenarioMapping,"Login");
		
		/*for(String name:testCaseNames )
		{
			System.out.println(name);
		}*/
		
		XSSFSheet testDataSheet= workbook.getSheet("Login");
		Map<String, Integer> LoginMapping= HashMappings(testDataSheet);
		System.out.print(LoginMapping);
		
		int lastRow= testDataSheet.getLastRowNum();
		System.out.print(lastRow);
		
		ArrayList<String> uname= new ArrayList<>();
		ArrayList<String> pass= new ArrayList<>();
		ArrayList<String> testType= new ArrayList<>();
		
		
		
		for(int i=1;i<=lastRow;i++)
		{
			Row currRow= testDataSheet.getRow(i);
			uname.add(currRow.getCell(LoginMapping.get("Username")).toString());
			pass.add(currRow.getCell(LoginMapping.get("Password")).toString());
			testType.add(currRow.getCell(LoginMapping.get("TestType")).toString());
				
		}
		
		int s= uname.size();
		Object obj[][]= new Object[s][3];
		for(int i=0;i<s;i++)
		{
			obj[i][0]=uname.get(i);
			obj[i][1]=pass.get(i);
			obj[i][2]=testType.get(i);
			
			System.out.println(obj[i][0]+" "+obj[i][1]);
		}
		
		return obj;
		
		
	}
}
