package dal;

import dal.StatisDAO;
import model.Account;
import model.Payment;
import model.UserStatis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatisDAOTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private StatisDAO statisDAO;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        statisDAO = new StatisDAO();
        statisDAO.connection = mockConnection; // Gán connection mock vào DAO
    }

    @Test
    void testGetNumberOfQuiz() throws Exception {
        when(mockConnection.prepareStatement("select count(*) from Quiz_Set")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(5);

        int count = statisDAO.getNumberOfQuiz();
        assertEquals(5, count);
    }

    @Test
    void testGetNumberOfUser() throws Exception {
        when(mockConnection.prepareStatement("select count(*) from Accounts")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(10);

        int count = statisDAO.getNumberOfUser();
        assertEquals(10, count);
    }

    @Test
    void testGetNumberOfTransaction() throws Exception {
        when(mockConnection.prepareStatement("select count(*) from Transaction_History")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt(1)).thenReturn(15);

        int count = statisDAO.getNumberOfTransaction();
        assertEquals(15, count);
    }

    @Test
    void testGetTotalRevenue() throws Exception {
        when(mockConnection.prepareStatement("select sum(Amount) from Transaction_History\nwhere Status = 'PAID'"))
                .thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getLong(1)).thenReturn(5000L);

        long revenue = statisDAO.getTotalRevenue();
        assertEquals(5000L, revenue);
    }

    @Test
    void testGetNewCreatedUser() throws Exception {
        when(mockConnection.prepareStatement("select top (6) UserName, profileImage from Accounts order by CreatedDate desc"))
                .thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getString("UserName")).thenReturn("user1", "user2");
        when(mockResultSet.getString("profileImage")).thenReturn("img1.png", "img2.png");

        List<Account> users = statisDAO.getNewCreatedUser();
        assertEquals(2, users.size());
        assertEquals("user1", users.get(0).getUserName());
        assertEquals("img1.png", users.get(0).getProfileImage());
    }

    @Test
    void testGetAllUserStatistics() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("UserName")).thenReturn("testUser");
        when(mockResultSet.getString("Email")).thenReturn("test@example.com");
        when(mockResultSet.getString("ProfileImage")).thenReturn("profile.jpg");
        when(mockResultSet.getInt("NumOfBlog")).thenReturn(3);
        when(mockResultSet.getInt("NumOfComment")).thenReturn(5);
        when(mockResultSet.getInt("NumOfFeedBack")).thenReturn(2);
        when(mockResultSet.getInt("NumOfFolder")).thenReturn(4);
        when(mockResultSet.getInt("NumOfQuiz")).thenReturn(6);

        List<UserStatis> stats = statisDAO.getAllUserStatistics();
        assertEquals(1, stats.size());
        assertEquals("testUser", stats.get(0).getUserName());
    }

    @Test
    void testGetNewPayments() throws Exception {
        when(mockConnection.prepareStatement("SELECT Top(6) * FROM Transaction_History where Status = 'PAID' ORDER BY Created_Date DESC"))
                .thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("transaction_id")).thenReturn("TX123");
        when(mockResultSet.getString("order_code")).thenReturn("ORD456");
        when(mockResultSet.getInt("amount")).thenReturn(100);
        when(mockResultSet.getString("status")).thenReturn("PAID");
        when(mockResultSet.getString("description")).thenReturn("Test Payment");
        when(mockResultSet.getString("UserName")).thenReturn("testUser");
        when(mockResultSet.getDate("Created_Date")).thenReturn(Date.valueOf("2024-03-01"));

        List<Payment> payments = statisDAO.getNewPayments();
        assertEquals(1, payments.size());
        assertEquals("TX123", payments.get(0).getTransactionId());
    }

    @Test
    void testGetNumberOfQuiz_Exception() throws Exception {
        when(mockConnection.prepareStatement("select count(*) from Quiz_Set"))
                .thenThrow(new SQLException("Database error"));

        int count = statisDAO.getNumberOfQuiz();
        assertEquals(0, count); // Kiểm tra xem DAO có trả về giá trị mặc định khi lỗi không
    }

    @Test
    void testGetNumberOfUser_Exception() throws Exception {
        when(mockConnection.prepareStatement("select count(*) from Accounts"))
                .thenThrow(new SQLException("SQL Error"));

        int count = statisDAO.getNumberOfUser();
        assertEquals(0, count);
    }

    @Test
    void testGetNumberOfTransaction_Exception() throws Exception {
        when(mockConnection.prepareStatement("select count(*) from Transaction_History"))
                .thenThrow(new SQLException("SQL Error"));

        int count = statisDAO.getNumberOfTransaction();
        assertEquals(0, count);
    }

    @Test
    void testGetTotalRevenue_Exception() throws Exception {
        when(mockConnection.prepareStatement("select sum(Amount) from Transaction_History\nwhere Status = 'PAID'"))
                .thenThrow(new SQLException("SQL Error"));

        long revenue = statisDAO.getTotalRevenue();
        assertEquals(0, revenue);
    }

    @Test
    void testGetNewCreatedUser_Exception() throws Exception {
        when(mockConnection.prepareStatement("select top (6) UserName, profileImage from Accounts order by CreatedDate desc"))
                .thenThrow(new SQLException("SQL Error"));

        List<Account> users = statisDAO.getNewCreatedUser();
        assertNotNull(users);
        assertEquals(0, users.size()); // Kiểm tra danh sách trả về có rỗng không
    }

    @Test
    void testGetAllUserStatistics_Exception() throws Exception {
        when(mockConnection.prepareStatement(anyString()))
                .thenThrow(new SQLException("SQL Error"));

        List<UserStatis> stats = statisDAO.getAllUserStatistics();
        assertNotNull(stats);
        assertEquals(0, stats.size());
    }

}

