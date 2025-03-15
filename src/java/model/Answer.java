/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DUCA
 */
public class Answer {

    private int answerID;
    private int quizID;
    private String content;
    private boolean isCorrect;

    public Answer() {
    }

    public Answer(int answerID, int quizID, String content, boolean isCorrect) {
        this.answerID = answerID;
        this.quizID = quizID;
        this.content = content;
        this.isCorrect = isCorrect;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public int getQuizID() {
        return quizID;
    }

    public String getContent() {
        return content;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    } 
}
