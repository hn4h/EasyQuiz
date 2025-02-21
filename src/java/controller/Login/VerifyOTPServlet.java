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
import jakarta.servlet.http.HttpSession;
import model.OTP;
import model.TokenForgetPassword;
import service.OTPService;
import service.ResetService;

/**
 *
 * @author 11
 */
@WebServlet(name="VerifyOTPServlet", urlPatterns={"/verifyotp"})
public class VerifyOTPServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet VerifyOTPServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet VerifyOTPServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String link= "login/otp.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        if(email == null){
            response.sendRedirect("home");
        }else{
            OTPService service = new OTPService();
            String otp = service.generateOTP();
            session.setAttribute("otp", otp);
            boolean isSend = service.sendEmail(email, otp);
        if (!isSend) {
            request.setAttribute("mess", "can not send request");
            request.getRequestDispatcher(link).forward(request, response);
            return;
        }
        request.setAttribute("success", "send request success");
        request.getRequestDispatcher(link).forward(request, response);            
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
        String otp = request.getParameter("otp1") + request.getParameter("otp2") + request.getParameter("otp3") + request.getParameter("otp4") + request.getParameter("otp5") + request.getParameter("otp6");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String otpSession = (String) session.getAttribute("otp");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        if(otp == null || otpSession == null || !otp.equals(otpSession)){
            request.setAttribute("mess", "otp is not correct");
            request.getRequestDispatcher(link).forward(request, response);
            return;
        }
        AccountDAO dao = new AccountDAO();
        dao.createAccount(username, password, email);
        session.removeAttribute("email");
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.removeAttribute("otp");
        response.sendRedirect("login");
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
