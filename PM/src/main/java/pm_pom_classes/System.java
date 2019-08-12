package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class System
{
	public System(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Location")
	private WebElement location;

	@FindBy(linkText="Subsystem")
	private WebElement subsystem;
	
	@FindBy(linkText="Data Management")
	private WebElement dataManagement;
	
	@FindBy(linkText="Options ")
	private WebElement options;
	
	@FindBy(linkText="Email Server")
	private WebElement emailServer;
	
	@FindBy(linkText="Configuration Wizard")
	private WebElement configurationWizard;
	
	@FindBy(linkText="Batch Operation")
	private WebElement batchOperation;
	
	@FindBy(linkText="Password Settings ")
	private WebElement passwordSettings ;

	public WebElement getLocation() 
	{
		return location;
	}

	public WebElement getSubsystem() 
	{
		return subsystem;
	}

	public WebElement getDataManagement() 
	{
		return dataManagement;
	}

	public WebElement getOptions() 
	{
		return options;
	}

	public WebElement getEmailServer() 
	{
		return emailServer;
	}

	public WebElement getConfigurationWizard() 
	{
		return configurationWizard;
	}

	public WebElement getBatchOperation() 
	{
		return batchOperation;
	}

	public WebElement getPasswordSettings() 
	{
		return passwordSettings;
	}
	
}
