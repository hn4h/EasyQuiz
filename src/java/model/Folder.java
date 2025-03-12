/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author 11
 */
public class Folder {
    private int folderId;
    private String folderName;
    private String userName;
    private Date folderDate;
    private String folderDescription;
    private int quizSetCount;
    
    public Folder() {
    }

    public Folder(int folderId, String folderName, String userName, Date folderDate, String folderDescription, int quizSetCount) {
        this.folderId = folderId;
        this.folderName = folderName;
        this.userName = userName;
        this.folderDate = folderDate;
        this.folderDescription = folderDescription;
        this.quizSetCount = quizSetCount;
    }

    public int getFolderId() {
        return folderId;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getUserName() {
        return userName;
    }

    public Date getFolderDate() {
        return folderDate;
    }

    public String getFolderDescription() {
        return folderDescription;
    }

    public int getQuizSetCount() {
        return quizSetCount;
    }

    public void setFolderId(int folderId) {
        this.folderId = folderId;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFolderDate(Date folderDate) {
        this.folderDate = folderDate;
    }

    public void setFolderDescription(String folderDescription) {
        this.folderDescription = folderDescription;
    }

    public void setQuizSetCount(int quizSetCount) {
        this.quizSetCount = quizSetCount;
    }
    
    
}
