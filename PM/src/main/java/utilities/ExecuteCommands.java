package utilities;

import java.util.ArrayList;
import java.util.Set;

import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pm_pom_classes.PM_User;
import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SNM_SystemPage;
import snm_pom_classes.SNM_ToolsPage;

public class ExecuteCommands 
{
	WebDriver driver;
	public PM_User pmUser;
	public SNM_Login_Page snmLoginPage;
	public SNM_Main_Page snmMainPage;
	public SNM_SystemPage snmSystempage;
	public SNM_ToolsPage toolsPage;
	public String title = "Command Line Interface";
	WebDriverWait wait;
	
	public ExecuteCommands(WebDriver driver)
	{
		this.driver = driver;
		pmUser = new PM_User(driver);
		snmLoginPage = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmSystempage = new SNM_SystemPage(driver);
		toolsPage = new SNM_ToolsPage(driver);
		wait = new WebDriverWait(driver, 100);
	}
	
	public void executeCmds(String methodName, ExcelReadAndWrite ipData, ExcelReadAndWrite loginData, ArrayList<String> pmTestData) throws InterruptedException
	{
		driver.get(ipData.getData(1, 0));
		String[] credentials = loginData.getData("test_snm_valid_login", 1);
		snmLoginPage.snm_login(credentials[0], credentials[1]);
		snmMainPage.getTools().click();
		String parentWindow = driver.getWindowHandle();
		toolsPage.getCommandLine().click();
		Thread.sleep(2000);
		Set<String> windows = driver.getWindowHandles();
		for(String window : windows)
		{
			if(!window.equals(parentWindow))
			{
				driver.switchTo().window(window);
				if(driver.getTitle().trim().equals(title))
				{
					driver.switchTo().frame(toolsPage.getcmdFrame());
					for(String cmd : pmTestData)
					{
						toolsPage.getCommandField().sendKeys(cmd);
//						wait.until(ExpectedConditions.elementToBeClickable(By.name("cmdApply")));
						toolsPage.getCmdApply().click();
						wait.until(ExpectedConditions.elementToBeClickable(By.name("cmdApply")));
						Thread.sleep(1000);
					}
					driver.close();
				}
			}
		}
		driver.switchTo().window(parentWindow);
		pmUser.getLogoutLink().click();
	}
}
