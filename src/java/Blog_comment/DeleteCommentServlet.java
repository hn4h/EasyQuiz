/*
 * Click nbfs://netbeans/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://netbeans/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Blog_comment;

import dal.CommentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import model.Account;
import model.Comment;

/**
 *
 * @author DUCA
 */
@WebServlet(name = "DeleteCommentServlet", urlPatterns = {"/deletecomment"})
public class DeleteCommentServlet extends HttpServlet {

    private CommentDAO commentDAO;

    @Override
    public void init() throws ServletException {
        commentDAO = new CommentDAO();
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteCommentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteCommentServlet at " + request.getContextPath() + "</h1>");
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

        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        // Get parameters from the request
        int commentId;
        try {
            commentId = Integer.parseInt(request.getParameter("commentId"));
        } catch (NumberFormatException e) {
            response.sendRedirect("error");
            return;
        }
        int blogId;
        try {
            // Get the comment from the database to verify ownership
            Comment comment = commentDAO.getCommentById(commentId);
            blogId = comment.getBlogId();
            if (comment == null) {
                response.sendRedirect("error");
                return;
            }

            // Check if the logged-in user is the owner of the comment
            if (!comment.getUserName().equals(account.getUserName())) {
               response.sendRedirect("error"); 
                return;
            }

            // Delete the comment from the database
            commentDAO.deleteComment(commentId);
            response.sendRedirect("blogdetail?blogId=" + blogId);
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to delete comment: " + e.getMessage());
        }
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
        // Set response content type
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        // Get the session to retrieve the logged-in user
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("You must be logged in to delete a comment.");
            return;
        }

        // Get parameters from the request
        int commentId;
        try {
            commentId = Integer.parseInt(request.getParameter("commentId"));
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid comment ID.");
            return;
        }

        try {
            // Get the comment from the database to verify ownership
            Comment comment = commentDAO.getCommentById(commentId);
            if (comment == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Comment not found.");
                return;
            }

            // Check if the logged-in user is the owner of the comment
            if (!comment.getUserName().equals(account.getUserName())) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("You are not authorized to delete this comment.");
                return;
            }

            // Delete the comment from the database
            commentDAO.deleteComment(commentId);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Comment deleted successfully.");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Failed to delete comment: " + e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet for deleting comments";
    }// </editor-fold>
}