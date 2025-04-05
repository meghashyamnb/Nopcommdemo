package Test;

import com.example.BaseTest;
import com.example.LoginPage;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test
    public void testOpenSiteAndSearchProduct() {
        // Pass the same driver that was initialized in BaseTest
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openSiteAndSearchProduct();
        // Add assertions here to verify results
    }
}
