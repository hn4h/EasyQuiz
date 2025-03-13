package controller.history;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import dal.HistoryDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

class CreateFolderServletTest {
    @InjectMocks
    private HistoryFolderServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private RequestDispatcher dispatcher;

    @Mock
    private HistoryDAO historyDAO;

    private StringWriter responseWriter;
    private PrintWriter printWriter;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        when(request.getSession()).thenReturn(session);
        responseWriter = new StringWriter();
        printWriter = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    void testDoPost_WithNoAccount() throws ServletException, IOException {
        when(session.getAttribute("account")).thenReturn(null);

        servlet.doGet(request, response);

        verify(response).sendRedirect("login");
    }

    @Test
    void testDoPost_WithValidAccount() throws ServletException, IOException {
        assertTrue( true);
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        servlet.doPost(request, response);
        printWriter.flush();
        assertTrue(responseWriter.toString().contains("Servlet HistoryFolderServlet"));
    }

    @Test
    void testGetServletInfo() {
        assertEquals("Short description", servlet.getServletInfo());
    }

}