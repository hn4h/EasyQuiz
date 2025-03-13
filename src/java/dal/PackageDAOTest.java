package dal;

import model.Package;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
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
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Dùng Reflection để inject `connection` vào `DBContext`
        Field connectionField = DBContext.class.getDeclaredField("connection");
        connectionField.setAccessible(true);
        connectionField.set(packageDAO, connection);

        // Khi `prepareStatement` được gọi với bất kỳ SQL nào, trả về `preparedStatement`
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    void testGetAllPackages() throws Exception {
        // Giả lập dữ liệu trả về từ ResultSet
        when(resultSet.next()).thenReturn(true, false); // Có 1 dòng dữ liệu
        when(resultSet.getInt("Package_ID")).thenReturn(1);
        when(resultSet.getString("Package_Name")).thenReturn("Premium");
        when(resultSet.getString("Package_Description")).thenReturn("Full Access");
        when(resultSet.getInt("Package_Value")).thenReturn(10);
        when(resultSet.getInt("Package_Amount")).thenReturn(100);
        when(resultSet.getDate("Created_Date")).thenReturn(Date.valueOf("2024-03-07"));

        // Gọi hàm cần test
        List<Package> packages = packageDAO.getAllPackages();

        // Kiểm tra kết quả
        assertNotNull(packages);
        assertEquals(1, packages.size());

        Package p = packages.get(0);
        assertEquals(1, p.getId());
        assertEquals("Premium", p.getName());
        assertEquals("Full Access", p.getDescription());
        assertEquals(10, p.getValue());
        assertEquals(100, p.getPrice());
        assertEquals(Date.valueOf("2024-03-07"), p.getCreatedDate());

        // Kiểm tra phương thức mock
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeQuery();
        verify(resultSet, times(2)).next();
    }

    @Test
    void testGetAllPackagesForPurchase() throws Exception {
        // Giả lập dữ liệu trả về từ ResultSet
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getString("Package_Name")).thenReturn("Basic");
        when(resultSet.getString("Package_Description")).thenReturn("Limited Access");
        when(resultSet.getInt("Package_Amount")).thenReturn(50);

        // Gọi hàm cần test
        List<Package> packages = packageDAO.getAllPackagesForPurchase();

        // Kiểm tra kết quả
        assertNotNull(packages);
        assertEquals(1, packages.size());

        Package p = packages.get(0);
        assertEquals("Basic", p.getName());
        assertEquals("Limited Access", p.getDescription());
        assertEquals(50, p.getPrice());

        // Kiểm tra phương thức mock
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeQuery();
        verify(resultSet, times(2)).next();
    }

    @Test
    void testGetAllPackagesForDashboard() throws Exception {
        // Giả lập dữ liệu trả về từ ResultSet
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt("Package_ID")).thenReturn(2);
        when(resultSet.getString("Package_Name")).thenReturn("Pro");
        when(resultSet.getString("Package_Description")).thenReturn("Advanced Features");
        when(resultSet.getInt("Package_Value")).thenReturn(20);
        when(resultSet.getInt("Package_Amount")).thenReturn(200);
        when(resultSet.getDate("Created_Date")).thenReturn(Date.valueOf("2024-03-07"));

        // Gọi hàm cần test
        List<Package> packages = packageDAO.getAllPackagesForDashboard();

        // Kiểm tra kết quả
        assertNotNull(packages);
        assertEquals(1, packages.size());

        Package p = packages.get(0);
        assertEquals(2, p.getId());
        assertEquals("Pro", p.getName());
        assertEquals("Advanced Features", p.getDescription());
        assertEquals(20, p.getValue());
        assertEquals(200, p.getPrice());
        assertEquals(Date.valueOf("2024-03-07"), p.getCreatedDate());

        // Kiểm tra phương thức mock
        verify(connection).prepareStatement(anyString());
        verify(preparedStatement).executeQuery();
        verify(resultSet, times(2)).next();
    }
}
