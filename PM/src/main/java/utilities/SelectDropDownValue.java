package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectDropDownValue 
{
	Select sel;
	public void selectByIndex(WebElement ele, int index)
	{
		sel = new Select(ele);
		sel.selectByIndex(index);
	}
	
	public void selectByVisibleText(WebElement ele, String dropDownValue)
	{
		sel = new Select(ele);
		sel.selectByVisibleText(dropDownValue);		
	}
	
	public void selectByValue(WebElement ele, String value)
	{
		sel = new Select(ele);
		sel.selectByValue(value);
	}
	
	public String getIndexValue(WebElement ele)
	{
		sel = new Select(ele);
		return sel.getFirstSelectedOption().getText();
	}
	
	
}
