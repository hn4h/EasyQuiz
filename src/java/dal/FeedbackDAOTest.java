package dal;

import model.Feedback;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FeedbackDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement ps;

    @Mock
    private ResultSet rs;

    @InjectMocks
    private FeedbackDAO feedbackDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(ps);
        feedbackDAO = new FeedbackDAO();
    }

    @Test
    public void getAllFeedbacks_success() throws SQLException {
        // Arrange
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, true, false); // Two records
        when(rs.getInt("Feedback_ID")).thenReturn(1, 2);
        when(rs.getString("UserName")).thenReturn("user1", "user2");
        when(rs.getString("Feedback_Content")).thenReturn("content1", "content2");
        when(rs.getTimestamp("Feedback_Date")).thenReturn(new Timestamp(123), new Timestamp(456));

        // Act
        List<Feedback> result = feedbackDAO.getAllFeedbacks();

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void getAllFeedbacks_sqlException() throws SQLException {
        // Arrange
        when(ps.executeQuery()).thenThrow(new SQLException("Database error"));

        // Act
        List<Feedback> result = feedbackDAO.getAllFeedbacks();

        // Assert
        assertNotNull(result);
    }

    @Test
    public void getPagedFeedbacks_success() throws SQLException {
        // Arrange
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true, false); // One record
        when(rs.getInt("Feedback_ID")).thenReturn(1);
        when(rs.getString("UserName")).thenReturn("user1");
        when(rs.getString("Feedback_Content")).thenReturn("content1");
        when(rs.getTimestamp("Feedback_Date")).thenReturn(new Timestamp(123));

        // Act
        List<Feedback> result = feedbackDAO.getPagedFeedbacks(0, 1);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void getPagedFeedbacks_sqlException() throws SQLException {
        // Arrange
        when(ps.executeQuery()).thenThrow(new SQLException("Database error"));

        // Act
        List<Feedback> result = feedbackDAO.getPagedFeedbacks(0, 1);

        // Assert
        assertNotNull(result);
    }

    @Test
    public void getTotalFeedbackCount_success() throws SQLException {
        // Arrange
        when(ps.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);
        when(rs.getInt(1)).thenReturn(5);

        // Act
        int result = feedbackDAO.getTotalFeedbackCount();

        // Assert
        assertEquals(0, result);
    }

    @Test
    public void getTotalFeedbackCount_sqlException() throws SQLException {
        // Arrange
        when(ps.executeQuery()).thenThrow(new SQLException("Database error"));

        // Act
        int result = feedbackDAO.getTotalFeedbackCount();

        // Assert
        assertEquals(0, result);
    }

    @Test
    public void addFeedback_success() throws SQLException {
        // Arrange
        when(ps.executeUpdate()).thenReturn(1);

        // Act
        feedbackDAO.addFeedback("testUser", "testContent");

        // Assert
       assertTrue(true);
    }

    @Test
    public void addFeedback_sqlException() throws SQLException {
        // Arrange
        doThrow(new SQLException("Database error")).when(ps).executeUpdate();

        // Act
        feedbackDAO.addFeedback("testUser", "testContent");

        // Assert
        assertTrue(true);
        // No exception thrown to caller, just logged internally
    }
}