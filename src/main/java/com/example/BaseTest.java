package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Initializes the WebDriverManager and creates a ChromeOptions object
     * with desired arguments. Subclasses can call this method to get
     * a properly configured ChromeOptions instance.
     */
    protected ChromeOptions getChromeOptions() {
        // Ensure the chromedriver is set up
        WebDriverManager.chromedriver().clearDriverCache().setup();

        // Define the Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        // options.addArguments("--headless"); // Uncomment if you need headless mode

        return options;
    }

    /**
     * Creates a new ChromeDriver instance and sets up an explicit WebDriverWait.
     * Typically called inside a @BeforeMethod or @BeforeTest in a subclass.
     */
    protected void initializeDriver() {
        driver = new ChromeDriver(getChromeOptions());
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    /**
     * Gracefully quits the driver.
     * Typically called inside an @AfterMethod or @AfterTest in a subclass.
     */
    protected void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

