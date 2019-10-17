package base;

import java.io.IOException;

import javax.swing.text.Utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utilities.Take_Screenshot;

public class TestListener implements ITestListener
{
	WebDriver driver = null;

	public void onFinish(ITestContext arg0) 
	{}

	public void onStart(ITestContext arg0) {}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {	}

//	@Override
	public void onTestFailure(ITestResult result) 
	{	
		/*String methodName = result.getMethod().getMethodName().trim();
		try 
		{
			ITestContext context = result.getTestContext();
			driver = (WebDriver)context.getAttribute("WebDriver");
			new Take_Screenshot().get_Screenshot(driver, methodName);
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}*/
	}

	public void onTestSkipped(ITestResult arg0) {	}

	public void onTestStart(ITestResult arg0) {	}

	public void onTestSuccess(ITestResult arg0) {}

}
