package test;

import dal.FolderDAO;
import model.Folder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FolderDAOTest {
    @Mock private Connection mockConnection;
    @Mock private PreparedStatement mockStatement;
    @Mock private ResultSet mockResultSet;

    private FolderDAO folderDAO;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        folderDAO = new FolderDAO();
        folderDAO.connection = mockConnection;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
    }

    @Test
    void testGetAllFoldersByUsername_SingleResult() throws Exception {
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("Folder_ID")).thenReturn(1);
        when(mockResultSet.getString("Folder_Name")).thenReturn("Math");
        when(mockResultSet.getString("UserName")).thenReturn("EasyQuizUser");
        when(mockResultSet.getInt("QuizSetCount")).thenReturn(5);

        List<Folder> folders = folderDAO.getAllFoldersByUsername("EasyQuizUser");

        assertNotNull(folders);
        assertEquals(1, folders.size());
        assertEquals(1, folders.get(0).getFolderId());
        assertEquals("Math", folders.get(0).getFolderName());
        assertEquals("EasyQuizUser", folders.get(0).getUserName());
        assertEquals(5, folders.get(0).getQuizSetCount());
    }

    @Test
    void testGetAllFoldersByUsername_MultipleResults() throws Exception {
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("Folder_ID")).thenReturn(1, 2);
        when(mockResultSet.getString("Folder_Name")).thenReturn("Math", "Science");
        when(mockResultSet.getString("UserName")).thenReturn("EasyQuizUser", "EasyQuizUser");
        when(mockResultSet.getInt("QuizSetCount")).thenReturn(5, 3);

        List<Folder> folders = folderDAO.getAllFoldersByUsername("EasyQuizUser");

        assertNotNull(folders);
        assertEquals(2, folders.size());

        assertEquals(1, folders.get(0).getFolderId());
        assertEquals("Math", folders.get(0).getFolderName());
        assertEquals(5, folders.get(0).getQuizSetCount());

        assertEquals(2, folders.get(1).getFolderId());
        assertEquals("Science", folders.get(1).getFolderName());
        assertEquals(3, folders.get(1).getQuizSetCount());
    }

    @Test
    void testGetAllFoldersByUsername_NoResults() throws Exception {
        when(mockResultSet.next()).thenReturn(false);

        List<Folder> folders = folderDAO.getAllFoldersByUsername("UnknownUser");

        assertNotNull(folders);
        assertTrue(folders.isEmpty());
    }

    @Test
    void testGetAllFoldersByUsername_SQLException() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        List<Folder> folders = folderDAO.getAllFoldersByUsername("EasyQuizUser");

        assertNotNull(folders);
        assertTrue(folders.isEmpty());
    }
}