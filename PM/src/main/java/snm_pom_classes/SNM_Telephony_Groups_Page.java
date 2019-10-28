package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_Telephony_Groups_Page 
{
	public SNM_Telephony_Groups_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="GroupDoNotDisturb")
	private WebElement DNDLink;
	public WebElement getDNDLink()
	{
		return DNDLink;
	}
	
	@FindBy(id="Final_Customer_Group")
	private WebElement customerLink;
	public WebElement getCustomerLink()
	{
		return customerLink;
	}
	
	
	@FindBy(id="HuntGroup")
	private WebElement huntGroupLink;
	public WebElement getHuntGroupLink()
	{
		return huntGroupLink;
	}
	
	
	@FindBy(id="HuntGroupMember")
	private WebElement huntGroupMemberMgrLink;
	public WebElement getHuntGroupMemberMgrLink()
	{
		return huntGroupMemberMgrLink;
	}
	
	@FindBy(id="CallPickupGroup")
	private WebElement callPickUpGrpLink;
	public WebElement getCallPickUpGrpLink()
	{
		return callPickUpGrpLink;
	}
	
	@FindBy(id="ExtensionGroupSystem")
	private WebElement extGrpSystem;
	public WebElement getExtGrpSystem()
	{
		return extGrpSystem;
	}
}
