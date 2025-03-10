/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.quiz;

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
import java.util.ArrayList;
import model.Account;

/**
 *
 * @author DUCA
 */
@WebServlet(name = "LearnQuizServlet", urlPatterns = {"/learnquiz"})
public class LearnQuizServlet extends HttpServlet {

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
            out.println("<title>Servlet LearnQuizServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LearnQuizServlet at " + request.getContextPath() + "</h1>");
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
        QuizSetDAO qsd = new QuizSetDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        if(a == null){
            response.sendRedirect("login");
            return;
        }
        if (quizSetIDParam != null) {
            try {
                int quizSetID = Integer.parseInt(quizSetIDParam);
                QuizDAO quizDAO = new QuizDAO();
                List<Quiz> quizzes = quizDAO.getQuizzesByQuizSetID(quizSetID);
                if (quizzes == null || quizzes.isEmpty()) {
                    request.setAttribute("error", "No quizzes found for quizSetID: " + quizSetID);
                } else {
                    // Kiểm tra và đảm bảo answers không null
                    for (Quiz quiz : quizzes) {
                        if (quiz.getAnswers() == null) {
                            quiz.setAnswers(new ArrayList<>());
                        }
                    }
                    request.setAttribute("quizDetail", qsd.getQuizDetailById(quizSetID));
                    request.setAttribute("quizzes", quizzes);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Quiz Set ID");
            }
        }
        request.getRequestDispatcher("quiz/learnquiz.jsp").forward(request, response);
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
