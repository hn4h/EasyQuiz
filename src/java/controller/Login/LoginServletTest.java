package controller.Login;

import dal.AccountDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintWriter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class LoginServletTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    AccountDAO accountDAO;

    @Mock
    RequestDispatcher dispatcher;

    @InjectMocks
    LoginServlet loginServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loginServlet = new LoginServlet(accountDAO);
    }
    @Test
    void doPost_withValidCredentials() throws Exception {
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(accountDAO.checkAuthen("test@example.com", "password")).thenReturn(new Account());
        when(request.getSession()).thenReturn(session);
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        doNothing().when(writer).flush();
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("login/login.jsp")).thenReturn(dispatcher);

        // Act
        loginServlet.doPost(request, response);

        // Assert
        verify(session).setAttribute(eq("account"), any(Account.class));
        verify(response).sendRedirect("home");
    }

    @Test
    void doPost_withInvalidCredentials() throws Exception {
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("wrongpassword");
        when(accountDAO.checkAuthen("test@example.com", "wrongpassword")).thenReturn(null);
        when(request.getRequestDispatcher("login/login.jsp")).thenReturn(dispatcher);

        loginServlet.doPost(request, response);

        verify(request).setAttribute("error", "Invalid username or password");
        verify(dispatcher).forward(request, response);
    }
    @Test
    void doPost_withNullInput() throws Exception {
        // Trường hợp email là null
        when(request.getParameter("email")).thenReturn(null);
        when(request.getParameter("password")).thenReturn("password");
        when(accountDAO.checkAuthen(null, "password")).thenReturn(null);
        when(request.getRequestDispatcher("login/login.jsp")).thenReturn(dispatcher);

        loginServlet.doPost(request, response);

        verify(request).setAttribute("error", "Invalid username or password");
        verify(dispatcher).forward(request, response);

        // Trường hợp password là null
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn(null);
        when(accountDAO.checkAuthen("test@example.com", null)).thenReturn(null);

        loginServlet.doPost(request, response);

        verify(request, times(2)).setAttribute("error", "Invalid username or password");
        verify(dispatcher, times(2)).forward(request, response);
    }

    // Test mới: Email hoặc password rỗng
    @Test
    void doPost_withEmptyInput() throws Exception {
        // Trường hợp email rỗng
        when(request.getParameter("email")).thenReturn("");
        when(request.getParameter("password")).thenReturn("password");
        when(accountDAO.checkAuthen("", "password")).thenReturn(null);
        when(request.getRequestDispatcher("login/login.jsp")).thenReturn(dispatcher);

        loginServlet.doPost(request, response);

        verify(request).setAttribute("error", "Invalid username or password");
        verify(dispatcher).forward(request, response);

        // Trường hợp password rỗng
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("");
        when(accountDAO.checkAuthen("test@example.com", "")).thenReturn(null);

        loginServlet.doPost(request, response);

        verify(request, times(2)).setAttribute("error", "Invalid username or password");
        verify(dispatcher, times(2)).forward(request, response);
    }
}