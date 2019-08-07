package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;

public class StoreAndRetreiveDataFromMap 
{	
	public Map<String, Map<String, String>> excelData;
	public Map<String, String> data;
	public Workbook book;
	public Sheet sheet;
	
	public void storeDataToMap(String excelPath, ArrayList<String> list)
	{
		Reporter.log("I am in Map method", true);
		excelData = new HashMap<String, Map<String, String>>();
		data = new HashMap<String, String>();
		File file = new File(excelPath);
		try 
		{
			FileInputStream fis = new FileInputStream(file);
			if(excelPath.substring(excelPath.indexOf(".")).equals(".xlsx"))
			{
				book = new XSSFWorkbook(fis);
			}
			else if(excelPath.substring(excelPath.indexOf(".")).equals(".xls"))
			{
				book = new HSSFWorkbook(fis);
			}
			else
			{
				Reporter.log("File is neither of type .xlsx or .xls. Please check the file type");
			}
			
			for(String keyData : list)
			{
				sheet = book.getSheet(keyData);
				int totalRows = sheet.getPhysicalNumberOfRows();
				for(int i=1; i<totalRows; i++)
				{
					String cellKey = sheet.getRow(i).getCell(0).getStringCellValue().trim();
					String cellValue = sheet.getRow(i).getCell(1).getStringCellValue().trim();
					data.put(cellKey, cellValue);
				}
			excelData.put(keyData, data);
			}
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/*public String getMapData(String excelPath, String sheetName, String key)
	{
		 Map<String, Map<String, String>> data = storeDataToMap(excelPath, sheetName);
		 Map<String, String> data1 = data.get(sheetName);
		 String value=data1.get(key);
		 return value;		 
	}*/
}
