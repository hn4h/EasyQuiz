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
    private double amount;
    private String currency;
    private String status;
    private String orderCode;
    private String description;

    public Transaction() {
    }

    public Transaction(int transactionId, int transactionTypeId, Date createdDate, double amount, String currency, String status, String orderCode, String description) {
        this.transactionId = transactionId;
        this.transactionTypeId = transactionTypeId;
        this.createdDate = createdDate;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.orderCode = orderCode;
        this.description = description;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
    
}
