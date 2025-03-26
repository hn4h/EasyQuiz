package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import model.Comment;

public class CommentDAO extends DBContext {

    public CommentDAO() {
        super(); // Call the DBContext constructor to initialize the connection
    }

    public void addComment(Comment comment) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO dbo.Comment (UserName, Blog_ID, Comment_Content) VALUES (?, ?, ?)";

        try {
            conn = this.connection; // Use the inherited connection from DBContext
            if (conn == null || conn.isClosed()) {
                throw new SQLException("Database connection is null or closed.");
            }

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, comment.getUserName());
            stmt.setInt(2, comment.getBlogId());
            stmt.setString(3, comment.getCommentContent());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw e; // Re-throw to be handled by the servlet
        } finally {
            // Close resources (note: connection is managed by DBContext, but we close stmt)
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close statement: " + e.getMessage());
                }
            }
            // Avoid closing the inherited connection here to prevent interference with DBContext
        }
    }
    
    public Comment getCommentById(int commentId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM dbo.Comment WHERE Comment_ID = ?";

        try {
            conn = this.connection;
            if (conn == null || conn.isClosed()) {
                throw new SQLException("Database connection is null or closed.");
            }

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, commentId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("Comment_ID"));
                comment.setBlogId(rs.getInt("Blog_ID"));
                comment.setUserName(rs.getString("UserName"));
                comment.setCommentContent(rs.getString("Comment_Content"));
                return comment;
            }
            return null; 
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close ResultSet: " + e.getMessage());
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close statement: " + e.getMessage());
                }
            }
        }
    }

    public void deleteComment(int commentId) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM dbo.Comment WHERE Comment_ID = ?";

        try {
            conn = this.connection;
            if (conn == null || conn.isClosed()) {
                throw new SQLException("Database connection is null or closed.");
            }

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, commentId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No comment found with ID: " + commentId);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Failed to close statement: " + e.getMessage());
                }
            }
        }
    }
}
