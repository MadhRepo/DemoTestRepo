package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.Keys;

import java.time.Duration;

public class Nifty50Page {
    private WebDriver driver;
    private WebDriverWait wait;

    private By searchInput = By.cssSelector("input.rbt-input-main.form-control.rbt-input");
    private By searchIcon = By.cssSelector("span.input-group-text.search_icon");

    public Nifty50Page(WebDriver driver) {
        this.driver = driver;
        // Use seconds as int for compatibility
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void searchStock(String stockName) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
        input.clear();
        input.sendKeys(stockName);

        // Wait for dropdown to appear (max 5s)
        By dropdownLocator = By.xpath("//a[contains(@id,'async-navbar-search-item')][.//span[@class='rt' and text()='" + stockName.toUpperCase() + "']]");
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(dropdownLocator));
            WebElement dropdownItem = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
            dropdownItem.click();
        } catch (TimeoutException e) {
            // Fallback: send ENTER if dropdown not clickable
            input.sendKeys(Keys.ENTER);
        }

        // Wait for the anchor with the correct symbol to be visible
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("a[data-nse-translate-symbol='" + stockName.toUpperCase() + "']")));
        } catch (TimeoutException e) {
            takeDebugScreenshot("searchStockTimeout_" + stockName + ".png");
            throw e;
        }
    }

    private void takeDebugScreenshot(String fileName) {
        try {
            java.io.File dir = new java.io.File("screenshots");
            if (!dir.exists()) dir.mkdirs();
            java.io.File srcFile = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(org.openqa.selenium.OutputType.FILE);
            java.io.File destFile = new java.io.File(dir, fileName);
            org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
        } catch (Exception ex) {
            // Ignore screenshot errors
        }
    }
}