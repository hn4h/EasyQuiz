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
public class Account {
    private String userName;
    private String hashedPassword;
    private boolean isAdmin;
    private boolean isDeleted;
    private String email;
    private String profileImage;
    private Date createdDate;
    private Date expiredDate;
    public Account() {
    }

    public Account(String userName, String hashedPassword, boolean isAdmin, boolean isDeleted, String email, String profileImage, Date createdDate, Date expiredDate) {
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.isAdmin = isAdmin;
        this.isDeleted = isDeleted;
        this.email = email;
        this.profileImage = profileImage;
        this.createdDate = createdDate;
        this.expiredDate = expiredDate;
    }

    public Account(String userName, String hashedPassword, String email) {
        this.userName = userName;
        this.hashedPassword = hashedPassword;
        this.email = email;
    }
    

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

   
    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
