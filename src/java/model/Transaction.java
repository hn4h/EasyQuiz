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
public class Transaction {
    private int transactionId;
    private int transactionTypeId;
    private Date createdDate;
    private double transactionAmount;

    public Transaction(int transactionId, int transactionTypeId, Date createdDate, double transactionAmount) {
        this.transactionId = transactionId;
        this.transactionTypeId = transactionTypeId;
        this.createdDate = createdDate;
        this.transactionAmount = transactionAmount;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
    
}
