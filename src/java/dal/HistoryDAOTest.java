package dal;

import model.Folder;
import model.QuizHistory;
import model.QuizSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class HistoryDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private HistoryDAO historyDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    void testGetHistoryQuizSet_Success() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("Quiz_Set_ID")).thenReturn(1);
        when(resultSet.getString("Quiz_Set_Name")).thenReturn("Test Quiz");
        when(resultSet.getString("Author")).thenReturn("testUser");
        when(resultSet.getInt("Number_Of_Quiz")).thenReturn(5);
        when(resultSet.getTimestamp("Created_Date")).thenReturn(new Timestamp(123456789));
        when(resultSet.getString("Quiz_Set_Description")).thenReturn("Test Description");
        when(resultSet.getTimestamp("Quiz_Date")).thenReturn(new Timestamp(987654321));

        List<QuizHistory> result = historyDAO.getHistoryQuizSet("testUser");

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getQuizSetId());
        assertEquals("Test Quiz", result.get(0).getQuizSetName());
        verify(preparedStatement).setString(1, "testUser");
    }

    @Test
    void testGetHistoryQuizSet_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("DB Error"));

        List<QuizHistory> result = historyDAO.getHistoryQuizSet("testUser");

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllFolderByUserName_Success() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("Folder_ID")).thenReturn(1);
        when(resultSet.getString("Folder_Name")).thenReturn("Test Folder");
        when(resultSet.getDate("Folder_Date")).thenReturn(new Date(123456789));
        when(resultSet.getInt("QuizSetCount")).thenReturn(3);

        List<Folder> result = historyDAO.getAllFolderByUserName("testUser");

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getFolderId());
        assertEquals("Test Folder", result.get(0).getFolderName());
        verify(preparedStatement).setString(1, "testUser");
    }

    @Test
    void testGetAllFolderByUserName_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("DB Error"));

        List<Folder> result = historyDAO.getAllFolderByUserName("testUser");

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllCreatedQuizSet_Success() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("Quiz_Set_ID")).thenReturn(1);
        when(resultSet.getString("Quiz_Set_Name")).thenReturn("Test Quiz");
        when(resultSet.getInt("Number_Of_Quiz")).thenReturn(5);
        when(resultSet.getString("Author")).thenReturn("testUser");
        when(resultSet.getString("ProfileImage")).thenReturn("image.jpg");
        when(resultSet.getDate("Created_Date")).thenReturn(new Date(123456789));

        List<QuizSet> result = historyDAO.getAllCreatedQuizSet("testUser");

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getQuizSetId());
        assertEquals("testUser", result.get(0).getAuthor().getUserName());
    }

    @Test
    void testGetAllQuizSetByFolderId_Success() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("Quiz_Set_ID")).thenReturn(1);
        when(resultSet.getString("Quiz_Set_Name")).thenReturn("Test Quiz");
        when(resultSet.getInt("Number_Of_Quiz")).thenReturn(5);
        when(resultSet.getString("Author")).thenReturn("testUser");
        when(resultSet.getString("ProfileImage")).thenReturn("image.jpg");

        List<QuizSet> result = historyDAO.getAllQuizSetByFolderId(1);

        assertEquals(1, result.size());
        verify(preparedStatement).setInt(1, 1);
    }

    @Test
    void testGetFolderByFolderId_SuccessWithData() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false); // Có dữ liệu
        when(resultSet.getInt("Folder_ID")).thenReturn(0);
        when(resultSet.getString("Folder_Name")).thenReturn("Test Folder");
        when(resultSet.getDate("Folder_Date")).thenReturn(new Date(123456789));
        when(resultSet.getString("Folder_Description")).thenReturn("Test Description");
        when(resultSet.getInt("QuizSetCount")).thenReturn(3);

        Folder result = historyDAO.getFolderByFolderId(1);

        assertEquals(0, result.getFolderId());
        assertEquals("Test Folder", result.getFolderName());
        assertEquals("Folder_Description", result.getFolderDescription());
        assertEquals(3, result.getQuizSetCount());
        verify(preparedStatement).setInt(1, 1);
    }

    @Test
    void testGetFolderByFolderId_NoData() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false); // Không có dữ liệu

        Folder result = historyDAO.getFolderByFolderId(1);

        // Kiểm tra object rỗng (default values)
        assertEquals(0, result.getFolderId());
        assertNull(result.getFolderName());
        assertNull(result.getFolderDate());
        assertNull(result.getFolderDescription());
        assertEquals(0, result.getQuizSetCount());
        verify(preparedStatement).setInt(1, 1);
    }

    @Test
    void testCreateFolder_SuccessWithGeneratedKey() throws SQLException {
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true); // Có generated key
        when(resultSet.getInt(1)).thenReturn(1);

        int result = historyDAO.createFolder("Test Folder", "testUser");

        assertEquals(1, result);
        verify(preparedStatement).setString(1, "Test Folder");
        verify(preparedStatement).setString(2, "testUser");
        verify(preparedStatement).setString(3, "");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testCreateFolder_NoGeneratedKey() throws SQLException {
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        when(preparedStatement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false); // Không có generated key

        int result = historyDAO.createFolder("Test Folder", "testUser");

        assertEquals(-1, result);
        verify(preparedStatement).setString(1, "Test Folder");
        verify(preparedStatement).setString(2, "testUser");
        verify(preparedStatement).setString(3, "");
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testCreateFolder_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString(), anyInt())).thenThrow(new SQLException("DB Error"));

        int result = historyDAO.createFolder("Test Folder", "testUser");

        assertEquals(-1, result);
    }

    @Test
    void testDeleteFolder_Success() throws SQLException {
        // Mock hai PreparedStatement khác nhau cho hai câu SQL
        PreparedStatement st1 = mock(PreparedStatement.class);
        PreparedStatement st2 = mock(PreparedStatement.class);

        when(connection.prepareStatement("DELETE FROM Folder_Contain WHERE Folder_ID = ?"))
                .thenReturn(st1);
        when(connection.prepareStatement("DELETE FROM Folder WHERE Folder_ID = ? AND UserName = ?"))
                .thenReturn(st2);

        when(st1.executeUpdate()).thenReturn(1);
        when(st2.executeUpdate()).thenReturn(1);

        historyDAO.deleteFolder(1, "testUser");

        // Verify cả hai statement được gọi đúng
        verify(st1).setInt(1, 1);
        verify(st2).setInt(1, 1);
        verify(st2).setString(2, "testUser");
        verify(st1).executeUpdate();
        verify(st2).executeUpdate();
    }

    @Test
    void testGetAllHistoryQuizSet_Success() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("quiz_Set_ID")).thenReturn(1);
        when(resultSet.getString("Quiz_Set_Name")).thenReturn("Test Quiz");
        when(resultSet.getInt("Number_Of_Quiz")).thenReturn(5);
        when(resultSet.getString("Author")).thenReturn("testUser");

        List<QuizSet> result = historyDAO.getAllHistoryQuizSet("testUser");

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getQuizSetId());
        verify(preparedStatement).setString(1, "testUser");
        verify(preparedStatement).setString(2, "testUser");
    }

    @Test
    void testAddQuizSetToFolder_Failure() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(0);

        boolean result = historyDAO.addQuizSetToFolder(1, 1);

        assertFalse(result);
    }

    @Test
    void testDeleteFolder_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("DB Error"));

        historyDAO.deleteFolder(1, "testUser");
    }

    @Test
    void testDeleteQuizSetFromFolder_Failure() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(0);

        boolean result = historyDAO.deleteQuizSetFromFolder(1, 1);

        assertFalse(result);
    }

    @Test
    void testGetAllCreatedQuizSet_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("DB Error"));
        historyDAO.getAllCreatedQuizSet("testUser");
    }

    @Test
    void testGetAllQuizSetByFolderId_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("DB Error"));
        historyDAO.getAllQuizSetByFolderId(1);
    }

    @Test
    void testGetFolderByFolderId_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("DB Error"));
        historyDAO.getFolderByFolderId(1);
    }

    @Test
    void testDeleteFolder_SQLExceptionOnSecondStatement() throws SQLException {
        PreparedStatement st1 = mock(PreparedStatement.class);
        when(connection.prepareStatement("DELETE FROM Folder_Contain WHERE Folder_ID = ?")).thenReturn(st1);
        when(connection.prepareStatement("DELETE FROM Folder WHERE Folder_ID = ? AND UserName = ?"))
                .thenThrow(new SQLException("DB Error"));
        historyDAO.deleteFolder(1, "testUser");
    }

    @Test
    void testGetAllHistoryQuizSet_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("DB Error"));
        historyDAO.getAllHistoryQuizSet("testUser");
    }

    @Test
    void testDeleteQuizSetFromFolder_Success() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1); // rowsAffected > 0

        boolean result = historyDAO.deleteQuizSetFromFolder(1, 1);

        assertTrue(result); // Kiểm tra nhánh if (rowsAffected > 0)
        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).setInt(2, 1);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testDeleteQuizSetFromFolder_NoRowsAffected() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(0); // rowsAffected = 0

        boolean result = historyDAO.deleteQuizSetFromFolder(1, 1);

        assertFalse(result); // Kiểm tra nhánh else của if (rowsAffected > 0)
        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).setInt(2, 1);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testDeleteQuizSetFromFolder_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("DB Error"));

        boolean result = historyDAO.deleteQuizSetFromFolder(1, 1);

        assertFalse(result); // Kiểm tra trả về false khi có SQLException
    }

    // Test cho addQuizSetToFolder
    @Test
    void testAddQuizSetToFolder_Success() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1); // rowsAffected > 0

        boolean result = historyDAO.addQuizSetToFolder(1, 1);

        assertTrue(result); // Kiểm tra nhánh if (rowsAffected > 0)
        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).setInt(2, 1);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testAddQuizSetToFolder_NoRowsAffected() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(0); // rowsAffected = 0

        boolean result = historyDAO.addQuizSetToFolder(1, 1);

        assertFalse(result); // Kiểm tra nhánh else của if (rowsAffected > 0)
        verify(preparedStatement).setInt(1, 1);
        verify(preparedStatement).setInt(2, 1);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testAddQuizSetToFolder_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("DB Error"));

        boolean result = historyDAO.addQuizSetToFolder(1, 1);

        assertFalse(result);
    }

}