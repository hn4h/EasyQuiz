/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.dashboard;

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
@WebServlet(name="ManageUserServlet", urlPatterns={"/manageuser"})
public class ManageUserServlet extends HttpServlet {
   
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
        StatisDAO dao = new StatisDAO();
        request.setAttribute("users", dao.getAllUserStatistics());
        request.getRequestDispatcher("dashboard/user.jsp").forward(request, response);
        } 
}
