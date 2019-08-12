package pm_pom_classes;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Departments 
{
	//add button
	@FindBy(name="onAdd")
	private WebElement addButton;
	
		
	public WebElement getAddButton() 
	{
		return addButton;
	}
	
	//remove button
	@FindBy(name="onRemove")
	private WebElement removeButton;
	
		
	public WebElement getRemoveButton() 
	{
		return removeButton;
	}
	
	//expand button
	@FindBy(name="onExpand")
	private WebElement expandButton;
	
		
	public WebElement getExpandButton() 
	{
		return expandButton;
	}
	
	//collapse button
	@FindBy(name="onCollapse")
	private WebElement collapseButton;
	
		
	public WebElement getCollapseButton() 
	{
		return collapseButton;
	}
	
	//refresh button
	@FindBy(name="onRefresh")
	private WebElement refreshButton;
	
		
	public WebElement getRefreshButton() 
	{
		return refreshButton;
	}
	
	//department search text box
	@FindBy(id="stext")
	private WebElement departmentSearchText;
		
	public WebElement getDepartmentSearchText() 
	{
		return departmentSearchText;
	}
	
	public void setDepartmentSearchText(String departmentSearchText) 
	{
		this.departmentSearchText.sendKeys(departmentSearchText);
	}

	//main department
	@FindBy(xpath="(//span[@class='standartTreeRow'])[1]")
	private WebElement defaultDepartment;
		
	public WebElement getDefaultDepartment() 
	{
		return defaultDepartment;
	}
	
	
	//department name
	@FindBy(id="departmentname")
	private WebElement departmentname;
		
	public WebElement getDepartmentname() 
	{
		return departmentname;
	}
	
	public void setDepartmentname(String departmentName) 
	{
		departmentname.sendKeys(departmentName);
	}
	
	//parent department dropdown
	@FindBy(id="parentdeptname")
	private WebElement parentdeptDropDown;
		
	public WebElement getParentdeptDropDown() 
	{
		return parentdeptDropDown;
	}
	
	//location dropdown
	@FindBy(id="locationname")
	private WebElement locationname;
		
	public WebElement getLocationname() 
	{
		return locationname;
	}
	
	//description
	@FindBy(name="description")
	private WebElement description;
		
	public WebElement getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description.sendKeys(description);
	}
	
	//apply button
	@FindBy(name="onApplyButton")
	private WebElement onApplyButton;
		
	public WebElement getApplyButton() 
	{
		return onApplyButton;
	}
	

}
