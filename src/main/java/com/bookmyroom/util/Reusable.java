package com.bookmyroom.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Reusable {
	
	static org.apache.log4j.Logger log = Logger.getLogger(Reusable.class.getName());
	static WebDriver driver;
	WebDriverWait wait;
	WebElement element;
	public Reusable(WebDriver driver)
	{
		Reusable.driver=driver;
	}	
	
	public void maximize()
	{
		driver.manage().window().maximize();
	}
	
	//Open URL
	public void navigateTo(String url)
	{
		System.out.println("Navigate to:"+url);
		driver.get(url);
	}
	
	public void navigateBack()
	{
		System.out.println("Navigate back");
		driver.navigate().back();
	}
	
	public void implicitWait() {
		driver.manage().timeouts().implicitlyWait(Constants.timeInt, TimeUnit.SECONDS);
	}
	
	/*public void explicitWait() {
		WebDriverWait wait= new WebDriverWait(driver,Constants.timeInt);
		
	}*/
	
	public By Locator(String type, String value) {
		try {
			Thread.sleep(3500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		By by;
		switch(type) {
		case "id":
			by= By.id(value);
			break;
		case "name":
			by=By.name(value);
			break;
		case "xpath":
			by=By.xpath(value);
			break;
		case "css":
			by=By.cssSelector(value);
			break;
		case "linktext":
			by=By.linkText(value);
			break;
        default:
        	by= null;
        	break;
		}
		return by;
		}
	
	//Enter values in text field
	public void enterText(String type,String value, String text) {
		By locator;
		System.out.println("Enter text: "+type+"  "+ value);
		locator=Locator(type, value);
		element = new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.click();
		element.sendKeys(text);
	}
	//Click on a particular element
	public void click(String type,String value) {
		
		By locator;
		locator=Locator(type,value);
		System.out.println("Click on: "+value);
		element= new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.click();
		System.out.println("Clicked!");
	}
	//Get text
	public String selectAndGetText(String type,String value)
	{
		By locator;
		locator= Locator(type,value);
		element= new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element.getText();
	}
	//Selects and clears the text field
	public void selectAndClear(String type,String value)
	{
		By locator;
		locator= Locator(type,value);
		element= new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
	}
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	public void verifyURL(String expected)
	{
		System.out.println("Comparing: "+driver.getCurrentUrl()+" and "+expected);
		Assert.assertEquals(driver.getCurrentUrl(), expected);
	}
	
	public void verifyTitle(String message) {
		Assert.assertEquals(driver.getTitle(), message);
	}
	
	//verify
	public void verify(String type,String value,String message)
	{
		Assert.assertEquals(selectAndGetText(type,value), message);
		
	}
	
	public void checkHotel(String type,String value,String parm,String expected)
	{
		
	}
	
	public void verifyPresence(String type,String value,String parm,String expected)
	{
		By locator1;
		By locator2;
		
		locator1= Locator(type,value);
		locator2= Locator(type,parm);
		try {
		implicitWait();
		element=driver.findElement(locator1);
		System.out.println("-------- Comparing1: "+element.getText()+" and "+expected+"---------");
		if(!element.getText().equalsIgnoreCase(expected))
			Assert.assertFalse(true);
		else
			System.out.println("comparidion sucessful");
		
		}catch(NoSuchElementException e)
		{
			element=driver.findElement(locator2);
			log.error(e);
			System.out.println("-------- Comparing2: "+element.getText()+" and "+expected+"---------");
			if(!element.getText().equalsIgnoreCase(expected))
				Assert.assertFalse(true);
			else {System.out.println("Assert True");
				Assert.assertTrue(true);}
		}
		//Assert.assertTrue(true);
		//System.out.println("----------------- Verify: "+element+" ------------------");
		//Assert.assertNotEquals(element, null);
	}
	//select from drop down boxes 
	public void selectFromDropDownBox(String type,String value,String text) {
		By locator;
		locator=Locator(type,value);
		Select select= new Select( new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(locator)));
		select.selectByVisibleText(text);
	}
	//Take Screenshot
	public String takeSnapShot(String name) throws IOException{
    TakesScreenshot scrShot =(TakesScreenshot)driver;
    File srcFile=scrShot.getScreenshotAs(OutputType.FILE);
    File destFile=new File("D:\\OxygenWorkspace\\bookmyroom\\output\\"+name+".png");
    FileUtils.copyFile(srcFile, destFile);
    return destFile.getAbsolutePath();
    //return "D:\\OxygenWorkspace\\bookmyroom\\output\\"+name+".png";
    //System.out.println("Done!");           
     }
	
	public void closeBrowser(WebDriver driver) {
		driver.close();
	}
	
	
	
		
	
}
		
