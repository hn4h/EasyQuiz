/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Login;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import model.GoogleAccount;

/**
 *
 * @author 11
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        String code = request.getParameter("code");
        if (code == null) {
            return;
        }
        String accessToken = GoogleLogin.getToken(code);
        GoogleAccount acc = GoogleLogin.getUserInfo(accessToken);
        if (acc == null) {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("client/login.jsp").forward(request, response);
        } else {
            AccountDAO d = new AccountDAO();

            Account a = d.checkEmail(acc.getEmail());
            if (a == null) {
                d.createAccountByEmail(acc.getEmail(), acc.getPicture());
                a = d.checkEmail(acc.getEmail());
            } else if (a.isIsDeleted()) {
                request.setAttribute("error", "Your account isn't allowed to log in!");
                request.getRequestDispatcher("login/login.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("account", a);
                LocalDate expiredDate = a.getExpiredDate().toLocalDate(); // Chuyển từ java.sql.Date sang LocalDate nếu cần
                LocalDate today = LocalDate.now();
                if (ChronoUnit.DAYS.between(today, expiredDate) == 1) {
                    request.getSession().setAttribute("warningMessage", "Your premium package will be expired tomorrow!");
                }
                request.getSession().setAttribute("successMessage", "Login successfully!");
                response.sendRedirect("home");
            }
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
        if (request.getParameter("code") != null) {
            processRequest(request, response);
        } else {
            request.getRequestDispatcher("login/login.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        AccountDAO d = new AccountDAO();
        Account a = d.checkAuthen(email, password);
        if (a == null) {
            request.setAttribute("error", "Invalid username or password!");
            request.getRequestDispatcher("login/login.jsp").forward(request, response);
        } else if (a.isIsDeleted()) {
            request.setAttribute("error", "This account is deleted!");
            request.getRequestDispatcher("login/login.jsp").forward(request, response);
        } else {
            if (a.isIsAdmin()) {
                HttpSession session = request.getSession();
                session.setAttribute("account", a);
                request.getSession().setAttribute("successMessage", "Login successfully!");
                response.sendRedirect("dashboard");
            }else{
            HttpSession session = request.getSession();
            session.setAttribute("account", a);
            request.getSession().setAttribute("successMessage", "Login successfully!");
            response.sendRedirect("home");}
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
