/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import dal.PaymentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author 11
 */
@WebServlet(name="SuccessServlet", urlPatterns={"/success"})
public class SuccessServlet extends HttpServlet {
   private final PaymentDAO paymentDAO;

    public SuccessServlet() {
        this.paymentDAO = new PaymentDAO();
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String code = request.getParameter("code");
        String id = request.getParameter("id");
        String cancel = request.getParameter("cancel");
        String status = request.getParameter("status");
        String orderCode = request.getParameter("orderCode");
        if (id != null && status != null) {
                // Update payment status in database
                boolean updated = paymentDAO.updatePaymentStatus(id, status);
                if (updated) {
                    System.out.println("Payment status updated successfully: " + id + " -> " + status);
                } else {
                    System.out.println("No payment found with transactionId: " + id);
                }
            }
        request.getRequestDispatcher("upgrade/success.jsp").forward(request, response);
        
    } 
}
