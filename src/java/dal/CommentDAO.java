package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}
