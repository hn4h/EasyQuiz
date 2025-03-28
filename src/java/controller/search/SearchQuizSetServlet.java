/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.search;

import dal.QuizSetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.QuizSet;
import model.QuizSetDetail;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "SearchQuizSetServlet", urlPatterns = {"/searchquizset"})
public class SearchQuizSetServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchQuizSetServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchQuizSetServlet at " + request.getContextPath() + "</h1>");
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
        String input = request.getParameter("input");
        String quizId = request.getParameter("quizId");
        int id = 0;

        QuizSetDAO qsDao = new QuizSetDAO();
        List<QuizSet> qList = qsDao.searchAllQuizSetByName(input);
        if (qList == null || qList.isEmpty()) {
            request.setAttribute("quizSet", qList);
            request.setAttribute("input", input);
            request.getRequestDispatcher("search/flashcard.jsp").forward(request, response);
            return;
        } else {
            if (quizId == null) {
                id = qList.get(0).getQuizSetId();
            } else {
                try {
                    id = Integer.parseInt(quizId);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
        }
        QuizSetDetail quizDetail = qsDao.getQuizDetailById(id);
        request.setAttribute("quizTitle", qsDao.getQuizSetById(id).getQuizSetName());
        request.setAttribute("input", input);
        request.setAttribute("preview", quizDetail);
        request.setAttribute("quizSet", qList);
        request.getRequestDispatcher("search/flashcard.jsp").forward(request, response);
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
        response.sendRedirect("error");
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
