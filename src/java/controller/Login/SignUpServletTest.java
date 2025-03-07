package controller.Login;

import dal.AccountDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SignUpServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private AccountDAO accountDAO;

    @Mock
    private HttpSession session; // Mock the session

    @InjectMocks
    private SignUpServlet signUpServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("login/register.jsp")).thenReturn(requestDispatcher);

        signUpServlet.doGet(request, response);

        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doPost_SuccessfulSignup() throws ServletException, IOException {
        String username = "testuser";
        String password = "test_password";
        String email = "test@example.com";

        when(request.getParameter("username")).thenReturn(username);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("email")).thenReturn(email);
        when(request.getParameter("confirmPassword")).thenReturn(password); // Passwords match
        when(accountDAO.checkUsername(username)).thenReturn(false); // Username doesn't exist
        when(request.getSession()).thenReturn(session); // Get the mocked session

        signUpServlet.doPost(request, response);

        // verify(accountDAO).createAccount(username, password, email); // Verify account creation
        verify(session).setAttribute("username", username); // Verify session attributes
        verify(session).setAttribute("password", password);
        verify(session).setAttribute("email", email);
        verify(response).sendRedirect("verifyotp");
    }

    @Test
    void doPost_UsernameExists() throws ServletException, IOException {
        String username = "EasyQuiz343293";

        when(request.getParameter("username")).thenReturn(username);
        when(accountDAO.checkUsername(username)).thenReturn(true); // Username exists
        when(request.getRequestDispatcher("login/register.jsp")).thenReturn(requestDispatcher);

        signUpServlet.doPost(request, response);

        verify(request).setAttribute("error", "Username already exists");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doPost_PasswordsDontMatch() throws ServletException, IOException {
        String password = "test_password";
        String differentPassword = "different_password";

        when(request.getParameter("password")).thenReturn(password);
        when(request.getParameter("confirmPassword")).thenReturn(differentPassword); // Passwords don't match
        when(request.getRequestDispatcher("login/register.jsp")).thenReturn(requestDispatcher);

        signUpServlet.doPost(request, response);

        verify(request).setAttribute("error", "Password and Repeat Password do not match");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void processRequest() throws ServletException, IOException {
        // Mock PrintWriter để tránh NullPointerException
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);

        // Gọi processRequest
        signUpServlet.processRequest(request, response);

        // Verify rằng PrintWriter.println() được gọi (hoặc các logic khác trong processRequest)
        verify(writer, atLeastOnce()).println(anyString());
    }

    @Test
    void getServletInfo() {
        String servletInfo = signUpServlet.getServletInfo();
        assertEquals("Short description", servletInfo);
    }
}