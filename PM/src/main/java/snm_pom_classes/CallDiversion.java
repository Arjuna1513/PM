package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CallDiversion 
{
	@FindBy(linkText=" System Call Diversion")
	private WebElement  systemCallDiversion;
	
	@FindBy(linkText=" Customer Call Diversion")
	private WebElement  customerCallDiversion;
	
	public CallDiversion(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
}
