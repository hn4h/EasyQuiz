/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.dashboard;

import dal.PaymentDAO;
import dal.StatisDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author 11
 */
@WebServlet(name="ManageTransactionServlet", urlPatterns={"/managetransaction"})
public class ManageTransactionServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Account a = (Account) request.getSession().getAttribute("account");
        if(a == null) {
            response.sendRedirect("login");
            return;
        }
        if(a.isIsAdmin() == false) {
            response.sendRedirect("error");
            return;
        }
        PaymentDAO paymentDAO = new PaymentDAO();
        request.setAttribute("transactions", paymentDAO.getAllPayments());
        request.getRequestDispatcher("dashboard/transaction.jsp").forward(request, response);
    } 
}
