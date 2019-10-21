package tests;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import base.ConfigClass;
import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SNM_NumberPlan;
import snm_pom_classes.SNM_NumberSeries;
import snm_pom_classes.SNM_Number_Analysis_Page;
import utilities.ExecuteCommands;
import utilities.LoggerClass;
import utilities.NumberSeries_Utils;
import utilities.Take_Screenshot;

public class SNM_External_NumberSeries_Test extends ConfigClass
{
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	public SNM_Number_Analysis_Page numAnalysis;
	public SNM_NumberPlan numPlan;
	public SNM_NumberSeries numSeries;
	Logger log = LoggerClass.getLogger("SNM_Internal_NumberSeries_Test");
	WebDriverWait wait = null;
	
/*	@Test
	public void test_create_externalCoordinatedNumbers(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "EC");
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "EC");
		}
	}
	
	@Test
	public void test_create_externalDestinationNumbers(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "ED");
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "ED");
		}
	}
	
	@Test
	public void test_create_leastCostRoutingAccessNumbers(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "LC");
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "LC");
		}
	}
	
	
	@Test
	public void test_create_commonDirectInDialingOperatorNumbers(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "OD");
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "OD");
		}
	}
	
	@Test
	public void test_create_ownNodeNumbers(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "EN");
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "EN");
		}
	}
	
	@Test
	public void test_create_commonPublicDirectoryNumbers(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "CP");
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "CP");
		}
	}
	
	@Test
	public void test_create_accessNumbersForMobExtWithOutAuth(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "R1");
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "R1");
		}
	}
	
	@Test
	public void test_create_accessNumbersForMobExtWithAuth(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "R2");
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "R2");
		}
	}
	
	
	@Test
	public void test_create_accessNumbersForMobExtWithOutAuthAndDialTone(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "R3");
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "R3");
		}
	}
*/	
	
	@Test
	public void test_create_publicDestLeastCostRouting(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "PD");
			
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "PD");
		}
	}
	
	
	
	@Test
	public void test_create_directInwardServiceAccess(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "DI");
			
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "DI");
		}
	}
	
	
	
	@Test
	public void test_create_fictiousDestNumbers(Method method, ITestContext context1) throws Exception
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
			new NumberSeries_Utils().createAnyExternalNumberSeries
			(driver, method.getName(), ipData, snmTests, loginData, log, "DP");
			
			
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
			new NumberSeries_Utils().deleteAnyExternalNumberSeries(
					driver, method.getName(), ipData, snmTests, loginData, log, "DP");
		}
	}
}
