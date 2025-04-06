package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class AmazonDemo {

        protected WebDriver driver;

        /**
         * Sets up the ChromeDriver with desired options.
         */
        public void setupDriver() {
                // Setup ChromeDriver using WebDriverManager
                WebDriverManager.chromedriver().clearDriverCache().setup();

                // Define Chrome options
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--no-sandbox");
                options.addArguments("--start-maximized"); // Open browser in maximized mode
                // options.addArguments("--headless"); // Uncomment for headless execution

                // Initialize the WebDriver with the specified options
                driver = new ChromeDriver(options);
        }

        /**
         * Opens the specified website URL.
         *
         * @param url The URL of the website to open.
         */
        public void openWebsite(String url) {
                if (driver == null) {
                        throw new IllegalStateException("Driver is not initialized. Call setupDriver() first.");
                }
                driver.get(url);
        }

        /**
         * Closes the browser and cleans up the WebDriver instance.
         */
        public void tearDown() {
                if (driver != null) {
                        driver.quit();
                        driver = null;
                }
        }
}

