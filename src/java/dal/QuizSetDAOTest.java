package dal;

import dal.QuizSetDAO;
import model.Account;
import model.QuizSet;
import model.QuizSetDetail;
import model.FlashCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class QuizSetDAOTest {

    private QuizSetDAO quizSetDAO;

    @BeforeEach
    void setUp() {
        quizSetDAO = new QuizSetDAO();
    }

    @Test
    void testGetPopularQuizSet() {
        List<QuizSet> result = quizSetDAO.getPopularQuizSet();
        assertNotNull(result);
        assertTrue(result.size() <= 9, "Số lượng quiz set không vượt quá 9");
    }

    @Test
    void testGetQuizDetailById() {
        int quizSetId = 1;
        QuizSetDetail result = quizSetDAO.getQuizDetailById(quizSetId);
        assertNotNull(result, "Không tìm thấy chi tiết Quiz Set");
        assertEquals(quizSetId, result.getQs().getQuizSetId());
    }

    @Test
    void testGetQuizSetById() {
        int quizSetId = 1;
        QuizSet result = quizSetDAO.getQuizSetById(quizSetId);
        assertNotNull(result);
        assertEquals(quizSetId, result.getQuizSetId());
    }

    @Test
    void testGetAllQuizSetByUsername() {
        String username = "testUser";
        List<QuizSet> result = quizSetDAO.getAllQuizSetByUsername(username);
        assertNotNull(result);
        for (QuizSet qs : result) {
            assertEquals(username, qs.getAuthor().getUserName());
        }
    }

    @Test
    void testAddQuizSet() {
        QuizSet quizSet = new QuizSet();
        quizSet.setQuizSetName("Test Quiz Set");
        quizSet.setNumberOfQuiz(10);
        Account author = new Account();
        author.setUserName("testUser");
        quizSet.setAuthor(author);
        quizSet.setQuizSetDescription("Mô tả test");

        int generatedId = quizSetDAO.addQuizSet(quizSet);
        assertTrue(generatedId > 0);
    }

    @Test
    void testAddQuizHistory() {
        int quizSetId = 1;
        String username = "testUser";
        assertDoesNotThrow(() -> quizSetDAO.addQuizHistory(quizSetId, username));
    }

    @Test
    void testSearchAllQuizSetByName() {
        String quizSetName = "Test";
        List<QuizSet> result = quizSetDAO.searchAllQuizSetByName(quizSetName);
        assertNotNull(result);
        for (QuizSet qs : result) {
            assertTrue(qs.getQuizSetName().contains(quizSetName));
        }
    }
}