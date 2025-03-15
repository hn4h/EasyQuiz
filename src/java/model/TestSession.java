/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class TestSession {
    private int quizSetID;
    private int totalQuestions;
    private int timeLimit;
    private List<Quiz> questions;
    private List<UserAnswer> userAnswers;

    public TestSession() {
    }

    public TestSession(int quizSetID, int totalQuestions, int timeLimit, List<Quiz> questions, List<UserAnswer> userAnswers) {
        this.quizSetID = quizSetID;
        this.totalQuestions = totalQuestions;
        this.timeLimit = timeLimit;
        this.questions = questions;
        this.userAnswers = userAnswers;
    }

    public int getQuizSetID() {
        return quizSetID;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public List<Quiz> getQuestions() {
        return questions;
    }

    public List<UserAnswer> getUserAnswers() {
        return userAnswers;
    }

    public void setQuizSetID(int quizSetID) {
        this.quizSetID = quizSetID;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setQuestions(List<Quiz> questions) {
        this.questions = questions;
    }

    public void setUserAnswers(List<UserAnswer> userAnswers) {
        this.userAnswers = userAnswers;
    }
}
