package Search;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.example.AmazonDemo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchTestcase extends AmazonDemo {

    // Use the driver inherited from AmazonDemo.
    // Remove duplicate declarations.
    private WebDriverWait wait;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeSuite
    public void setupReports() {
        // Initialize ExtentReports once for the entire suite.
        extent = new ExtentReports();
        ExtentSparkReporter amazonReporter = new ExtentSparkReporter("src/test/resources/AmazonReport.html");
        extent.attachReporter(amazonReporter);
    }

    @BeforeMethod
    public void setUp() {
        // Initialize the driver using the inherited setup method.
        setupDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        // Navigate to the Amazon homepage.
        driver.get("https://www.amazon.in/ref=nav_logo");
    }

    // Generic method to perform a search operation.
    public void searchForItem(String productName) {
        System.out.println("Page Title: " + driver.getTitle());
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.clear();
        searchBox.sendKeys(productName);
        WebElement searchButton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
        searchButton.click();
    }

    @Test
    public void searchSamsung() {
        test = extent.createTest("searchSamsung");
        searchForItem("Samsung Galaxy S24 256GB");
        // Wait until search results load; adjust the XPath as needed.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-component-type='s-search-result']")));
        test.pass("Samsung search completed");
    }

    @Test
    public void searchIphone() {
        test = extent.createTest("searchIphone");
        searchForItem("iPhone 14");
        // Wait for the search results section to load fully.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='listitem']")));

        // Locate product titles (update XPath if necessary)
        List<WebElement> productList = driver.findElements(By.xpath("//span[contains(text(),'iPhone')]"));
        // Locate corresponding price elements.
        List<WebElement> priceWholeList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='a-price-whole']")));
        List<WebElement> priceFractionList = driver.findElements(By.xpath("//span[@class='a-price-fraction']"));

        System.out.println("Total Products Found: " + productList.size());
        System.out.println("Total Prices Found: " + priceWholeList.size());
        System.out.println("List of iPhone products and prices:");
        for (int i = 0; i < productList.size(); i++) {
            String productName = productList.get(i).getText();
            String productPrice = "Price not available";
            if (i < priceWholeList.size() && i < priceFractionList.size()) {
                productPrice = priceWholeList.get(i).getText() + "." + priceFractionList.get(i).getText();
            } else if (i < priceWholeList.size()) {
                productPrice = priceWholeList.get(i).getText();
            }
            System.out.println(productName + " - ₹" + productPrice);
        }
        test.pass("iPhone search completed");
    }

    @Test
    public void searchXmobile() {
        test = extent.createTest("searchXmobile");
        searchForItem("Xiomi");
        // Wait until search results load.
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-component-type='s-search-result']")));
        test.pass("Xiaomi search completed");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}
