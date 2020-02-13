package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.ConfigClass;
import pm_pom_classes.PM_User;
import utilities.ExecuteCommands;
import utilities.ReusableUnits_Analog_Digital;
import utilities.Take_Screenshot;

public class TrialAndError extends ConfigClass
{
	File f = null;
	Workbook book = null;
	org.apache.poi.ss.usermodel.Sheet sheet = null;
	Row row = null;
	Cell cell = null;
	FileInputStream fis = null;
	HashMap<String, String> map = null;
	
	
	@DataProvider(name="data")
	public Object[][] DataProvider(Method method) throws Exception
	{
		map = new HashMap<String, String>();
		 map.clear();
		String[] testData = null;
		try
		{
			f = new File(excelPath);
			fis = new FileInputStream(f);
			book = new XSSFWorkbook(fis);
			sheet = book.getSheet("logindata");
			int ttlRows = sheet.getLastRowNum();
			for(int i=1; i<=ttlRows; i++)
			{
				/*int ttlCells = sheet.getRow(i).getLastCellNum();
				for(int j=0; j<ttlCells; j++)
				{
					String value = sheet.getRow(i).getCell(j).getStringCellValue().trim();
					System.out.println(value);
				}*/
				String value1 = sheet.getRow(i).getCell(0).getStringCellValue().trim();
				String value2 = sheet.getRow(i).getCell(1).getStringCellValue().trim();
//				System.out.println(value1 +" : "+value2);
				map.put(value1, value2);
			}
			System.out.println("Size of Map is : "+map.size());
		}
		catch(Error e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		catch(Exception e)
		{
				new Take_Screenshot().get_Screenshot(driver, method.getName());
				throw e;
		}
		finally
		{
			
		}
		  Object[][] obj = new Object[1][1]; 
			obj[0][0] = map; 
			return obj;
		
	}
		
		
	@Test(dataProvider="data")
	public void test_method1(HashMap<String,String> map, Method method) throws Exception
	{
		Set<String> set = map.keySet();
		Collection<String> vals = map.values();
		
	}
	
}
