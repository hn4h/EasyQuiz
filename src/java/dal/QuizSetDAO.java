package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            String sql = "SELECT q.Quiz_Set_ID, q.Quiz_Set_Name, "
                    + "q.Author, q.Number_Of_Quiz, q.Created_Date, q.Quiz_Set_Description,a.ProfileImage FROM Quiz_Set q "
                    + "join Accounts a on a.UserName = q.Author "
                    + "WHERE Quiz_Set_ID = ?";
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

    public List<QuizSet> getAllQuizSetByUsername(String username) {
        List<QuizSet> list = new ArrayList<>();
        try {
            String sql = "SELECT q.Quiz_Set_ID, q.Quiz_Set_Name, q.Number_Of_Quiz, q.Created_Date,\n"
                    + "                     a.UserName, a.ProfileImage, a.Email\n"
                    + "                     FROM Quiz_Set q JOIN Accounts a ON q.Author = a.UserName \n"
                    + "                     WHERE q.Author = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuizSet qs = new QuizSet();
                qs.setQuizSetId(rs.getInt("quiz_Set_ID"));
                qs.setQuizSetName(rs.getString("Quiz_Set_Name"));
                qs.setNumberOfQuiz(rs.getInt("Number_Of_Quiz"));
                qs.setCreatedDate(rs.getDate("Created_Date"));
                Account a = new Account();
                a.setUserName(rs.getString("UserName"));
                a.setEmail(rs.getString("Email"));
                a.setProfileImage(rs.getString("ProfileImage"));
                qs.setAuthor(a);
                list.add(qs);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public int addQuizSet(QuizSet qs) {
        int generatedKeys = 1;
        try {
            String sql = "INSERT INTO Quiz_Set (Quiz_Set_Name, Author, Number_Of_Quiz, Quiz_Set_Description) VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, qs.getQuizSetName());
            ps.setString(2, qs.getAuthor().getUserName());
            ps.setInt(3, qs.getNumberOfQuiz());
            ps.setString(4, qs.getQuizSetDescription());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedKeys = rs.getInt(1); // Lấy Quiz_Set_ID vừa tạo
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return generatedKeys;
    }

    public void addQuizHistory(int quizSetID, String userName) {
        try {
            // Kiểm tra xem bản ghi đã tồn tại hay chưa
            String checkSql = "SELECT COUNT(*) FROM Quiz_Set_History WHERE Quiz_Set_ID = ? AND UserName = ?";
            PreparedStatement checkPs = connection.prepareStatement(checkSql);
            checkPs.setInt(1, quizSetID);
            checkPs.setString(2, userName);
            ResultSet rs = checkPs.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Nếu đã tồn tại, cập nhật thời gian Quiz_Date
                String updateSql = "UPDATE Quiz_Set_History SET Quiz_Date = GETDATE() WHERE Quiz_Set_ID = ? AND UserName = ?";
                PreparedStatement updatePs = connection.prepareStatement(updateSql);
                updatePs.setInt(1, quizSetID);
                updatePs.setString(2, userName);
                updatePs.executeUpdate();
            } else {
                // Nếu chưa tồn tại, thêm mới vào bảng
                String insertSql = "INSERT INTO Quiz_Set_History (Quiz_Set_ID, UserName) VALUES (?,?)";
                PreparedStatement insertPs = connection.prepareStatement(insertSql);
                insertPs.setInt(1, quizSetID);
                insertPs.setString(2, userName);
                insertPs.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<QuizSet> searchAllQuizSetByName(String quizSetName) {
        List<QuizSet> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    q.Quiz_Set_ID, \n"
                + "    q.Quiz_Set_Name, \n"
                + "    COUNT(distinct qsh.UserName) AS Popular,\n"
                + "    q.Author, \n"
                + "    q.Number_Of_Quiz, \n"
                + "    q.Created_Date, \n"
                + "    q.Quiz_Set_Description, \n"
                + "    a.ProfileImage \n"
                + "FROM Quiz_Set q\n"
                + "left JOIN Accounts a ON a.UserName = q.Author\n"
                + "left JOIN Quiz_Set_History qsh ON qsh.Quiz_Set_ID = q.Quiz_Set_ID\n"
                + "WHERE q.Quiz_Set_Name LIKE '%' + ? + '%'\n"
                + "GROUP BY \n"
                + "    q.Quiz_Set_ID, \n"
                + "    q.Quiz_Set_Name, \n"
                + "    q.Author, \n"
                + "    q.Number_Of_Quiz, \n"
                + "    q.Created_Date, \n"
                + "    q.Quiz_Set_Description, \n"
                + "    a.ProfileImage\n"
                + "ORDER BY Popular DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, quizSetName);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
                list.add(qs);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void updateQuizSet(int quizSetId, String title, String description, int numberOfQuestions) {
        try {
            String sql = "UPDATE Quiz_Set SET Quiz_Set_Name = ?, Number_Of_Quiz = ?, Quiz_Set_Description = ? WHERE Quiz_Set_ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setInt(2, numberOfQuestions);
            ps.setString(3, description);
            ps.setInt(4, quizSetId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteQuizSet(int quizSetId) {
        try {
            // Xóa bản ghi trong Folder_Contain
            String sql1 = "DELETE FROM Folder_Contain WHERE Quiz_Set_ID = ?";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ps1.setInt(1, quizSetId);
            ps1.executeUpdate();

            // Xóa lịch sử làm bài
            String sql2 = "DELETE FROM Quiz_Set_History WHERE Quiz_Set_ID = ?";
            PreparedStatement ps2 = connection.prepareStatement(sql2);
            ps2.setInt(1, quizSetId);
            ps2.executeUpdate();

            // Xóa câu trả lời
            String sql3 = "DELETE FROM Answer WHERE Quiz_ID IN (SELECT Quiz_ID FROM Quiz WHERE Quiz_Set_ID = ?)";
            PreparedStatement ps3 = connection.prepareStatement(sql3);
            ps3.setInt(1, quizSetId);
            ps3.executeUpdate();

            // Xóa câu hỏi
            String sql4 = "DELETE FROM Quiz WHERE Quiz_Set_ID = ?";
            PreparedStatement ps4 = connection.prepareStatement(sql4);
            ps4.setInt(1, quizSetId);
            ps4.executeUpdate();

            // Xóa bộ đề
            String sql5 = "DELETE FROM Quiz_Set WHERE Quiz_Set_ID = ?";
            PreparedStatement ps5 = connection.prepareStatement(sql5);
            ps5.setInt(1, quizSetId);
            ps5.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        QuizSetDAO d = new QuizSetDAO();
        d.deleteQuizSet(5);
    }
}
