package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LoggerClass 
{
	private static Logger log = null;
	
	public static Logger testBeginning(String testCaseName)
	{
		if(log==null)
		{
			log = Logger.getLogger(testCaseName);
			DOMConfigurator.configure("log4j.xml");
			log.info("========= Beginning of testcase :"+testCaseName+" ===========");
			return log;
		}
		return log;
	}
	
	public static Logger testEnd(String testCaseName)
	{
		if(log==null)
		{
			log = Logger.getLogger(testCaseName);
			DOMConfigurator.configure("log4j.xml");
			log.info("========= End of testcase :"+testCaseName+" ===========");
			return log;
		}
		return log;
	}
	
	public static Logger info(String message, String name)
	{
		if(log==null)
		{
			log = Logger.getLogger(name);
			DOMConfigurator.configure("log4j.xml");
			log.info(message);
			return log;
		}
		return log;
	}

	
	public static Logger warning(String message, String name)
	{
		if(log==null)
		{
			log = Logger.getLogger(name);
			DOMConfigurator.configure("log4j.xml");
			log.warn(message);
			return log;
		}
		return log;
	}
	
	public static Logger error(String message, String name)
	{
		if(log==null)
		{
			log = Logger.getLogger(name);
			DOMConfigurator.configure("log4j.xml");
			log.error(message);
			return log;
		}
		return log;
	}
	
	
	public static Logger fatal(String message, String name)
	{
		if(log==null)
		{
			log = Logger.getLogger(name);
			DOMConfigurator.configure("log4j.xml");
			log.fatal(message);
			return log;
		}
		return log;
	}
}
