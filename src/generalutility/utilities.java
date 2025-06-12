package generalutility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class utilities {
	public static WebDriver Firefoxbrowser(String url)
	{
		System.setProperty("webdriver.gecko.driver","E:\\Selenium_Project\\browserdriver\\geckodriver-v0.22.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to(url);
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		return driver;		
	}
}
