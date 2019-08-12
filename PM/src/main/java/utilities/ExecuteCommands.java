package utilities;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import snm_pom_classes.SNM_Login_Page;
import snm_pom_classes.SNM_Main_Page;
import snm_pom_classes.SystemPage;
import snm_pom_classes.ToolsPage;

public class ExecuteCommands 
{
	WebDriver driver;
	public SNM_Login_Page snmLoginPage;
	public SNM_Main_Page snmMainPage;
	public SystemPage snmSystempage;
	public ToolsPage toolsPage;
	
	public ExecuteCommands(WebDriver driver)
	{
		this.driver = driver;
		snmLoginPage = new SNM_Login_Page(driver);
		snmMainPage = new SNM_Main_Page(driver);
		snmSystempage = new SystemPage(driver);
		toolsPage = new ToolsPage(driver);
	}
	
	public void executeCmds(String url, ArrayList<String> cmds) throws InterruptedException
	{
		driver.get(url);
		snmLoginPage.snm_login("", "");
		snmMainPage.getTools().click();
		String parentWindow = driver.getWindowHandle();
		toolsPage.getCommandLine().click();
		Thread.sleep(2000);
		Set<String> windows = driver.getWindowHandles();
		for(String window : windows)
		{
			while(!window.equals(parentWindow))
			{
				driver.switchTo().window(window);
				if(driver.getTitle().trim().equals(""))
				{
					driver.switchTo().frame(toolsPage.getcmdFrame());
					for(String cmd : cmds)
					{
						toolsPage.getCommandField().sendKeys(cmd);
						toolsPage.getCmdApply().click();
						Thread.sleep(1000);
					}
				}
			}
		}
	}
}
