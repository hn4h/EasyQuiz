/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.dashboard;

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
 * @author admin
 */
@WebServlet(name="EditPackgeServlet", urlPatterns={"/editPackage"})
public class EditPackgeServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        String idString = request.getParameter("editPackageId");
        String name = request.getParameter("editPackageName");
        String description = request.getParameter("editPackageDescription");
        String valueString = request.getParameter("editPackageValue");
        String priceString = request.getParameter("editPackagePrice");
        int value,price, id;
        try {
            System.out.println(valueString);
            System.out.println(priceString);
            value = Integer.parseInt(valueString);
            price = Integer.parseInt(priceString);
            id = Integer.parseInt(idString);
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Invalid value or price");
            response.sendRedirect("managepackage");
            System.out.println("loi1");
            return;
        }        
        if (name == null || description == null || name.isEmpty() || description.isEmpty()) {
            request.getSession().setAttribute("error", "Name and description cannot be empty");
            response.sendRedirect("managepackage");
            System.out.println("loi2");
            return;
        }
        if (value <= 0 || price <= 0) {
            request.getSession().setAttribute("error", "Value and price must be greater than 0");
            response.sendRedirect("managepackage");
            System.out.println("loi3");
            return;
        }
        // Add package to database
        PackageDAO dao = new PackageDAO();
        model.Package p = new model.Package(name, description, value, price);
        p.setId(id);
        dao.updatePackage(p);
        // Redirect to managepackage
        request.getSession().setAttribute("message", "Edit package successfully!");
        response.sendRedirect("managepackage");
    }

}
