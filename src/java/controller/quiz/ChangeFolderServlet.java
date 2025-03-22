/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.quiz;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.HistoryDAO;

/**
 *
 * @author Lenovo
 */
@WebServlet(name="ChangeFolderServlet", urlPatterns={"/changefolder"})
public class ChangeFolderServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet ChangeFolderServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeFolderServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("error");
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String quizSetIdStr = request.getParameter("quizSetId");
        String oldFolderIdStr = request.getParameter("oldFolderId");
        String newFolderIdStr = request.getParameter("newFolderId");
        if (oldFolderIdStr == null || quizSetIdStr == null || newFolderIdStr == null) {
            response.sendRedirect("home");
            return;
        }
        int oldFolderId = 0;
        int newFolderId = 0;
        try {
            oldFolderId = Integer.parseInt(oldFolderIdStr);
            newFolderId = Integer.parseInt(newFolderIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("home");
            System.out.println(e);
        }
        int quizSetId = 0;
        try {
            quizSetId = Integer.parseInt(quizSetIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("home");
            System.out.println(e);
        }
        HistoryDAO dao = new HistoryDAO();
        boolean success1 = dao.deleteQuizSetFromFolder(oldFolderId, quizSetId);
        boolean success2 = dao.addQuizSetToFolder(newFolderId, quizSetId);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write((success1 & success2) ? "success" : "fail");
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
