package Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import com.example.RegisterUser ;



public class RegisterTest {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        // Create a Spark reporter instance and attach it to ExtentReports
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("src/test/resources/Report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @BeforeTest
    public void Open() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.className("ico-register")).click();

    }


    @Test
    public void registerNewUser() {
       // Create test instance for reporting
        test = extent.createTest("registerNewUser");
        try {
            // Step 1: Navigate to homepage and register page
            driver.get("https://demo.nopcommerce.com/");
            driver.findElement(By.className("ico-register")).click();

            // Step 2: Use RegisterUser page object
            RegisterUser register = new RegisterUser(driver);
            test.info("Filling registration form for Jane Doe");

            // Fill the registration form
            register.fillRegistrationForm("female", "Jane", "Doe",
                    "jane.doe@example.com", "Test@1234", "TCS", "Test@1234");
            test.info("Submitting registration form for Jane Doe");

            // Submit the form
            register.submitForm();

            // Verification: Check if registration was successful (adjust the condition as needed)
            if (driver.getPageSource().contains("Your registration completed")) {
                test.pass("Registration for Jane Doe completed successfully");
            } else {
                test.fail("Registration for Jane Doe did not complete as expected");
            }
        } catch (Exception e) {
            test.fail("Test encountered an exception: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }

    }


    @Test
    public void registerNewUserOne() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //test Report
        test = extent.createTest("registerNewUserOne");

        // Step 1: Navigate to homepage and register page
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.className("ico-register")).click();

        // Step 2: Use RegisterUser page object
        RegisterUser register = new RegisterUser(driver);

        // 👇 Your line goes here
        register.fillRegistrationForm("female", "Test", "Doe", "jane.doe@gmail.com", "Test@1234","WiproTest","Test@1234");

        // Optional: submit form
        register.submitForm();

        // Close browser
        driver.quit();
    }

    @AfterTest
    public void tearDown() {
        // Close the browser if the driver is not null
        if (driver != null) {
            driver.quit();
            System.out.println("Driver quit successfully.");
        }
    }
    @AfterSuite
    public void tearDownReport() {
        // Write all the test information to the report
        extent.flush();
        System.out.println("Extent report flushed successfully.");
    }
}
