package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebElement;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    static WebDriver driver;
    static LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:9999/QuizzApp/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    void testLogin() {
        // Sử dụng phương thức login từ lớp LoginPage
        loginPage.login("113han004@gmail.com", "87654321");
        // Kiểm tra thông báo thành công
        assertEquals("Login successfully!", loginPage.getMessage());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
