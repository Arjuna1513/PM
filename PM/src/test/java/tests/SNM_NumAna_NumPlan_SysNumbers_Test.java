package tests;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.ConfigClass;
import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SNM_NumAna__Num_Plan_SystemNumbers_Page;
import snm_pom_classes.SNM_NumberPlan;
import snm_pom_classes.SNM_NumberSeries;
import snm_pom_classes.SNM_Number_Analysis_Page;
import utilities.CleanUP;
import utilities.ExplicitWait;
import utilities.LoggerClass;
import utilities.Take_Screenshot;

public class SNM_NumAna_NumPlan_SysNumbers_Test extends ConfigClass
{
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	public SNM_Number_Analysis_Page numAnalysis;
	public SNM_NumberPlan numPlan;
	public SNM_NumberSeries numSeries;
	public SNM_NumAna__Num_Plan_SystemNumbers_Page sysNumbers;
	Logger log = LoggerClass.getLogger("SNM_NumAna_NumPlan_SysNumbers_Test");
	@Test
	public void test_addSystemNumbers(Method method) throws Exception
	{
		snmLogin = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		numAnalysis = new SNM_Number_Analysis_Page(driver);
		numPlan = new SNM_NumberPlan(driver);
		numSeries = new SNM_NumberSeries(driver);
		sysNumbers = new SNM_NumAna__Num_Plan_SystemNumbers_Page(driver);
		snmLogin = new SNM_Login_Page(driver);
		
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);
		String[] testData = snmTests.getData(method.getName(), 1);
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 5);
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_NumAna_NumPlan_SysNumbers_Test");
			driver.get(ipData.getData(1, 0));
			snmLogin.snm_login(snmCredentials[0], snmCredentials[1]);
			snmMainPage.getNumber_Analysis().click();;
			numAnalysis.getNumber_Plan_Link().click();
			numPlan.getSystemNumbers().click();
			sysNumbers.setInternationalPrefix(testData[0]);
			sysNumbers.setCountryCode(testData[0]);
			sysNumbers.setNationalPrefix(testData[0]);
			sysNumbers.getApplyButton().click();
			wait.until(ExpectedConditions.textToBePresentInElement(sysNumbers.getResponseMessage(), sysNumbers.getMessage()));
			sysNumbers.getDoneButton().click();
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
			log.info(e.toString());
			throw e;
		}
		finally
		{
			new CleanUP(driver).removeSystemNumbers(driver, method.getName(), loginData, ipData);
		}
	}
}
