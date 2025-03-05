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
import model.Comment;

/**
 *
 * @author 11
 */
public class BlogDAO extends DBContext {

    public List<Blog> getPopularBlog() {
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

    public List<Blog> getAllBlogs() {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT Blog_ID, Blog_Content, Blog_Title, Author, Blog_Date FROM Blog WHERE Is_Deleted = 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("Blog_ID"));
                blog.setBlogContent(rs.getString("Blog_Content"));
                blog.setBlogTitle(rs.getString("Blog_Title"));
                blog.setCreatedDate(rs.getDate("Blog_Date"));

                Account author = new Account();
                author.setUserName(rs.getString("Author"));
                blog.setAuthor(author);

                blog.setComments(getCommentsByBlogId(blog.getBlogId()));
                list.add(blog);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Comment> getCommentsByBlogId(int blogId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT Comment_ID, UserName, Blog_ID, Comment_Content, Comment_Date FROM Comment WHERE Blog_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("Comment_ID"));
                comment.setUserName(rs.getString("UserName"));
                comment.setBlogId(rs.getInt("Blog_ID"));
                comment.setCommentContent(rs.getString("Comment_Content"));
                comment.setCreatedDate(rs.getDate("Comment_Date"));
                comments.add(comment);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return comments;
    }
}
