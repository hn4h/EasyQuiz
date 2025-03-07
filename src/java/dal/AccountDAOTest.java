package dal;

import dal.AccountDAO;
import model.Account;
import model.PasswordUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private AccountDAO accountDAO;

    @BeforeEach
    void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    void getAccountByEmail_AccountFound() throws SQLException {
        String email = "test@example.com";
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("username")).thenReturn("testuser");
        when(resultSet.getString("profileImage")).thenReturn("profile.jpg");
        when(resultSet.getString("email")).thenReturn(email);

        Account account = accountDAO.getAccountByEmail(email);

        assertNotNull(account);
        assertEquals("testuser", account.getUserName());
        assertEquals("profile.jpg", account.getProfileImage());
        assertEquals(email, account.getEmail());

        verify(connection).prepareStatement("Select * from Accounts where email = ?");
        verify(preparedStatement).setString(1, email);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
        verify(resultSet).getString("username");
        verify(resultSet).getString("profileImage");
        verify(resultSet).getString("email");
    }

    @Test
    void getAccountByEmail_AccountNotFound() throws SQLException {
        String email = "nonexistent@example.com";
        when(resultSet.next()).thenReturn(false);

        Account account = accountDAO.getAccountByEmail(email);

        assertNull(account);

        verify(connection).prepareStatement("Select * from Accounts where email = ?");
        verify(preparedStatement).setString(1, email);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
    }

    @Test
    void getAccountByEmail_SQLException() throws SQLException {
        String email = "test@example.com";
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        Account account = accountDAO.getAccountByEmail(email);

        assertNull(account);

        verify(connection).prepareStatement("Select * from Accounts where email = ?");
    }


    @Test
    void checkAuthen_Success() throws SQLException {
        String email = "test@example.com";
        String password = "password";
        String hashedPassword = "hashedPassword";

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("username")).thenReturn("testuser");
        when(resultSet.getString("HashedPassword")).thenReturn(hashedPassword);
        when(resultSet.getDate("createdDate")).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString("profileImage")).thenReturn("profile.jpg");
        when(resultSet.getInt("is_admin")).thenReturn(1);
        when(resultSet.getDate("expiredDate")).thenReturn(new Date(System.currentTimeMillis() + 86400000));
        when(resultSet.getString("email")).thenReturn(email);
        when(resultSet.getInt("is_deleted")).thenReturn(0);

        try (MockedStatic<PasswordUtil> mockedStatic = Mockito.mockStatic(PasswordUtil.class)) {
            mockedStatic.when(() -> PasswordUtil.verifyPassword(password, hashedPassword)).thenReturn(true);

            Account account = accountDAO.checkAuthen(email, password);

            assertNotNull(account);
            assertEquals("testuser", account.getUserName());
            assertEquals(email, account.getEmail());
            assertTrue(account.isIsAdmin());
        }

        verify(connection).prepareStatement("Select * from Accounts where Email = ?");
        verify(preparedStatement).setString(1, email);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
    }

    @Test
    void checkAuthen_PasswordMismatch() throws SQLException {
        String email = "test@example.com";
        String password = "wrongPassword";
        String hashedPassword = "hashedPassword";

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("HashedPassword")).thenReturn(hashedPassword);

        try (MockedStatic<PasswordUtil> mockedStatic = Mockito.mockStatic(PasswordUtil.class)) {
            mockedStatic.when(() -> PasswordUtil.verifyPassword(password, hashedPassword)).thenReturn(false);

            Account account = accountDAO.checkAuthen(email, password);

            assertNull(account);
        }
    }

    @Test
    void checkAuthen_AccountNotFound() throws SQLException {
        String email = "nonexistent@example.com";
        String password = "password";

        when(resultSet.next()).thenReturn(false);

        Account account = accountDAO.checkAuthen(email, password);

        assertNull(account);
    }

    @Test
    void checkAuthen_SQLException() throws SQLException {
        String email = "test@example.com";
        String password = "password";

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        Account account = accountDAO.checkAuthen(email, password);

        assertNull(account);
    }

    @Test
    void checkAuthen_AccountDeleted() throws SQLException {
        String email = "test@example.com";
        String password = "password";
        String hashedPassword = "hashedPassword";

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("HashedPassword")).thenReturn(hashedPassword);
        when(resultSet.getInt("is_deleted")).thenReturn(1);

        try (MockedStatic<PasswordUtil> mockedStatic = Mockito.mockStatic(PasswordUtil.class)) {
            mockedStatic.when(() -> PasswordUtil.verifyPassword(password, hashedPassword)).thenReturn(true);

            Account account = accountDAO.checkAuthen(email, password);

            assertNull(account);
        }
    }

    @Test
    void checkAuthen_NotAdmin() throws SQLException {
        String email = "test@example.com";
        String password = "password";
        String hashedPassword = "hashedPassword";

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("username")).thenReturn("testuser");
        when(resultSet.getString("HashedPassword")).thenReturn(hashedPassword);
        when(resultSet.getDate("createdDate")).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString("profileImage")).thenReturn("profile.jpg");
        when(resultSet.getInt("is_admin")).thenReturn(0); // Đặt is_admin là 0 (không phải admin)
        when(resultSet.getDate("expiredDate")).thenReturn(new Date(System.currentTimeMillis() + 86400000));
        when(resultSet.getString("email")).thenReturn(email);
        when(resultSet.getInt("is_deleted")).thenReturn(0);

        try (MockedStatic<PasswordUtil> mockedStatic = Mockito.mockStatic(PasswordUtil.class)) {
            mockedStatic.when(() -> PasswordUtil.verifyPassword(password, hashedPassword)).thenReturn(true);

            Account account = accountDAO.checkAuthen(email, password);

            assertNotNull(account);
            assertEquals("testuser", account.getUserName());
            assertEquals(email, account.getEmail());
            assertFalse(account.isIsAdmin()); // Kiểm tra isAdmin là false
        }

        verify(connection).prepareStatement("Select * from Accounts where Email = ?");
        verify(preparedStatement).setString(1, email);
        verify(preparedStatement).executeQuery();
        verify(resultSet).next();
    }

}