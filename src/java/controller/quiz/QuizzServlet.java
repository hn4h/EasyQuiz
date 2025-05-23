/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.quiz;

import dal.HistoryDAO;
import dal.QuizSetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.Account;
import model.Folder;
import model.QuizSet;

/**
 *
 * @author DUCA
 */
@WebServlet(name = "QuizzServlet", urlPatterns = {"/quizz"})
public class QuizzServlet extends HttpServlet {

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
            out.println("<title>Servlet QuizzServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizzServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String idRaw = request.getParameter("id");
        int id = 0;
        HistoryDAO hd = new HistoryDAO();
        QuizSetDAO qsd = new QuizSetDAO();
        Account acc = (Account) session.getAttribute("account");
        if (idRaw == null) {
            response.sendRedirect("error");
            return;
        }

        try {
            id = Integer.parseInt(idRaw);
        } catch (NumberFormatException e) {
            response.sendRedirect("error");
            return;
        }

        if (qsd.getQuizSetById(id) == null) {
            response.sendRedirect("error");
            return;
        }
        List<Folder> fList = new ArrayList<>();
        Map<Integer, List<Integer>> folderQuizMap = new HashMap<>();
        if (acc != null) {
            qsd.addQuizHistory(id, acc.getUserName());
            fList = hd.getAllFolderByUserName(acc.getUserName());
            for (Folder folder : fList) {
                List<QuizSet> qList = hd.getAllQuizSetByFolderId(folder.getFolderId());
                List<Integer> quizIdsInFolder = qList
                        .stream()
                        .map(QuizSet::getQuizSetId)
                        .collect(Collectors.toList());
                folderQuizMap.put(folder.getFolderId(), quizIdsInFolder);
            }
        }
        request.setAttribute("listFolder", fList);
        request.setAttribute("folderQuizMap", folderQuizMap);
        request.setAttribute("currentQuizSetId", id);
        request.setAttribute("quizDetail", qsd.getQuizDetailById(id));
        request.getRequestDispatcher("quiz/quiz.jsp").forward(request, response);
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
