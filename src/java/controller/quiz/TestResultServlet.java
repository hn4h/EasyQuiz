/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.quiz;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dal.QuizDAO;
import dal.QuizSetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Account;
import model.Answer;
import model.Quiz;
import model.TestSession;
import model.UserAnswer;

/**
 *
 * @author DUCA
 */
@WebServlet(name = "TestResultServlet", urlPatterns = {"/testresult"})
public class TestResultServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TestResultServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestResultServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("error");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        TestSession test = (TestSession) session.getAttribute("testSession");
        if (test == null) {
            response.sendRedirect("home");
            return;
        }
        int correctCount = 0;
        List<UserAnswer> userAnswers = new ArrayList<>();

        for (Quiz quiz : test.getQuestions()) {
            String answerParam = request.getParameter("userAnswer_" + quiz.getQuizID());
            if (answerParam != null && !answerParam.trim().isEmpty()) {
                int selectedAnswerID = Integer.parseInt(answerParam);
                UserAnswer userAnswer = new UserAnswer(quiz.getQuizID(), selectedAnswerID);
                userAnswers.add(userAnswer);

                for (Answer a : quiz.getAnswers()) {
                    if (a.getAnswerID() == selectedAnswerID && a.isCorrect()) {
                        correctCount++;
                    }
                }
            }
        }
        int incorrectCount = test.getTotalQuestions() - correctCount;
        int percentage = (int) ((correctCount * 100.0) / test.getTotalQuestions());
        test.setUserAnswers(userAnswers);
        request.setAttribute("percentage", percentage);
        request.setAttribute("incorrectCount", incorrectCount);
        request.setAttribute("correctCount", correctCount);
        request.getRequestDispatcher("quiz/testresult.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
