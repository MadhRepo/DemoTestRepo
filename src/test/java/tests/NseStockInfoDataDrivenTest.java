package tests;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.StockInfo;

import java.time.Duration;

public class NseStockInfoDataDrivenTest extends BaseStockTest {

    @Test(dataProvider = "stockData")
    public void testStockInformationDisplay(StockInfo expected, int rowIndex, String tcId) throws InterruptedException {
        expectedStock.set(expected);
        testcaseId.set(tcId);
        String browser = browserName.get();
        ExtentTest extentTest = extent.createTest(tcId + " [" + browser.toUpperCase() + "] - Verify Stock Information Display: " + expected.getStockName());
        test.set(extentTest);
        setupAndNavigateToNifty50(expected);

        // Verify the stock search results
        try {
            String expectedStockUrl = "https://www.nseindia.com/get-quotes/equity?symbol=" + expected.getStockName().toUpperCase();
            String currentUrl = driver.get().getCurrentUrl();
            Assert.assertTrue(currentUrl.contains(expectedStockUrl), "Stock search failed for: " + expected.getStockName());

            String stockSelector = "a[data-nse-translate-symbol='" + expected.getStockName().toUpperCase() + "']";

            WebElement stockElement = new WebDriverWait(driver.get(), Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(stockSelector)));

            String actualStockName = stockElement.getText();
            String actualStockFullName = driver.get().findElement(By.cssSelector("#quoteName")).getText();
            String actualStockCurrentPrice = driver.get().findElement(By.cssSelector("span#quoteLtp")).getText();

            Assert.assertEquals(actualStockName, expected.getStockName(), "Stock name mismatch");
            test.get().pass("expected stockName: " + expected.getStockName() + ", actual stockName: " + actualStockName);
            Assert.assertEquals(actualStockFullName, expected.getStockFullName(), "Stock full name mismatch");
            test.get().pass("expected stockFullName: " + expected.getStockFullName() + ", actual stockFullName: " + actualStockFullName);

            double tolerance = 2.00;
            double expectedPrice = Double.parseDouble(expected.getCurrentPrice());
            double actualPrice = Double.parseDouble(actualStockCurrentPrice);
            if (Math.abs(expectedPrice - actualPrice) <= tolerance) {
                test.get().pass("Expected stockCurrentPrice: " + expected.getCurrentPrice() +
                        ", Actual stockCurrentPrice: " + actualStockCurrentPrice +
                        ", match within tolerance: " + tolerance);
            } else {
                test.get().fail("Expected stockCurrentPrice: " + expected.getCurrentPrice() +
                        ", Actual stockCurrentPrice: " + actualStockCurrentPrice +
                        ", mismatch within tolerance: " + tolerance);
                hasSoftFailure.set(true);
            }
        } catch (Throwable e) {
            hasSoftFailure.set(true);
            test.get().fail("Test search for stock information: " + expected.getStockName() + " failed - " + e.getMessage());
        }

        // Verify 52 Week High and Low prices
        try {
            String high = driver.get().findElement(By.xpath("//span[normalize-space()='52 Week High']/parent::td/following-sibling::td/span")).getText();
            String low = driver.get().findElement(By.xpath("//span[normalize-space()='52 Week Low']/parent::td/following-sibling::td/span")).getText();
            Assert.assertNotNull(high, "52 Week High should not be null");
            Assert.assertNotNull(low, "52 Week Low should not be null");
            test.get().pass("Test 52 Week High and Low prices for stock: " + expected.getStockName());
            extentTest.info("52 Week High price: " + high + ", 52 Week Low price: " + low);
        } catch (Throwable e) {
            hasSoftFailure.set(true);
            test.get().fail("Test 52 Week High and Low prices for stock: " + expected.getStockName() + " failed - " + e.getMessage());
        }

        // Calculate profit or loss
        try {
            String priceText = driver.get().findElement(By.cssSelector("span#quoteLtp")).getText().replaceAll(",", "");
            double currentPrice = Double.parseDouble(priceText);

            String buyPriceText = expected.getBuyingPrice().replaceAll(",", "");
            double buyPrice = Double.parseDouble(buyPriceText);

            double profitOrLoss = currentPrice - buyPrice;
            Assert.assertTrue(currentPrice > 0, "Current price should be positive");
            extentTest.info("Buy Price: " + buyPrice + ", Current Price: " + currentPrice + ", Profit/Loss: " + profitOrLoss);
            if (profitOrLoss > 0) {
                extentTest.pass("Profit of " + profitOrLoss + " for stock: " + expected.getStockName());
            } else if (profitOrLoss < 0) {
                extentTest.warning("Loss of " + Math.abs(profitOrLoss) + " for stock: " + expected.getStockName());
            } else {
                extentTest.info("No profit or loss for stock: " + expected.getStockName());
            }
            Assert.assertFalse(Double.isNaN(profitOrLoss), "Profit/Loss should be a valid number");
            test.get().pass("Test Calculate profit or loss for stock: " + expected.getStockName());
        } catch (Throwable e) {
            hasSoftFailure.set(true);
            test.get().fail("Test Calculate profit or loss for stock: " + expected.getStockName() + " failed - " + e.getMessage());
        }

        // Final assertion to fail the test if any soft failure occurred
        if (hasSoftFailure.get()) {
            Assert.fail("One or more verifications failed. See above for details.");
        }
    }
}