/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 11
 */
public class SummaryData {
    private double revenue;
    private long users;
    private long quizzes;
    private long transactions;

    public SummaryData(double revenue, long users, long quizzes, long transactions) {
        this.revenue = revenue;
        this.users = users;
        this.quizzes = quizzes;
        this.transactions = transactions;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public long getUsers() {
        return users;
    }

    public void setUsers(long users) {
        this.users = users;
    }

    public long getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(long quizzes) {
        this.quizzes = quizzes;
    }

    public long getTransactions() {
        return transactions;
    }

    public void setTransactions(long transactions) {
        this.transactions = transactions;
    }
    
}
