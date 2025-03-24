/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.dashboard;

import dal.PackageDAO;
import dal.StatisDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;

/**
 *
 * @author 11
 */
@WebServlet(name = "ManagePackageServlet", urlPatterns = {"/managepackage"})
public class ManagePackageServlet extends HttpServlet {

    private PackageDAO dao = new PackageDAO();

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
        List<model.Package> packages = dao.getAllPackagesForDashboard(); // Sử dụng phương thức mới
        request.setAttribute("packages", packages);
        request.getRequestDispatcher("dashboard/package.jsp").forward(request, response);
    }
}
