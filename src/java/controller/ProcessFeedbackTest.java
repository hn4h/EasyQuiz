package controller;

import dal.FeedbackDAO;
import jakarta.servlet.RequestDispatcher;
import model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.PrintWriter;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ProcessFeedbackTest {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpSession session;

    @Mock
    FeedbackDAO feedbackDAO;

    @InjectMocks
    ProcessFeedback processFeedback;

//    @InjectMocks
//    ProcessFeedback processFeedback = new ProcessFeedback(feedbackDAO);
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        processFeedback = new ProcessFeedback(feedbackDAO);
    }

    @Test
    void doPost_withValidFeedback() throws Exception {
        when(request.getParameter("feedbackContent")).thenReturn("This is a feedback");
        when(request.getSession()).thenReturn(session);
        Account account = new Account();
        account.setUserName("EasyQuiz173299");
        when(session.getAttribute("account")).thenReturn(account);
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        doNothing().when(writer).flush();
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("feedback/feedback.jsp")).thenReturn(dispatcher);

        processFeedback.doPost(request, response);

        verify(feedbackDAO, times(1)).addFeedback("EasyQuiz173299", "This is a feedback");
        verify(request, times(1)).setAttribute("successMessage", "Send feedback successfully.");
        verify(request, times(1)).getRequestDispatcher("feedback/feedback.jsp");
        verify(dispatcher).forward(request, response);
    }
    @Test
    void doPost_withNoAccount() throws Exception {
        when(request.getParameter("feedbackContent")).thenReturn("This is a feedback");
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("account")).thenReturn(null);
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        doNothing().when(writer).flush();
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("feedback/feedback.jsp")).thenReturn(dispatcher);

        processFeedback.doPost(request, response);

        verify(request, times(1)).setAttribute("errorMessage", "You must log in before performing this action.");
        verify(request, times(1)).getRequestDispatcher("feedback/feedback.jsp");
    }

    @Test
    void doPost_withEmptyFeedback() throws Exception {
        when(request.getParameter("feedbackContent")).thenReturn("");
        when(request.getSession()).thenReturn(session);
        Account account = new Account();
        account.setUserName("testUser");
        when(session.getAttribute("account")).thenReturn(account);
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        doNothing().when(writer).flush();
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("feedback/feedback.jsp")).thenReturn(dispatcher);

        processFeedback.doPost(request, response);

        verify(request, times(1)).setAttribute("errorMessage", "You must complete the content before performing this action.");
        verify(request, times(1)).getRequestDispatcher("feedback/feedback.jsp");
    }

    @Test
    void doPost_withFeedbackDAOException() throws Exception {
        when(request.getParameter("feedbackContent")).thenReturn("This is a feedback");
        when(request.getSession()).thenReturn(session);
        Account account = new Account();
        account.setUserName("testUser");
        when(session.getAttribute("account")).thenReturn(account);
        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);
        doNothing().when(writer).flush();
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("feedback/feedback.jsp")).thenReturn(dispatcher);
        doThrow(new RuntimeException("Database error")).when(feedbackDAO).addFeedback(anyString(), anyString());

        processFeedback.doPost(request, response);

        verify(request, times(1)).setAttribute("errorMessage", "An error occurred while processing your feedback. Please try again later.");
    }

        @Test
    void doGet_shouldForwardToFeedbackJsp() throws Exception {
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("feedback/feedback.jsp")).thenReturn(dispatcher);

        processFeedback.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher("feedback/feedback.jsp");
        verify(dispatcher).forward(request, response);
    }
    @Test
    void getServletInfo_shouldReturnShortDescription() {
        String info = processFeedback.getServletInfo();
        assertEquals("Short description", info);
    }

}