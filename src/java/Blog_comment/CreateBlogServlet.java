/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Blog_comment;

import dal.BlogDAO;
import dal.DBContext;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "CreateBlogServlet", urlPatterns = {"/createblog"})
public class CreateBlogServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BlogDAO blogDAO; // Add BlogDAO instance

    @Override
    public void init() throws ServletException {
        blogDAO = new BlogDAO(); // Initialize BlogDAO
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateBlogServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateBlogServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        // Get session and account
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        // Check if user is logged in
        if (account == null) {
            request.setAttribute("errorMessage", "You must log in before creating a blog.");
            request.getRequestDispatcher("blog/blog.jsp").forward(request, response);
            return;
        }

        // Get form parameters
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String author = account.getUserName();

        // Debug output to verify values
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
        System.out.println("Author: " + author);

        // Validate parameters
        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            request.setAttribute("errorMessage", "You must complete all fields before creating a blog.");
            request.getRequestDispatcher("blog/blog.jsp").forward(request, response);
            return;
        }

        // Add blog to database
        boolean success = blogDAO.addBlog(title, content, author);
        if (success) {
            System.out.println("Blog created successfully.");
            request.setAttribute("successMessage", "Blog created successfully.");
            response.sendRedirect(request.getContextPath() + "/blog");
        } else {
            request.setAttribute("errorMessage", "Failed to create blog. Please try again.");
            request.getRequestDispatcher("blog/blog.jsp").forward(request, response);
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
