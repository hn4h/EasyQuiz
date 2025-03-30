
package blog;

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
 * @author admin
 */
@WebServlet(name="DeleteBlogServlet", urlPatterns={"/deleteblog"})
public class DeleteBlogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String blogId = request.getParameter("blogId");
        if (blogId == null) {
            response.sendRedirect("error");
            return;
        }
        if (blogId.isEmpty()) {
            response.sendRedirect("error");
            return;
        }
        int blogID;
        try {
            blogID = Integer.parseInt(blogId);
        } catch (NumberFormatException e) {
            response.sendRedirect("error");
            return;
        }
        BlogDAO bd = new BlogDAO();
        Blog blog = bd.getBlogById(Integer.parseInt(blogId));
        if (blog == null) {
            response.sendRedirect("error");
            return;
        }
        Account a = (Account) request.getSession().getAttribute("account");
        if (a == null) {
            response.sendRedirect("error");
            return;
        }
        if (blog.getAuthor().getUserName().equals(a.getUserName()) || a.isIsAdmin()) {
            bd.deleteBlog(blogID);
            request.getSession().setAttribute("successMessage", "Delete blog successfully!");
            response.sendRedirect("blog");
            return;
        } else {
            response.sendRedirect("error");
            return;
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String blogId = request.getParameter("blogId");
        if (blogId == null) {
            response.sendRedirect("error");
            return;
        }
        if (blogId.isEmpty()) {
            response.sendRedirect("error");
            return;
        }
        int blogID;
        try {
            blogID = Integer.parseInt(blogId);
        } catch (NumberFormatException e) {
            response.sendRedirect("error");
            return;
        }
        BlogDAO bd = new BlogDAO();
        Blog blog = bd.getBlogById(Integer.parseInt(blogId));
        if (blog == null) {
            response.sendRedirect("error");
            return;
        }
        Account a = (Account) request.getSession().getAttribute("account");
        if (a == null) {
            response.sendRedirect("error");
            return;
        }
        if (blog.getAuthor().equals(a.getUserName())) {
            bd.deleteBlog(blogID);
            response.sendRedirect("manageblog");
        } else {
            response.sendRedirect("error");
        }
        if(!a.isIsAdmin()) {
            response.sendRedirect("error");
            return;
        }else{
            bd.deleteBlog(blogID);
            response.sendRedirect("manageblog");
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
