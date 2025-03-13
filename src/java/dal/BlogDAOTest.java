package dal;

import model.Account;
import model.Blog;
import model.Comment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlogDAOTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private BlogDAO blogDAO;

    @BeforeEach
    void setUp() throws SQLException, NoSuchFieldException, IllegalAccessException {
        MockitoAnnotations.openMocks(this);
        blogDAO = new BlogDAO();

        // Inject the mocked connection into the BlogDAO instance
        Field connectionField = DBContext.class.getDeclaredField("connection");
        connectionField.setAccessible(true);
        connectionField.set(blogDAO, connection);

        // Default mock behavior for preparedStatement
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(connection, preparedStatement, resultSet);
    }

    @Test
    void getPopularBlog_Success() throws SQLException {
        // Arrange: Mock the main query for getPopularBlog
        ResultSet blogResultSet = mock(ResultSet.class);
        when(blogResultSet.next()).thenReturn(true, true, false); // 2 blogs
        when(blogResultSet.getInt("Blog_ID")).thenReturn(1, 2);
        when(blogResultSet.getString("Blog_Content")).thenReturn("Content1", "Content2");
        when(blogResultSet.getString("Blog_Title")).thenReturn("Title1", "Title2");
        when(blogResultSet.getString("Author")).thenReturn("Author1", "Author2");
        when(blogResultSet.getDate("Blog_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Mock the PreparedStatement for getPopularBlog query
        when(connection.prepareStatement("SELECT Top(9) b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date FROM Blog b\n" +
                "join Comment c on c.Blog_ID = b.Blog_ID\n" +
                "group by b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date\n" +
                "order by count(c.Comment_ID) desc")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(blogResultSet);

        // Mock the comments query for getCommentsByBlogId for blog 1 (Blog_ID = 1)
        ResultSet commentResultSet1 = mock(ResultSet.class);
        when(commentResultSet1.next()).thenReturn(true, false); // 1 comment for blog 1
        when(commentResultSet1.getInt("Comment_ID")).thenReturn(1);
        when(commentResultSet1.getString("UserName")).thenReturn("User1");
        when(commentResultSet1.getInt("Blog_ID")).thenReturn(1);
        when(commentResultSet1.getString("Comment_Content")).thenReturn("Comment1");
        when(commentResultSet1.getDate("Comment_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Mock the comments query for getCommentsByBlogId for blog 2 (Blog_ID = 2)
        ResultSet commentResultSet2 = mock(ResultSet.class);
        when(commentResultSet2.next()).thenReturn(true, false); // 1 comment for blog 2
        when(commentResultSet2.getInt("Comment_ID")).thenReturn(2);
        when(commentResultSet2.getString("UserName")).thenReturn("User2");
        when(commentResultSet2.getInt("Blog_ID")).thenReturn(2);
        when(commentResultSet2.getString("Comment_Content")).thenReturn("Comment2");
        when(commentResultSet2.getDate("Comment_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Mock the PreparedStatement for getCommentsByBlogId query
        PreparedStatement commentStmt = mock(PreparedStatement.class);
        when(connection.prepareStatement("SELECT * FROM Comment WHERE Blog_ID = ? ORDER BY Comment_Date DESC"))
                .thenReturn(commentStmt);
        when(commentStmt.executeQuery())
                .thenReturn(commentResultSet1) // First call for Blog_ID = 1
                .thenReturn(commentResultSet2); // Second call for Blog_ID = 2

        // Act
        List<Blog> result = blogDAO.getPopularBlog();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1, result.get(0).getBlogId());
        assertEquals("Title1", result.get(0).getBlogTitle());
        assertEquals("Author1", result.get(0).getAuthor().getUserName());
        assertEquals(1, result.get(0).getComments().size()); // Should now pass
        assertEquals(1, result.get(1).getComments().size()); // Check blog 2 comments
        verify(connection).prepareStatement("SELECT Top(9) b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date FROM Blog b\n" +
                "join Comment c on c.Blog_ID = b.Blog_ID\n" +
                "group by b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date\n" +
                "order by count(c.Comment_ID) desc");
        verify(commentStmt).setInt(eq(1), eq(1)); // Verify setInt for Blog_ID = 1
        verify(commentStmt).setInt(eq(1), eq(2)); // Verify setInt for Blog_ID = 2
        verify(commentStmt, times(2)).executeQuery(); // Verify executeQuery called twice
    }

    @Test
    void getPopularBlog_Empty() throws SQLException {
        // Arrange
        when(resultSet.next()).thenReturn(false);

        // Act
        List<Blog> result = blogDAO.getPopularBlog();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void getPopularBlog_SQLException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        List<Blog> result = blogDAO.getPopularBlog();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void getSomePopularBlog_Success() throws SQLException {
        // Arrange: Mock the main query for getSomePopularBlog
        ResultSet blogResultSet = mock(ResultSet.class);
        when(blogResultSet.next()).thenReturn(true, false); // 1 blog
        when(blogResultSet.getInt("Blog_ID")).thenReturn(1);
        when(blogResultSet.getString("Blog_Content")).thenReturn("Content1");
        when(blogResultSet.getString("Blog_Title")).thenReturn("Title1");
        when(blogResultSet.getString("Author")).thenReturn("Author1");
        when(blogResultSet.getDate("Blog_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Mock the PreparedStatement for getSomePopularBlog query
        when(connection.prepareStatement("SELECT Top(3) b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date FROM Blog b\n" +
                "join Comment c on c.Blog_ID = b.Blog_ID\n" +
                "group by b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date\n" +
                "order by count(c.Comment_ID) desc")).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(blogResultSet);

        // Mock the comments query for getCommentsByBlogId for blog 1 (Blog_ID = 1)
        ResultSet commentResultSet = mock(ResultSet.class);
        when(commentResultSet.next()).thenReturn(true, false); // 1 comment for blog 1
        when(commentResultSet.getInt("Comment_ID")).thenReturn(1);
        when(commentResultSet.getString("UserName")).thenReturn("User1");
        when(commentResultSet.getInt("Blog_ID")).thenReturn(1);
        when(commentResultSet.getString("Comment_Content")).thenReturn("Comment1");
        when(commentResultSet.getDate("Comment_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Mock the PreparedStatement for getCommentsByBlogId query
        PreparedStatement commentStmt = mock(PreparedStatement.class);
        when(connection.prepareStatement("SELECT * FROM Comment WHERE Blog_ID = ? ORDER BY Comment_Date DESC"))
                .thenReturn(commentStmt);
        when(commentStmt.executeQuery()).thenReturn(commentResultSet); // Return comments for Blog_ID = 1

        // Act
        List<Blog> result = blogDAO.getSomePopularBlog();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getBlogId());
        assertEquals("Title1", result.get(0).getBlogTitle());
        assertEquals("Author1", result.get(0).getAuthor().getUserName());
        assertEquals(1, result.get(0).getComments().size()); // Should now pass
        verify(connection).prepareStatement("SELECT Top(3) b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date FROM Blog b\n" +
                "join Comment c on c.Blog_ID = b.Blog_ID\n" +
                "group by b.Blog_ID, b.Blog_Content, b.Blog_Title, b.Author, b.Blog_Date\n" +
                "order by count(c.Comment_ID) desc");
        verify(commentStmt).setInt(eq(1), eq(1)); // Verify setInt for Blog_ID = 1
        verify(commentStmt).executeQuery();
    }

    @Test
    void getSomePopularBlog_Empty() throws SQLException {
        // Arrange
        when(resultSet.next()).thenReturn(false);

        // Act
        List<Blog> result = blogDAO.getSomePopularBlog();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void getSomePopularBlog_SQLException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        List<Blog> result = blogDAO.getSomePopularBlog();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void getAllBlogs_Success() throws SQLException {
        // Arrange: Mock the main query for getAllBlogs
        ResultSet blogResultSet = mock(ResultSet.class);
        when(blogResultSet.next()).thenReturn(true, false); // 1 blog
        when(blogResultSet.getInt("Blog_ID")).thenReturn(1);
        when(blogResultSet.getString("Blog_Content")).thenReturn("Content1");
        when(blogResultSet.getString("Blog_Title")).thenReturn("Title1");
        when(blogResultSet.getString("Author")).thenReturn("Author1");
        when(blogResultSet.getDate("Blog_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Mock the PreparedStatement for getAllBlogs query
        when(connection.prepareStatement("SELECT Blog_ID, Blog_Content, Blog_Title, Author, Blog_Date FROM Blog WHERE Is_Deleted = 0"))
                .thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(blogResultSet);

        // Mock the comments query for getTopCommentsByBlogId for blog 1 (Blog_ID = 1)
        ResultSet commentResultSet = mock(ResultSet.class);
        when(commentResultSet.next()).thenReturn(true, false); // 1 comment for blog 1
        when(commentResultSet.getInt("Comment_ID")).thenReturn(1);
        when(commentResultSet.getString("UserName")).thenReturn("User1");
        when(commentResultSet.getInt("Blog_ID")).thenReturn(1);
        when(commentResultSet.getString("Comment_Content")).thenReturn("Comment1");
        when(commentResultSet.getDate("Comment_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Mock the PreparedStatement for getTopCommentsByBlogId query
        PreparedStatement commentStmt = mock(PreparedStatement.class);
        when(connection.prepareStatement("SELECT TOP 3 * FROM Comment WHERE Blog_ID = ? ORDER BY Comment_Date DESC"))
                .thenReturn(commentStmt);
        when(commentStmt.executeQuery()).thenReturn(commentResultSet); // Return comments for Blog_ID = 1

        // Act
        List<Blog> result = blogDAO.getAllBlogs();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getBlogId());
        assertEquals("Title1", result.get(0).getBlogTitle());
        assertEquals("Author1", result.get(0).getAuthor().getUserName());
        assertEquals(1, result.get(0).getComments().size()); // Should now pass
        verify(connection).prepareStatement("SELECT Blog_ID, Blog_Content, Blog_Title, Author, Blog_Date FROM Blog WHERE Is_Deleted = 0");
        verify(commentStmt).setInt(eq(1), eq(1)); // Verify setInt for Blog_ID = 1
        verify(commentStmt).executeQuery();
    }

    @Test
    void getAllBlogs_Empty() throws SQLException {
        // Arrange
        when(resultSet.next()).thenReturn(false);

        // Act
        List<Blog> result = blogDAO.getAllBlogs();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void getAllBlogs_SQLException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        List<Blog> result = blogDAO.getAllBlogs();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void getCommentsByBlogId_Success() throws SQLException {
        // Arrange
        int blogId = 1;
        when(resultSet.next()).thenReturn(true, false); // 1 comment
        when(resultSet.getInt("Comment_ID")).thenReturn(1);
        when(resultSet.getString("UserName")).thenReturn("User1");
        when(resultSet.getInt("Blog_ID")).thenReturn(blogId);
        when(resultSet.getString("Comment_Content")).thenReturn("Comment1");
        when(resultSet.getDate("Comment_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Act
        List<Comment> result = blogDAO.getCommentsByBlogId(blogId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Comment1", result.get(0).getCommentContent());
        verify(preparedStatement).setInt(1, blogId);
        verify(connection).prepareStatement("SELECT * FROM Comment WHERE Blog_ID = ? ORDER BY Comment_Date DESC");
    }

    @Test
    void getCommentsByBlogId_Empty() throws SQLException {
        // Arrange
        int blogId = 1;
        when(resultSet.next()).thenReturn(false);

        // Act
        List<Comment> result = blogDAO.getCommentsByBlogId(blogId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(preparedStatement).setInt(1, blogId);
    }

    @Test
    void getCommentsByBlogId_SQLException() throws SQLException {
        // Arrange
        int blogId = 1;
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        List<Comment> result = blogDAO.getCommentsByBlogId(blogId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void getTopCommentsByBlogId_Success() throws SQLException {
        // Arrange
        int blogId = 1;
        when(resultSet.next()).thenReturn(true, false); // 1 comment
        when(resultSet.getInt("Comment_ID")).thenReturn(1);
        when(resultSet.getString("UserName")).thenReturn("User1");
        when(resultSet.getInt("Blog_ID")).thenReturn(blogId);
        when(resultSet.getString("Comment_Content")).thenReturn("Comment1");
        when(resultSet.getDate("Comment_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Act
        List<Comment> result = blogDAO.getTopCommentsByBlogId(blogId);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(preparedStatement).setInt(1, blogId);
        verify(connection).prepareStatement("SELECT TOP 3 * FROM Comment WHERE Blog_ID = ? ORDER BY Comment_Date DESC");
    }

    @Test
    void getTopCommentsByBlogId_Empty() throws SQLException {
        // Arrange
        int blogId = 1;
        when(resultSet.next()).thenReturn(false);

        // Act
        List<Comment> result = blogDAO.getTopCommentsByBlogId(blogId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(preparedStatement).setInt(1, blogId);
    }

    @Test
    void getTopCommentsByBlogId_SQLException() throws SQLException {
        // Arrange
        int blogId = 1;
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        List<Comment> result = blogDAO.getTopCommentsByBlogId(blogId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void getBlogsByPage_Success() throws SQLException {
        // Arrange
        int page = 1, pageSize = 2;
        when(resultSet.next()).thenReturn(true, false); // 1 blog
        when(resultSet.getInt("Blog_ID")).thenReturn(1);
        when(resultSet.getString("Blog_Content")).thenReturn("Content1");
        when(resultSet.getString("Blog_Title")).thenReturn("Title1");
        when(resultSet.getString("Author")).thenReturn("Author1");
        when(resultSet.getDate("Blog_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Mock getTopCommentsByBlogId
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // 1 comment
        when(resultSet.getInt("Comment_ID")).thenReturn(1);
        when(resultSet.getString("UserName")).thenReturn("User1");
        when(resultSet.getInt("Blog_ID")).thenReturn(1);
        when(resultSet.getString("Comment_Content")).thenReturn("Comment1");
        when(resultSet.getDate("Comment_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Act
        List<Blog> result = blogDAO.getBlogsByPage(page, pageSize);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(preparedStatement).setInt(1, 0); // (page - 1) * pageSize
        verify(preparedStatement).setInt(2, pageSize);
        verify(connection).prepareStatement("SELECT * FROM Blog ORDER BY Blog_Date DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
    }

    @Test
    void getBlogsByPage_Empty() throws SQLException {
        // Arrange
        int page = 1, pageSize = 2;
        when(resultSet.next()).thenReturn(false);

        // Act
        List<Blog> result = blogDAO.getBlogsByPage(page, pageSize);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(preparedStatement).setInt(1, 0);
        verify(preparedStatement).setInt(2, pageSize);
    }

    @Test
    void getBlogsByPage_SQLException() throws SQLException {
        // Arrange
        int page = 1, pageSize = 2;
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        List<Blog> result = blogDAO.getBlogsByPage(page, pageSize);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void getTotalBlogs_Success() throws SQLException {
        // Arrange
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(10);

        // Act
        int result = blogDAO.getTotalBlogs();

        // Assert
        assertEquals(10, result);
        verify(connection).prepareStatement("SELECT COUNT(*) FROM Blog");
    }

    @Test
    void getTotalBlogs_NoRows() throws SQLException {
        // Arrange
        when(resultSet.next()).thenReturn(false);

        // Act
        int result = blogDAO.getTotalBlogs();

        // Assert
        assertEquals(0, result);
        verify(connection).prepareStatement("SELECT COUNT(*) FROM Blog");
    }

    @Test
    void getTotalBlogs_SQLException() throws SQLException {
        // Arrange
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        int result = blogDAO.getTotalBlogs();

        // Assert
        assertEquals(0, result);
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void searchBlogsByTitle_Success() throws SQLException {
        // Arrange
        String keyword = "test";
        when(resultSet.next()).thenReturn(true, false); // 1 blog
        when(resultSet.getInt("Blog_ID")).thenReturn(1);
        when(resultSet.getString("Blog_Content")).thenReturn("Content1");
        when(resultSet.getString("Blog_Title")).thenReturn("Title1");
        when(resultSet.getString("Author")).thenReturn("Author1");
        when(resultSet.getDate("Blog_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Mock getCommentsByBlogId
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false); // 1 comment
        when(resultSet.getInt("Comment_ID")).thenReturn(1);
        when(resultSet.getString("UserName")).thenReturn("User1");
        when(resultSet.getInt("Blog_ID")).thenReturn(1);
        when(resultSet.getString("Comment_Content")).thenReturn("Comment1");
        when(resultSet.getDate("Comment_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Act
        List<Blog> result = blogDAO.searchBlogsByTitle(keyword);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(preparedStatement).setString(1, "%test%");
        verify(connection).prepareStatement("SELECT * FROM Blog WHERE Blog_Title LIKE ? ORDER BY Blog_Date DESC");
    }

    @Test
    void searchBlogsByTitle_Empty() throws SQLException {
        // Arrange
        String keyword = "test";
        when(resultSet.next()).thenReturn(false);

        // Act
        List<Blog> result = blogDAO.searchBlogsByTitle(keyword);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(preparedStatement).setString(1, "%test%");
    }

    @Test
    void searchBlogsByTitle_SQLException() throws SQLException {
        // Arrange
        String keyword = "test";
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        List<Blog> result = blogDAO.searchBlogsByTitle(keyword);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void addBlog_Success() throws SQLException {
        // Arrange
        String title = "New Blog";
        String content = "Blog content";
        String author = "Author1";
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = blogDAO.addBlog(title, content, author);

        // Assert
        assertTrue(result);
        verify(preparedStatement).setString(1, title);
        verify(preparedStatement).setString(2, content);
        verify(preparedStatement).setString(3, author);
        verify(connection).prepareStatement("INSERT INTO dbo.Blog (Blog_Title, Blog_Content, Author, Blog_Date, Is_Deleted) VALUES (?, ?, ?, GETDATE(), 0)");
    }

    @Test
    void addBlog_NoRowsAffected() throws SQLException {
        // Arrange
        String title = "New Blog";
        String content = "Blog content";
        String author = "Author1";
        when(preparedStatement.executeUpdate()).thenReturn(0);

        // Act
        boolean result = blogDAO.addBlog(title, content, author);

        // Assert
        assertFalse(result);
        verify(preparedStatement).setString(1, title);
        verify(preparedStatement).setString(2, content);
        verify(preparedStatement).setString(3, author);
    }

    @Test
    void addBlog_SQLException() throws SQLException {
        // Arrange
        String title = "New Blog";
        String content = "Blog content";
        String author = "Author1";
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        boolean result = blogDAO.addBlog(title, content, author);

        // Assert
        assertFalse(result);
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void addComment_Success() throws SQLException {
        // Arrange
        int blogId = 1;
        String userName = "User1";
        String commentContent = "New comment";
        when(preparedStatement.executeUpdate()).thenReturn(1);

        // Act
        boolean result = blogDAO.addComment(blogId, userName, commentContent);

        // Assert
        assertTrue(result);
        verify(preparedStatement).setInt(1, blogId);
        verify(preparedStatement).setString(2, userName);
        verify(preparedStatement).setString(3, commentContent);
        verify(connection).prepareStatement("INSERT INTO Comment (BlogId, UserName, CommentContent, CommentDate) VALUES (?, ?, ?, GETDATE())");
    }

    @Test
    void addComment_NoRowsAffected() throws SQLException {
        // Arrange
        int blogId = 1;
        String userName = "User1";
        String commentContent = "New comment";
        when(preparedStatement.executeUpdate()).thenReturn(0);

        // Act
        boolean result = blogDAO.addComment(blogId, userName, commentContent);

        // Assert
        assertFalse(result);
        verify(preparedStatement).setInt(1, blogId);
        verify(preparedStatement).setString(2, userName);
        verify(preparedStatement).setString(3, commentContent);
    }

    @Test
    void addComment_SQLException() throws SQLException {
        // Arrange
        int blogId = 1;
        String userName = "User1";
        String commentContent = "New comment";
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        boolean result = blogDAO.addComment(blogId, userName, commentContent);

        // Assert
        assertFalse(result);
        verify(connection).prepareStatement(anyString());
    }

    @Test
    void getBlogById_Success() throws SQLException {
        // Arrange: Mock the main query for getBlogById
        int blogId = 1;
        ResultSet blogResultSet = mock(ResultSet.class);
        when(blogResultSet.next()).thenReturn(true); // 1 blog found
        when(blogResultSet.getInt("Blog_ID")).thenReturn(blogId);
        when(blogResultSet.getString("Blog_Content")).thenReturn("Content1");
        when(blogResultSet.getString("Blog_Title")).thenReturn("Title1");
        when(blogResultSet.getString("Author")).thenReturn("Author1");
        when(blogResultSet.getDate("Blog_Date")).thenReturn(new Date(System.currentTimeMillis()));
        when(blogResultSet.getBoolean("Is_Deleted")).thenReturn(false);

        // Mock the PreparedStatement for getBlogById query
        when(connection.prepareStatement("SELECT Blog_ID, Blog_Content, Blog_Title, Author, Blog_Date, Is_Deleted FROM Blog WHERE Blog_ID = ?"))
                .thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(blogResultSet);

        // Mock the comments query for getCommentsByBlogId for blog 1 (Blog_ID = 1)
        ResultSet commentResultSet = mock(ResultSet.class);
        when(commentResultSet.next()).thenReturn(true, false); // 1 comment for blog 1
        when(commentResultSet.getInt("Comment_ID")).thenReturn(1);
        when(commentResultSet.getString("UserName")).thenReturn("User1");
        when(commentResultSet.getInt("Blog_ID")).thenReturn(blogId);
        when(commentResultSet.getString("Comment_Content")).thenReturn("Comment1");
        when(commentResultSet.getDate("Comment_Date")).thenReturn(new Date(System.currentTimeMillis()));

        // Mock the PreparedStatement for getCommentsByBlogId query
        PreparedStatement commentStmt = mock(PreparedStatement.class);
        when(connection.prepareStatement("SELECT * FROM Comment WHERE Blog_ID = ? ORDER BY Comment_Date DESC"))
                .thenReturn(commentStmt);
        when(commentStmt.executeQuery()).thenReturn(commentResultSet); // Return comments for Blog_ID = 1

        // Act
        Blog result = blogDAO.getBlogById(blogId);

        // Assert
        assertNotNull(result);
        assertEquals(blogId, result.getBlogId());
        assertEquals("Title1", result.getBlogTitle());
        assertEquals("Author1", result.getAuthor().getUserName());
        assertEquals(1, result.getComments().size()); // Should now pass
        verify(connection).prepareStatement("SELECT Blog_ID, Blog_Content, Blog_Title, Author, Blog_Date, Is_Deleted FROM Blog WHERE Blog_ID = ?");
        verify(preparedStatement).setInt(eq(1), eq(blogId)); // Verify setInt for Blog_ID in main query
        verify(commentStmt).setInt(eq(1), eq(blogId)); // Verify setInt for Blog_ID in comments query
        verify(commentStmt).executeQuery();
    }

    @Test
    void getBlogById_NotFound() throws SQLException {
        // Arrange
        int blogId = 1;
        when(resultSet.next()).thenReturn(false); // No blog found

        // Act
        Blog result = blogDAO.getBlogById(blogId);

        // Assert
        assertNull(result);
        verify(preparedStatement).setInt(1, blogId);
    }

    @Test
    void getBlogById_SQLException() throws SQLException {
        // Arrange
        int blogId = 1;
        when(connection.prepareStatement(anyString())).thenThrow(new SQLException("Database error"));

        // Act
        Blog result = blogDAO.getBlogById(blogId);

        // Assert
        assertNull(result);
        verify(connection).prepareStatement(anyString());
    }
}