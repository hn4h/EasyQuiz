package dal;

import org.junit.jupiter.api.Test;

import model.Package;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PackageDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private PackageDAO packageDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }
    //Test lay all package thanh cong
    @Test
    void getAllPackages_SuccessfulQuery() throws SQLException {
        // Arrange
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // Only one record
        when(resultSet.getInt("Transaction_Type_ID")).thenReturn(1);
        when(resultSet.getString("Transaction_Type_Name")).thenReturn("Basic Package");
        when(resultSet.getString("Transaction_Type_Description")).thenReturn("Basic access");
        when(resultSet.getInt("Transaction_Value")).thenReturn(100);
        when(resultSet.getDouble("Transaction_Amount")).thenReturn(9.99);
        when(resultSet.getDate("Created_Date")).thenReturn(Date.valueOf("2024-03-07"));

        // Act
        List<Package> packages = packageDAO.getAllPackages();

        // Assert
        assertNotNull(packages);
        assertEquals(1, packages.size());
        Package p = packages.get(0);
        assertEquals(1, p.getId());
        assertEquals("Basic Package", p.getName());
        assertEquals("Basic access", p.getDescription());
        assertEquals(100, p.getValue());
        assertEquals(9.99, p.getPrice());
        assertEquals(Date.valueOf("2024-03-07"), p.getCreatedDate());

        // Verify interactions
        verify(preparedStatement).executeQuery();
        verify(resultSet, times(2)).next();
    }

    @Test
    void getAllPackages_NoDataFound() throws SQLException {
        // Arrange
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false); // No records found

        // Act
        List<Package> packages = packageDAO.getAllPackages();

        // Assert
        assertNotNull(packages);
        assertTrue(packages.isEmpty());

        // Verify interactions
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
    }

    @Test
    void getAllPackages_SQLException() throws SQLException {
        // Arrange
        when(preparedStatement.executeQuery()).thenThrow(new SQLException("Database error"));

        // Act
        List<Package> packages = packageDAO.getAllPackages();

        // Assert
        assertNotNull(packages);
        assertTrue(packages.isEmpty());
    }
}