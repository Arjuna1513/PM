package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Administrators 
{
	@FindBy(linkText="Administrator")
	private WebElement administrator;

	public WebElement getAdministrator() 
	{
		return administrator;
	}
	
	public Administrators(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
}
