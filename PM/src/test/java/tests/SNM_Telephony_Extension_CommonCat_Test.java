package tests;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.ConfigClass;
import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SNM_TelephonyExtensionsPage;
import snm_pom_classes.SNM_TelephonyPage;
import snm_pom_classes.SNM_Telephony_Extensions_CSP_Page;
import snm_pom_classes.SNM_Telephony_Extensions_CommonCategory;
import utilities.CleanUP;
import utilities.LoggerClass;
import utilities.ReusableUnits;
import utilities.Take_Screenshot;

public class SNM_Telephony_Extension_CommonCat_Test extends ConfigClass
{
	public SNM_Login_Page snmLogin;
	public SNM_Main_Page snmMainPage;
	Logger log = LoggerClass.getLogger("SNM_Telephony_Extension_CommonCat_Test");
	public SNM_TelephonyPage snmTelephony;
	WebDriverWait wait = null;
	public SNM_TelephonyExtensionsPage telExt;
	public SNM_Telephony_Extensions_CommonCategory snmCAT;
	
	@Test
	public void test_create_CAT(Method method) throws Exception
	{
		String[] snmCredentials = loginData.getData("test_snm_valid_login", 1);;
		String[] testData = snmTests.getData(method.getName(), 1);
		
		try
		{
			snmTests.checkTestStatus(method.getName());
			LoggerClass.markTestBeginning(method.getName(), "SNM_Telephony_Extension_CommonCat_Test");
			new ReusableUnits(driver).createWorkingCAT(driver, method.getName(), 
					ipData, snmTests, loginData, log, testData[0]);
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
//			new CleanUP(driver).deleteCSP(driver, ipData, loginData,testData[0], log);
		}
	}
	
	
	
}
