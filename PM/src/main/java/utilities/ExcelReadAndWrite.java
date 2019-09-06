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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;
import org.testng.SkipException;

public class ExcelReadAndWrite 
{
	
	File file;
	FileInputStream fis;
	Workbook book;
	Sheet sheet;
	Map<String, Sheet> allInOneData;
	
	public ExcelReadAndWrite(String sheet, String excelFilePath)
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
	
	public String[] getData(String methodName,int cell)
	{
		String[] values = null;
		int ttlRows = getTotalRows();
		for(int i=1; i<ttlRows; i++)
		{
			if(sheet.getRow(i).getCell(0).getStringCellValue().trim().equalsIgnoreCase(methodName))
			{
				values = sheet.getRow(i).getCell(cell).getStringCellValue().trim().split(",");
				return values;
			}
		}
		return values;
		
	}
	
	
	public String getData(int row, int cell)
	{
		String value = sheet.getRow(row).getCell(cell).getStringCellValue().trim();
		System.out.println("Fetchec value is : "+value);
		return value;
	}

	public void checkTestStatus(String methodName)
	{
		String flag = null;
		try
		{
		for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++)
		{
			if(sheet.getRow(i).getCell(0).getStringCellValue().trim().equalsIgnoreCase(methodName))
			{
				System.out.println(sheet.getRow(i).getCell(1).getStringCellValue().trim());
				flag = sheet.getRow(i).getCell(2).getStringCellValue();
				if(flag.equalsIgnoreCase("N"))
				{
					System.out.println(flag);
					throw new SkipException("TestCase "+methodName+" is Skipped because flag is set to NO");
				}
			}
		}
		}
		catch(Exception e)
		{
//			System.out.println("No Idea, why I have written this method, it started throwing error for no reason");
		}
	}
	
	public String checkTestStatusToCleanData(String methodName)
	{
		String flag = null;
		try
		{
		for(int i=1; i<sheet.getPhysicalNumberOfRows(); i++)
		{
			if(sheet.getRow(i).getCell(0).getStringCellValue().trim().equalsIgnoreCase(methodName))
			{
				flag = sheet.getRow(i).getCell(2).getStringCellValue().trim();
			}
		}
		}
		catch(Exception e)
		{
//			System.out.println("No Idea, why I have written this method, it started throwing error for no reason");
		}
		return flag;
	}
}
