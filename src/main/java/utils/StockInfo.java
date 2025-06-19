package utils;

public class StockInfo {
    private String testcaseId;
    private String stockName;
    private String stockFullName;
    private String buyingPrice;
    private String currentPrice;

    public StockInfo(String testcaseId, String stockName, String stockFullName, String currentPrice, String buyingPrice) {
        this.testcaseId = testcaseId;
        this.stockName = stockName;
        this.stockFullName = stockFullName;
        this.buyingPrice = buyingPrice;
        this.currentPrice = currentPrice;
    }

    public String getTestcaseId() { return testcaseId; }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockFullName() {
        return stockFullName != null ? stockFullName.toUpperCase() : null;
    }

    public void setStockFullName(String stockFullName) {
        this.stockFullName = stockFullName;
    }

    public String getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "TC" + testcaseId + " - " + stockName;
    }
}