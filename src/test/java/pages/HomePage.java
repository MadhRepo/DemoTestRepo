package pages;

import org.openqa.selenium.*;
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
        // Scroll into view to avoid overlays
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        try {
            link.click();
        } catch (ElementClickInterceptedException e) {
            // Try clicking with JS as a fallback
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        driver.getCurrentUrl();
    }
}