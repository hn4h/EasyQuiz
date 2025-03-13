package controller.Login;

import static org.mockito.Mockito.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

class LogOutServletTest {

    @InjectMocks
    private LogOutServlet yourServlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoGet() throws ServletException, IOException {
        // Mock session
        when(request.getSession()).thenReturn(session);

        // Gọi phương thức doGet()
        yourServlet.doGet(request, response);

        // Kiểm tra xem session đã remove attribute "account" chưa
        verify(session).removeAttribute("account");

        // Kiểm tra xem response đã redirect đến "home" chưa
        verify(response).sendRedirect("home");
    }
}