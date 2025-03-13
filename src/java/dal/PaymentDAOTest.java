package dal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

import model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.sql.Connection;

class PaymentDAOTest {

    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement preparedStatement;
    @Mock
    private ResultSet resultSet;

    private PaymentDAO paymentDAO;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Khởi tạo paymentDAO trước khi thiết lập connection
        paymentDAO = new PaymentDAO();

        // Dùng Reflection để inject `connection` vào `DBContext`
        Field connectionField = DBContext.class.getDeclaredField("connection");
        connectionField.setAccessible(true);
        connectionField.set(paymentDAO, connection);

        // Khi `prepareStatement` được gọi với bất kỳ SQL nào, trả về `preparedStatement`
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @Test
    void testSavePayment() throws Exception {
        Payment payment = new Payment();
        payment.setTransactionId("TX123");
        payment.setOrderCode("ORD001");
        payment.setAmount(100);
        payment.setUserName("user1");
        payment.setStatus("Completed");
        payment.setValue(10);
        payment.setDescription("Payment for Order");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        paymentDAO.savePayment(payment);

        verify(preparedStatement, times(1)).setString(1, "TX123");
        verify(preparedStatement, times(1)).setString(2, "ORD001");
        verify(preparedStatement, times(1)).setInt(3, 100);
        verify(preparedStatement, times(1)).setString(4, "user1");
        verify(preparedStatement, times(1)).setString(5, "Completed");
        verify(preparedStatement, times(1)).setInt(6, 10);
        verify(preparedStatement, times(1)).setString(7, "Payment for Order");
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testGetAllPayments() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        when(resultSet.next()).thenReturn(true, true, false); // Giả lập 2 dòng dữ liệu
        when(resultSet.getString("transaction_id")).thenReturn("TX123", "TX124");
        when(resultSet.getString("order_code")).thenReturn("ORD001", "ORD002");
        when(resultSet.getInt("amount")).thenReturn(100, 200);
        when(resultSet.getString("status")).thenReturn("Completed", "Pending");
        when(resultSet.getString("description")).thenReturn("Payment 1", "Payment 2");
        when(resultSet.getString("UserName")).thenReturn("user1", "user2");
        when(resultSet.getInt("value_package")).thenReturn(10, 20);
        when(resultSet.getDate("Created_Date")).thenReturn(new Date(System.currentTimeMillis()));

        List<Payment> payments = paymentDAO.getAllPayments();

        assertEquals(2, payments.size());
        assertEquals("TX123", payments.get(0).getTransactionId());
        assertEquals("TX124", payments.get(1).getTransactionId());
    }

    @Test
    void testGetPaymentByTransactionId() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString("transaction_id")).thenReturn("TX123");
        when(resultSet.getString("order_code")).thenReturn("ORD001");
        when(resultSet.getInt("amount")).thenReturn(100);
        when(resultSet.getString("status")).thenReturn("Completed");
        when(resultSet.getString("description")).thenReturn("Payment 1");
        when(resultSet.getString("UserName")).thenReturn("user1");
        when(resultSet.getInt("value_package")).thenReturn(10);
        when(resultSet.getDate("Created_Date")).thenReturn(new Date(System.currentTimeMillis()));

        Payment payment = paymentDAO.getPaymentByTransactionId("TX123");

        assertNotNull(payment);
        assertEquals("TX123", payment.getTransactionId());
    }

    @Test
    void testUpdatePaymentStatus() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = paymentDAO.updatePaymentStatus("TX123", "Failed");

        assertTrue(result);
        verify(preparedStatement, times(1)).setString(1, "Failed");
        verify(preparedStatement, times(1)).setString(2, "TX123");
        verify(preparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testDeletePayment() throws Exception {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = paymentDAO.deletePayment("TX123");

        assertTrue(result);
        verify(preparedStatement, times(1)).setString(1, "TX123");
        verify(preparedStatement, times(1)).executeUpdate();
    }
}