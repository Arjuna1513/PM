package utilities;

import java.io.File;
import java.io.IOException;

//import org.apache.commons.io.FilenameUtils;
//import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;

public class Take_Screenshot 
{
	public void get_Screenshot(WebDriver driver, String fileName) throws IOException
	{
		System.out.println("I am in screenshot method");
		System.out.println("The value of driver instance is :"+ driver );
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
//		File dest = new File(System.getProperty("user.dir").replace("\\", "\\"+"\\")+"\\ScreenShots\\"+fileName+".png");
		File dest = new File(System.getProperty("user.dir")+"/ScreenShots/"+fileName+".png");
		System.out.println(System.getProperty("user.dir"));
//		FileUtils.copyFile(src, dest);
		FileHandler.copy(src, dest);
		System.out.println("Done with screen shot");
	}
	
	
	/*public void takeScreenShot_upon_testFail(WebDriver driver,) throws IOException
	{
//		driver.close();
		if(result.getStatus() == 2)
		{
			String methodName = result.getMethod().getMethodName();
			new Take_Screenshot().get_Screenshot(driver, methodName);
		}
	}*/
}
