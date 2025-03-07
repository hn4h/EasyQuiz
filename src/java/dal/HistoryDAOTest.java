package dal;

import model.Folder;
import org.junit.jupiter.api.Test;
import model.QuizSet;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HistoryDAOTest {
    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private HistoryDAO quizSetDAO; // Giả sử class của bạn tên là QuizSetDAO

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    void getAllFolderByUserName_SuccessfulQuery() throws SQLException {
        // Arrange
        String userName = "testUser";
        String sql = "SELECT \n"
                + "    f.Folder_ID,\n"
                + "    f.Folder_Name,\n"
                + "    f.Folder_Date,\n"
                + "    f.Folder_Description,\n"
                + "    a.UserName,\n"
                + "    a.Email,\n"
                + "    COUNT(fc.Quiz_Set_ID) as QuizSetCount\n"
                + "FROM \n"
                + "    Accounts a\n"
                + "    INNER JOIN Folder f ON a.UserName = f.UserName\n"
                + "    LEFT JOIN Folder_Contain fc ON f.Folder_ID = fc.Folder_ID AND fc.Is_Deleted = 0\n"
                + "WHERE \n"
                + "    a.UserName = ? \n"
                + "    AND a.is_deleted = 0\n"
                + "    AND f.Folder_ID NOT IN (\n"
                + "        SELECT Folder_ID \n"
                + "        FROM Folder_Contain \n"
                + "        WHERE Is_Deleted = 1\n"
                + "    )\n"
                + "GROUP BY \n"
                + "    f.Folder_ID,\n"
                + "    f.Folder_Name,\n"
                + "    f.Folder_Date,\n"
                + "    f.Folder_Description,\n"
                + "    a.UserName,\n"
                + "    a.Email\n"
                + "ORDER BY \n"
                + "    f.Folder_Date DESC";

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false); // 1 record
        when(resultSet.getInt("Folder_ID")).thenReturn(1);
        when(resultSet.getString("Folder_Name")).thenReturn("Test Folder");
        when(resultSet.getDate("Folder_Date")).thenReturn(Date.valueOf("2024-03-06"));
        when(resultSet.getString("Folder_Description")).thenReturn("Sample Description");
        when(resultSet.getInt("QuizSetCount")).thenReturn(3);

        // Act
        List<Folder> result = quizSetDAO.getAllFolderByUserName(userName);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        Folder folder = result.get(0);
        assertEquals(1, folder.getFolderId());
        assertEquals("Test Folder", folder.getFolderName());
        assertEquals(Date.valueOf("2024-03-06"), folder.getFolderDate());
        assertEquals("Folder_Description", folder.getFolderDescription());
        assertEquals(3, folder.getQuizSetCount());

        // Verify interactions
        verify(connection).prepareStatement(sql);
        verify(preparedStatement).setString(1, userName);
        verify(preparedStatement).executeQuery();
    }

    @Test
    void getAllFolderByUserName_EmptyResult() throws SQLException {
        String userName = "testUser";
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false); // No data

        List<Folder> result = quizSetDAO.getAllFolderByUserName(userName);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(preparedStatement).setString(1, userName);
        verify(preparedStatement).executeQuery();
    }

    @Test
    void getAllFolderByUserName_SQLException() throws SQLException {
        String userName = "testUser";
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        List<Folder> result = quizSetDAO.getAllFolderByUserName(userName);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

//    @Test
//    void getAllCreatedQuizSet() throws SQLException {
//        String userName = "testUser";
//        String sql = "select * from Quiz_Set where Author = ?";
//
//        when(connection.prepareStatement(sql)).thenThrow(new SQLException("DB error"))
//                .thenReturn(preparedStatement);
//
//        List<QuizSet> resultWithException = quizSetDAO.getAllCreatedQuizSet(userName);
//        assertNotNull(resultWithException);
//        assertTrue(resultWithException.isEmpty());
//
//        when(preparedStatement.executeQuery()).thenReturn(resultSet);
//        when(resultSet.next()).thenReturn(true).thenReturn(false);
//        when(resultSet.getInt("quiz_Set_ID")).thenReturn(1);
//        when(resultSet.getString("Quiz_Set_Name")).thenReturn("Test Quiz");
//        when(resultSet.getInt("Number_Of_Quiz")).thenReturn(5);
//        when(resultSet.getString("Author")).thenReturn(userName);
//
//        List<QuizSet> resultWithData = quizSetDAO.getAllCreatedQuizSet(userName);
//        assertNotNull(resultWithData);
//        assertEquals(1, resultWithData.size());
//        QuizSet qs = resultWithData.get(0);
//        assertEquals(1, qs.getQuizSetId());
//        assertEquals("Test Quiz", qs.getQuizSetName());
//        assertEquals(5, qs.getNumberOfQuiz());
//        assertEquals(userName, qs.getAuthor().getUserName());
//
//        verify(connection, times(2)).prepareStatement(sql);
//        verify(preparedStatement).setString(1, userName);
//        verify(preparedStatement).executeQuery();
//    }
}