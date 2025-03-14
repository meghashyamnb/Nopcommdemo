package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class amazonDemo {
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
        driver.get("https://www.amazon.in/ref=nav_logo");;

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Print the title for confirmation
        System.out.println("Page Title: " + driver.getTitle());
        WebElement addSearchInfo = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        addSearchInfo.sendKeys("Samsung Galaxy S24 256GB");
        Actions actions = new Actions(driver);
        WebElement search = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
        search.click();
        List<WebElement> allList =  driver.findElements(By.xpath("//div"));


        // Close the browser
        driver.quit();
    }
}
