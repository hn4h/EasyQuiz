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
        String sql = "SELECT Top(9) b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, a.ProfileImage, b.Blog_Date FROM Blog b\n"
                + "join Comment c on c.Blog_ID = b.Blog_ID\n"
                + "join Accounts a on a.userName = b.Author\n"
                + "group by b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date, a.ProfileImage\n"
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
                a.setProfileImage(rs.getString("ProfileImage"));
                b.setAuthor(a);
                b.setCreatedDate(rs.getDate("Blog_Date"));
                b.setComments(getCommentsByBlogId(b.getBlogId()));
                list.add(b);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Blog> getSomePopularBlog() {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT Top(3) b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, a.ProfileImage, b.Blog_Date FROM Blog b\n"
                + "join Comment c on c.Blog_ID = b.Blog_ID\n"
                + "join Accounts a on a.userName = b.Author\n"
                + "group by b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date, a.ProfileImage\n"
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
                a.setProfileImage(rs.getString("ProfileImage"));
                b.setAuthor(a);
                b.setCreatedDate(rs.getDate("Blog_Date"));
                b.setComments(getCommentsByBlogId(b.getBlogId()));
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
        String sql = "SELECT b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, a.ProfileImage, b.Blog_Date "
                + "FROM Blog b "
                + "JOIN Accounts a ON a.UserName = b.Author "
                + "WHERE b.Is_Deleted = 0";
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
                author.setProfileImage(rs.getString("ProfileImage")); // Thêm ProfileImage
                blog.setAuthor(author);

                blog.setComments(getTopCommentsByBlogId(blog.getBlogId()));
                list.add(blog);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Comment> getCommentsByBlogId(int blogId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT c.Comment_ID, c.UserName, c.Blog_ID, c.Comment_Content, c.Comment_Date, a.ProfileImage "
                + "FROM Comment c "
                + "JOIN Accounts a ON a.UserName = c.UserName "
                + "WHERE c.Blog_ID = ? "
                + "ORDER BY c.Comment_Date DESC";
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
                c.setProfileImage(rs.getString("ProfileImage")); // Gán ProfileImage
                comments.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return comments;
    }

    public List<Comment> getTopCommentsByBlogId(int blogId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT TOP 3 c.Comment_ID, c.UserName, c.Blog_ID, c.Comment_Content, c.Comment_Date, a.ProfileImage "
                + "FROM Comment c "
                + "JOIN Accounts a ON a.UserName = c.UserName "
                + "WHERE c.Blog_ID = ? "
                + "ORDER BY c.Comment_Date DESC";
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
                c.setProfileImage(rs.getString("ProfileImage")); // Thêm ProfileImage
                comments.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return comments;
    }

    public List<Blog> getBlogsByPage(int page, int pageSize) {
        List<Blog> list = new ArrayList<>();
        String sql = "SELECT b.Blog_ID, b.Blog_Title, b.Blog_Content, b.Author, a.ProfileImage, b.Blog_Date "
                + "FROM Blog b "
                + "JOIN Accounts a ON a.UserName = b.Author "
                + "ORDER BY b.Blog_Date DESC "
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
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
                author.setProfileImage(rs.getString("ProfileImage")); // Thêm ProfileImage
                blog.setAuthor(author);
                blog.setCreatedDate(rs.getDate("Blog_Date"));
                blog.setComments(getTopCommentsByBlogId(blog.getBlogId()));
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
        String sql = "SELECT b.Blog_ID, b.Blog_Title, b.Blog_Content, b.Author, a.ProfileImage, b.Blog_Date "
                + "FROM Blog b "
                + "JOIN Accounts a ON a.UserName = b.Author "
                + "WHERE b.Blog_Title LIKE ? "
                + "ORDER BY b.Blog_Date DESC";
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
                author.setProfileImage(rs.getString("ProfileImage")); // Thêm ProfileImage
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

    public Blog getBlogById(int blogId) {
        String sql = "SELECT b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, a.ProfileImage, b.Blog_Date, b.Is_Deleted "
                + "FROM Blog b "
                + "JOIN Accounts a ON a.UserName = b.Author "
                + "WHERE b.Blog_ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, blogId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Blog blog = new Blog();
                blog.setBlogId(rs.getInt("Blog_ID"));
                blog.setBlogContent(rs.getString("Blog_Content"));
                blog.setBlogTitle(rs.getString("Blog_Title"));
                Account author = new Account();
                author.setUserName(rs.getString("Author"));
                author.setProfileImage(rs.getString("ProfileImage")); // Thêm ProfileImage
                blog.setAuthor(author);
                blog.setCreatedDate(rs.getDate("Blog_Date"));
                blog.setIsDeleted(rs.getBoolean("Is_Deleted"));
                blog.setComments(getCommentsByBlogId(blogId));
                return blog;
            }
        } catch (SQLException e) {
            System.out.println("Error getting blog by ID: " + e.getMessage());
        }
        return null;
    }
}
