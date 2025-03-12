/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author 11
 */
public class QuizSet {
    private int quizSetId;
    private String quizSetName;
    private Account author;
    private Date createdDate;
    private int numberOfQuiz;
    private String quizSetDescription;
    private String formattedDate;

    public QuizSet() {
    }

    public QuizSet(int quizSetId, String quizSetName, Account author, Date createdDate, int numberOfQuiz, String quizSetDescription) {
        this.quizSetId = quizSetId;
        this.quizSetName = quizSetName;
        this.author = author;
        this.createdDate = createdDate;
        this.numberOfQuiz = numberOfQuiz;
        this.quizSetDescription = quizSetDescription;
    }
    
    public int getQuizSetId() {
        return quizSetId;
    }

    public void setQuizSetId(int quizSetId) {
        this.quizSetId = quizSetId;
    }

    public String getQuizSetName() {
        return quizSetName;
    }

    public void setQuizSetName(String quizSetName) {
        this.quizSetName = quizSetName;
    }

    public Account getAuthor() {
        return author;
    }

    public void setAuthor(Account author) {
        this.author = author;
    }

    public int getNumberOfQuiz() {
        return numberOfQuiz;
    }

    public void setNumberOfQuiz(int numberOfQuiz) {
        this.numberOfQuiz = numberOfQuiz;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getQuizSetDescription() {
        return quizSetDescription;
    }

    public void setQuizSetDescription(String quizSetDescription) {
        this.quizSetDescription = quizSetDescription;
    }
    
    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
    
}
