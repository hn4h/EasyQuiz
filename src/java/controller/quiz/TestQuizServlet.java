/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.quiz;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import dal.QuizDAO;
import dal.QuizSetDAO;
import model.Quiz;
import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import model.Account;
import model.UserAnswer;

/**
 *
 * @author DUCA
 */
@WebServlet(name = "TestQuizServlet", urlPatterns = {"/testquiz"})
public class TestQuizServlet extends HttpServlet {

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
            out.println("<title>Servlet TestQuizServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TestQuizServlet at " + request.getContextPath() + "</h1>");
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
        String quizSetIDParam = request.getParameter("quizSetID");
        String numberQuizParam = request.getParameter("numberQuiz"); // lay so luong quiz
        QuizSetDAO qsd = new QuizSetDAO();
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");

        if (a == null) {
            response.sendRedirect("login");
            return;
        }

        if (quizSetIDParam != null) {
            try {
                int quizSetID = Integer.parseInt(quizSetIDParam);
                QuizDAO quizDAO = new QuizDAO();
                List<Quiz> quizzes = quizDAO.getQuizzesByQuizSetID(quizSetID);

                if (quizzes == null || quizzes.isEmpty()) {
                    request.setAttribute("error", "No quizzes found for quizSetID: " + quizSetID);
                } else {
                    for (Quiz quiz : quizzes) {
                        if (quiz.getAnswers() == null) {
                            quiz.setAnswers(new ArrayList<>());
                        }
                    }

                    // xu ly so quiz ngau nhien voi so number quiz duoc cung cap
                    if (numberQuizParam != null) {
                        try {
                            int numberQuiz = Integer.parseInt(numberQuizParam);
                            if (numberQuiz > 0 && numberQuiz <= quizzes.size()) {
                                // tron ngau nhien danh sach quiz va lay so luong can thiet
                                Collections.shuffle(quizzes);
                                quizzes = quizzes.subList(0, numberQuiz);
                            } else if (numberQuiz > quizzes.size()) {
                                // Nếu số lượng yêu cầu lớn hơn số quiz hiện có, lấy tất cả
                                // Không cần làm gì thêm vì quizzes đã chứa toàn bộ
                            } else {
                                request.setAttribute("error", "Invalid number of quizzes requested");
                            }
                        } catch (NumberFormatException e) {
                            request.setAttribute("error", "Invalid numberQuiz parameter");
                        }
                    }

                    request.setAttribute("quizDetail", qsd.getQuizDetailById(quizSetID));
                    request.setAttribute("quizzes", quizzes);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid Quiz Set ID");
            }
        }
        request.getRequestDispatcher("quiz/testquiz.jsp").forward(request, response);
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
        HttpSession session = request.getSession();

        // Đọc JSON từ request
        BufferedReader reader = request.getReader();
        StringBuilder jsonData = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonData.append(line);
        }

        // Kiểm tra nếu request rỗng
        if (jsonData.length() == 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Dữ liệu JSON trống!");
            return;
        }

        try {
            // Chuyển JSON thành danh sách UserAnswer
            Gson gson = new Gson();
            Type listType = new TypeToken<List<UserAnswer>>() {
            }.getType();
            List<UserAnswer> userAnswers = gson.fromJson(jsonData.toString(), listType);

            // Kiểm tra nếu danh sách userAnswers rỗng hoặc null
            if (userAnswers == null || userAnswers.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Danh sách UserAnswer trống hoặc không hợp lệ!");
                return;
            }
            QuizDAO qd = new QuizDAO();
            // Debug: In ra console để kiểm tra
            System.out.println("Received userAnswers: " + userAnswers);
            for (UserAnswer user : userAnswers) {
                if (user.getQuiz() != null) {
                    user.setQuiz(qd.getQuizById(user.getQuiz().getQuizID()));
                }
                if (user.getUserAnswer() != null) {
                    user.setUserAnswer(qd.getAnswerById(user.getUserAnswer().getAnswerID()));
                }
            }
            // Lưu vào session để dùng ở trang testresult.jsp
            session.setAttribute("userAnswers", userAnswers);

            // Trả về trạng thái thành công
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Dữ liệu đã được lưu thành công!");

        } catch (JsonSyntaxException e) {
            // Bắt lỗi nếu JSON không hợp lệ
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Lỗi định dạng JSON: " + e.getMessage());
            e.printStackTrace();
        }
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
