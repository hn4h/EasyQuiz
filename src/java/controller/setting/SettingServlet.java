/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.setting;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author DUCA
 */
@WebServlet(name = "SettingServlet", urlPatterns = {"/setting"})
public class SettingServlet extends HttpServlet {

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
            out.println("<title>Servlet SettingServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingServlet at " + request.getContextPath() + "</h1>");
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
    private AccountDAO accountDAO;

    @Override
    public void init() throws ServletException {
        accountDAO = new AccountDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        account = accountDAO.getAccountByUserName(account.getUserName());
        if (account == null) {
            response.sendRedirect("login");
            return;
        }
        session.setAttribute("account", account);

        request.getRequestDispatcher("/setting/setting.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String alertMessage = "";
        if (account == null) {
            response.sendRedirect("login");
            return;
        }

        String action = request.getParameter("action");
//        System.out.println("Action received: " + action);

        if ("updateAvatar".equals(action)) {
            String newAvatar = request.getParameter("avatar");
            System.out.println("Received avatar path: " + newAvatar);
            if (newAvatar != null) {
                newAvatar = newAvatar.replace("./", "");
//                System.out.println("Normalized avatar path: " + newAvatar);
                if (accountDAO.updateProfileImage(account.getUserName(), newAvatar)) {
                    account.setProfileImage(newAvatar);
                    session.setAttribute("account", account);
                    alertMessage = "Change avatar successfully!";
//                    System.out.println("Avatar updated successfully to: " + newAvatar);
                } else {
//                    System.out.println("Failed to update avatar in database for: " + newAvatar);
                }
            } else {
//                System.out.println("Avatar parameter is null");
            }
        } else if ("updateUsername".equals(action)) {
            String newUsername = request.getParameter("newUsername");
//            System.out.println("Received new username: " + newUsername);
//            System.out.println("Current username: " + account.getUserName());

            if (newUsername != null && !newUsername.trim().isEmpty()) {
                if (!newUsername.equals(account.getUserName())) { // Check if it's actually different
                    if (!accountDAO.checkUsername(newUsername)) { // Ensure username is unique
                        if (accountDAO.updateUsername(account.getUserName(), newUsername)) {
                            account.setUserName(newUsername);
                            alertMessage = "Change username successfully!";
                            session.setAttribute("account", account);
//                            System.out.println("Username updated successfully to: " + newUsername);
                        } else {
//                            System.out.println("Failed to update username in database for: " + newUsername);
                            request.setAttribute("error", "Failed to update username. Please try again.");
                            //request.getSession().setAttribute("error", "Failed to update username. Please try again.");
                            request.getRequestDispatcher("/setting/setting.jsp").forward(request, response);
                            //response.sendRedirect("setting?t=" + System.currentTimeMillis());
                            return;
                        }
                    } else {
//                        System.out.println("Username already exists: " + newUsername);
                        request.setAttribute("error", "Username already taken. Please choose another.");
                        //request.getSession().setAttribute("error", "Username already taken. Please choose another.");
                        request.getRequestDispatcher("/setting/setting.jsp").forward(request, response);
                        //response.sendRedirect("setting?t=" + System.currentTimeMillis());
                        return;
                    }
                } else {
//                    System.out.println("New username is the same as current: " + newUsername);
                }
            } else {
//                System.out.println("Invalid username (null or empty): " + newUsername);
                request.setAttribute("error", "Username cannot be empty.");
                //request.getSession().setAttribute("error", "Username cannot be empty.");
                request.getRequestDispatcher("/setting/setting.jsp").forward(request, response);
                //response.sendRedirect("setting?t=" + System.currentTimeMillis());
                return;
            }
        } else {
//            System.out.println("Unknown action: " + action);
        }
        if (alertMessage != "") {
            request.getSession().setAttribute("successMessage", alertMessage);
        }
        response.sendRedirect("setting?t=" + System.currentTimeMillis());
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
