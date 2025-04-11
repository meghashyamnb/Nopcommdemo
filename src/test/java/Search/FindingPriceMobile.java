package Search;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.AmazonDemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FindingPriceMobile extends AmazonDemo {

    // ExtentReports and test instance
    private ExtentReports extent;
    private ExtentTest test;
    private WebDriverWait wait;

    @BeforeTest
    public void setup() {
        // Initialize driver using the inherited method from AmazonDemo
        setupDriver();
        // Optionally, initialize your ExtentReports instance (this is a simple example)
        extent = new ExtentReports();
        ExtentSparkReporter amazonReporter = new ExtentSparkReporter("src/test/resources/AmazonReport.html");
        extent.attachReporter(amazonReporter);
        // Navigate to Amazon homepage
        driver.get("https://www.amazon.in/");
        // Initialize an explicit wait (20 seconds)
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // Helper method to perform the search; note that it is not a test method
    public void searchForItem(String productName) {
        System.out.println("Page Title: " + driver.getTitle());
        // Locate the search box, clear it, and enter product name
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.clear();
        searchBox.sendKeys(productName);
        // Click the search button
        WebElement searchButton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
        searchButton.click();
    }

    @Test
    public void searchSamsung() {
        test = extent.createTest("searchSamsung");
        // Use the helper method to search for the product
        searchForItem("Samsung Galaxy S24 256GB");
        // Optionally wait until the page title or some element indicates search results loaded
        wait.until(driver -> driver.getTitle().toLowerCase().contains("samsung"));
        test.pass("Search for Samsung Galaxy S24 256GB executed successfully.");
    }

    @Test
    public void searchPhone() {
        test = extent.createTest("searchPhone");
        // Navigate to Amazon's homepage again (if needed)
        driver.get("https://www.amazon.in/ref=nav_logo");
        System.out.println("Page Title: " + driver.getTitle());
        // Locate and use the search box
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.sendKeys("Samsung Galaxy S24 256GB");
        // Use Actions class to click on the search button
        Actions actions = new Actions(driver);
        WebElement searchButton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
        searchButton.click();
        // Retrieve a list of <div> elements as an example (you can adjust the locator as needed)
        List<WebElement> allList = driver.findElements(By.xpath("//div"));
        System.out.println("Number of <div> elements found: " + allList.size());
        test.pass("Search phone test executed successfully with " + allList.size() + " div elements found.");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extent != null) {
            extent.flush();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        // Write all the test information to the report
        extent.flush();
        System.out.println("Extent report flushed successfully.");
    }
}
