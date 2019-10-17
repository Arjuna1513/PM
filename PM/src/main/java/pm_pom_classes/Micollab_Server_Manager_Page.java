package pm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Micollab_Server_Manager_Page 
{
	public Micollab_Server_Manager_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//frame[@name='navigation']")
	private WebElement navigationFrame;
	
	public WebElement getInToNavigationFrame()
	{
		return navigationFrame;
	}
	
	@FindBy(name="header")
	private WebElement headerFrame;
	
	public WebElement getHeaderFrame()
	{
		return headerFrame;
	}
	
	@FindBy(xpath="//a[text()='Users and Services']")
	private WebElement usersAndServicesLink;
	
	public WebElement getUsersAndServicesLink()
	{
		return usersAndServicesLink;
	}
	
	@FindBy(name="main")
	private WebElement mainFrame;
	
	public WebElement getMainFrame()
	{
		return mainFrame;
	}
	
	@FindBy(xpath="//input[@name='basicSearchTerm']")
	private WebElement userSearchTextBox;
	
	public WebElement getUserSearchTextBox()
	{
		return userSearchTextBox;
	}
	
	@FindBy(id="basicSearchButton_label")
	private WebElement searchButton;
	
	public WebElement getSearchButton()
	{
		return searchButton;
	}
	
	@FindBy(name="firstNameDisplay")
	private WebElement firstNameTextBox;
	
	public WebElement getFirstNameTextBox()
	{
		return firstNameTextBox;
	}
	
	@FindBy(name="lastNameDisplay")
	private WebElement lastNameTextBox;
	
	public WebElement getLastNameTextBox()
	{
		return lastNameTextBox;
	}
	
	@FindBy(name="uccBundle")
	private WebElement uccBundleDropDown;
	
	public WebElement getUccBundleDropDown()
	{
		return uccBundleDropDown;
	}
	
	@FindBy(name="emailAddress")
	private WebElement emailAddress;
	
	public WebElement getEmailAddress()
	{
		return emailAddress;
	}
	
	@FindBy(id="phones_0")
	private WebElement phoneTab;
	
	public WebElement getPhoneTab()
	{
		return phoneTab;
	}
	
	@FindBy(id="cancel_label")
	private WebElement cancelButton;
	
	public WebElement getCancelButton()
	{
		return cancelButton;
	}
	
	@FindBy(name="phones[4].sipPassword")
	private WebElement sipPassword;
	
	public WebElement getSipPassword()
	{
		return sipPassword;
	}
	
	@FindBy(name="phones[4].confirmSipPassword")
	private WebElement sipConfirmPassword;
	
	public WebElement getSipConfirmPassword()
	{
		return sipConfirmPassword;
	}
	
	@FindBy(xpath="//a[text()='MiCollab Client']")
	private WebElement micollabClientTab;
	
	public WebElement getMicollabClientTab()
	{
		return micollabClientTab;
	}
	
	@FindBy(xpath="//a[@href='/server-common/cgi-bin/logout']")
	private WebElement logoutLink;
	
	public WebElement getLogoutLink()
	{
		return logoutLink;
	}
	
	@FindBy(linkText="MiCollab Configuration")
	private WebElement micoConfigurationTab;
	
	public WebElement getMicollabConfigTab()
	{
		return micoConfigurationTab;
	}
}
