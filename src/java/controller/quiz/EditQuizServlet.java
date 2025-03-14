/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.quiz;

import dal.AnswerDAO;
import dal.QuizDAO;
import dal.QuizSetDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Account;
import model.Answer;
import model.Quiz;
import model.QuizSet;

/**
 *
 * @author 11
 */
@WebServlet(name = "EditQuizServlet", urlPatterns = {"/editquiz"})
public class EditQuizServlet extends HttpServlet {

    private QuizSet quizset;
    private List<Quiz> quizzes;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            QuizSetDAO qsDao = new QuizSetDAO();
            if (qsDao.getQuizSetById(id) == null) {
                response.sendRedirect("home");
                return;
            }
            quizset = qsDao.getQuizSetById(id);
            Account a = (Account) request.getSession().getAttribute("account");
            if (a == null) {
                response.sendRedirect("home");
                return;
            }
            if (quizset.getAuthor().getUserName().equals(a.getUserName()) == false) {
                response.sendRedirect("home");
                return;
            }
            request.setAttribute("quizset", quizset);
            QuizDAO qDao = new QuizDAO();
            quizzes = qDao.getQuizzesByQuizSetID(id);
            request.setAttribute("quizzes", quizzes);
            request.getRequestDispatcher("quiz/editquiz.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendRedirect("home");
        }
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
        int quizSetId = quizset.getQuizSetId();
        String title = request.getParameter("quizTitle");
        String description = request.getParameter("quizDescription");
        String numberOfQuestions = request.getParameter("questionCount");

        QuizSetDAO qsDao = new QuizSetDAO();
        QuizDAO qDao = new QuizDAO();
        AnswerDAO aDao = new AnswerDAO();
        try {
            // 1. Cập nhật Quiz_Set nếu có thay đổi
            if (!title.equals(quizset.getQuizSetName()) || !description.equals(quizset.getQuizSetDescription())) {
                qsDao.updateQuizSet(quizSetId, title, description, quizset.getNumberOfQuiz()); // Số lượng quiz sẽ được cập nhật sau
            }

            // 2. Lấy danh sách Quiz hiện có trong database để so sánh
            List<Integer> existingQuizIds = quizzes.stream()
                    .map(Quiz::getQuizID)
                    .collect(Collectors.toList());
            List<Integer> submittedQuizIds = new ArrayList<>();

            // 3. Xử lý từng Quiz từ form
            int quizIndex = 1;
            while (request.getParameter("quizId" + quizIndex) != null) {
                String quizIdStr = request.getParameter("quizId" + quizIndex);
                String quizContent = request.getParameter("question" + quizIndex);

                if ("new".equals(quizIdStr)) {
                    String question = request.getParameter("question" + quizIndex);
                    int quizId = qDao.addQuiz(quizSetId, question);
                    String correctAnswer = request.getParameter("correct" + quizIndex);
                    for (int j = 1; j <= 4; j++) {
                        String answer = request.getParameter("answer" + quizIndex + "." + j);
                        boolean isCorrect = false;
                        if (j == Integer.parseInt(correctAnswer)) {
                            isCorrect = true;
                        }
                        qDao.addAnswer(quizId, answer, isCorrect);
                    }
                } else {
                    // Cập nhật Quiz hiện có
                    int quizId = Integer.parseInt(quizIdStr);
                    submittedQuizIds.add(quizId);
                    String question = request.getParameter("question" + quizIndex);
                    quizId = qDao.addQuiz(quizSetId, question);
                    System.out.println(question);
                    String correctAnswer = request.getParameter("correct" + quizIndex);
                    System.out.println(correctAnswer);
                    for (int j = 1; j <= 4; j++) {
                        String answer = request.getParameter("answer" + quizIndex + "." + j);
                        boolean isCorrect = false;
                        if (j == Integer.parseInt(correctAnswer)) {
                            isCorrect = true;
                        }
                        qDao.addAnswer(quizId, answer, isCorrect);
                    }
                    Quiz quiz = qDao.getQuizById(quizId);
                    if (!quiz.getContent().equals(quizContent)) {
                        quiz.setContent(quizContent);
                        qDao.updateQuiz(quiz);
                    }

                    // Cập nhật Answers
                    int answerIndex = 1;
                    while (request.getParameter("answerId" + quizIndex + "." + answerIndex) != null) {
                        int answerId = Integer.parseInt(request.getParameter("answerId" + quizIndex + "." + answerIndex));
                        String answerContent = request.getParameter("answer" + quizIndex + "." + answerIndex);
                        boolean isCorrect = String.valueOf(answerIndex).equals(request.getParameter("correct_" + quizIndex));

                        Answer answer = aDao.getAnswerById(answerId);
                        if (!answer.getContent().equals(answerContent) || answer.isCorrect() != isCorrect) {
                            answer.setContent(answerContent);
                            answer.setIsCorrect(isCorrect);
                            aDao.updateAnswer(answer);
                        }
                        answerIndex++;
                        if (answerId > 4) {
                            break;
                        }
                    }
                }
                quizIndex++;
            }

            // 4. Xóa các Quiz không còn trong form
            for (int existingQuizId : existingQuizIds) {
                if (!submittedQuizIds.contains(existingQuizId)) {
                    aDao.deleteAnswersByQuizId(existingQuizId); // Xóa Answers trước
                    qDao.deleteQuiz(existingQuizId); // Xóa Quiz
                }
            }

            // 5. Cập nhật Number_Of_Quiz trong Quiz_Set
            int updatedNumberOfQuiz = qDao.getQuizzesByQuizSetID(quizSetId).size();
            if (updatedNumberOfQuiz != quizset.getNumberOfQuiz()) {
                qsDao.updateQuizSet(quizSetId, title, description, updatedNumberOfQuiz);
            }

            // Chuyển hướng về trang chi tiết Quiz_Set
            response.sendRedirect("quizz?id=" + quizSetId);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("home");
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
