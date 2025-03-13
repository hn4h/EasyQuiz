package service;

import dal.BlogDAO;
import model.Blog;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlogServiceTest {

    @Mock
    private BlogDAO blogDAO;

    @InjectMocks
    private BlogService blogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
        // Reset mocks after each test to avoid state leakage
        Mockito.reset(blogDAO);
    }

    @Test
    void getAllBlogs_Success() {
        // Arrange
        List<Blog> mockBlogs = new ArrayList<>();
        mockBlogs.add(new Blog()); // Add a dummy Blog for testing
        when(blogDAO.getAllBlogs()).thenReturn(mockBlogs);

        // Act
        List<Blog> result = blogService.getAllBlogs();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertSame(mockBlogs, result); // Verify the same instance is returned
        verify(blogDAO).getAllBlogs();
    }

    @Test
    void getAllBlogs_EmptyList() {
        // Arrange
        List<Blog> emptyList = new ArrayList<>();
        when(blogDAO.getAllBlogs()).thenReturn(emptyList);

        // Act
        List<Blog> result = blogService.getAllBlogs();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(blogDAO).getAllBlogs();
    }

    @Test
    void getAllBlogs_Exception() {
        // Arrange
        RuntimeException exception = new RuntimeException("Database error");
        when(blogDAO.getAllBlogs()).thenThrow(exception);

        // Act & Assert
        Exception thrown = assertThrows(RuntimeException.class, () -> blogService.getAllBlogs());
        assertEquals(exception, thrown);
        verify(blogDAO).getAllBlogs();
    }
}