package controller.quiz;

import dal.QuizSetDAO;
import model.QuizSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

class QuizzServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private QuizSetDAO quizSetDAO;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private QuizzServlet quizSetServlet;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);

        // Inject mock DAO vào servlet bằng Reflection
        Field daoField = QuizzServlet.class.getDeclaredField("qsd");
        daoField.setAccessible(true);
        daoField.set(quizSetServlet, quizSetDAO);

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    void doGet_IdIsNull_ShouldRedirectToHome() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn(null);
        quizSetServlet.doGet(request, response);
        verify(response).sendRedirect("home");
    }

    @Test
    void doGet_IdIsNotANumber_ShouldRedirectToHome() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("abc");
        quizSetServlet.doGet(request, response);
        verify(response).sendRedirect("home");
    }

    @Test
    void doGet_QuizSetNotFound_ShouldRedirectToHome() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(quizSetDAO.getQuizSetById(1)).thenReturn(null);
        quizSetServlet.doGet(request, response);
        verify(response, times(1)).sendRedirect("home");
        verify(requestDispatcher, never()).forward(request, response);
    }

    @Test
    void doGet_QuizSetFound_ShouldForwardToQuizPage() throws ServletException, IOException {
        when(request.getParameter("id")).thenReturn("1");
        when(quizSetDAO.getQuizSetById(1)).thenReturn(new QuizSet());

        when(request.getRequestDispatcher("quiz/quiz.jsp")).thenReturn(requestDispatcher);

        quizSetServlet.doGet(request, response);

        verify(request).setAttribute(eq("quizDetail"), any());
        verify(requestDispatcher).forward(request, response);
    }
}