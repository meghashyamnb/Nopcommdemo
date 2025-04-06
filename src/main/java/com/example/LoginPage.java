package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver driver;

    // Constructor: accept a driver from the test
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Example: open the site and search for a product
    public void openSiteAndSearchProduct() {
        driver.get("https://demo.nopcommerce.com/");

        System.out.println("Page Title: " + driver.getTitle());

        // Click the home image
        WebElement selectHome =
                driver.findElement(By.xpath("//img[@alt='nopCommerce demo store']"));
        selectHome.click();

        // Enter a search term
        WebElement addSearchInfo = driver.findElement(By.xpath("//input[@class='search-box-text ui-autocomplete-input']"));
        addSearchInfo.sendKeys("Samsung Galaxy S24 256GB");
    }
}
