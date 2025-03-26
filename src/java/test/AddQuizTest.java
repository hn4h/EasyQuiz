package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddQuizTest {
    WebDriver driver;
    AddQuizPage addQuizPage;

    @BeforeEach
    public void setUp() {
        try {
            System.setProperty("webdriver.edge.driver", "C:/edgedriver/msedgedriver.exe");
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            driver = new EdgeDriver(options);
            driver.manage().window().maximize();
            driver.get("http://localhost:9999/QuizzApp/addquiz");
            addQuizPage = new AddQuizPage(driver);
        } catch (Exception e) {
            System.err.println("Failed to initialize WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAddQuizSuccessfully() {
        try {
            // Add a quiz with sample data
            addQuizPage.addQuiz(
                    "Sample Quiz Title", // Quiz title
                    "This is a sample quiz description.", // Quiz description
                    "What is 2 + 2?", // Question
                    "2", // Option 1
                    "3", // Option 2
                    "4", // Option 3 (correct answer)
                    "5", // Option 4
                    3 // Correct answer (option 3)
            );

            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains("http://localhost:9999/QuizzApp/quiz"));
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    @Test
    void testAddQuizWithEmptyTitle() {
        try {
            addQuizPage.addQuiz(
                    "", // Quiz title empty
                    "This is a sample quiz description.",
                    "What is 2 + 2?",
                    "2",
                    "3",
                    "4",
                    "5",
                    3
            );
            String currentUrl = driver.getCurrentUrl();
            System.out.println(currentUrl);
            Assert.assertFalse(currentUrl.contains("http://localhost:9999/QuizzApp/quiz?quizId="));
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    @Test
    void testAddQuizWithEmptyQuestion() {
        try {
            addQuizPage.addQuiz(
                    "Sample Quiz Title",
                    "This is a sample quiz description.",
                    "", // Question empty
                    "2",
                    "3",
                    "4",
                    "5",
                    3
            );
            String currentUrl = driver.getCurrentUrl();
            Assert.assertFalse(currentUrl.contains("http://localhost:9999/QuizzApp/quiz?quizId="));
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    @Test
    void testAddQuizWithAllEmptyOptions() {
        try {
            addQuizPage.addQuiz(
                    "Sample Quiz Title",
                    "This is a sample quiz description.",
                    "What is 2 + 2?",
                    "", // Option 1 empty
                    "", // Option 2 empty
                    "", // Option 3 empty
                    "", // Option 4 empty
                    3
            );
            String currentUrl = driver.getCurrentUrl();
            Assert.assertFalse(currentUrl.contains("http://localhost:9999/QuizzApp/quiz?quizId="));
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    @Test
    void testAddQuizWithEmptyDescription() {
        try {
            // Add a quiz with an empty description
            addQuizPage.addQuiz(
                    "Quiz With Empty Description", // Quiz title
                    "", // Empty description
                    "What is 2 + 2?", // Question
                    "2", // Option 1
                    "3", // Option 2
                    "4", // Option 3 (correct answer)
                    "5", // Option 4
                    3 // Correct answer (option 3)
            );

            // Check if description is optional (adjust based on your app's behavior)
            // If description is required, expect an error message
            String currentUrl = driver.getCurrentUrl();
            Assert.assertFalse(currentUrl.contains("http://localhost:9999/QuizzApp/quiz?quizId="));
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        } else {
            System.err.println("Driver is null, cannot quit browser.");
        }
    }
}