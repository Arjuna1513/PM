package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_NumberPlan 
{
	@FindBy(id="Number_Series")
	private WebElement  numberSeries;
	
	@FindBy(id="Service_Codes")
	private WebElement  serviceCodes;
	
	@FindBy(id="External_NumberLength")
	private WebElement  externalNumberLength;
	
	@FindBy(id="Number_Conversion")
	private WebElement  NumberConversion;
	
	@FindBy(id="Number_Conversion_Upload")
	private WebElement  numberConversionUpload;
	
	@FindBy(id="System_Number")
	private WebElement  systemNumbers;
	
	
	public WebElement getNumberSeries() 
	{
		return numberSeries;
	}

	public WebElement getServiceCodes() 
	{
		return serviceCodes;
	}

	public WebElement getExternalNumberLength() 
	{
		return externalNumberLength;
	}

	public WebElement getNumberConversion() 
	{
		return NumberConversion;
	}

	public WebElement getNumberConversionUpload() 
	{
		return numberConversionUpload;
	}

	public WebElement getSystemNumbers() 
	{
		return systemNumbers;
	}

	public SNM_NumberPlan(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
}
