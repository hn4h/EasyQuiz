import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import dal.QuizSetHistoryDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Account;
import model.QuizSet;

class QuizSetHistoryDAOTest {

    @InjectMocks
    private QuizSetHistoryDAO dao;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    void testGetQuizSetHistoryTop4ByUserName_Success() throws SQLException {
        // Arrange
        String userName = "testUser";
        String sql = "SELECT Top(4) s.Quiz_Set_ID, s.Quiz_Set_Name ,s.Number_Of_Quiz, s.Author FROM Quiz_Set_History h\n"
                + "join Quiz_Set s on s.Quiz_Set_ID = h.Quiz_Set_ID\n"
                + "WHERE h.UserName = ?\n"
                + "order by h.Quiz_Date desc";

        // Mock JDBC behavior
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        // Mock ResultSet with 2 rows of data
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false); // 2 rows
        when(resultSet.getInt("quiz_Set_ID")).thenReturn(1).thenReturn(2);
        when(resultSet.getString("Quiz_Set_Name")).thenReturn("Quiz 1").thenReturn("Quiz 2");
        when(resultSet.getInt("Number_Of_Quiz")).thenReturn(10).thenReturn(15);
        when(resultSet.getString("Author")).thenReturn("author1").thenReturn("author2");

        // Act
        List<QuizSet> result = dao.getQuizSetHistoryTop4ByUserName(userName);

        // Assert
        assertNotNull(result, "Result list should not be null");
        assertEquals(2, result.size(), "List should contain 2 QuizSet objects");

        QuizSet quizSet1 = result.get(0);
        assertEquals(1, quizSet1.getQuizSetId());
        assertEquals("Quiz 1", quizSet1.getQuizSetName());
        assertEquals(10, quizSet1.getNumberOfQuiz());
        assertEquals("author1", quizSet1.getAuthor().getUserName());

        QuizSet quizSet2 = result.get(1);
        assertEquals(2, quizSet2.getQuizSetId());
        assertEquals("Quiz 2", quizSet2.getQuizSetName());
        assertEquals(15, quizSet2.getNumberOfQuiz());
        assertEquals("author2", quizSet2.getAuthor().getUserName());

        verify(preparedStatement).setString(1, userName);
        verify(preparedStatement).executeQuery();
        verify(resultSet, times(3)).next(); // Called 3 times to iterate and terminate
    }

    @Test
    void testGetQuizSetHistoryTop4ByUserName_NoData() throws SQLException {
        // Arrange
        String userName = "testUser";
        String sql = "SELECT Top(4) s.Quiz_Set_ID, s.Quiz_Set_Name ,s.Number_Of_Quiz, s.Author FROM Quiz_Set_History h\n"
                + "join Quiz_Set s on s.Quiz_Set_ID = h.Quiz_Set_ID\n"
                + "WHERE h.UserName = ?\n"
                + "order by h.Quiz_Date desc";

        // Mock JDBC behavior with no data
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false); // No rows

        // Act
        List<QuizSet> result = dao.getQuizSetHistoryTop4ByUserName(userName);

        // Assert
        assertNotNull(result, "Result list should not be null");
        assertEquals(0, result.size(), "List should be empty when no data is returned");

        verify(preparedStatement).setString(1, userName);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
    }

    @Test
    void testGetQuizSetHistoryTop4ByUserName_SQLException() throws SQLException {
        // Arrange
        String userName = "testUser";
        String sql = "SELECT Top(4) s.Quiz_Set_ID, s.Quiz_Set_Name ,s.Number_Of_Quiz, s.Author FROM Quiz_Set_History h\n"
                + "join Quiz_Set s on s.Quiz_Set_ID = h.Quiz_Set_ID\n"
                + "WHERE h.UserName = ?\n"
                + "order by h.Quiz_Date desc";

        // Mock JDBC behavior with SQLException
        when(connection.prepareStatement(sql)).thenThrow(new SQLException("Database error"));

        // Act
        List<QuizSet> result = dao.getQuizSetHistoryTop4ByUserName(userName);

        // Assert
        assertNotNull(result, "Result list should not be null even on SQLException");
        assertEquals(0, result.size(), "List should be empty when SQLException occurs");

        verify(connection).prepareStatement(sql);
        verifyNoInteractions(preparedStatement, resultSet); // No further interaction after exception
    }
}