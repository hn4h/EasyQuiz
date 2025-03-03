/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Blog;

/**
 *
 * @author 11
 */
public class BlogDAO extends DBContext{
    public List<Blog> getPopularBlog(){
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT Top(9) b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author FROM Blog b\n"
                + "join Comment c on c.Blog_ID = b.Blog_ID\n"
                + "group by b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author\n"
                + "order by count(c.Comment_ID) desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog b = new Blog();
                b.setBlogId(rs.getInt("Blog_ID"));
                b.setBlogContent(rs.getString("Blog_Content"));
                b.setBlogTitle(rs.getString("Blog_Title"));
                Account a = new Account();
                a.setUserName(rs.getString("Author"));
                b.setAuthor(a);
                list.add(b);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
