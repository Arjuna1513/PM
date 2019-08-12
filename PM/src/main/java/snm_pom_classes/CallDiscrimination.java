package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CallDiscrimination 
{
	@FindBy(linkText=" Group Names")
	private WebElement  groupNames;
	
	@FindBy(linkText=" Permitted Numbers")
	private WebElement permittedNumbers;
	
	public CallDiscrimination(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
}
