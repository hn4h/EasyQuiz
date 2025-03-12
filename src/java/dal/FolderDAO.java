/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Folder;

/**
 *
 * @author admin
 */
public class FolderDAO extends DBContext{
    
    public List<Folder> getAllFoldersByUsername(String username){
        List<Folder> list = new ArrayList<>();
        try {
            String sql = "SELECT f.Folder_ID, f.Folder_Name, f.UserName, \n" +
"                 COUNT(fc.Quiz_Set_ID) AS QuizSetCount \n" +
"                 FROM Folder f \n" +
"                 LEFT JOIN Folder_Contain fc ON f.Folder_ID = fc.Folder_ID\n" +
"                 WHERE f.UserName = ?\n" +
"                 GROUP BY f.Folder_ID, f.Folder_Name, f.UserName";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Folder f = new Folder();
                f.setFolderId(rs.getInt("Folder_ID"));
                f.setFolderName(rs.getString("Folder_Name"));
                f.setUserName(rs.getString("UserName"));
                f.setQuizSetCount(rs.getInt("QuizSetCount"));
                list.add(f);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        FolderDAO d = new FolderDAO();
        System.out.println(d.getAllFoldersByUsername("EasyQuiz911539"));
    }
}
