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
        String sql = "SELECT Top(9) b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date FROM Blog b\n"
                + "join Comment c on c.Blog_ID = b.Blog_ID\n"
                + "group by b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date\n"
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
                b.setCreatedDate(rs.getDate("Blog_Date"));
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
        String sql = "SELECT TOP 100 * FROM Comment WHERE Blog_ID = ? ORDER BY Comment_Date DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comment c = new Comment();
                c.setCommentId(rs.getInt("Comment_ID"));
                c.setUserName(rs.getString("UserName"));
                c.setBlogId(rs.getInt("Blog_ID"));
                c.setCommentContent(rs.getString("Comment_Content"));
                c.setCreatedDate(rs.getDate("Comment_Date"));
                comments.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return comments;
    }

    public List<Blog> getBlogsByPage(int page, int pageSize) {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM Blog ORDER BY Blog_Date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (page - 1) * pageSize);
            ps.setInt(2, pageSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("Blog_ID"));
                blog.setBlogTitle(rs.getString("Blog_Title"));
                blog.setBlogContent(rs.getString("Blog_Content"));
                Account author = new Account();
                author.setUserName(rs.getString("Author"));
                blog.setAuthor(author);
                blog.setCreatedDate(rs.getDate("Blog_Date"));
                blog.setComments(getCommentsByBlogId(blog.getBlogId()));
                list.add(blog);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getTotalBlogs() {
        String sql = "SELECT COUNT(*) FROM Blog";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Blog> searchBlogsByTitle(String keyword) {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT * FROM Blog WHERE Blog_Title LIKE ? ORDER BY Blog_Date DESC";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("Blog_ID"));
                blog.setBlogTitle(rs.getString("Blog_Title"));
                blog.setBlogContent(rs.getString("Blog_Content"));
                Account author = new Account();
                author.setUserName(rs.getString("Author"));
                blog.setAuthor(author);
                blog.setCreatedDate(rs.getDate("Blog_Date"));
                blog.setComments(getCommentsByBlogId(blog.getBlogId()));
                list.add(blog);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean addBlog(String title, String content, String author) {
        String sql = "INSERT INTO dbo.Blog (Blog_Title, Blog_Content, Author, Blog_Date, Is_Deleted) VALUES (?, ?, ?, GETDATE(), 0)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, author);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Returns true if at least one row was inserted
        } catch (SQLException e) {
            System.out.println("Error adding blog: " + e.getMessage());
            return false;
        }
    }
    
    public boolean addComment(int blogId, String userName, String commentContent) {
        String sql = "INSERT INTO Comment (BlogId, UserName, CommentContent, CommentDate) VALUES (?, ?, ?, GETDATE())";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, blogId);
            ps.setString(2, userName);
            ps.setString(3, commentContent);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error adding comment: " + e.getMessage());
            return false;
        }
    }
}
