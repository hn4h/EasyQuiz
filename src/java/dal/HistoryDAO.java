/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Folder;
import model.QuizHistory;
import model.QuizSet;

/**
 *
 * @author Lenovo
 */
public class HistoryDAO extends DBContext {

    public List<QuizHistory> getHistoryQuizSet(String userName) {
        List<QuizHistory> list = new ArrayList<>();
        String sql = "SELECT qs.Quiz_Set_ID, qs.Quiz_Set_Name, qs.Author, qs.Number_Of_Quiz, qs.Created_Date, qs.Quiz_Set_Description, "
                + "qsh.Quiz_Date, FORMAT(qsh.Quiz_Date, 'MM-yyyy') AS MonthYear "
                + "FROM Quiz_Set_History qsh "
                + "JOIN Quiz_Set qs ON qsh.Quiz_Set_ID = qs.Quiz_Set_ID "
                + "WHERE qsh.UserName = ? "
                + "ORDER BY qsh.Quiz_Date DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuizHistory quiz = new QuizHistory();
                quiz.setQuizSetId(rs.getInt("Quiz_Set_ID"));
                quiz.setQuizSetName(rs.getString("Quiz_Set_Name"));
                quiz.setAuthor(rs.getString("Author"));
                quiz.setNumberOfQuiz(rs.getInt("Number_Of_Quiz"));
                quiz.setCreatedDate(rs.getTimestamp("Created_Date"));
                quiz.setDescription(rs.getString("Quiz_Set_Description"));
                quiz.setQuizDate(rs.getTimestamp("Quiz_Date"));
                list.add(quiz);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<QuizSet> seachForHistoryQuiz(String quizSetName, String userName) {
        List<QuizSet> list = new ArrayList<>();
        String sql = "SELECT s.Quiz_Set_ID, s.Quiz_Set_Name, s.Number_Of_Quiz, s.Author "
                + "FROM Quiz_Set_History h "
                + "JOIN Quiz_Set s ON s.Quiz_Set_ID = h.Quiz_Set_ID "
                + "WHERE h.UserName = ? "
                + "AND s.Quiz_Set_Name LIKE '%' + ? + '%' "
                + "ORDER BY h.Quiz_Date DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ps.setString(2, quizSetName);
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

    public List<QuizSet> getCreatedQuiz(String userName) {
        List<QuizSet> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    qs.Quiz_Set_ID,\n"
                + "    qs.Quiz_Set_Name,\n"
                + "    qs.Author,\n"
                + "    qs.Number_Of_Quiz,\n"
                + "    qs.Created_Date,\n"
                + "    qs.Quiz_Set_Description\n"
                + "FROM \n"
                + "    Quiz_Set qs\n"
                + "WHERE \n"
                + "    qs.Author = ?";
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

    public List<Folder> getAllFodlerByUserName(String userName) {
        List<Folder> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    f.Folder_ID,\n"
                + "    f.Folder_Name,\n"
                + "    f.Folder_Date,\n"
                + "    f.Folder_Description,\n"
                + "    a.UserName,\n"
                + "    a.Email,\n"
                + "    COUNT(fc.Quiz_Set_ID) as QuizSetCount\n"
                + "FROM \n"
                + "    Accounts a\n"
                + "    INNER JOIN Folder f ON a.UserName = f.UserName\n"
                + "    LEFT JOIN Folder_Contain fc ON f.Folder_ID = fc.Folder_ID AND fc.Is_Deleted = 0\n"
                + "WHERE \n"
                + "    a.UserName = ? \n"
                + "    AND a.is_deleted = 0\n"
                + "    AND f.Folder_ID NOT IN (\n"
                + "        SELECT Folder_ID \n"
                + "        FROM Folder_Contain \n"
                + "        WHERE Is_Deleted = 1\n"
                + "    )\n"
                + "GROUP BY \n"
                + "    f.Folder_ID,\n"
                + "    f.Folder_Name,\n"
                + "    f.Folder_Date,\n"
                + "    f.Folder_Description,\n"
                + "    a.UserName,\n"
                + "    a.Email\n"
                + "ORDER BY \n"
                + "    f.Folder_Date DESC";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Folder f = new Folder();
                f.setFolderId(rs.getInt("Folder_ID"));
                f.setFolderName(rs.getString("Folder_Name"));
                f.setFolderDate(rs.getDate("Folder_Date"));
                f.setFolderDescription("Folder_Description");
                f.setQuizSetCount(rs.getInt("QuizSetCount"));
                list.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<QuizSet> getAllCreatedQuizSet(String userName) {
        List<QuizSet> list = new ArrayList<>();
        String sql = "select * from Quiz_Set where Author = ?\n"
                + "order by Created_Date";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<QuizSet> getAllQuizSetByFolderId(int foldeId) {
        List<QuizSet> list = new ArrayList<>();
        String sql = "select f.Folder_ID, f.Folder_Name, count(fc.Quiz_Set_ID) as QuizSetCount from Folder f\n"
                + "left join Folder_Contain fc on fc.Folder_ID = f.Folder_ID\n"
                + "where f.Folder_ID = ?\n"
                + "group by f.Folder_ID, f.Folder_Name";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, foldeId);
            ResultSet rs = st.executeQuery();
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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Folder getFolderByFolderId(int folderId) {
        Folder f = new Folder();
        String sql = "select f.Folder_ID, f.Folder_Name, f.Folder_Date, f.UserName, f.Folder_Description, "
                + "count(fc.Quiz_Set_ID) as QuizSetCount from Folder f\n"
                + "left join Folder_Contain fc on fc.Folder_ID = f.Folder_ID\n"
                + "where f.Folder_ID = ?\n"
                + "group by f.Folder_ID, f.Folder_Name, f.Folder_Date, f.Folder_Description, f.UserName";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, folderId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                f.setFolderId(rs.getInt("Folder_Id"));
                f.setFolderName(rs.getString("Folder_Name"));
                f.setFolderDate(rs.getDate("Folder_Date"));
                f.setFolderDescription("Folder_Description");
                f.setQuizSetCount(rs.getInt("QuizSetCount"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return f;
    }

    public int createFolder(String folderName, String userName) {
        String sql = "INSERT INTO Folder (Folder_Name, UserName, Folder_Description)  \n"
                + "VALUES (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            st.setString(1, folderName);
            st.setString(2, userName);
            st.setString(3, "");
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
             if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public void deleteFolder(int folderId, String userName) {
        String sql1 = "DELETE FROM Folder_Contain WHERE Folder_ID = ?";
        String sql2 = "DELETE FROM Folder WHERE Folder_ID = ? AND UserName = ?";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, folderId);
            st1.executeUpdate();

            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, folderId);
            st2.setString(2, userName);
            st2.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<QuizSet> getAllHistoryQuizSet(String userName) {
        List<QuizSet> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    Quiz_Set_ID, \n"
                + "    Quiz_Set_Name, \n"
                + "	Number_Of_Quiz,\n"
                + "	Author,\n"
                + "    MAX(Activity_Date) AS Activity_Date, \n"
                + "    STRING_AGG(Activity_Type, ', ') AS Activity_Type\n"
                + "FROM (\n"
                + "    SELECT \n"
                + "        qs.Quiz_Set_ID, \n"
                + "        qs.Quiz_Set_Name,\n"
                + "		qs.Number_Of_Quiz,\n"
                + "		qs.Author,\n"
                + "        qs.Created_Date AS Activity_Date, \n"
                + "        'Created' AS Activity_Type\n"
                + "    FROM Quiz_Set qs\n"
                + "    WHERE qs.Author = ?\n"
                + "    UNION \n"
                + "    SELECT \n"
                + "        qsh.Quiz_Set_ID, \n"
                + "        qs.Quiz_Set_Name, \n"
                + "		qs.Number_Of_Quiz,\n"
                + "		qs.Author,\n"
                + "        qsh.Quiz_Date AS Activity_Date, \n"
                + "        'Studied' AS Activity_Type\n"
                + "    FROM Quiz_Set_History qsh\n"
                + "    JOIN Quiz_Set qs ON qsh.Quiz_Set_ID = qs.Quiz_Set_ID\n"
                + "    WHERE qsh.UserName = ?\n"
                + ") AS Combined\n"
                + "GROUP BY Quiz_Set_ID, Quiz_Set_Name, Number_Of_Quiz, Author\n"
                + "ORDER BY Activity_Date DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, userName);
            st.setString(2, userName);
            ResultSet rs = st.executeQuery();
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
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        HistoryDAO d = new HistoryDAO();
        List<Folder> list = d.getAllFodlerByUserName("EasyQuiz343293");
        System.out.println(list.size());
    }

}
