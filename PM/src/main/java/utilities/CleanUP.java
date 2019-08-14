package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import junit.framework.Assert;
import pm_pom_classes.PM_Login_Page;
import pm_pom_classes.PM_Main_Page;
import pm_pom_classes.PM_User;
import pm_pom_classes.PM_Users;

public class CleanUP 
{
	public PM_Login_Page pmLoginPage;
	public PM_Main_Page pmMainPage;
	public PM_User pmUser;
	public PM_Users pmUsers;
	
	public void deleteUser(WebDriver driver, ExcelReadAndWrite ipData, String[] data, String[] testData)
	{
		pmMainPage = new PM_Main_Page(driver);
		pmLoginPage = new PM_Login_Page(driver);
		pmUser = new PM_User(driver);
		pmUsers = new PM_Users(driver);
		driver.get(ipData.getData(0, 0));
		pmLoginPage.PM_Login(data[0], data[1]);
		pmMainPage.getUsers().click();
		pmUsers.getUser().click();
		pmUser.setUserSearchTextBox(testData[0]);
		pmUser.getOnViewRangeButton().click();
		driver.findElement(By.xpath("(//td[contains(text(),'"+testData[0]+"')]//preceding-sibling::td)[8]")).click();
		driver.switchTo().alert().accept();
		Assert.assertEquals(pmUser.getResponseMessage().getText().trim(), "Remove operation successful for:");
		pmUser.getLogoutLink().click();
	}
}
