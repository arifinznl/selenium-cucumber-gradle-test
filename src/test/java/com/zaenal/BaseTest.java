package com.zaenal;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BaseTest {
    protected static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
//        WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;

//        switch (browser.toLowerCase()){
//            case "chrome":
//                driver = WebDriverManager.chromedriver().create();
//                break;
//            case "firefox":
//                driver = WebDriverManager.firefoxdriver().create();
//                break;
//            case "edge":
//                driver = WebDriverManager.edgedriver().create();
//                break;
//            default:
//                driver = WebDriverManager.chromedriver().create();
//                break;

        }
    }
}
