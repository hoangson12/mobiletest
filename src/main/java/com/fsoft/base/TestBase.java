package com.fsoft.base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class TestBase {
	public static AppiumDriver<WebElement> driver;
	public static int durShort = 300;
	public static int durLong = 3000;
	
	public WebElement element(String xpath)
	{
		return driver.findElement(By.xpath(xpath));
	}
	
	public List<WebElement> elements (String xpath)
	{
		return driver.findElements(By.xpath(xpath));
	}
	

}
