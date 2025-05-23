/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Answer;
import model.Quiz;

public class QuizDAO extends DBContext {

    public List<Quiz> getQuizzesByQuizSetID(int quizSetID) {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT q.Quiz_ID, q.Quiz_Set_ID, q.Quiz_content, "
                + "a.Answer_ID, a.Answer_Content, a.Is_Correct "
                + "FROM Quiz q JOIN Answer a ON q.Quiz_ID = a.Quiz_ID "
                + "WHERE q.Quiz_Set_ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quizSetID);
            ResultSet rs = ps.executeQuery();

            Quiz currentQuiz = null;
            List<Answer> currentAnswers = new ArrayList<>();
            int lastQuizID = -1;

            while (rs.next()) {
                int quizID = rs.getInt("Quiz_ID");
                if (quizID != lastQuizID) {
                    if (currentQuiz != null) {
                        currentQuiz.setAnswers(currentAnswers);
                        quizzes.add(currentQuiz);
                    }
                    currentAnswers = new ArrayList<>();
                    currentQuiz = new Quiz(quizID, rs.getInt("Quiz_Set_ID"), rs.getString("Quiz_content"), null);
                    lastQuizID = quizID;
                }
                Answer answer = new Answer(rs.getInt("Answer_ID"), quizID, rs.getString("Answer_Content"), rs.getBoolean("Is_Correct"));
                currentAnswers.add(answer);
            }

            if (currentQuiz != null) {
                currentQuiz.setAnswers(currentAnswers);
                quizzes.add(currentQuiz);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }

    public int addQuiz(int quizSetID, String content) {
        String sql = "INSERT INTO Quiz (Quiz_Set_ID, Quiz_content) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, quizSetID);
            ps.setString(2, content);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void addAnswer(int quizID, String content, boolean isCorrect) {
        String sql = "INSERT INTO Answer (Quiz_ID, Answer_Content, Is_Correct) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quizID);
            ps.setString(2, content);
            ps.setBoolean(3, isCorrect);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Quiz getQuizById(int quizId) {
        String sql = "SELECT q.Quiz_ID, q.Quiz_Set_ID, q.Quiz_content, "
                + "a.Answer_ID, a.Answer_Content, a.Is_Correct "
                + "FROM Quiz q JOIN Answer a ON q.Quiz_ID = a.Quiz_ID "
                + "WHERE q.quiz_ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quizId);
            ResultSet rs = ps.executeQuery();
            List<Answer> currentAnswers = new ArrayList<>();
            if (rs.next()) {
                Quiz quiz = new Quiz(quizId, rs.getInt("Quiz_Set_ID"), rs.getString("Quiz_content"));
                currentAnswers = new ArrayList<>();
                Answer answer = new Answer(rs.getInt("Answer_ID"), quizId, rs.getString("Answer_Content"), rs.getBoolean("Is_Correct"));
                currentAnswers.add(answer);
                quiz.setAnswers(currentAnswers);
                return quiz;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void updateQuiz(Quiz quiz) {
        String sql = "UPDATE Quiz SET Quiz_content = ? WHERE Quiz_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, quiz.getContent());
            ps.setInt(2, quiz.getQuizID());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteQuiz(int quizID) {
        String sql = "DELETE FROM Quiz WHERE Quiz_ID = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quizID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();}
        }
    
    
    
    public Answer getAnswerById(int id) {
        try {
            String sql = "select a.Answer_ID, a.Answer_content, a.Is_Correct from Answer a where Answer_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Answer a = new Answer();
                a.setAnswerID(rs.getInt("Answer_ID"));
                a.setContent(rs.getString("Answer_Content"));
                a.setIsCorrect(rs.getBoolean("Is_Correct"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        QuizDAO qd = new QuizDAO();
        Quiz q = qd.getQuizById(1);
        Answer a = qd.getAnswerById(1);
        System.out.println(q);
        System.out.println(a);
    }
}
