package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Logs
{
	public Logs(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Audit Trail")
	private WebElement auditTrail;
	
	@FindBy(linkText="Events")
	private WebElement events;
	
	@FindBy(linkText="Security")
	private WebElement security;

	public WebElement getAuditTrail() 
	{
		return auditTrail;
	}

	public WebElement getEvents() 
	{
		return events;
	}

	public WebElement getSecurity() 
	{
		return security;
	}
}
