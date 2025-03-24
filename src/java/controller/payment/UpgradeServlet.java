/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.payment;

import dal.PackageDAO;
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
@WebServlet(name="UpgradeServlet", urlPatterns={"/upgrade"})
public class UpgradeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        Account a = (Account) request.getSession().getAttribute("account");
        if(a == null) {
            response.sendRedirect("login");
            return;
        }
        PackageDAO packageDAO = new PackageDAO();
        request.setAttribute("packages", packageDAO.getAllPackagesForPurchase());
        request.getRequestDispatcher("upgrade/upgrade.jsp").forward(request, response);
    } 

}
