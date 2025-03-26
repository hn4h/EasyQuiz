/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Blog_comment;

import dal.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.Blog;

/**
 *
 * @author 11
 */
@WebServlet(name = "EditBlogServlet", urlPatterns = {"/editBlog"})
public class EditBlogServlet extends HttpServlet {

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
        BlogDAO blogDAO = new BlogDAO();
        String id = request.getParameter("blogId");
        if (id == null) {
            response.sendRedirect("error");
            return;
        }
        try {
            int blogId = Integer.parseInt(id);
            Blog blog = blogDAO.getBlogById(blogId);
            if (blog == null) {
                response.sendRedirect("error");
                return;
            }
            if (blog.isIsDeleted()) {
                response.sendRedirect("error");
                return;
            }
            Account account = (Account) request.getSession().getAttribute("account");
            if (!blog.getAuthor().getUserName().equals(account.getUserName())) {
                response.sendRedirect("error");
                return;
            }
            request.setAttribute("blog", blog);
            request.getRequestDispatcher("blog/editBlog.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error");
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
        String id = request.getParameter("blogId");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        if (id == null || title == null || content == null) {
            response.sendRedirect("error");
            return;
        }
        try {
            int blogId = Integer.parseInt(id);
            BlogDAO blogDAO = new BlogDAO();
            Blog blog = blogDAO.getBlogById(blogId);
            if (blog == null) {
                response.sendRedirect("error");
                return;
            }
            if (blog.isIsDeleted()) {
                response.sendRedirect("error");
                return;
            }
            Account account = (Account) request.getSession().getAttribute("account");
            if (!blog.getAuthor().getUserName().equals(account.getUserName())) {
                response.sendRedirect("error");
                return;
            }
            if (!title.equals(blog.getBlogTitle()) || !content.equals(blog.getBlogContent())) {
                blog.setBlogTitle(title);
                blog.setBlogContent(content);
                blogDAO.updateBlog(blog);
                request.getSession().setAttribute("successMessage", "Edit blog successfully!");
                response.sendRedirect("blogdetail?blogId=" + blogId);
            } else {
                response.sendRedirect("blogdetail?blogId=" + blogId);
            }

        } catch (Exception e) {
            response.sendRedirect("error");
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
