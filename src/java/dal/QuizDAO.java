/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Quiz;
import model.Answer;

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
}
