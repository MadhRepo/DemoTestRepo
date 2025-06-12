/*This Class will validate the Nifty Status as of Today*/
package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Nifty_Search {

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.gecko.driver","E:\\Selenium_Project\\browserdriver\\geckodriver-v0.22.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("https://www.nseindia.com/");
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		System.out.println("Normal Market Status :: "+driver.findElement(By.xpath("//span[@id='status1']")).getText().trim());
		System.out.println("Nifty 50 Today's Price :: "+driver.findElement(By.xpath("//p[@class='right']//nobr")).getText().trim());
		System.out.println("Nifty 50 Today's Price Change :: "+driver.findElement(By.xpath("//nobr/span[1]")).getText().trim());
		System.out.println("Nifty Arrow Color :: "+driver.findElement(By.xpath("//nobr/span[2]")).getText());		
		System.out.println("Nifty 50 Today's Price change Percentage :: "+driver.findElement(By.xpath("//nobr/span[3]")).getText().trim());
//		driver.findElement(By.linkText("NSE Bond Futures")).click();
		WebElement menu =driver.findElement(By.linkText("Live Market"));
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//		WebElement menu = driver.findElement(By.linkText("NSE Bond Futures"));
		Actions builder = new Actions(driver);		
		Action abc = builder.moveToElement(menu).build();
		abc.perform();
		WebDriverWait wait = new WebDriverWait(driver, 5); 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("NSE Bond Futures")));
		driver.findElement(By.linkText("NSE Bond Futures")).click();
		 
//		
//		driver.findElement(By.linkText("NSE Bond Futures")).click();
		
	}

}
