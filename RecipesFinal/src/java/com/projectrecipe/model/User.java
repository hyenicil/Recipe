/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projectrecipe.model;

import javax.faces.bean.ManagedBean;



/**
 *
 * @author HÜSEYİN
 */
@ManagedBean
public class User {
    private int userId;
    private String userName;
    private String surName;
    private String eMail;
    private String password;
    private String nickName;
    private static String permission;

    public User(String useremail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * @return the userId
     */
    
  
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", surName=" + surName + ", eMail=" + eMail + ", password=" + password + ", nickName=" + nickName +", permission=" + permission+ '}';
    }
    public User(){}

    public User(int userId, String userName, String surName, String eMail, String password, String nickName) {
        this.userId = userId;
        this.userName = userName;
        this.surName = surName;
        this.eMail = eMail;
        this.password = password;
        this.nickName = nickName;
        
    }
    
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the surName
     */
    public String getSurName() {
        return surName;
    }

    /**
     * @param surName the surName to set
     */
    public void setSurName(String surName) {
        this.surName = surName;
    }

    /**
     * @return the eMail
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * @param eMail the eMail to set
     */
    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }



    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPermission(){
        return permission="K";
    }
    public void setPermission(String perMission){
        this.permission=perMission;
    }
  
    
}
