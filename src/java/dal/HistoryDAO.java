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
        } catch(SQLException e){
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
