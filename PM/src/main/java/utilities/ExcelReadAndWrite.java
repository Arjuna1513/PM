package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.testng.Reporter;

public class ExcelReadAndWrite 
{
	
	File file;
	FileInputStream fis;
	Workbook book;
	Sheet sheet;
	Map<String, Sheet> allInOneData;
	
	
	
	public void fetchExcelData(String sheet, String excelFilePath)
	{
		file = new File(excelFilePath);
		try 
		{
			fis = new FileInputStream(file);
		
			if(excelFilePath.substring(excelFilePath.indexOf(".")).equals(".xlsx"))
			{
//				book = new XSSFWorkbook(fis);	
			}
			else if(excelFilePath.substring(excelFilePath.indexOf(".")).equals(".xls"))
			{
				book = new HSSFWorkbook(fis);	
			}
			else
			{
				Reporter.log("File is neither of type .xlsx or .xls, please check the type of the file",true);
			}
				this.sheet = book.getSheet(sheet);
		}		
		catch(FileNotFoundException e)
		{
			Reporter.log(e.getMessage());
		}
		catch(IOException e)
		{
			Reporter.log(e.getMessage());	
		}
	}
		
	
	public int getTotalRows()
	{
		return sheet.getPhysicalNumberOfRows();
	}
	
	public String getData(int row, int cell)
	{
		//System.out.println("Before fetching flag value");
		return sheet.getRow(row).getCell(cell).getStringCellValue();
		
	}
	
	public String getTestCaseFlag(String path, int cell,String testName)
	{
		for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++)
		{
			if(sheet.getRow(i).getCell(0).getStringCellValue().trim().equalsIgnoreCase(testName))
			{
				System.out.println("Match Found");
				return sheet.getRow(i).getCell(cell).getStringCellValue();
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public void fetchExcelData(ArrayList<String> list, String excelFilePath)
	{
		file = new File(excelFilePath);
		try 
		{
			fis = new FileInputStream(file);
		
			if(excelFilePath.substring(excelFilePath.indexOf(".")).equals(".xlsx"))
			{
				book = new XSSFWorkbook(fis);	
			}
			else if(excelFilePath.substring(excelFilePath.indexOf(".")).equals(".xls"))
			{
				book = new HSSFWorkbook(fis);	
			}
			else
			{
				Reporter.log("File is neither of type .xlsx or .xls, please check the type of the file",true);
			}
			
			for(int i=0; i<list.size(); i++)
			{
				sheet = book.getSheet(list.get(i));
				allInOneData.put(list.get(i), sheet);
			}
		}		
		catch(FileNotFoundException e)
		{
			Reporter.log(e.getMessage());
		}
		catch(IOException e)
		{
			Reporter.log(e.getMessage());	
		}
	}
		
	
	public int getTotalRows()
	{
		return sheet.getPhysicalNumberOfRows();
	}
	
	public String getData(Sheet sheetName, int row, int cell)
	{
		return sheet.getRow(row).getCell(cell).getStringCellValue();
	}*/
}
