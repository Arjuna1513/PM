package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PM_Users
{
	@FindBy(linkText="User")
	private WebElement user;
	
	@FindBy(linkText="Departments")
	private WebElement departments;
	
	@FindBy(linkText="UDF Mapping")
	private WebElement udfMapping;
	
	@FindBy(linkText="Unlock")
	private WebElement unlockUser;

	public WebElement getUser() 
	{
		return user;
	}

	public WebElement getDepartments() 
	{
		return departments;
	}

	public WebElement getUdfMapping() 
	{
		return udfMapping;
	}

	public WebElement getUnlockUser() 
	{
		return unlockUser;
	}
	
	public PM_Users(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
}
