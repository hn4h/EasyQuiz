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
    private String userName;
    private Date createdDate;
    private String quizSetDescription;

    public QuizSet() {
    }

    public QuizSet(int quizSetId, String quizSetName, String userName, Date createdDate, String quizSetDescription) {
        this.quizSetId = quizSetId;
        this.quizSetName = quizSetName;
        this.userName = userName;
        this.createdDate = createdDate;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
    
}
