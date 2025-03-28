/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.quiz;

import dal.AccountDAO;
import dal.QuizDAO;
import dal.QuizSetDAO;
import model.Quiz;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import model.Account;
import model.TestSession;

/**
 *
 * @author DUCA
 */
@WebServlet(name = "TestQuizServlet", urlPatterns = {"/testquiz"})
public class TestQuizServlet extends HttpServlet {

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
            out.println("<title>Servlet TestQuizServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestQuizServlet at " + request.getContextPath() + "</h1>");
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
        String quizSetIDParam = request.getParameter("quizSetID");
        String numberQuizParam = request.getParameter("numberQuiz"); 
        int timeLimit = Integer.parseInt(request.getParameter("timeLimit"));
        QuizSetDAO qsd = new QuizSetDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        
        if (a == null) {
            response.sendRedirect("login");
            return;
        }
        AccountDAO ad = new AccountDAO();
        if(!ad.checkPremium(a.getUserName())){
            if(!ad.checkLimitTest(a.getUserName())){
                session.setAttribute("message","You have used up all your test attempts for today. Please come back tomorrow.");
                response.sendRedirect("quizz?id="+ quizSetIDParam);
                return;
            }
        }
        if (quizSetIDParam != null) {
            try {
                int quizSetID = Integer.parseInt(quizSetIDParam);
                QuizDAO quizDAO = new QuizDAO();
                List<Quiz> quizzes = quizDAO.getQuizzesByQuizSetID(quizSetID);
                
                if (quizzes == null || quizzes.isEmpty()) {
                    request.setAttribute("error", "No quizzes found for quizSetID: " + quizSetID);
                } else {
                    for(Quiz quiz : quizzes){
                        Collections.shuffle(quiz.getAnswers());
                    }
                    TestSession test = new TestSession();
                    test.setQuizSetID(quizSetID);
                    test.setTimeLimit(timeLimit);
                    session.setAttribute("testSession", test);
                    // xu ly so quiz ngau nhien voi so number quiz duoc cung cap
                    if (numberQuizParam != null) {
                        try {
                            int numberQuiz = Integer.parseInt(numberQuizParam);
                            test.setTotalQuestions(numberQuiz);
                            if (numberQuiz > 0 && numberQuiz <= quizzes.size()) {
                                // tron ngau nhien danh sach quiz va lay so luong can thiet
                                Collections.shuffle(quizzes);
                                quizzes = quizzes.subList(0, numberQuiz);
                            } else if (numberQuiz > quizzes.size()) {
                                // Nếu số lượng yêu cầu lớn hơn số quiz hiện có, lấy tất cả
                                // Không cần làm gì thêm vì quizzes đã chứa toàn bộ
                            } else {
                                request.setAttribute("error", "Invalid number of quizzes requested");
                            }
                        } catch (NumberFormatException e) {
                            request.setAttribute("error", "Invalid numberQuiz parameter");
                        }
                    }
                    test.setQuestions(quizzes);
                    request.setAttribute("quizDetail", qsd.getQuizDetailById(quizSetID));
                    request.setAttribute("quizzes", quizzes);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Quiz Set ID");
            }
        }
        request.getRequestDispatcher("quiz/testquiz.jsp").forward(request, response);
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
        processRequest(request, response);
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
