package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class PM_Main_Page
{
	@FindBy(id="Users")
	private WebElement users;
	
	@FindBy(id="Services")
	private WebElement services;
	
	@FindBy(id="Administrators")
	private WebElement administrators;
	
	@FindBy(id="System")
	private WebElement system;
	
	@FindBy(id="Logs")
	private WebElement logs;
	
	@FindBy(id="Own_Settings")
	private WebElement own_Settings;
	
	@FindBy(className="minorLinks")
	private WebElement loggedIn;
	
	@FindBy(xpath="//a[text()='About']")
	private WebElement aboutLink;
	
	@FindBy(xpath="//a[text()='User Guide']")
	private WebElement userGuideLink;
	
	@FindBy(xpath="//a[text()='Site Map']")
	private WebElement siteMapLink;
	
	@FindBy(xpath="//a/b[text()=' Logout']")
	private WebElement logoutLink;

	public WebElement getUsers() 
	{
		return users;
	}

	public WebElement getServices() 
	{
		return services;
	}

	public WebElement getAdministrators() 
	{
		return administrators;
	}

	public WebElement getSystem() 
	{
		return system;
	}

	public WebElement getLogs() 
	{
		return logs;
	}

	public WebElement getOwn_Settings() 
	{
		return own_Settings;
	}

	public WebElement getLoggedIn() 
	{
		return loggedIn;
	}

	public WebElement getAboutLink() 
	{
		return aboutLink;
	}

	public WebElement getUserGuideLink() 
	{
		return userGuideLink;
	}

	public WebElement getSiteMapLink() 
	{
		return siteMapLink;
	}

	public WebElement getLogoutLink() 
	{
		return logoutLink;
	}
	
	public PM_Main_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
}
