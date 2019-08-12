package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_Main_Page 
{
	@FindBy(xpath="//span[text()=' Logged in as: ']")
	private WebElement loggedInUserAccount;
	
	
	@FindBy(id="Initial_Setup")
	private WebElement initailSetUp;
	
	@FindBy(id="Number_Analysis")
	private WebElement number_Analysis;
	
	@FindBy(id="Telephony")
	private WebElement telephony;
	
	@FindBy(id="Services")
	private WebElement services;
	
	@FindBy(id="System")
	private WebElement system;
	
	@FindBy(id="Tools")
	private WebElement tools;
	
	@FindBy(id="Logs")
	private WebElement logs;
	
	@FindBy(xpath="//h2[contains(text(),'Welcome')]")
	private WebElement welcomeText;
	
	public SNM_Main_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getLoggedInUserAccount()
	{
		return loggedInUserAccount;
	}
	
	public WebElement getWelcomeText() 
	{
		return welcomeText;
	}

	
	@FindBy(linkText="About")
	private WebElement aboutLink;
	
	public WebElement getAboutLink() 
	{
		return aboutLink;
	}

	@FindBy(linkText="User Guide")
	private WebElement userGuideLink;
	
	public WebElement getUserGuideLink() 
	{
		return userGuideLink;
	}
	
	@FindBy(linkText="Site Map")
	private WebElement site_Map;
	
	
	public WebElement getSite_Map() 
	{
		return site_Map;
	}

	public WebElement getNumber_Analysis() 
	{
		return number_Analysis;
	}

	public WebElement getTelephony() 
	{
		return telephony;
	}

	public WebElement getServices() 
	{
		return services;
	}

	public WebElement getSystem() 
	{
		return system;
	}

	public WebElement getTools() 
	{
		return tools;
	}

	public WebElement getLogs() 
	{
		return logs;
	}


	@FindBy(linkText="Logout")
	private WebElement logoutButton;

	public WebElement getLogoutButton() 
	{
		return logoutButton;
	}
}
