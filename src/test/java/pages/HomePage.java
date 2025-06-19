package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By acceptCookiesBtn = By.id("onetrust-accept-btn-handler");
    private By nifty50Link = By.xpath("//a[@href='/index-tracker/NIFTY 50' and contains(.,'NIFTY 50')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        // Use seconds as long for JDK 11/Selenium 3 compatibility
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void acceptCookiesIfPresent() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesBtn));
            btn.click();
        } catch (Exception ignored) {}
    }

    public void clickNifty50() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(nifty50Link));
        String originalWindow = driver.getWindowHandle();
        link.click();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        driver.getCurrentUrl();
    }
}