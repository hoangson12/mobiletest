package com.fsoft.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fsoft.base.TestBase;
import com.fsoft.model.AppHomeScreen;
import com.fsoft.model.AppSettingScreen;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;

public class AppTest extends TestBase{
	
	public static String sessID = "";
	public AppHomeScreen home;
	public AppSettingScreen setting;
	private static AppiumDriverLocalService service;
	
	
	@BeforeTest
	public void beforeTest ()throws MalformedURLException
	{
//		service = AppiumDriverLocalService.buildDefaultService();
//        service.start();
//        
//        if (service == null || !service.isRunning()) {
//            throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
//        }
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.0");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "S8");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		cap.setCapability(MobileCapabilityType.APP, "C:\\Users\\nguye\\Documents\\work\\MobileTest\\apk\\app-debug.apk");
		cap.setCapability("udid", "ce031713ebbb661802");
		cap.setCapability("autoGrantPermissions", "true");		
		
		driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		sessID = driver.getSessionId().toString();		
		//init pageobject
		home = new AppHomeScreen();
		setting = new AppSettingScreen();		
	}
	
	
	@Test
	public void verifyChooseBook () throws InterruptedException
	{
		driver.tap(1, element(home.buttonSetting), durShort);
		Thread.sleep(1000);
		driver.tap(1, element(home.buttonSettingSelectBook), durShort);
		Thread.sleep(1000);
		driver.tap(1, elements(setting.listBook).get(0), durShort);
		Thread.sleep(1000);
		String bookName = "Book Name: Bible.txt";		
		Assert.assertEquals(element(home.textBookName).getAttribute("value"), bookName);		
	}
	
	@Test
	public void verifyPlayButton () throws InterruptedException
	{
		driver.tap(1, element(home.buttonPlay), durShort);
		Thread.sleep(1000);
		String pauseButton = "PAUSE";
		Assert.assertEquals(element(home.buttonPlay).getText(), pauseButton);
	}
	
	@Test
	public void verifyBookSetting () throws InterruptedException
	{
		driver.tap(1, element(home.buttonSetting), durShort);
		Thread.sleep(1000);
		driver.tap(1, element(home.buttonSettingBookSetting), durShort);
		Thread.sleep(1000);
		Assert.assertEquals(element(setting.saveButton).isDisplayed(), true);
	}
	
	
	@AfterTest
	public void afterTest()
	{
		driver.quit();
	}
}
