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

/**
 *
 * @author 11
 */
@WebServlet(name = "DashBoardServlet", urlPatterns = {"/dashboard"})
public class DashBoardServlet extends HttpServlet {

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
            out.println("<title>Servlet DashBoardServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashBoardServlet at " + request.getContextPath() + "</h1>");
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
        
        String chartOption = request.getParameter("chartOption");
        StatisDAO db = new StatisDAO();
        request.setAttribute("numOfFeedback", db.getNumberOfFeedback());
        request.setAttribute("numOfUser", db.getNumberOfUser());
        request.setAttribute("numOfTransaction", db.getNumberOfTransaction());
        request.setAttribute("totalRevenue", db.getTotalRevenue());
        request.setAttribute("newUser", db.getNewCreatedUser());
        request.setAttribute("newTransaction", db.getNewPayments());
        if (chartOption == null) {
            request.setAttribute("chart", db.getRevenueLast30Days());
            request.setAttribute("chartOption", "2");
        } else {
            switch (chartOption) {
                case "1":
                    request.setAttribute("chart", db.getRevenueLast7Days());
                    request.setAttribute("chartOption", "1");
                    break;
                case "2":
                    request.setAttribute("chart", db.getRevenueLast30Days());
                    request.setAttribute("chartOption", "2");
                    break;
                case "3":
                    request.setAttribute("chart", db.getRevenueLast90Days());
                    request.setAttribute("chartOption", "3");
                    break;
                case "4":
                    request.setAttribute("chart", db.getRevenueThisYear());
                    request.setAttribute("chartOption", "4");
                    break;
                default:
                    request.setAttribute("chart", db.getRevenueLast30Days());
                    request.setAttribute("chartOption", "2");
            }
        }
        request.getRequestDispatcher("dashboard/dashboard.jsp").forward(request, response);
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
        processRequest(request, response);
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
