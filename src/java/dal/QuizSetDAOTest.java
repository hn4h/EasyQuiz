package dal;

import org.junit.jupiter.api.Test;
import model.QuizSet;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuizSetDAOTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private QuizSetDAO quizSetDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    void getQuizSetById_SuccessfulQuery() throws SQLException {
        // Arrange
        int id = 1;
        String sql = "SELECT q.Quiz_Set_ID, q.Quiz_Set_Name, q.Author, q.Number_Of_Quiz, q.Created_Date, q.Quiz_Set_Description,a.ProfileImage FROM Quiz_Set q join Accounts a on a.UserName = q.Author WHERE Quiz_Set_ID = ?";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("Quiz_Set_ID")).thenReturn(id);
        when(resultSet.getString("Quiz_Set_Name")).thenReturn("Sample Quiz");
        when(resultSet.getInt("Number_Of_Quiz")).thenReturn(10);
        when(resultSet.getString("Author")).thenReturn("testUser");
        when(resultSet.getString("ProfileImage")).thenReturn("profile.png");
        when(resultSet.getDate("Created_Date")).thenReturn(Date.valueOf("2024-03-06"));
        when(resultSet.getString("Quiz_Set_Description")).thenReturn("Sample Description");

        // Act
        QuizSet result = quizSetDAO.getQuizSetById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getQuizSetId());
        assertEquals("Sample Quiz", result.getQuizSetName());
        assertEquals(10, result.getNumberOfQuiz());
        assertEquals("testUser", result.getAuthor().getUserName());
        assertEquals("profile.png", result.getAuthor().getProfileImage());
        assertEquals(Date.valueOf("2024-03-06"), result.getCreatedDate());
        assertEquals("Sample Description", result.getQuizSetDescription());

        // Verify interactions
        verify(connection).prepareStatement(sql);
        verify(preparedStatement).setInt(1, id);
        verify(preparedStatement).executeQuery();
    }

    @Test
    void getQuizSetById_NotFound() throws SQLException {
        // Arrange
        int id = 1;
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false); // No data found

        // Act
        QuizSet result = quizSetDAO.getQuizSetById(id);

        // Assert
        assertNull(result);

        // Verify interactions
        verify(preparedStatement).setInt(1, id);
        verify(preparedStatement).executeQuery();
    }

    @Test
    void getQuizSetById_SQLException() throws SQLException {
        // Arrange
        int id = 1;
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        QuizSet result = quizSetDAO.getQuizSetById(id);

        // Assert
        assertNull(result);
    }
}