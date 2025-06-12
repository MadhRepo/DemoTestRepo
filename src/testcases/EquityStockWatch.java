package testcases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import browser_Setup.Launch_Browser;

public class EquityStockWatch {
	WebDriver driver;
	
	@Before
	public void webPageLoader()
	{
		Launch_Browser Browse = new Launch_Browser();
		driver = Browse.Launch_Browser();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.navigate().to("https://www.nseindia.com/");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	@Test
	public void equityStockwatchmethod() {
		
		WebElement menu = driver.findElement(By.linkText("Live Market"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Actions builder = new Actions(driver);
		builder.moveToElement(menu).build().perform();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Equity Stock")));
		driver.findElement(By.linkText("Equity Stock")).click();		
		Select bankNiftySelect = new Select(driver.findElement(By.id("bankNiftySelect")));
		bankNiftySelect.selectByIndex(2);		
		driver.findElement(By.xpath("//a[@class='download-data-link1']")).click();
		Robot object;
		try {
			object = new Robot();
			object.keyPress(KeyEvent.VK_DOWN);
			object.keyRelease(KeyEvent.VK_DOWN);
			object.keyPress(KeyEvent.VK_ENTER);
			object.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
