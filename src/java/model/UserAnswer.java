/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author admin
 */
public class UserAnswer {
    private Quiz quiz;
    private Answer userAnswer;

    public UserAnswer() {
    }

    public UserAnswer(Quiz quiz, Answer userAnswer) {
        this.quiz = quiz;
        this.userAnswer = userAnswer;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Answer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Answer userAnswer) {
        this.userAnswer = userAnswer;
    }
}
