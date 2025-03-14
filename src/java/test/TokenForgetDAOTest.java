package test;

import dal.TokenForgetDAO;
import model.TokenForgetPassword;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TokenForgetDAOTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @Mock
    private ResultSet mockResultSet;

    @InjectMocks
    private TokenForgetDAO tokenForgetDAO;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        tokenForgetDAO.connection = mockConnection; // Inject the mock connection
    }

    @Test
    void testGetFormatDate() {
        LocalDateTime now = LocalDateTime.of(2024, 3, 12, 10, 30, 45);
        String formattedDate = tokenForgetDAO.getFormatDate(now);
        assertEquals("2024-03-12 10:30:45", formattedDate);
    }

    @Test
    void testInsertTokenForget_Success() throws Exception {
        TokenForgetPassword token = new TokenForgetPassword(1, "user123", false, "token123", LocalDateTime.now());

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        boolean result = tokenForgetDAO.insertTokenForget(token);

        assertTrue(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testInsertTokenForget_Failure() throws Exception {
        TokenForgetPassword token = new TokenForgetPassword(1, "user123", false, "token123", LocalDateTime.now());

        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        boolean result = tokenForgetDAO.insertTokenForget(token);

        assertFalse(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testInsertTokenForget_Exception() throws Exception {
        TokenForgetPassword token = new TokenForgetPassword(1, "user123", false, "token123", LocalDateTime.now());

        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("DB Error"));

        boolean result = tokenForgetDAO.insertTokenForget(token);

        assertFalse(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testGetTokenPassword_Found() throws Exception {
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("userName")).thenReturn("user123");
        when(mockResultSet.getBoolean("isUsed")).thenReturn(false);
        when(mockResultSet.getString("token")).thenReturn("token123");
        when(mockResultSet.getTimestamp("expiryTime")).thenReturn(Timestamp.valueOf(LocalDateTime.now()));

        TokenForgetPassword result = tokenForgetDAO.getTokenPassword("token123");

        assertNotNull(result);
        assertEquals("user123", result.getUserName());
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    void testGetTokenPassword_NotFound() throws Exception {
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        TokenForgetPassword result = tokenForgetDAO.getTokenPassword("token123");

        assertNull(result);
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    void testGetTokenPassword_Exception() throws Exception {
        when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException("DB Error"));

        TokenForgetPassword result = tokenForgetDAO.getTokenPassword("token123");

        assertNull(result);
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    void testUpdateStatus_Success() throws Exception {
        TokenForgetPassword token = new TokenForgetPassword(1, "user123", true, "token123", LocalDateTime.now());

        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        assertDoesNotThrow(() -> tokenForgetDAO.updateStatus(token));
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testUpdateStatus_Exception() throws Exception {
        TokenForgetPassword token = new TokenForgetPassword(1, "user123", true, "token123", LocalDateTime.now());

        when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException("DB Error"));

        assertDoesNotThrow(() -> tokenForgetDAO.updateStatus(token));
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}