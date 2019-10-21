package snm_pom_classes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SNM_LeastCostRouting 
{
	@FindBy(linkText=" Least Cost Routing")
	private WebElement leastCostRouting;
	
	public SNM_LeastCostRouting(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
}
