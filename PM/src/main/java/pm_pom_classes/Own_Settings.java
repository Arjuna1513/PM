package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Own_Settings 
{
	public Own_Settings(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Extensions")
	private WebElement extensions;
	
	@FindBy(linkText="General")
	private WebElement general;

	public WebElement getExtensions() 
	{
		return extensions;
	}

	public WebElement getGeneral() 
	{
		return general;
	}
	
}
