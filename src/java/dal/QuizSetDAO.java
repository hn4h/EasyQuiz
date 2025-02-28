package dal;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizSetDAO extends DBContext {
    

    public void createQuizSet(String quizSetName, String description, String author) {
        try {
            String sql = "INSERT INTO QuizSet(quizSetName, description, author) VALUES(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, quizSetName);
            ps.setString(2, description);
            ps.setString(3, author);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteQuizSet(int quizSetID) {
        try {
            String sql = "DELETE FROM QuizSet WHERE quizSetID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quizSetID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateQuizSet(int quizSetID, String quizSetName, String description) {
        try {
            String sql = "UPDATE QuizSet SET quizSetName = ?, description = ? WHERE quizSetID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, quizSetName);
            ps.setString(2, description);
            ps.setInt(3, quizSetID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void addQuestionToQuizSet(int quizSetID, int questionID) {
        try {
            String sql = "INSERT INTO QuizSetQuestion(quizSetID, questionID) VALUES(?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quizSetID);
            ps.setInt(2, questionID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeQuestionFromQuizSet(int quizSetID, int questionID) {
        try {
            String sql = "DELETE FROM QuizSetQuestion WHERE quizSetID = ? AND questionID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quizSetID);
            ps.setInt(2, questionID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

