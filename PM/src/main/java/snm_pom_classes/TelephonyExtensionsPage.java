package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TelephonyExtensionsPage 
{
	public TelephonyExtensionsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//account code
	@FindBy(id="Account_Code")
	private WebElement accountCode;
	
	public WebElement getAccountCode()
	{
		return accountCode;
	}
	
	//common category
	@FindBy(id="Common_Category")
	private WebElement commonCategory;
	
	public WebElement getCommonCategory()
	{
		return commonCategory;
	}
	
	//CSP
	@FindBy(id="CSP")
	private WebElement CSP;
	
	public WebElement getCSP()
	{
		return CSP;
	}
	
	//common abbreviated number
	@FindBy(id="Common_Abbreviated_Number")
	private WebElement commonAbbreviatedNumber;
	
	public WebElement getCommonAbbreviatedNumber()
	{
		return commonAbbreviatedNumber;
	}
	
	//CommonAuthorizationCode
	@FindBy(id="CommonAuthorizationCode")
	private WebElement commonAuthorizationCode;
	
	public WebElement getCommonAuthorizationCode()
	{
		return commonAuthorizationCode;
	}
	
	//MobileExtensionSupport
	@FindBy(id="MobileExtensionSupport")
	private WebElement mobileExtensionSupport;
	
	public WebElement getMobileExtensionSupport()
	{
		return mobileExtensionSupport;
	}
	
	//Delay_Seizure_List
	@FindBy(id="Delay_Seizure_List")
	private WebElement delay_Seizure_List;
	
	public WebElement getDelay_Seizure_List()
	{
		return delay_Seizure_List;
	}
}
