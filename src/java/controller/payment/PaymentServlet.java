/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.payment;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PaymentServlet extends HttpServlet {
   
    private static final long serialVersionUID = 1L;
    private final Gson gson;
    private static final String PAYOS_API_URL = "https://sandbox.payos.vn/v1/payment-requests";
    private static final String CLIENT_ID = "fdd3a44e-f5ea-445c-ae4e-ed1bf53398b8";
    private static final String API_KEY = "ef26f367-c15c-467e-8c93-985dd59a91bb";
    private static final String CHECKSUM_KEY = "6668d926ca0cfa8d8496d84402b08f1992aad29c2705801c47d12bdfdf31fc82";

    public PaymentServlet() {
        this.gson = new Gson();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Get form parameters
            String amount = request.getParameter("amount");
            String description = request.getParameter("description");

            // Create payment request body
            Map<String, Object> paymentData = new HashMap<>();
            paymentData.put("orderCode", String.valueOf(System.currentTimeMillis()).substring(7));
            paymentData.put("amount", Integer.parseInt(amount));
            paymentData.put("description", description);
            
            // Add items array
            Map<String, Object> item = new HashMap<>();
            item.put("name", "Product");
            item.put("quantity", 1);
            item.put("price", Integer.parseInt(amount));
            paymentData.put("items", new Object[]{item});

            // Add return URLs
            String domain = request.getScheme() + "://" + request.getServerName() + ":" + 
                          request.getServerPort() + request.getContextPath();
            paymentData.put("returnUrl", domain + "/success.html");
            paymentData.put("cancelUrl", domain + "/cancel.html");

            // Create connection to PayOS
            URL url = new URL(PAYOS_API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("x-client-id", CLIENT_ID);
            conn.setRequestProperty("x-api-key", API_KEY);
            conn.setDoOutput(true);

            // Write request body
            String jsonBody = gson.toJson(paymentData);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Read response
            StringBuilder responseBody = new StringBuilder();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    responseBody.append(responseLine.trim());
                }
            }

            // Parse response
            Map<String, Object> responseData = gson.fromJson(responseBody.toString(), Map.class);

            if (responseData.containsKey("checkoutUrl")) {
                // Redirect to PayOS checkout page
                response.sendRedirect((String) responseData.get("checkoutUrl"));
            } else {
                throw new Exception("Payment creation failed: " + 
                    responseData.getOrDefault("desc", "Unknown error"));
            }

        } catch (Exception e) {
            response.setStatus(500);
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            response.setContentType("application/json");
            String jsonError = gson.toJson(errorResponse);
            response.getWriter().write(jsonError);
        }
    }
}
