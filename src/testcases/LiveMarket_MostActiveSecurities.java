package testcases;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import browser_Setup.Launch_Browser;

import liveMarket.MostActiveSecurities;
import result.MyReportingUtil;

public class LiveMarket_MostActiveSecurities extends MyReportingUtil{
	WebDriver driver;
	MostActiveSecurities MASBYU;
	
	@Test
	public void ByValueMostActiveSecurities() {
		reporting("Verify Most Active Securities Method By Value Started ","Method Should Start Successfully","Method started Successfully","Pass");
		Launch_Browser browser = new Launch_Browser();
		driver = browser.Launch_Browser();
		reporting("Verify Broswer Launch ","Launched Successfully","Launched Successfully","Pass");
		driver.findElement(By.id("main_livemkt")).click();
		reporting("Verify Click functionality ","Clicked Successfully","Clicked Success","Pass");
		driver.findElement(By.id("main_liveany_masc")).click();
		String Value = "Value";
		reporting("Verify Selection Input","Value","Value","Pass");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		MASBYU = new MostActiveSecurities();		
		MASBYU.MostActiveSecuritiesByValue(driver, Value);
		driver.quit();
		reporting("Verify Most Active Securities by Value data completed","Completed Successfully ","Successfully Completed ","Pass");
	}

	@Test
	public void ByVolumneMostActiveSecurities() {
		reporting("Verify Most Active Securities Method By Volume Started ","Method Should Start Successfully","Method started Successfully","Pass");
		Launch_Browser browser = new Launch_Browser();
		reporting("Verify Broswer Launch ","Launched Successfully","Launched Successfully","Pass");
		driver = browser.Launch_Browser();
		driver.findElement(By.id("main_livemkt")).click();
		reporting("Verify Click functionality ","Clicked Successfully","Clicked Success","Pass");
		driver.findElement(By.id("main_liveany_masc")).click();
		String Value = "Volume";
		reporting("Verify Selection Input","Volume","Volume","Pass");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		MASBYU = new MostActiveSecurities();
		MASBYU.MostActiveSecuritiesByValue(driver, Value);
		driver.quit();
	}
}
