package utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
    public static void setupChrome() {
        WebDriverManager.chromedriver().setup();
    }

    public static void setupFirefox() {
        WebDriverManager.firefoxdriver().setup();
    }

    public static void setupEdge() {
        WebDriverManager.edgedriver().setup();
    }
}