/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 11
 */
public class Creator {
    private Account account;
    private int numberOfQuizSet;

    public Creator() {
    }

    public Creator(Account account, int numberOfQuizSet) {
        this.account = account;
        this.numberOfQuizSet = numberOfQuizSet;
    }

    public Account getAccount() {
        return account;
    }

    public int getNumberOfQuizSet() {
        return numberOfQuizSet;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setNumberOfQuizSet(int numberOfQuizSet) {
        this.numberOfQuizSet = numberOfQuizSet;
    }
    
}
