/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class QuizHistory {
    private int quizSetId;
    private String quizSetName;
    private String author;
    private int numberOfQuiz;
    private Date createdDate;
    private String description;
    private Date quizDate;

    public QuizHistory(int quizSetId, String quizSetName, String author, int numberOfQuiz, Date createdDate, String description, Date quizDate) {
        this.quizSetId = quizSetId;
        this.quizSetName = quizSetName;
        this.author = author;
        this.numberOfQuiz = numberOfQuiz;
        this.createdDate = createdDate;
        this.description = description;
        this.quizDate = quizDate;
    }

    public QuizHistory() {
    }

    public int getQuizSetId() {
        return quizSetId;
    }

    public String getQuizSetName() {
        return quizSetName;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfQuiz() {
        return numberOfQuiz;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getDescription() {
        return description;
    }

    public Date getQuizDate() {
        return quizDate;
    }

    public void setQuizSetId(int quizSetId) {
        this.quizSetId = quizSetId;
    }

    public void setQuizSetName(String quizSetName) {
        this.quizSetName = quizSetName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setNumberOfQuiz(int numberOfQuiz) {
        this.numberOfQuiz = numberOfQuiz;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuizDate(Date quizDate) {
        this.quizDate = quizDate;
    }
    
    public String getMonthYear() {
        SimpleDateFormat monthYearFormat = new SimpleDateFormat("MM-yyyy");
        return monthYearFormat.format(quizDate);
    }
}
