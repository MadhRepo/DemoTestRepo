package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;

import pages.HomePage;
import pages.Nifty50Page;
import utils.StockInfo;
import utils.StockInfoReader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseStockTest {
    protected static final Logger logger = LogManager.getLogger(BaseStockTest.class);

    protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static final ThreadLocal<HomePage> homePage = new ThreadLocal<>();
    protected static final ThreadLocal<Nifty50Page> nifty50Page = new ThreadLocal<>();
    protected static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    protected static final ThreadLocal<String> browserName = new ThreadLocal<>();
    protected static final ThreadLocal<StockInfo> expectedStock = new ThreadLocal<>();
    protected static final ThreadLocal<String> testcaseId = new ThreadLocal<>();
    protected static final AtomicInteger tcCounter = new AtomicInteger(1);

    protected static ExtentReports extent;

    @BeforeClass
    public void setUpReport() {
        logger.info("Setting up Extent report");
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @AfterClass
    public void tearDownReport() {
        logger.info("Flushing Extent report");
        if (extent != null) {
            extent.flush();
        }
    }

    @BeforeMethod
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser, Method method, Object[] testData) {
        browserName.set(browser);
        logger.info("Launching {} browser and navigating to NSE India", browser);
        WebDriver localDriver;
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            localDriver = new org.openqa.selenium.firefox.FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            localDriver = new org.openqa.selenium.edge.EdgeDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            localDriver = new org.openqa.selenium.chrome.ChromeDriver();
        }
        localDriver.get("https://www.nseindia.com/");
        driver.set(localDriver);
        homePage.set(new HomePage(localDriver));
        nifty50Page.set(new Nifty50Page(localDriver));
        // Take screenshot before test
        String stockName = extractStockName(testData);
        takeScreenshot(method.getName(), stockName, "before");
    }

    @AfterMethod(alwaysRun = true)
    public void logTestResult(ITestResult result) {
        ExtentTest extentTest = test.get();
        if (extentTest != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                extentTest.fail("Test failed: " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest.skip("Test skipped: " + result.getThrowable());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass("Test passed");
            }
        }
    }

    @AfterMethod
    public void tearDown(Method method, Object[] testData) {
        String tcId = testcaseId.get() != null ? testcaseId.get() : "TCXX";
        // Take screenshot after test
        String stockName = extractStockName(testData);
        takeScreenshot(method.getName(), stockName, "after");
        logger.info("Closing browser: {}", browserName.get());
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
        homePage.remove();
        nifty50Page.remove();
        test.remove();
        browserName.remove();
        expectedStock.remove();
        testcaseId.remove();
    }

    // JDK 11 compatible screenshot method using FileUtils
    protected void takeScreenshot(String testCase, String stockName, String phase) {
        try {
            WebDriver webDriver = driver.get();
            if (webDriver == null) return;
            File srcFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            String dir = "screenshots";
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String browser = browserName.get() == null ? "NA" : browserName.get();
            String fileName = String.format("%s_%s_%s_%s_%s.png",
                    browser,
                    testCase,
                    stockName == null ? "NA" : stockName,
                    phase,
                    timestamp
            );
            File destFile = new File(dirFile, fileName);
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException | WebDriverException e) {
            // Log or handle as needed
        }
    }

    @DataProvider(name = "stockData")
    public Object[][] stockDataProvider() throws Exception {
        ClassLoader classLoader = getClass().getClassLoader();
        java.net.URL resource = classLoader.getResource("testdata/expected_stocks.csv");
        if (resource == null) {
            throw new java.io.FileNotFoundException("expected_stocks.csv not found in resources");
        }
        java.util.List<StockInfo> stocks = StockInfoReader.readFromCsv(resource.getPath());
        Object[][] data = new Object[stocks.size()][3];
        for (int i = 0; i < stocks.size(); i++) {
            StockInfo stock = stocks.get(i);
            data[i][0] = stock;
            data[i][1] = i + 1; // 1-based row index
            data[i][2] = stock.getTestcaseId(); // Testcase_ID from CSV
        }
        return data;
    }

    protected void setupAndNavigateToNifty50(StockInfo expected) throws InterruptedException {
        homePage.get().acceptCookiesIfPresent();
        homePage.get().clickNifty50();
        Thread.sleep(1000);
        nifty50Page.get().searchStock(expected.getStockName());
        Thread.sleep(2000);
    }

    private String extractStockName(Object[] testData) {
        if (testData != null && testData.length > 0 && testData[0] instanceof StockInfo) {
            return ((StockInfo) testData[0]).getStockName();
        }
        return "NA";
    }
}