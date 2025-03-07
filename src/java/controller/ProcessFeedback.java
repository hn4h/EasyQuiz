/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DBContext;
import dal.FeedbackDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author DUCA
 */
@WebServlet(name = "ProcessFeedback", urlPatterns = {"/feedback"})
public class ProcessFeedback extends HttpServlet {
    private FeedbackDAO feedbackDAO;

    public ProcessFeedback(FeedbackDAO feedbackDAO) {
        this.feedbackDAO = feedbackDAO;
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        request.getRequestDispatcher("feedback/feedback.jsp").forward(request, response);
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
        String feedbackContent = request.getParameter("feedbackContent");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            //handle no username
            request.setAttribute("errorMessage", "You must log in before performing this action.");
            request.getRequestDispatcher("feedback/feedback.jsp").forward(request, response);
        }
        else if (feedbackContent.trim().isEmpty()) {
            request.setAttribute("errorMessage", "You must complete the content before performing this action.");
            request.getRequestDispatcher("feedback/feedback.jsp").forward(request, response);

        }
        else {
            try {
                feedbackDAO.addFeedback(account.getUserName(), feedbackContent);
                request.setAttribute("successMessage", "Send feedback successfully.");
            } catch (RuntimeException e) {
                request.setAttribute("errorMessage", "An error occurred while processing your feedback. Please try again later.");
            }
            request.getRequestDispatcher("feedback/feedback.jsp").forward(request, response);
        }
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
