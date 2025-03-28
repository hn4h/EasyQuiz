/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author 11
 */
public class TokenForgetPassword {

    private int id;
    private String userName;
    private boolean isUsed;
    private String token;
    private LocalDateTime expiredTime;

    public TokenForgetPassword(int id, String userName, boolean isUsed, String token, LocalDateTime expiredTime) {
        this.id = id;
        this.userName = userName;
        this.isUsed = isUsed;
        this.token = token;
        this.expiredTime = expiredTime;
    }
    public TokenForgetPassword(String userName, boolean isUsed, String token, LocalDateTime expiredTime) {
        this.userName = userName;
        this.isUsed = isUsed;
        this.token = token;
        this.expiredTime = expiredTime;
    }

    public TokenForgetPassword() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public boolean isIsUsed() {
        return isUsed;
    }

    public void setIsUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(LocalDateTime expiredTime) {
        this.expiredTime = expiredTime;
    }

}
