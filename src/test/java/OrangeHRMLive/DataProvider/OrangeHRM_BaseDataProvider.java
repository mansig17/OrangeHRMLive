package OrangeHRMLive.DataProvider;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;


public class OrangeHRM_BaseDataProvider
{
	public static XSSFWorkbook workbook;
	public static Map <String, Integer> TestScenarioMapping;
	public static XSSFSheet testCasesSheet;
	public void BaseDataProvider() throws IOException
	{
		
		System.out.println("In base provider");
		File file= new File("C:\\Users\\SRIJAN\\OneDrive\\OrangeHRM.xlsx");
		FileInputStream fis= new FileInputStream(file);
		workbook= new XSSFWorkbook(fis);
		
		testCasesSheet= workbook.getSheet("TestCases");
		
		TestScenarioMapping=HashMappings(testCasesSheet);
		
		
	}
	
	//Common function to create mappings for sheets
	public Map<String, Integer> HashMappings(XSSFSheet sheet)
	{
		Map<String, Integer> map= new HashMap<>();
		
		Row firstRow= sheet.getRow(0);
	
		
		for(Cell cell: firstRow)
		{
			map.put(cell.getStringCellValue(), cell.getColumnIndex());
		}
		
		//System.out.print(map);
		return map;
	}
	
	
	public ArrayList<String> getTestCaseNames(XSSFSheet sheet, Map<String, Integer> map, String transaction)
	{
		
		System.out.println("Getting test case names");
		int lastRow= sheet.getLastRowNum();
		//System.out.println(lastRow);
		ArrayList<String> testCaseNames =new ArrayList<String>();  
		Object [][] data;
		int dataCount=0;
		for(int i=1;i<=lastRow;i++)
		{
			 Row currentRow = sheet.getRow(i);
			String transactionName= currentRow.getCell(map.get("Transaction")).toString();
			///System.out.println(transactionName);
			String TestRun= currentRow.getCell(map.get("Run")).toString();
			String TestName= currentRow.getCell(map.get("TestCaseName")).toString();
			
			if((transactionName.equalsIgnoreCase(transaction))&& TestRun.equalsIgnoreCase("yes"))
			{
				testCaseNames.add(TestName.toString());
				//System.out.print(i+" "+testCaseNames);
				
			}
			
		}
			return testCaseNames;
	}
	
	public void getDataforTransaction(XSSFSheet sheet, Map<String, Integer> map, ArrayList<String> testnames)
	{
		int LastRow = sheet.getLastRowNum();
		
		
		
	}

}
