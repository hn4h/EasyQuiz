/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Feedback; 

public class FeedbackDAO extends DBContext {

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT Feedback_ID, UserName, Feedback_Content, Feedback_Date FROM Feedback";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setFeedbackId(rs.getInt("Feedback_ID"));
                f.setUserName(rs.getString("UserName"));
                f.setFeedbackContent(rs.getString("Feedback_Content"));
                f.setFeedbackDate(rs.getTimestamp("Feedback_Date"));
                list.add(f);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Feedback> getPagedFeedbacks(int startIndex, int recordsPerPage) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT Feedback_ID, UserName, Feedback_Content, Feedback_Date FROM Feedback ORDER BY Feedback_ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, startIndex);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback();
                f.setFeedbackId(rs.getInt("Feedback_ID"));
                f.setUserName(rs.getString("UserName"));
                f.setFeedbackContent(rs.getString("Feedback_Content"));
                f.setFeedbackDate(rs.getTimestamp("Feedback_Date"));
                list.add(f);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int getTotalFeedbackCount() {
        String sql = "SELECT COUNT(*) FROM Feedback";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public void addFeedback(String username, String feedbackContent) {
        String sql = "INSERT INTO Feedback (UserName, Feedback_Content) VALUES (?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, feedbackContent);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}