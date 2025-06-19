package tests;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.StockInfo;

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

            String actualStockName = driver.get().findElement(By.cssSelector("a[data-nse-translate-symbol='" + expected.getStockName().toUpperCase() + "']")).getText();
            String actualStockFullName = driver.get().findElement(By.cssSelector("#quoteName")).getText();
            String actualStockCurrentPrice = driver.get().findElement(By.cssSelector("span#quoteLtp")).getText();

            Assert.assertEquals(actualStockName, expected.getStockName(), "Stock name mismatch");
            Assert.assertEquals(actualStockFullName, expected.getStockFullName(), "Stock full name mismatch");
            Assert.assertEquals(actualStockCurrentPrice, expected.getCurrentPrice(), "Current price mismatch");

            test.get().pass("expected stockName : " + expected.getStockName() + "," + "actual stockName : " + actualStockName);
            test.get().pass("expected stockFullName : " + expected.getStockFullName() + "," + "actual stockFullName : " + actualStockFullName);
            test.get().pass("expected stockCurrentPrice : " + expected.getCurrentPrice() + "," + "actual stockCurrentPrice : " + actualStockCurrentPrice);
            test.get().pass("Test search for stock information: " + expected.getStockName());
        } catch (Exception e) {
            test.get().fail("Test search for stock information: " + expected.getStockName() + " failed - " + e.getMessage());
            throw e;
        }

        // Verify 52 Week High and Low prices
        try {
            String high = driver.get().findElement(By.xpath("//span[normalize-space()='52 Week High']/parent::td/following-sibling::td/span")).getText();
            String low = driver.get().findElement(By.xpath("//span[normalize-space()='52 Week Low']/parent::td/following-sibling::td/span")).getText();
            Assert.assertNotNull(high, "52 Week High should not be null");
            Assert.assertNotNull(low, "52 Week Low should not be null");
            test.get().pass("Test 52 Week High and Low prices for stock: " + expected.getStockName());
            extentTest.info("52 Week High price: " + high + ", 52 Week Low price: " + low);
        } catch (Exception e) {
            test.get().fail("Test 52 Week High and Low prices for stock: " + expected.getStockName() + " failed - " + e.getMessage());
            throw e;
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
        } catch (Exception e) {
            test.get().fail("Test Calculate profit or loss for stock: " + expected.getStockName() + " failed - " + e.getMessage());
            throw e;
        }

    }
}