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
@WebServlet(name="ActivePackageServlet", urlPatterns={"/activepackage"})
public class ActivePackageServlet extends HttpServlet {
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
        PackageDAO pd = new PackageDAO();
        String pid = request.getParameter("packageId");
        String status = request.getParameter("status");
        int id = 0;
        try {
            id = Integer.parseInt(pid);
            if ("Active".equals(status)) {
                pd.activePackage(id);
            } else {
                pd.deactivePackage(id);
            }
            response.sendRedirect("managepackage");
        } catch (Exception e) {
            System.out.println(e);
        }
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
