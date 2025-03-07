package controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Folder;
import dal.HistoryDAO;

class HistoryFolderServletTest {

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
    void testDoGet_WithValidAccount() throws ServletException, IOException {
        Account mockAccount = new Account();
        mockAccount.setUserName("testUser");
        when(session.getAttribute("account")).thenReturn(mockAccount);

        Folder mockFolder = new Folder();
        mockFolder.setFolderId(1);
        List<Folder> mockFolders = new ArrayList<>();
        mockFolders.add(mockFolder);

        when(historyDAO.getAllFolderByUserName("testUser")).thenReturn(mockFolders);
        when(request.getRequestDispatcher("history/folders.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        verify(request).setAttribute(eq("folder"), captor.capture());

        List<Folder> actualFolders = captor.getValue();
        assertNotNull(actualFolders, "Captured folder list is null");
        assertEquals(0, actualFolders.size());
        verify(dispatcher).forward(request, response);
    }


    @Test
    void testDoGet_WithNoAccount() throws ServletException, IOException {
        when(session.getAttribute("account")).thenReturn(null);

        servlet.doGet(request, response);

        verify(response).sendRedirect("login");
    }

    @Test
    void testDoPost() throws ServletException, IOException {
        servlet.doPost(request, response);
        printWriter.flush();
        assertTrue(responseWriter.toString().contains("Servlet HistoryFolderServlet"));
    }

    @Test
    void testGetServletInfo() {
        assertEquals("Short description", servlet.getServletInfo());
    }
}
