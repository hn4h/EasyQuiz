/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.payment;

import java.io.IOException;
import java.util.Date;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import vn.payos.PayOS;
import vn.payos.type.CheckoutResponseData;
import vn.payos.type.ItemData;
import vn.payos.type.PaymentData;
/**
 *
 * @author Asus
 */
public class CheckoutServlet extends HttpServlet {
   
    private static final long serialVersionUID = 1L;
    private final PayOS payOS;

    public CheckoutServlet() {
        super();
        // Initialize PayOS here
        this.payOS = new PayOS("fdd3a44e-f5ea-445c-ae4e-ed1bf53398b8", "ef26f367-c15c-467e-8c93-985dd59a91bb", "6668d926ca0cfa8d8496d84402b08f1992aad29c2705801c47d12bdfdf31fc82");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/":
                request.getRequestDispatcher("/index.html").forward(request, response);
                break;
            case "/success":
                request.getRequestDispatcher("/success.html").forward(request, response);
                break;
            case "/cancel":
                request.getRequestDispatcher("/cancel.html").forward(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("/create-payment-link".equals(request.getServletPath())) {
            createPaymentLink(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void createPaymentLink(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            final String baseUrl = getBaseUrl(request);
            final String productName = "Mì tôm hảo hảo ly";
            final String description = "Thanh toan don hang";
            final String returnUrl = baseUrl + "/success";
            final String cancelUrl = baseUrl + "/cancel";
            final int price = 2000;
            
            // Gen order code
            String currentTimeString = String.valueOf(new Date().getTime());
            long orderCode = Long.parseLong(currentTimeString.substring(currentTimeString.length() - 6));
            
            ItemData item = ItemData.builder().name(productName).quantity(1).price(price).build();
            PaymentData paymentData = PaymentData.builder()
                    .orderCode(orderCode)
                    .amount(price)
                    .description(description)
                    .returnUrl(returnUrl)
                    .cancelUrl(cancelUrl)
                    .item(item)
                    .build();
            
            CheckoutResponseData data = payOS.createPaymentLink(paymentData);
            String checkoutUrl = data.getCheckoutUrl();

            response.sendRedirect(checkoutUrl);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while creating the payment link.");
        }
    }

    private String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();

        StringBuilder url = new StringBuilder(scheme).append("://").append(serverName);
        
        if ((scheme.equals("http") && serverPort != 80) || (scheme.equals("https") && serverPort != 443)) {
            url.append(":").append(serverPort);
        }
        
        url.append(contextPath);
        return url.toString();
    }
}
