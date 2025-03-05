package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.FlashCard;
import model.QuizSet;
import model.QuizSetDetail;

public class QuizSetDAO extends DBContext {

    public List<QuizSet> getPopularQuizSet() {
        List<QuizSet> list = new ArrayList<>();
        try {
            String sql = "SELECT Top(9) s.Quiz_Set_ID, s.Quiz_Set_Name ,s.Number_Of_Quiz, s.Author, a.ProfileImage FROM Quiz_Set_History h\n"
                    + "join Quiz_Set s on s.Quiz_Set_ID = h.Quiz_Set_ID\n"
                    + "join Accounts a on a.UserName = s.author\n"
                    + "WHERE  h.Quiz_Date >= DATEADD(day, -7, GETDATE())\n"
                    + "group by s.Quiz_Set_ID, s.Quiz_Set_Name ,s.Number_Of_Quiz, s.Author, a.ProfileImage\n"
                    + "order by count(h.Quiz_Set_ID) desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuizSet qs = new QuizSet();
                qs.setQuizSetId(rs.getInt("quiz_Set_ID"));
                qs.setQuizSetName(rs.getString("Quiz_Set_Name"));
                qs.setNumberOfQuiz(rs.getInt("Number_Of_Quiz"));
                Account a = new Account();
                a.setUserName(rs.getString("Author"));
                a.setProfileImage(rs.getString("ProfileImage"));
                qs.setAuthor(a);
                list.add(qs);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public QuizSetDetail getQuizDetailById(int id) {
//        if (this.getQuizDetailById(id) == null) {
//            return null;
//        }
        List<FlashCard> list = new ArrayList<>();
        try {
            String sql = "select q.Quiz_content, a.Answer_Content from quiz q join Answer a on a.Quiz_ID = q.Quiz_ID\n"
                    + "where q.Quiz_Set_ID = ? and a.Is_Correct = 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                FlashCard fc = new FlashCard();
                fc.setDefinition(rs.getString("Quiz_Content"));
                fc.setTerm(rs.getString("Answer_Content"));
                list.add(fc);
            }
            return new QuizSetDetail(this.getQuizSetById(id), list);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public QuizSet getQuizSetById(int id) {
        try {
            String sql = "SELECT q.Quiz_Set_ID, q.Quiz_Set_Name, q.Author, q.Number_Of_Quiz, q.Created_Date, q.Quiz_Set_Description,a.ProfileImage FROM Quiz_Set q join Accounts a on a.UserName = q.Author WHERE Quiz_Set_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                QuizSet qs = new QuizSet();
                qs.setQuizSetId(rs.getInt("Quiz_Set_ID"));
                qs.setQuizSetName(rs.getString("Quiz_Set_Name"));
                qs.setNumberOfQuiz(rs.getInt("Number_Of_Quiz"));
                Account a = new Account();
                a.setUserName(rs.getString("Author"));
                a.setProfileImage(rs.getString("ProfileImage"));
                qs.setAuthor(a);
                qs.setCreatedDate(rs.getDate("Created_Date"));
                qs.setQuizSetDescription(rs.getString("Quiz_Set_Description"));
                return qs;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        QuizSetDAO d = new QuizSetDAO();

        System.out.println(d.getQuizDetailById(1).getQs().getQuizSetName());
    }
}
