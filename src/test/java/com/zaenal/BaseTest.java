package com.zaenal;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class BaseTest {
    protected static WebDriver driver;

    protected void getDriver() {
        String browser = System.getProperty("browser", "chrome"); // default: chrome
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver(options);

//        driver = WebDriverManager.firefoxdriver().create();
//        driver.manage().window().maximize();

        switch (browser.toLowerCase()){
            case "chrome":
                driver = WebDriverManager.chromedriver().create();
                break;
            case "firefox":
                driver = WebDriverManager.firefoxdriver().create();
                break;
            case "edge":
                driver = WebDriverManager.edgedriver().create();
                break;
            default:
                driver = WebDriverManager.chromedriver().create();
                break;

        }
    }
}
