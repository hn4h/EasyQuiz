/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import model.Transaction;

/**
 *
 * @author 11
 */
public class TransactionDAO extends DBContext{

    public boolean saveTransaction(Transaction transaction) {
        String sql = "INSERT INTO payments (transaction_id, order_code, amount, currency, status, description) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, transaction.getTransactionId());
            ps.setString(2, transaction.getOrderCode());
            ps.setFloat(3, (float) transaction.getAmount());
            ps.setString(4, transaction.getCurrency());
            ps.setString(5, transaction.getStatus());
            ps.setString(6, transaction.getDescription());
            ps.executeUpdate();
            System.out.println("Payment saved successfully: " + transaction.getTransactionId());
            return true;
        } catch (SQLException e) {
            System.err.println("Error saving payment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
        
    }

    public boolean updatePaymentStatus(String transactionId, String status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
