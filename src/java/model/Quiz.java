/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author DUCA
 */
import java.util.List;

public class Quiz {

    private int quizID;
    private int quizSetID;
    private String content;
    private List<Answer> answers;

    public Quiz(int quizID, int quizSetID, String content, List<Answer> answers) {
        this.quizID = quizID;
        this.quizSetID = quizSetID;
        this.content = content;
        this.answers = answers;
    }

    public int getQuizID() {
        return quizID;
    }

    public int getQuizSetID() {
        return quizSetID;
    }

    public String getContent() {
        return content;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
