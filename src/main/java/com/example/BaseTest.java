package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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

        try {
            // Wait up to 10 seconds for the reCAPTCHA iframe to be available and switch to it
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                            By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")
                    ));

            // Wait up to 20 seconds for the reCAPTCHA checkbox to become clickable, then click it
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-checkmark")))
                    .click();

            // Switch back to the main document after interacting with the recaptcha iframe
            driver.switchTo().defaultContent();

        } catch (TimeoutException te) {
            System.out.println("Recaptcha elements not found within specified time: " + te.getMessage());
        }
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
