package testcases;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Search_Equity_Get_Details {

	public static void main(String[] args) {
		
//		Properties prop =new Properties();
//		InputStream input = null;
//		String filename ="database.properties";
//		input = obj.getClass().getClassLoader().getResourceAsStream(filename);
//		
//		if (input == null)
//		{
//			System.out.println("Sorry, unable to find " + filename);
//			return;
//		}
		
//		try {
//			input = new FileInputStream(filename);
//			prop.load(input);
//			String Script = prop.getProperty("Script");
//			System.out.println("Data :: "+Script);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		System.setProperty("webdriver.gecko.driver","E:\\Selenium_Project\\browserdriver\\geckodriver-v0.22.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.navigate().to("https://www.nseindia.com/");
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
		driver.findElement(By.id("keyword")).sendKeys("HDFC");
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		WebElement Script_Click = driver.findElement(By.xpath("//span[@id='ajax_response']//li[@class='selected']/a"));
		Script_Click.click();
		driver.manage().timeouts().pageLoadTimeout(20,TimeUnit.SECONDS);
//		OutputStream output = null;
		
//		try {
//			output = new FileOutputStream("OutputFile.properties");
//			String VWAP_Value=driver.findElement(By.xpath("//span[@id='vwap']")).getText().trim();
//			prop.setProperty("VWAP",VWAP_Value);						
//			prop.store(output, null);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		System.out.println("Company_Name :: "+driver.findElement(By.id("companyName")).getText().trim());
		System.out.println("Last_Updated_Div :: "+driver.findElement(By.id("LastUpdatedDiv")).getText().trim());
		System.out.println("Last_Traded_Price :: "+driver.findElement(By.id("lastPrice")).getText().trim());
		System.out.println("Last_change :: "+driver.findElement(By.id("change")).getText().trim());
		System.out.println("Last_change_Percentage :: "+driver.findElement(By.id("pChange")).getText().trim());
		System.out.println("Previous_Close :: "+driver.findElement(By.id("previousClose")).getText().trim());
		System.out.println("Open :: "+driver.findElement(By.id("open")).getText().trim());
		System.out.println("Day_High :: "+driver.findElement(By.id("dayHigh")).getText().trim());
		System.out.println("Day_Low :: "+driver.findElement(By.id("dayLow")).getText().trim());
		System.out.println("Close_Price :: "+driver.findElement(By.id("closePrice")).getText().trim());
		System.out.println("ISIN :: "+driver.findElement(By.id("isinCode")).getText().trim());		
		System.out.println("Volume weighted average price :: "+driver.findElement(By.xpath("//span[@id='vwap']")).getText().trim());
		System.out.println("Face Value :: "+driver.findElement(By.xpath("//span[@id='faceValue']")).getText().trim());
		System.out.println("Traded Volume (shares) :: "+driver.findElement(By.xpath("//span[@id='tradedVolume']")).getText().trim());
		System.out.println("Traded Value (lacs) :: "+driver.findElement(By.xpath("//span[@id='tradedValue']")).getText().trim());
		System.out.println("Free Float Market Cap(Crs) :: "+driver.findElement(By.xpath("//span[@id='ffmid']")).getText().trim());
		System.out.println("52 week high :: "+driver.findElement(By.xpath("//span[@id='high52']")).getText().trim());
		System.out.println("52 week high Date :: "+driver.findElement(By.xpath("//span[@id='mock_cm_adj_high_dt']")).getText().trim());
		System.out.println("52 week low :: "+driver.findElement(By.xpath("//span[@id='low52']")).getText().trim());
		System.out.println("52 week low Date :: "+driver.findElement(By.xpath("//span[@id='mock_cm_adj_low_dt']")).getText().trim());
		System.out.println("Lower Price Band :: "+driver.findElement(By.xpath("//span[@id='lowpriceBand']")).getText().trim());
		System.out.println("Upper Price Band :: "+driver.findElement(By.xpath("//span[@id='upperpriceBand']")).getText().trim());
		driver.quit();
	}

}
