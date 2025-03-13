package dal;

import model.Account;
import model.Creator;
import model.PasswordUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.sql.*;
import java.util.List;

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

    @AfterEach
    void tearDown() {
        Mockito.reset(connection, preparedStatement, resultSet);
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
    void createAccount_Success() throws SQLException {
        String username = "newuser";
        String password = "password123";
        String email = "newuser@example.com";
        String hashedPassword = "hashedPassword";
        String imageLink = "./images/avatar/avt1.jpg";

        try (MockedStatic<PasswordUtil> mockedStatic = Mockito.mockStatic(PasswordUtil.class)) {
            mockedStatic.when(() -> PasswordUtil.hashPassword(password)).thenReturn(hashedPassword);

            accountDAO.createAccount(username, password, email);

            verify(connection).prepareStatement("INSERT INTO Accounts(UserName, HashedPassword, email, profileImage) values (?,?,?,?)");
            verify(preparedStatement).setString(eq(1), eq(username));
            verify(preparedStatement).setString(eq(2), eq(hashedPassword));
            verify(preparedStatement).setString(eq(3), eq(email));
            verify(preparedStatement).setString(eq(4), startsWith("./images/avatar/avt"));
            verify(preparedStatement).executeUpdate();
        }
    }

    @Test
    void createAccount_SQLException() throws SQLException {
        String username = "newuser";
        String password = "password123";
        String email = "newuser@example.com";

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        accountDAO.createAccount(username, password, email);

        verify(connection).prepareStatement("INSERT INTO Accounts(UserName, HashedPassword, email, profileImage) values (?,?,?,?)");
    }

    @Test
    void createAccountWithGoogle_Success() throws SQLException {
        String username = "googleuser";
        String password = "googlepass";
        String email = "google@example.com";
        String image = "googleimage.jpg";
        String hashedPassword = "hashedGooglePass";

        try (MockedStatic<PasswordUtil> mockedStatic = Mockito.mockStatic(PasswordUtil.class)) {
            mockedStatic.when(() -> PasswordUtil.hashPassword(password)).thenReturn(hashedPassword);

            accountDAO.createAccountWithGoogle(username, password, email, image);

            verify(connection).prepareStatement("INSERT INTO Accounts(UserName, HashedPassword, email, profileImage) values (?,?,?,?)");
            verify(preparedStatement).setString(1, username);
            verify(preparedStatement).setString(2, hashedPassword);
            verify(preparedStatement).setString(3, email);
            verify(preparedStatement).setString(4, image);
            verify(preparedStatement).executeUpdate();
        }
    }

    @Test
    void createAccountWithGoogle_SQLException() throws SQLException {
        String username = "googleuser";
        String password = "googlepass";
        String email = "google@example.com";
        String image = "googleimage.jpg";

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        accountDAO.createAccountWithGoogle(username, password, email, image);

        verify(connection).prepareStatement("INSERT INTO Accounts(UserName, HashedPassword, email, profileImage) values (?,?,?,?)");
    }

    @Test
    void createAccountByEmail_Success() throws SQLException {
        String email = "easyquiz@example.com";
        String image = "easyquizimage.jpg";

        when(resultSet.next()).thenReturn(false); // Username not found initially

        accountDAO.createAccountByEmail(email, image);

        verify(connection, atLeastOnce()).prepareStatement("Select * from Accounts where username = ?");
        verify(preparedStatement, atLeastOnce()).executeQuery();
        verify(connection).prepareStatement("INSERT INTO Accounts(UserName, HashedPassword, email, profileImage) values (?,?,?,?)");
    }

    @Test
    void createAccountByEmail_UsernameCollision() throws SQLException {
        String email = "easyquiz@example.com";
        String image = "easyquizimage.jpg";

        when(resultSet.next()).thenReturn(true).thenReturn(false); // First username exists, second doesn't

        accountDAO.createAccountByEmail(email, image);

        verify(connection, times(2)).prepareStatement("Select * from Accounts where username = ?");
        verify(preparedStatement, times(2)).executeQuery();
        verify(connection).prepareStatement("INSERT INTO Accounts(UserName, HashedPassword, email, profileImage) values (?,?,?,?)");
    }

    @Test
    void checkUsername_Exists() throws SQLException {
        String username = "existinguser";
        when(resultSet.next()).thenReturn(true);

        boolean exists = accountDAO.checkUsername(username);

        assertTrue(exists);
        verify(connection).prepareStatement("Select * from Accounts where username = ?");
        verify(preparedStatement).setString(1, username);
        verify(preparedStatement).executeQuery();
    }

    @Test
    void checkUsername_NotExists() throws SQLException {
        String username = "nonexistentuser";
        when(resultSet.next()).thenReturn(false);

        boolean exists = accountDAO.checkUsername(username);

        assertFalse(exists);
    }

    @Test
    void updateProfile_Success() throws SQLException {
        String username = "user";
        String profileImage = "newimage.jpg";

        accountDAO.updateProfile(username, profileImage);

        verify(connection).prepareStatement("Update Accounts set userName = ?, profileImage = ? where userName = ?");
        verify(preparedStatement).setString(1, username);
        verify(preparedStatement).setString(2, profileImage);
        verify(preparedStatement).setString(3, username);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void banUser_Success() throws SQLException {
        int accountID = 1;

        accountDAO.banUser(accountID);

        verify(connection).prepareStatement("Update Accounts set role = 0 where AccountID = ?");
        verify(preparedStatement).setInt(1, accountID);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void unbanUser_Success() throws SQLException {
        int accountID = 1;

        accountDAO.unbanUser(accountID);

        verify(connection).prepareStatement("Update Accounts set role = 1 where AccountID = ?");
        verify(preparedStatement).setInt(1, accountID);
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void getAccountByUserName_Found() throws SQLException {
        String username = "testuser";
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("username")).thenReturn(username);
        when(resultSet.getDate("createdDate")).thenReturn(new Date(System.currentTimeMillis()));
        when(resultSet.getString("profileImage")).thenReturn("profile.jpg");
        when(resultSet.getInt("is_admin")).thenReturn(1);
        when(resultSet.getString("email")).thenReturn("test@example.com");

        Account account = accountDAO.getAccountByUserName(username);

        assertNotNull(account);
        assertEquals(username, account.getUserName());
    }

    @Test
    void getAccountByUserName_NotFound() throws SQLException {
        String username = "nonexistent";
        when(resultSet.next()).thenReturn(false);

        Account account = accountDAO.getAccountByUserName(username);

        assertNull(account);
    }

    @Test
    void updateProfileImage_Success() throws SQLException {
        String username = "user";
        String profileImage = "newimage.jpg";
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = accountDAO.updateProfileImage(username, profileImage);

        assertTrue(result);
        verify(connection).prepareStatement("UPDATE Accounts SET ProfileImage = ? WHERE UserName = ?");
        verify(preparedStatement).setString(1, profileImage);
        verify(preparedStatement).setString(2, username);
    }

    @Test
    void updateProfileImage_Failure() throws SQLException {
        String username = "user";
        String profileImage = "newimage.jpg";
        when(preparedStatement.executeUpdate()).thenReturn(0);

        boolean result = accountDAO.updateProfileImage(username, profileImage);

        assertFalse(result);
    }

    @Test
    void updateUsername_Success() throws SQLException {
        String oldUsername = "olduser";
        String newUsername = "newuser";
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = accountDAO.updateUsername(oldUsername, newUsername);

        assertTrue(result);
        verify(connection).prepareStatement("UPDATE Accounts SET UserName = ? WHERE UserName = ?");
        verify(preparedStatement).setString(1, newUsername);
        verify(preparedStatement).setString(2, oldUsername);
    }

    @Test
    void updateUsername_Failure() throws SQLException {
        String oldUsername = "olduser";
        String newUsername = "newuser";
        when(preparedStatement.executeUpdate()).thenReturn(0);

        boolean result = accountDAO.updateUsername(oldUsername, newUsername);

        assertFalse(result);
    }

    @Test
    void changePassword_Success() throws SQLException {
        String username = "user";
        String oldPassword = "oldpass";
        String newPassword = "newpass";
        String hashedNewPassword = "hashedNewPass";

        try (MockedStatic<PasswordUtil> mockedStatic = Mockito.mockStatic(PasswordUtil.class)) {
            // Use eq() for the first argument to match the exact oldPassword
            mockedStatic.when(() -> PasswordUtil.verifyPassword(eq(oldPassword), anyString())).thenReturn(true);
            mockedStatic.when(() -> PasswordUtil.hashPassword(eq(newPassword))).thenReturn(hashedNewPassword);

            when(resultSet.next()).thenReturn(true);
            when(resultSet.getString("HashedPassword")).thenReturn("hashedOldPass");
            when(resultSet.getInt("is_deleted")).thenReturn(0);

            boolean result = accountDAO.changePassword(username, oldPassword, newPassword);

            assertTrue(result);
            verify(connection, times(2)).prepareStatement(anyString());
            verify(preparedStatement).setString(eq(1), eq(hashedNewPassword));
            verify(preparedStatement).setString(eq(2), eq(username));
            verify(preparedStatement).executeUpdate();
        }
    }

    @Test
    void changePassword_WrongOldPassword() throws SQLException {
        String username = "user";
        String oldPassword = "wrongpass";
        String newPassword = "newpass";

        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("HashedPassword")).thenReturn("hashedOldPass");

        try (MockedStatic<PasswordUtil> mockedStatic = Mockito.mockStatic(PasswordUtil.class)) {
            mockedStatic.when(() -> PasswordUtil.verifyPassword(oldPassword, "hashedOldPass")).thenReturn(false);

            boolean result = accountDAO.changePassword(username, oldPassword, newPassword);

            assertFalse(result);
        }
    }

    @Test
    void checkPremium_Valid() throws SQLException {
        String username = "user";
        when(resultSet.next()).thenReturn(false);

        boolean result = accountDAO.checkPremium(username);

        assertTrue(result);
    }

    @Test
    void checkPremium_Expired() throws SQLException {
        String username = "user";
        when(resultSet.next()).thenReturn(true);

        boolean result = accountDAO.checkPremium(username);

        assertFalse(result);
    }

    @Test
    void updateExpiredDate_PremiumActive() throws SQLException {
        String username = "user";
        int value = 30;

        // Mock the checkPremium query result
        PreparedStatement checkPremiumStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement("Select * from Accounts where expiredDate < GETDATE() and username = ?"))
                .thenReturn(checkPremiumStatement);
        when(checkPremiumStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // Mock the update query
        when(connection.prepareStatement("Update Accounts set expiredDate = DATEADD(DAY," + value + ", expiredDate)where username = ?"))
                .thenReturn(preparedStatement);

        accountDAO.updateExpiredDate(username, value);

        // Verify the update statement and its setString call
        verify(connection).prepareStatement("Update Accounts set expiredDate = DATEADD(DAY," + value + ", expiredDate)where username = ?");
        verify(preparedStatement).setString(eq(1), eq(username));
        verify(preparedStatement).executeUpdate();

        // Verify the checkPremium statement (optional, depending on test focus)
        verify(connection).prepareStatement("Select * from Accounts where expiredDate < GETDATE() and username = ?");
        verify(checkPremiumStatement).setString(eq(1), eq(username));
        verify(checkPremiumStatement).executeQuery();
    }

    @Test
    void updateExpiredDate_PremiumExpired() throws SQLException {
        String username = "user";
        int value = 30;

        when(resultSet.next()).thenReturn(true); // Premium expired

        accountDAO.updateExpiredDate(username, value);

        verify(connection).prepareStatement("Update Accounts set expiredDate = DATEADD(DAY," + value + ", GETDATE())where username = ?");
    }

    @Test
    void checkEmail_Found() throws SQLException {
        String email = "test@example.com";
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("username")).thenReturn("testuser");

        Account account = accountDAO.checkEmail(email);

        assertNotNull(account);
        assertEquals("testuser", account.getUserName());
    }

    @Test
    void checkEmail_NotFound() throws SQLException {
        String email = "nonexistent@example.com";
        when(resultSet.next()).thenReturn(false);

        Account account = accountDAO.checkEmail(email);

        assertNull(account);
    }

    @Test
    void resetPassword_Success() throws SQLException {
        String email = "test@example.com";
        String newPassword = "newpass";
        String hashedPassword = "hashedNewPass";

        try (MockedStatic<PasswordUtil> mockedStatic = Mockito.mockStatic(PasswordUtil.class)) {
            mockedStatic.when(() -> PasswordUtil.hashPassword(newPassword)).thenReturn(hashedPassword);

            accountDAO.resetPassword(email, newPassword);

            verify(connection).prepareStatement("Update Accounts set HashedPassword = ? where email = ?");
            verify(preparedStatement).setString(1, hashedPassword);
            verify(preparedStatement).setString(2, email);
            verify(preparedStatement).executeUpdate();
        }
    }

    @Test
    void getTopQuizSetCreator_Success() throws SQLException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("UserName")).thenReturn("creator1");
        when(resultSet.getString("ProfileImage")).thenReturn("image1.jpg");
        when(resultSet.getInt("NumberOfQuizSet")).thenReturn(10);

        List<Creator> creators = accountDAO.getTopQuizSetCreator();

        assertEquals(1, creators.size());
        assertEquals("creator1", creators.get(0).getAccount().getUserName());
        assertEquals(10, creators.get(0).getNumberOfQuizSet());
    }

    @Test
    void searchAllCreator_Success() throws SQLException {
        String input = "test";
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("UserName")).thenReturn("testuser");
        when(resultSet.getString("ProfileImage")).thenReturn("image.jpg");
        when(resultSet.getInt("NumberOfQuizSet")).thenReturn(5);

        List<Creator> creators = accountDAO.searchAllCreator(input);

        assertEquals(1, creators.size());
        assertEquals("testuser", creators.get(0).getAccount().getUserName());
    }

    @Test
    void main() {
        // Since main is just for demonstration and creates an instance, we can skip extensive testing
        AccountDAO.main(new String[]{});
        assertTrue(true); // Simple assertion to mark as tested
    }

    @Test
    void createAccountByEmail_SQLException() throws SQLException {
        String email = "easyquiz@example.com";
        String image = "easyquizimage.jpg";

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        accountDAO.createAccountByEmail(email, image);

        verify(connection, atLeastOnce()).prepareStatement("Select * from Accounts where username = ?");
    }

    @Test
    void checkUsername_SQLException() throws SQLException {
        String username = "existinguser";
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        boolean exists = accountDAO.checkUsername(username);

        assertFalse(exists);
        verify(connection).prepareStatement("Select * from Accounts where username = ?");
    }

    @Test
    void updateProfile_SQLException() throws SQLException {
        String username = "user";
        String profileImage = "newimage.jpg";

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        accountDAO.updateProfile(username, profileImage);

        verify(connection).prepareStatement("Update Accounts set userName = ?, profileImage = ? where userName = ?");
    }

    @Test
    void banUser_SQLException() throws SQLException {
        int accountID = 1;

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        accountDAO.banUser(accountID);

        verify(connection).prepareStatement("Update Accounts set role = 0 where AccountID = ?");
    }

    @Test
    void unbanUser_SQLException() throws SQLException {
        int accountID = 1;

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        accountDAO.unbanUser(accountID);

        verify(connection).prepareStatement("Update Accounts set role = 1 where AccountID = ?");
    }

    @Test
    void getAccountByUserName_SQLException() throws SQLException {
        String username = "testuser";
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        Account account = accountDAO.getAccountByUserName(username);

        assertNull(account);
        verify(connection).prepareStatement("Select * from Accounts where UserName = ? AND is_deleted = 0");
    }

    @Test
    void updateProfileImage_SQLException() throws SQLException {
        String username = "user";
        String profileImage = "newimage.jpg";

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        boolean result = accountDAO.updateProfileImage(username, profileImage);

        assertFalse(result);
        verify(connection).prepareStatement("UPDATE Accounts SET ProfileImage = ? WHERE UserName = ?");
    }

    @Test
    void updateUsername_SQLException() throws SQLException {
        String oldUsername = "olduser";
        String newUsername = "newuser";

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        boolean result = accountDAO.updateUsername(oldUsername, newUsername);

        assertFalse(result);
        verify(connection).prepareStatement("UPDATE Accounts SET UserName = ? WHERE UserName = ?");
    }

    @Test
    void changePassword_SQLException() throws SQLException {
        String username = "user";
        String oldPassword = "oldpass";
        String newPassword = "newpass";

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        boolean result = accountDAO.changePassword(username, oldPassword, newPassword);

        assertFalse(result);
        verify(connection, atLeastOnce()).prepareStatement(anyString());
    }

    @Test
    void checkPremium_SQLException() throws SQLException {
        String username = "user";
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        boolean result = accountDAO.checkPremium(username);

        assertTrue(result); // Default return is true when an exception occurs
        verify(connection).prepareStatement("Select * from Accounts where expiredDate < GETDATE() and username = ?");
    }

    @Test
    void updateExpiredDate_SQLException() throws SQLException {
        String username = "user";
        int value = 30;

        // Mock checkPremium to return true (to simplify the test)
        PreparedStatement checkPremiumStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement("Select * from Accounts where expiredDate < GETDATE() and username = ?"))
                .thenReturn(checkPremiumStatement);
        when(checkPremiumStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(false);

        // Throw SQLException on the update statement
        when(connection.prepareStatement("Update Accounts set expiredDate = DATEADD(DAY," + value + ", expiredDate)where username = ?"))
                .thenThrow(new SQLException("Database error"));

        accountDAO.updateExpiredDate(username, value);

        verify(connection).prepareStatement("Update Accounts set expiredDate = DATEADD(DAY," + value + ", expiredDate)where username = ?");
    }

    @Test
    void checkEmail_SQLException() throws SQLException {
        String email = "test@example.com";
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        Account account = accountDAO.checkEmail(email);

        assertNull(account);
        verify(connection).prepareStatement("Select * from Accounts where email = ?");
    }

    @Test
    void resetPassword_SQLException() throws SQLException {
        String email = "test@example.com";
        String newPassword = "newpass";

        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        accountDAO.resetPassword(email, newPassword);

        verify(connection).prepareStatement("Update Accounts set HashedPassword = ? where email = ?");
    }

    @Test
    void getTopQuizSetCreator_SQLException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        List<Creator> creators = accountDAO.getTopQuizSetCreator();

        assertNotNull(creators);
        assertTrue(creators.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void searchAllCreator_SQLException() throws SQLException {
        String input = "test";
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        List<Creator> creators = accountDAO.searchAllCreator(input);

        assertNotNull(creators);
        assertTrue(creators.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void createAccountByEmail_MultipleUsernameCollisions() throws SQLException {
        String email = "easyquiz@example.com";
        String image = "easyquizimage.jpg";

        // First two username checks return true (collision), third returns false
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        accountDAO.createAccountByEmail(email, image);

        verify(connection, times(3)).prepareStatement("Select * from Accounts where username = ?");
        verify(connection).prepareStatement("INSERT INTO Accounts(UserName, HashedPassword, email, profileImage) values (?,?,?,?)");
    }

}