/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import model.Answer;

/**
 *
 * @author 11
 */
public class AnswerDAO extends DBContext {

    public void updateAnswer(Answer answer) {
        String sql = "UPDATE Answer SET Answer_Content = ?, Is_Correct = ? WHERE Answer_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, answer.getContent());
            ps.setBoolean(2, answer.isCorrect());
            ps.setInt(3, answer.getAnswerID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAnswer(int answerID) {
        String sql = "DELETE FROM Answer WHERE Answer_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, answerID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Answer getAnswerById(int answerId) {
        String sql = "SELECT * FROM Answer WHERE Answer_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, answerId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Answer answer = new Answer();
                answer.setAnswerID(rs.getInt("Answer_ID"));
                answer.setContent(rs.getString("Answer_Content"));
                answer.setIsCorrect(rs.getBoolean("Is_Correct"));
                return answer;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteAnswersByQuizId(int quizID) {
        String sql = "DELETE FROM Answer WHERE Quiz_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quizID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
