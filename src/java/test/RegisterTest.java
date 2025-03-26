package test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegisterTest {
    WebDriver driver;
    RegisterPage registerPage;

    @BeforeMethod
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:9999/Project_Prj/register.jsp");
        registerPage = new RegisterPage(driver);
    }

    @Test
    public void testSuccessfulRegistration() {
        registerPage.register("test12" + System.currentTimeMillis() +"@gmail.com", "testuser" + System.currentTimeMillis(), "Password123", "Password123", "Ngo Minh Cuong");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://localhost:9999/Project_Prj/login.jsp", "Đăng ký thành công!");
    }

    @Test
    public void testInvalidEmail() {
        registerPage.register("test12" + System.currentTimeMillis(), "testuser" + System.currentTimeMillis(), "Password123", "Password123", "Ngo Minh Cuong");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://localhost:9999/Project_Prj/register.jsp", "Invalid email!");
    }

    @Test
    public void testIShortPassWord() {
        registerPage.register("test12" + System.currentTimeMillis() + "@gmail.com", "testuser" + System.currentTimeMillis(), "Pass", "Pass", "Ngo Minh Cuong");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://localhost:9999/Project_Prj/register.jsp", "Invalid password!");
    }

    @Test
    public void testILongPassWord() {
        registerPage.register("test12" + System.currentTimeMillis() + "@gmail.com", "testuser" + System.currentTimeMillis(), "Password111111111111111111111111", "Password111111111111111111111111", "Ngo Minh Cuong");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://localhost:9999/Project_Prj/register.jsp", "Invalid password!");
    }

    @Test
    public void testPasswordMismatch() {
        registerPage.register("test12" + System.currentTimeMillis() +"@gmail.com", "test2" + System.currentTimeMillis(), "Password123", "WrongPassword", "Ngo Minh Cuong");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("register"), "Giữ nguyên trang khi mật khẩu không khớp!");
    }

    @Test
    public void testInvalidUserName() {
        registerPage.register("test12" + System.currentTimeMillis(), "testuser", "Password123", "Password123", "Ngo Minh Cuong");
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "http://localhost:9999/Project_Prj/register.jsp", "Invalid email!");
    }

    @Test
    public void testEmptyFields() {
        registerPage.register("", "", "", "", "");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("register"), "Empty field!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Đóng trình duyệt
        }
    }
}