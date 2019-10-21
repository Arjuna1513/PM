package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_Number_Analysis_Page 
{
	@FindBy(id="Number_Plan")
	private WebElement Number_Plan_Link;
	
	@FindBy(id="Call_Diversion")
	private WebElement Call_Diversion_Link;
	
	@FindBy(id="Call_Discrimination")
	private WebElement Call_Discrimination_Link;
	
	@FindBy(id="Emergency_Number")
	private WebElement Emergency_Number_Link;
	
	@FindBy(id="Least_Cost_Route")
	private WebElement Least_Cost_Route_Link;
	
	public WebElement getNumber_Plan_Link() 
	{
		return Number_Plan_Link;
	}

	public WebElement getCall_Diversion_Link() 
	{
		return Call_Diversion_Link;
	}

	public WebElement getCall_Discrimination_Link() 
	{
		return Call_Discrimination_Link;
	}

	public WebElement getEmergency_Number_Link() 
	{
		return Emergency_Number_Link;
	}

	public WebElement getLeast_Cost_Route_Link() 
	{
		return Least_Cost_Route_Link;
	}

	public SNM_Number_Analysis_Page(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
	}
}
