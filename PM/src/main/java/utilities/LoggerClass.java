package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class LoggerClass 
{
	public static Logger log = null;
	
	public static void testBeginning(String testCaseName)
	{
		log = Logger.getLogger(testCaseName);
		DOMConfigurator.configure("log4j.xml");
		log.info("========= Beginning of testcase :"+testCaseName+" ===========");
	}
	
	public static void testEnd(String testCaseName)
	{
		log = Logger.getLogger(testCaseName);
		DOMConfigurator.configure("log4j.xml");
		log.info("========= End of testcase :"+testCaseName+" ===========");
	}
	
	public static void info(String message, String name)
	{
		log = Logger.getLogger(name);
		DOMConfigurator.configure("log4j.xml");
		log.info(message);
	}

	
	public static void warning(String message, String name)
	{
		log = Logger.getLogger(name);
		DOMConfigurator.configure("log4j.xml");
		log.warn(message);
	}
	
	public static void error(String message, String name)
	{
		log = Logger.getLogger(name);
		DOMConfigurator.configure("log4j.xml");
		log.error(message);
	}
	
	
	public static void fatal(String message, String name)
	{
		log = Logger.getLogger(name);
		DOMConfigurator.configure("log4j.xml");
		log.fatal(message);
	}
}
