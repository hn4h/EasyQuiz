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
@WebServlet(name = "DashBoardServlet", urlPatterns = {"/dashboard"})
public class DashBoardServlet extends HttpServlet {

    
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
}
