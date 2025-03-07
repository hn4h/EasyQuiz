package dal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;

class FeedbackDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @InjectMocks
    private FeedbackDAO feedbackDAO;

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }


    @Test
    void addFeedback_success() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        feedbackDAO.addFeedback("testUser", "This is a feedback");

        verify(preparedStatement, times(1)).setString(1, "testUser");
        verify(preparedStatement, times(1)).setString(2, "This is a feedback");
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void addFeedback_sqlException() throws SQLException {
        doThrow(new SQLException("Database error")).when(preparedStatement).executeUpdate();

        feedbackDAO.addFeedback("testUser", "This is a feedback");

        verify(preparedStatement, times(1)).setString(1, "testUser");
        verify(preparedStatement, times(1)).setString(2, "This is a feedback");
        verify(preparedStatement, times(1)).executeUpdate();
    }
    @Test
    void addFeedback_withNullInput() throws SQLException {
        // Test with null username
        feedbackDAO.addFeedback(null, "This is a feedback");

        // Test with null feedbackContent
        feedbackDAO.addFeedback("testUser", null);

        // Verify that preparedStatement is still called with null values
        verify(preparedStatement, times(1)).setString(1, null); // For null username
        verify(preparedStatement, times(1)).setString(2, "This is a feedback");
        verify(preparedStatement, times(1)).setString(1, "testUser"); // For null feedbackContent
        verify(preparedStatement, times(1)).setString(2, null);
        verify(preparedStatement, times(2)).executeUpdate();
    }
    @Test
    void addFeedback_withEmptyFeedbackContent() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(0); // Giả định không thêm feedback rỗng

        feedbackDAO.addFeedback("testUser", "");

        verify(preparedStatement, times(1)).setString(1, "testUser");
        verify(preparedStatement, times(1)).setString(2, "");
        verify(preparedStatement, times(1)).executeUpdate();
    }
}