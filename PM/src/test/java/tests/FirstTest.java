package tests;

import org.testng.annotations.Test;

import base.ConfigClass;

public class FirstTest extends ConfigClass
{
	@Test
	public void myTest()
	{
		System.out.println("Hello Jenkins");
	}
}
