# MyProject
Script is implemented to Verify Stock Information Displayed (implemented to check the below Stock Information is displayed and comparing it with the Expected values)
StockName, StockFullName, CurrentPrice.
And Enhanced of checking the 52 weeks high and low prices
And Enhanced for displaying Profit/Low obtained for a stock.

1. Clone this repo and navigate to the project root.
2. Ensure Java 11 and Maven 3.8+ are installed.
3. Run `mvn clean install` to build dependencies.
4. Place your test data in `src/test/resources/testdata/`.
5. To run all tests in parallel on Chrome, Firefox, and Edge, use:  
   `mvn test -DsuiteXmlFile=testng.xml`
6. Test results and screenshots will be saved in the project directory.
7. The framework generates Extent HTML reports (`extentReport.html`) for test execution.
8. Browser drivers are managed automatically by WebDriverManager.
9. See `src/test/java/tests/NseStockInfoDataDrivenTest.java` for sample test usage.

Prerequisites before running the script in regards to testdata:
1. Please update the CurrentPrice values of expected data - to the CurrentPrice of the market before you try to verify for the Current Value displayed.
2. For checking the Profit/Loss for a stock, ensure the testdata has such that , for
Testcase_ID 01 BuyPrice equal be to CurrentPrice
Testcase_ID 03 BuyPrice greater than CurrentPrice
Testcase_ID 04 Buy Price less than CurrentPrice
Testcase_ID 05 BuyPrice equal be to CurrentPrice
Testcase_ID 02, Testcase_ID 06 - are documented and expect to intentionally fail.
3. Try to run the test when during market stable timings between - Prefered 3:30 pm ist to 5:30 pm ist
