package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Payment;

public class PaymentDAO extends DBContext {

    // Method to save payment information
    public void savePayment(Payment payment) {
        String sql = "INSERT INTO Transaction_History (transaction_id, order_code, amount, username, status, Description) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        try ( 
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, payment.getTransactionId());
            stmt.setString(2, payment.getOrderCode());
            stmt.setInt(3, payment.getAmount());
            stmt.setString(4, payment.getUserName());
            stmt.setString(5, payment.getStatus());
            stmt.setString(6, payment.getDescription());
            stmt.executeUpdate();
            System.out.println("Payment saved successfully: " + payment.getTransactionId());
        } catch (SQLException e) {
            System.err.println("Error saving payment: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error saving payment: " + e.getMessage(), e);
        }
    }

    // Method to get all payments
    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String sql = "SELECT * FROM Transaction_History ORDER BY created_at DESC";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                payments.add(mapPayment(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving payments: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error retrieving payments: " + e.getMessage(), e);
        }
        return payments;
    }

    // Get payment by transaction ID
    public Payment getPaymentByTransactionId(String transactionId) {
        String sql = "SELECT * FROM Transaction_History WHERE transaction_id = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transactionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapPayment(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving payment: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error retrieving payment: " + e.getMessage(), e);
        }
        return null;
    }

    // Update payment status
    public boolean updatePaymentStatus(String transactionId, String status) {
        String sql = "UPDATE Transaction_History SET status = ? WHERE transaction_id = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, transactionId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Payment status updated successfully: " + transactionId + " -> " + status);
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Error updating payment status: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error updating payment status: " + e.getMessage(), e);
        }
    }

    // Delete payment
    public boolean deletePayment(String transactionId) {
        String sql = "DELETE FROM Transaction_History WHERE transaction_id = ?";
        try (
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transactionId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting payment: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error deleting payment: " + e.getMessage(), e);
        }
    }

    // Helper method to map ResultSet to Payment object
    private Payment mapPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setTransactionId(rs.getString("transaction_id"));
        payment.setOrderCode(rs.getString("order_code"));
        payment.setAmount(rs.getInt("amount"));
        payment.setStatus(rs.getString("status"));
        payment.setDescription(rs.getString("description"));
        return payment;
    }

    // Test the database connection
    public static void main(String[] args) {
        PaymentDAO dao = new PaymentDAO();
        
        // Test saving a payment
        Payment testPayment = new Payment();
        testPayment.setTransactionId("TEST-" + System.currentTimeMillis());
        testPayment.setOrderCode("OR123");
        testPayment.setAmount(1000);
        testPayment.setUserName("EasyQuiz343293");
        testPayment.setStatus("PENDING");
        testPayment.setDescription("Test payment");
        
        try {
            dao.savePayment(testPayment);
            System.out.println("Test payment saved successfully");
            
            // Retrieve and verify
            Payment saved = dao.getPaymentByTransactionId(testPayment.getTransactionId());
            if (saved != null) {
                System.out.println("Retrieved payment: " + saved.getTransactionId());
            }
        } catch (Exception e) {
            System.err.println("Test failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
