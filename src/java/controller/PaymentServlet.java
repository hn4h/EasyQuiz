/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.ItemData;
import vn.payos.type.PaymentData;
import vn.payos.*;
/**
 *
 * @author 11
 */
@WebServlet(name="PaymentServlet", urlPatterns={"/pay"})
public class PaymentServlet extends HttpServlet {
   
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
            out.println("<title>Servlet PaymentServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PaymentServlet at " + request.getContextPath () + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("payos/payos.jsp").forward(request, response);
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
        final String domain = "payos";
        String clientId = "fdd3a44e-f5ea-445c-ae4e-ed1bf53398b8";
        String apiKey = "ef26f367-c15c-467e-8c93-985dd59a91bb";
        String checksumKey = "6668d926ca0cfa8d8496d84402b08f1992aad29c2705801c47d12bdfdf31fc82";

        PayOS payOS = new PayOS(clientId, apiKey, checksumKey);
        Long orderCode = System.currentTimeMillis() / 1000;
            ItemData itemData = ItemData
                    .builder()
                    .name("Trial Package")
                    .quantity(1)
                    .price(200000)
                    .build();

            PaymentData paymentData = PaymentData
                    .builder()
                    .orderCode(orderCode)
                    .amount(200000)
                    .description("Thanh toán đơn hàng")
                    .returnUrl(domain + "/success.html")
                    .cancelUrl(domain + "/cancel.html")
                    .item(itemData)
                    .build();

            CheckoutResponseData result = null;
        try {
            result = payOS.createPaymentLink(paymentData);
        } catch (Exception ex) {
            Logger.getLogger(PaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            response.sendRedirect(result.getCheckoutUrl());

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
