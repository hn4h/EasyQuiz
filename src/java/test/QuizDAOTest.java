package test;

import dal.QuizDAO;
import model.Answer;
import model.Quiz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuizDAOTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private QuizDAO quizDAO;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        quizDAO = new QuizDAO();
        quizDAO.connection = mockConnection;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    void testGetQuizzesByQuizSetID_Success() throws Exception {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("Quiz_ID")).thenReturn(1, 1);
        when(mockResultSet.getInt("Quiz_Set_ID")).thenReturn(10);
        when(mockResultSet.getString("Quiz_content")).thenReturn("What is Java?");
        when(mockResultSet.getInt("Answer_ID")).thenReturn(101, 102);
        when(mockResultSet.getString("Answer_Content")).thenReturn("A programming language", "A coffee brand");
        when(mockResultSet.getBoolean("Is_Correct")).thenReturn(true, false);

        List<Quiz> quizzes = quizDAO.getQuizzesByQuizSetID(10);

        assertEquals(1, quizzes.size());
        assertEquals("What is Java?", quizzes.get(0).getContent());
        assertEquals(2, quizzes.get(0).getAnswers().size());
        assertTrue(quizzes.get(0).getAnswers().get(0).isCorrect());
    }

    @Test
    void testGetQuizzesByQuizSetID_NoResults() throws Exception {
        when(mockResultSet.next()).thenReturn(false);

        List<Quiz> quizzes = quizDAO.getQuizzesByQuizSetID(20);

        assertTrue(quizzes.isEmpty());
    }

    @Test
    void testGetQuizzesByQuizSetID_SQLException() throws Exception {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("Database error"));

        List<Quiz> quizzes = quizDAO.getQuizzesByQuizSetID(30);

        assertTrue(quizzes.isEmpty());
    }

    @Test
    void testAddQuiz_Success() throws Exception {
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);
        when(mockPreparedStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(5);

        int quizID = quizDAO.addQuiz(15, "What is Python?");

        assertEquals(5, quizID);
    }

    @Test
    void testAddQuiz_Failure() throws Exception {
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);
        when(mockResultSet.next()).thenReturn(false);

        int quizID = quizDAO.addQuiz(15, "What is Python?");

        assertEquals(-1, quizID);
    }

    @Test
    void testAddQuiz_SQLException() throws Exception {
        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("Database error"));

        int quizID = quizDAO.addQuiz(15, "What is Python?");

        assertEquals(-1, quizID);
    }

    @Test
    void testAddAnswer_Success() throws Exception {
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> quizDAO.addAnswer(5, "A snake", false));
    }

    @Test
    void testAddAnswer_SQLException() throws Exception {
        doThrow(new SQLException("Insert error")).when(mockPreparedStatement).executeUpdate();

        assertDoesNotThrow(() -> quizDAO.addAnswer(5, "A snake", false));
    }
}