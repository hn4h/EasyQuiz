package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.QuizSet;

public class QuizSetHistoryDAO extends DBContext {

    public List<QuizSet> getQuizSetHistoryTop4ByUserName(String userName) {
        List<QuizSet> list = new ArrayList<>();
        String sql = "SELECT Top(4) s.Quiz_Set_ID, s.Quiz_Set_Name ,s.Number_Of_Quiz, s.Author FROM Quiz_Set_History h\n"
                + "join Quiz_Set s on s.Quiz_Set_ID = h.Quiz_Set_ID\n"
                + "WHERE h.UserName = ?\n"
                + "order by h.Quiz_Date desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuizSet qs = new QuizSet();
                qs.setQuizSetId(rs.getInt("quiz_Set_ID"));
                qs.setQuizSetName(rs.getString("Quiz_Set_Name"));
                qs.setNumberOfQuiz(rs.getInt("Number_Of_Quiz"));
                Account a = new Account();
                a.setUserName(rs.getString("Author"));
                qs.setAuthor(a);
                list.add(qs);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        QuizSetHistoryDAO qs = new QuizSetHistoryDAO();
        List<QuizSet> d = qs.getQuizSetHistoryTop4ByUserName("EasyQuiz343293");
        System.out.println(d.size());
    }
    //Get popular quiz set
}
