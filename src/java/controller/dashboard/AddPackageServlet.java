/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.dashboard;

import dal.PackageDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Package;
/**
 *
 * @author 11
 */
@WebServlet(name="AddPackageServlet", urlPatterns={"/addPackage"})
public class AddPackageServlet extends HttpServlet {
   
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String name = request.getParameter("packageName");
        String description = request.getParameter("packageDescription");
        String valueString = request.getParameter("packageValue");
        String priceString = request.getParameter("packagePrice");
        int value,price;
        try {
            System.out.println(valueString);
            System.out.println(priceString);
            value = Integer.parseInt(valueString);
            price = Integer.parseInt(priceString);
        } catch (Exception e) {
            request.getSession().setAttribute("error", "Invalid value or price");
            response.sendRedirect("managepackage");
            System.out.println("loi1");
            return;
        }    
        PackageDAO pd = new PackageDAO();
        Package pack = pd.getPackageByName(name);
        if (pack != null) {
            request.getSession().setAttribute("error", "Package name is existed");
            response.sendRedirect("managepackage");
            System.out.println("loi2");
            return;
        }
        if (name == null || description == null || name.isEmpty() || description.isEmpty()) {
            request.getSession().setAttribute("error", "Name and description cannot be empty");
            response.sendRedirect("managepackage");
            System.out.println("loi3");
            return;
        }
        if (value <= 0 || price <= 0) {
            request.getSession().setAttribute("error", "Value and price must be greater than 0");
            response.sendRedirect("managepackage");
            System.out.println("loi4");
            return;
        }
        // Add package to database
        PackageDAO dao = new PackageDAO();
        Package p = new Package(name, description, value, price);
        dao.addPackage(p);
        // Redirect to managepackage
        request.getSession().setAttribute("message", "Add package successfully!");
        response.sendRedirect("managepackage");
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
