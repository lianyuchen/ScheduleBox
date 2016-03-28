package com.lyc.schedulebox.bean;

/**
 * Created by lianyuchen on 16/3/11.
 */
public class UserBean {
    private int userId;
    private String userName;
    private String userPwd;
    private String userPhone;
    private String userMail;
    private String userUUID;
    private String userPhote;
    private String userRegisteTime;
    public String getUserRegisteTime() {
        return userRegisteTime;
    }
    public void setUserRegisteTime(String userRegisteTime) {
        this.userRegisteTime = userRegisteTime;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPwd() {
        return userPwd;
    }
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
    public String getUserPhone() {
        return userPhone;
    }
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    public String getUserMail() {
        return userMail;
    }
    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
    public String getUserUUID() {
        return userUUID;
    }
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }
    public String getUserPhote() {
        return userPhote;
    }
    public void setUserPhote(String userPhote) {
        this.userPhote = userPhote;
    }

    @Override
    public String toString() {
        return "UserBean [userId=" + userId + ", userName=" + userName
                + ", userPwd=" + userPwd + ", userPhone=" + userPhone
                + ", userMail=" + userMail + ", userUUID=" + userUUID
                + ", userPhote=" + userPhote + ", userRegisteTime="
                + userRegisteTime + "]";
    }
}
