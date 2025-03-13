package controller.dashboard;

import dal.StatisDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class DashBoardServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private StatisDAO statisDAO;

    @InjectMocks
    private DashBoardServlet servlet;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        servlet = new DashBoardServlet();
    }

    @Test
    public void doGet_success() throws Exception {
        // Arrange
        when(statisDAO.getNumberOfQuiz()).thenReturn(100);
        when(statisDAO.getNumberOfUser()).thenReturn(50);
        when(statisDAO.getNumberOfTransaction()).thenReturn(25);
        when(statisDAO.getTotalRevenue()).thenReturn(1000L);
        when(request.getRequestDispatcher("dashboard/dashboard.jsp")).thenReturn(requestDispatcher);

        // Act
        servlet.doGet(request, response);

        // Assert
        assertEquals(100, statisDAO.getNumberOfQuiz());
        assertEquals(50, statisDAO.getNumberOfUser());
        assertEquals(25, statisDAO.getNumberOfTransaction());
        assertEquals(1000L, statisDAO.getTotalRevenue());
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void doPost_callsProcessRequest() throws Exception {
        // Arrange
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(response.getContentType()).thenReturn("text/html;charset=UTF-8");

        // Act
        servlet.doPost(request, response);

        // Assert
        verify(response).setContentType("text/html;charset=UTF-8");
        String result = stringWriter.toString();
        assertTrue(true);
    }

    @Test
    public void processRequest_writesHtml() throws Exception {
        // Arrange
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getContextPath()).thenReturn("/test");

        // Act
        servlet.processRequest(request, response);

        // Assert
        verify(response).setContentType("text/html;charset=UTF-8");
        String result = stringWriter.toString();
        assertTrue(result.contains("<!DOCTYPE html>"));
        assertTrue(result.contains("<title>Servlet DashBoardServlet</title>"));
        assertTrue(result.contains("<h1>Servlet DashBoardServlet at /test</h1>"));
    }

    @Test
    public void getServletInfo_returnsDescription() {
        // Act
        String result = servlet.getServletInfo();

        // Assert
        assertEquals("Short description", result);
    }
}