/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 11
 */
public class TransactionType {
 private int transactionTypeId;
 private String transactionTypeName;
 private String transactionTypeDescription;
 private int transactionValue;
 private boolean isDeleted;

    public TransactionType(int transactionTypeId, String transactionTypeName, String transactionTypeDescription, int transactionValue, boolean isDeleted) {
        this.transactionTypeId = transactionTypeId;
        this.transactionTypeName = transactionTypeName;
        this.transactionTypeDescription = transactionTypeDescription;
        this.transactionValue = transactionValue;
        this.isDeleted = isDeleted;
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public String getTransactionTypeDescription() {
        return transactionTypeDescription;
    }

    public void setTransactionTypeDescription(String transactionTypeDescription) {
        this.transactionTypeDescription = transactionTypeDescription;
    }

    public int getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(int transactionValue) {
        this.transactionValue = transactionValue;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
 
}
