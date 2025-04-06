package Test;

import com.example.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Initialize ChromeDriver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testOpenSiteAndSearchProduct() {
        // Create an instance of the LoginPage class
        LoginPage loginPage = new LoginPage(driver);

        // Call the method to open site and perform search
        loginPage.openSiteAndSearchProduct();

        // You can also add assertions here, e.g. check URL or page content
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after test
        if (driver != null) {
            driver.quit();
        }
    }
}
