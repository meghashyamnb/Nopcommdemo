package Test;


import com.example.RegisterUser;
import com.example.TestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class RegisterTestOne {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void registerNewUserVariant() throws InterruptedException{
        // Navigate to the registration page
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.className("ico-register")).click();

        Thread.sleep(2000);

        // Fill the form using Page Object
        RegisterUser register = new RegisterUser(driver);
        register.fillRegistrationForm(
                "female",
                "Priya",
                "Sharma",
                "priya.sharma@gmail.com",
                "Secure@123",
                "TestOrg",
                "Secure@123"
        );
        register.submitForm();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
