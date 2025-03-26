package test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Định nghĩa các locator (bộ chọn) để tìm phần tử trên trang
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("submit");
    private By successMessage = By.id("successMessage");

    // Các phương thức để tương tác với phần tử
    public void setUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Phương thức thực hiện toàn bộ hành động đăng nhập
    public void login(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickLoginButton();
    }

    // Phương thức lấy thông báo thành công
    public String getMessage() {
        return driver.findElement(successMessage).getText();
    }
}