package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import base.ConfigClass;
import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SNM_NumberPlan;
import snm_pom_classes.SNM_NumberSeries;
import snm_pom_classes.SNM_Number_Analysis_Page;
import utilities.NumberSeries_Utils;
import utilities.ExecuteCommands;
import utilities.ExplicitWait;
import utilities.LoggerClass;
import utilities.SelectDropDownValue;
import utilities.Take_Screenshot;

public class SNM_Internal_NumberSeries_Test extends ConfigClass
{
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	public SNM_Number_Analysis_Page numAnalysis;
	public SNM_NumberPlan numPlan;
	public SNM_NumberSeries numSeries;
	Logger log = LoggerClass.getLogger("SNM_Internal_NumberSeries_Test");
	WebDriverWait wait = null;
	
	@Test
	public void test_create_directoryNumbers(Method method, ITestContext context1) throws Exception
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Internal_NumberSeries_Test");
			new NumberSeries_Utils().createAnyInternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "EX");
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		finally
		{
			new NumberSeries_Utils().deleteAnyInternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "EX");
		}
	}
	
	@Test
	public void test_create_commonOperatorNumbers(Method method, ITestContext context1) throws Exception
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Internal_NumberSeries_Test");
			new NumberSeries_Utils().createAnyInternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "OC");
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		finally
		{
			new NumberSeries_Utils().deleteAnyInternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "OC");
		}
	}
	
	@Test
	public void test_create_individualOperatorNumbers(Method method, ITestContext context1) throws Exception
	{
		
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Internal_NumberSeries_Test");
			new NumberSeries_Utils().createAnyInternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "OI");
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		finally
		{
			new NumberSeries_Utils().deleteAnyInternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "OI");
		}
	}
	
	
	@Test
	public void test_create_commonAbbreviatedNumbers(Method method, ITestContext context1) throws Exception
	{		
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Internal_NumberSeries_Test");
			new NumberSeries_Utils().createAnyInternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "AC");
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		finally
		{
			new NumberSeries_Utils().deleteAnyInternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "AC");
		}
	}
	
	@Test
	public void test_create_emergencyOperatorNumbers(Method method, ITestContext context1) throws Exception
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Internal_NumberSeries_Test");
			new NumberSeries_Utils().createAnyInternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "OE");
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		finally
		{
			new NumberSeries_Utils().deleteAnyInternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "OE");
		}
	}
	
	@Test
	public void test_create_individualAbbreviatedNumbers(Method method, ITestContext context1) throws Exception
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Internal_NumberSeries_Test");
			new NumberSeries_Utils().createAnyInternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "AI");
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		finally
		{
			new NumberSeries_Utils().deleteAnyInternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "AI");
		}
	}
	
	@Test
	public void test_create_routeDirectoryNumbers(Method method, ITestContext context1) throws Exception
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Internal_NumberSeries_Test");
			new NumberSeries_Utils().createAnyInternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "RD");
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		finally
		{
			new NumberSeries_Utils().deleteAnyInternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "RD");
		}
	}
	
	@Test
	public void test_create_dialedNumberInfoService(Method method, ITestContext context1) throws Exception
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Internal_NumberSeries_Test");
			new NumberSeries_Utils().createAnyInternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "DN");
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		finally
		{
			new NumberSeries_Utils().deleteAnyInternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "DN");
		}
	}
	
	
	@Test
	public void test_create_pagingNumbers(Method method, ITestContext context1) throws Exception
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] snmCredentials = null;
		String[] testData = null;
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Internal_NumberSeries_Test");
			new NumberSeries_Utils().createAnyInternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "PG");
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		finally
		{
			new NumberSeries_Utils().deleteAnyInternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "PG");
		}
	}
	
	
	@Test
	public void test_create_gatewayRoutingNumbers(Method method, ITestContext context1) throws Exception
	{
		ArrayList<String> list = null;
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		wait = new WebDriverWait(driver, 30);
		
		String[] testData = null;
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Internal_NumberSeries_Test");
			testData = snmTests.getData(method.getName(), 1);
			
			list = new ArrayList<String>();
			list.add(testData[3]);
			new ExecuteCommands(driver).executeCmds(method.getName(), ipData, loginData, list);
			
			new NumberSeries_Utils().createAnyInternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "GR");
			
		}
		catch(Error e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		catch(Exception e)
		{
			new Take_Screenshot().get_Screenshot(driver, method.getName());
			log.fatal(e.toString());
			throw e;
		}
		finally
		{
			new NumberSeries_Utils().deleteAnyInternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "GR");
		}
	}
	
}
