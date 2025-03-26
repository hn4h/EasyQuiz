package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddQuizPage {
    WebDriver driver;

    @FindBy(name = "quizTitle")
    private WebElement quizTitleField;

    @FindBy(name = "quizDescription")
    private WebElement quizDescriptionField;

    @FindBy(name = "question1")
    private WebElement questionField;

    @FindBy(name = "answer1.1")
    private WebElement option1Field;

    @FindBy(name = "answer1.2")
    private WebElement option2Field;

    @FindBy(name = "answer1.3")
    private WebElement option3Field;

    @FindBy(name = "answer1.4")
    private WebElement option4Field;

    // Dynamic locator for correct answer radio buttons (requires a different approach since @FindBy doesn't support dynamic values)
    @FindBy(css = "input[name='correct1'][value='1']")
    private WebElement correctAnswer1;

    @FindBy(css = "input[name='correct1'][value='2']")
    private WebElement correctAnswer2;

    @FindBy(css = "input[name='correct1'][value='3']")
    private WebElement correctAnswer3;

    @FindBy(css = "input[name='correct1'][value='4']")
    private WebElement correctAnswer4;

    @FindBy(css = "button.create-btn")
    private WebElement createButton;

    @FindBy(id = "toastMessage2")
    private WebElement toastMessage;

    // Constructor
    public AddQuizPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to add a quiz (similar to register method in RegisterPage)
    public void addQuiz(String title, String description, String question, String option1, String option2, String option3, String option4, int correctOption) {
        quizTitleField.sendKeys(title);
        quizDescriptionField.sendKeys(description);
        questionField.sendKeys(question);
        option1Field.sendKeys(option1);
        option2Field.sendKeys(option2);
        option3Field.sendKeys(option3);
        option4Field.sendKeys(option4);

        // Select the correct answer based on the provided option
        switch (correctOption) {
            case 1:
                correctAnswer1.click();
                break;
            case 2:
                correctAnswer2.click();
                break;
            case 3:
                correctAnswer3.click();
                break;
            case 4:
                correctAnswer4.click();
                break;
            default:
                throw new IllegalArgumentException("Correct option must be between 1 and 4");
        }

        createButton.click();
    }
    public String getToastMessage() {

        return toastMessage.getText();
    }
}