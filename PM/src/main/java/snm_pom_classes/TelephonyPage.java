package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TelephonyPage 
{
	@FindBy(linkText="Extensions")
	private WebElement extensions;
	
	@FindBy(linkText="Operator")
	private WebElement operator;
	
	@FindBy(linkText="Call Center")
	private WebElement callCenter;
	
	@FindBy(linkText="Groups")
	private WebElement groups;
	
	@FindBy(linkText="External Lines")
	private WebElement externalLines;
	
	@FindBy(linkText="System Data")
	private WebElement systemData;
	
	@FindBy(linkText="IP Phone")
	private WebElement iPPhone;
	
	@FindBy(linkText="DECT")
	private WebElement DECT;
	
	public TelephonyPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getExtensions() {
		return extensions;
	}

	public WebElement getOperator() {
		return operator;
	}

	public WebElement getCallCenter() {
		return callCenter;
	}

	public WebElement getGroups() {
		return groups;
	}

	public WebElement getExternalLines() {
		return externalLines;
	}

	public WebElement getSystemData() {
		return systemData;
	}

	public WebElement getiPPhone() {
		return iPPhone;
	}

	public WebElement getDECT() {
		return DECT;
	}	
	
	
}
