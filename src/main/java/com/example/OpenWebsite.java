package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class OpenWebsite {
    public static void main(String[] args) {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().clearDriverCache().setup();

        // Chrome Options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized"); // Open browser in maximized mode
        // options.addArguments("--headless"); // Uncomment for headless execution

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(options);

        // Open the Website
        driver.get("https://demo.nopcommerce.com/");
        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        // Print the title for confirmation
        System.out.println("Page Title: " + driver.getTitle());

        WebElement selectHome = driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
        selectHome.click();

        WebElement addSearchInfo = driver.findElement(By.xpath("//input[@class='search-box-text ui-autocomplete-input']"));
        addSearchInfo.sendKeys("Samsung Galaxy S24 256GB");


        // Close the browser
        driver.quit();
    }
}
