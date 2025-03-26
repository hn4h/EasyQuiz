/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 11
 */
public class UserStatis {
    private String imageProfile;
    private String userName;
    private String email;
    private int numFolder;
    private int numBlog;
    private int numQuizSet;
    private int numComment;
    private int numFeedBack;
    private boolean isDeleted;

    public UserStatis() {
    }

    public UserStatis(String imageProfile, String userName, String email, int numFolder, int numBlog, int numQuizSet, int numComment, int numFeedBack, boolean isDeleted) {
        this.imageProfile = imageProfile;
        this.userName = userName;
        this.email = email;
        this.numFolder = numFolder;
        this.numBlog = numBlog;
        this.numQuizSet = numQuizSet;
        this.numComment = numComment;
        this.numFeedBack = numFeedBack;
        this.isDeleted = isDeleted;
    }

    public String getImageProfile() {
        return imageProfile;
    }

    public void setImageProfile(String imageProfile) {
        this.imageProfile = imageProfile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumFolder() {
        return numFolder;
    }

    public void setNumFolder(int numFolder) {
        this.numFolder = numFolder;
    }

    public int getNumBlog() {
        return numBlog;
    }

    public void setNumBlog(int numBlog) {
        this.numBlog = numBlog;
    }

    public int getNumQuizSet() {
        return numQuizSet;
    }

    public void setNumQuizSet(int numQuizSet) {
        this.numQuizSet = numQuizSet;
    }

    public int getNumComment() {
        return numComment;
    }

    public void setNumComment(int numComment) {
        this.numComment = numComment;
    }

    public int getNumFeedBack() {
        return numFeedBack;
    }

    public void setNumFeedBack(int numFeedBack) {
        this.numFeedBack = numFeedBack;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
