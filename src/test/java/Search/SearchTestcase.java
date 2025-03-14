package Search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchTestcase {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        // Initialize WebDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Open Amazon
        driver.get("https://www.amazon.in/ref=nav_logo");
    }

    // Generic method to perform search operation
    public void searchForItem(String productName) {
        // Print the title for confirmation
        System.out.println("Page Title: " + driver.getTitle());

        // Locate the search box and enter product name
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.clear();  // Clear any existing text in search box
        searchBox.sendKeys(productName);

        // Click the search button
        WebElement searchButton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
        searchButton.click();
    }

    @Test
    public void searchSamsung() {
        searchForItem("Samsung Galaxy S24 256GB");
        wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
    }

    @Test
    public void searchIphone() {
        searchForItem("Samsung Galaxy S24 256GB");

        // Wait for the search results section to load fully
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@role='listitem']")));

        // Locate all product title elements
        List<WebElement> productList =  driver.findElements(By.xpath("//span[text()='57,599']/ancestor::div[@class='puisg-col puisg-col-4-of-12 puisg-col-8-of-16 puisg-col-12-of-20 puisg-col-12-of-24 puis-list-col-right']//span[contains(text(),'Samsung Galaxy')]"));
        // Locate all corresponding price elements
        List<WebElement> priceWholeList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@class='a-price-whole']")));
        List<WebElement> priceFractionList = driver.findElements(By.xpath("//span[@class='a-price-fraction']"));

        // Debugging output
        System.out.println("Total Products Found: " + productList.size());
        System.out.println("Total Prices Found: " + priceWholeList.size());
        System.out.println("List of Samsung Galaxy S24 256GB products and prices:");
        // Print product details
        for (int i = 0; i < productList.size(); i++) {
            String productName = productList.get(i).getText();
            // Check if price exists, concatenate whole and fraction part
            String productPrice = "Price not available";
            if (i < priceWholeList.size() && i < priceFractionList.size()) {
                productPrice = priceWholeList.get(i).getText() + "." + priceFractionList.get(i).getText();
            } else if (i < priceWholeList.size()) {
                productPrice = priceWholeList.get(i).getText();
            }
            System.out.println(productName + " - ₹" + productPrice);
        }
    }

    @Test
    public void searchXmobile(){
        searchForItem("Xiomi");
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
