package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;

    @FindBy(id = "userName") // Sửa từ "username" thành "userNameInput"
    private WebElement usernameField;

    @FindBy(id = "email") // Sửa từ "email" thành "emailInput"
    private WebElement emailField;

    @FindBy(id = "password") // Sửa từ "password" thành "passwordInput"
    private WebElement passwordField;

    @FindBy(id = "confirmPassword") // Sửa từ "confirmPassword" thành "confirmPasswordInput"
    private WebElement confirmPasswordField;

    @FindBy(id = "fullName") // Sửa từ "confirmPassword" thành "confirmPasswordInput"
    private WebElement fullNameField;

    @FindBy(id = "submitButton") // Sửa từ "submitBtn" thành "submitButton"
    private WebElement submitButton;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void register(String email, String username, String password, String confirmPassword, String fullName) {
        emailField.sendKeys(email);
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);
        fullNameField.sendKeys(fullName);
        submitButton.click();
    }
}