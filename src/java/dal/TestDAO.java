package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Quiz;

public class TestDAO {
    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        String sql = "SELECT Quiz_ID, Quiz_set_ID, Quiz_content FROM Quiz";

        try {
            DBContext dbContext = new DBContext(); // Khởi tạo kết nối
            Connection conn = dbContext.connection; 

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("Quiz_ID");
                int setId = rs.getInt("Quiz_set_ID");
                String content = rs.getString("Quiz_content");

//                Quiz quiz = new Quiz(id, setId, content);
//                quizzes.add(quiz);
            }

            rs.close();
            ps.close();
            conn.close(); // Đóng kết nối

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizzes;
    }
}