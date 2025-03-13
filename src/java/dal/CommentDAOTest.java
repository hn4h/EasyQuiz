package dal;

import model.Comment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    private CommentDAO commentDAO;

    @BeforeEach
    void setUp() throws SQLException, NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        commentDAO = new CommentDAO();

        // Inject the mocked connection into the CommentDAO instance
        Field connectionField = DBContext.class.getDeclaredField("connection");
        connectionField.setAccessible(true);
        connectionField.set(commentDAO, connection);

        // Default mock behavior for preparedStatement
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(connection, preparedStatement);
    }

    @Test
    void addComment_Success() throws SQLException {
        // Arrange
        Comment comment = new Comment();
        comment.setUserName("User1");
        comment.setBlogId(1);
        comment.setCommentContent("Test comment");

        when(connection.isClosed()).thenReturn(false); // Connection is open
        when(preparedStatement.executeUpdate()).thenReturn(1); // Simulate successful update

        // Act
        commentDAO.addComment(comment);

        // Assert
        verify(connection).prepareStatement("INSERT INTO dbo.Comment (UserName, Blog_ID, Comment_Content) VALUES (?, ?, ?)");
        verify(preparedStatement).setString(1, "User1");
        verify(preparedStatement).setInt(2, 1);
        verify(preparedStatement).setString(3, "Test comment");
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
    }

    @Test
    void addComment_NullOrClosedConnection() throws SQLException {
        // Arrange
        Comment comment = new Comment();
        comment.setUserName("User1");
        comment.setBlogId(1);
        comment.setCommentContent("Test comment");

        when(connection.isClosed()).thenReturn(true); // Connection is closed

        // Act & Assert
        assertThrows(SQLException.class, () -> commentDAO.addComment(comment));
        verify(connection).isClosed();
        verifyNoInteractions(preparedStatement); // No statement preparation if connection is invalid
    }

    @Test
    void addComment_SQLExceptionDuringPreparation() throws SQLException {
        // Arrange
        Comment comment = new Comment();
        comment.setUserName("User1");
        comment.setBlogId(1);
        comment.setCommentContent("Test comment");

        when(connection.isClosed()).thenReturn(false); // Connection is open
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Failed to prepare statement"));

        // Act & Assert
        SQLException exception = assertThrows(SQLException.class, () -> commentDAO.addComment(comment));
        assertEquals("Failed to prepare statement", exception.getMessage());
        verify(connection).isClosed();
        verify(connection).prepareStatement("INSERT INTO dbo.Comment (UserName, Blog_ID, Comment_Content) VALUES (?, ?, ?)");
        verifyNoMoreInteractions(preparedStatement); // No further interactions due to exception
    }

    @Test
    void addComment_SQLExceptionDuringExecution() throws SQLException {
        // Arrange
        Comment comment = new Comment();
        comment.setUserName("User1");
        comment.setBlogId(1);
        comment.setCommentContent("Test comment");

        when(connection.isClosed()).thenReturn(false); // Connection is open
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenThrow(new SQLException("Failed to execute update"));

        // Act & Assert
        SQLException exception = assertThrows(SQLException.class, () -> commentDAO.addComment(comment));
        assertEquals("Failed to execute update", exception.getMessage());
        verify(connection).prepareStatement("INSERT INTO dbo.Comment (UserName, Blog_ID, Comment_Content) VALUES (?, ?, ?)");
        verify(preparedStatement).setString(1, "User1");
        verify(preparedStatement).setInt(2, 1);
        verify(preparedStatement).setString(3, "Test comment");
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close();
    }

    @Test
    void addComment_SQLExceptionDuringStatementClosure() throws SQLException {
        // Arrange
        Comment comment = new Comment();
        comment.setUserName("User1");
        comment.setBlogId(1);
        comment.setCommentContent("Test comment");

        when(connection.isClosed()).thenReturn(false); // Connection is open
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1); // Simulate successful update
        doThrow(new SQLException("Failed to close statement")).when(preparedStatement).close();

        // Act
        commentDAO.addComment(comment);

        // Assert
        verify(connection).prepareStatement("INSERT INTO dbo.Comment (UserName, Blog_ID, Comment_Content) VALUES (?, ?, ?)");
        verify(preparedStatement).setString(1, "User1");
        verify(preparedStatement).setInt(2, 1);
        verify(preparedStatement).setString(3, "Test comment");
        verify(preparedStatement).executeUpdate();
        verify(preparedStatement).close(); // Verify close is called despite exception
        // Note: The exception during close is logged to System.err, which we can't easily assert, but coverage is achieved
    }
}