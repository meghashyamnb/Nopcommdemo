package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterUser {

    private WebDriver driver;

    // Constructor to initialize WebDriver
    public RegisterUser(WebDriver driver) {
        this.driver = driver;
    }

    //select Register button
    public void submitForm() {
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
    }



    // Method to fill the registration form
    public void fillRegistrationForm(String gender, String firstName, String lastName, String email, String password,String CompanyName,String ConfirmPassword) {

        if (gender.equalsIgnoreCase("male")) {
            driver.findElement(By.cssSelector("#gender .male")).click();
        } else if (gender.equalsIgnoreCase("female")) {
            driver.findElement(By.cssSelector("#gender .female")).click();
        }
        driver.findElement(By.id("FirstName")).sendKeys(firstName);
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(lastName);
        driver.findElement(By.cssSelector("#Email")).sendKeys(email);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
        driver.findElement(By.id("Company")).sendKeys(CompanyName);
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(ConfirmPassword);
        driver.findElement(By.xpath("//button[@id='register-button']")).click();
    }

}

