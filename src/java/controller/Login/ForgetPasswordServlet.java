/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.Login;

import dal.AccountDAO;
import dal.TokenForgetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import model.TokenForgetPassword;
import service.ResetService;

/**
 *
 * @author 11
 */
@WebServlet(name = "ForgetPasswordServlet", urlPatterns = {"/forgetpassword"})
public class ForgetPasswordServlet extends HttpServlet {
    private final static String forgetPasswordLink = "login/forgetpassword.jsp";
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
            out.println("<title>Servlet ForgetPasswordServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForgetPasswordServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher(forgetPasswordLink).forward(request, response);
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
        AccountDAO dao = new AccountDAO();
        String email = request.getParameter("email");
        //email co ton tai trong db
        Account a = dao.getAccountByEmail(email);
        if (a == null) {
            request.setAttribute("mess", "Email doesn't existed");
            request.getRequestDispatcher(forgetPasswordLink).forward(request, response);
            return;
        }
        ResetService service = new ResetService();
//        ResetService service = new ResetService();
        String token = service.generateToken();

        String linkReset = "http://localhost:9999/QuizzApp/resetPassword?token=" + token;

        TokenForgetPassword newTokenForget = new TokenForgetPassword(
                a.getUserName(), false, token, service.expireDateTime());
        //send link to this email
        TokenForgetDAO daoToken = new TokenForgetDAO();
        boolean isInsert = daoToken.insertTokenForget(newTokenForget);
        if (!isInsert) {
            request.setAttribute("mess", "have error in server");
            request.getRequestDispatcher(forgetPasswordLink).forward(request, response);
            return;
        }
        boolean isSend = service.sendEmail(email, linkReset, a.getUserName());
        if (!isSend) {
            request.setAttribute("mess", "can not send request");
            request.getRequestDispatcher(forgetPasswordLink).forward(request, response);
            return;
        }
        request.setAttribute("success", "send request success");
        request.getRequestDispatcher(forgetPasswordLink).forward(request, response);
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
