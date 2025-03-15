/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class UserAnswer {
    private int quizID;
    private int selectedAnswerID;

    public UserAnswer() {
    }

    public UserAnswer(int quizID, int selectedAnswerID) {
        this.quizID = quizID;
        this.selectedAnswerID = selectedAnswerID;
    }

    public int getQuizID() {
        return quizID;
    }

    public int getSelectedAnswerID() {
        return selectedAnswerID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public void setSelectedAnswerID(int selectedAnswerID) {
        this.selectedAnswerID = selectedAnswerID;
    }

    
}
