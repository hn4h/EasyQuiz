/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.quiz;

import dal.QuizDAO;
import dal.QuizSetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.QuizSet;

/**
 *
 * @author 11
 */
@WebServlet(name = "AddQuizServlet", urlPatterns = {"/addquiz"})
public class AddQuizServlet extends HttpServlet {

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
            out.println("<title>Servlet AddQuizServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddQuizServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
        Account a = (Account) request.getSession().getAttribute("account");
        if(a == null) {
            response.sendRedirect("login");
            return;
        }
        request.getRequestDispatcher("quiz/createquiz.jsp").forward(request, response);
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
        Account a = (Account) request.getSession().getAttribute("account");
        if(a == null) {
            response.sendRedirect("login");
            return;
        }
        String title = request.getParameter("quizTitle");
        String description = request.getParameter("quizDescription");
        String numberOfQuestions = request.getParameter("questionCount");
        Account account = (Account) request.getSession().getAttribute("account");
        QuizSetDAO quizSetDAO = new QuizSetDAO();
        QuizSet qs = new QuizSet();
        qs.setAuthor(account);
        qs.setQuizSetName(title);
        qs.setQuizSetDescription(description);
        qs.setNumberOfQuiz(Integer.parseInt(numberOfQuestions));
        if(qs.getNumberOfQuiz() <= 0){
            request.setAttribute("error", "Number of questions must be greater than 0");
            request.setAttribute("quizSet", qs);
            request.getRequestDispatcher("quiz/createquiz.jsp").forward(request, response);
            return;
        }
        int quizSetId = quizSetDAO.addQuizSet(qs);
        QuizDAO quizDAO = new QuizDAO();
        for (int i = 1; i <= Integer.parseInt(numberOfQuestions); i++) {
            String question = request.getParameter("question" + i);
            int quizId = quizDAO.addQuiz(quizSetId, question);
            System.out.println(question);
            String correctAnswer = request.getParameter("correct" + i);
            System.out.println(correctAnswer);
            for (int j = 1; j <= 4; j++) {
                String answer = request.getParameter("answer" + i + "." + j);
                boolean isCorrect = false;
                if (j == Integer.parseInt(correctAnswer)) {
                    isCorrect = true;
                }
                quizDAO.addAnswer(quizId, answer, isCorrect);
            }
        }
        response.sendRedirect("quizz?id=" + quizSetId);
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
