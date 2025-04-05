package Search;

import com.example.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class FindingPriceMobile extends BaseTest {

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


}
