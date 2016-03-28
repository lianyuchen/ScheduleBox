package com.lyc.schedulebox.bean;

/**
 * Created by lianyuchen on 16/3/11.
 */
public class MindBean {
    private int mindId;
    private String mindContent;
    private String mindPubTime;
    private UserBean userBean;
    public UserBean getUserBean() {
        return userBean;
    }
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    public int getMindId() {
        return mindId;
    }
    public void setMindId(int mindId) {
        this.mindId = mindId;
    }
    public String getMindContent() {
        return mindContent;
    }
    public void setMindContent(String mindContent) {
        this.mindContent = mindContent;
    }
    public String getMindPubTime() {
        return mindPubTime;
    }
    public void setMindPubTime(String mindPubTime) {
        this.mindPubTime = mindPubTime;
    }
    @Override
    public String toString() {
        return "MindBean [mindId=" + mindId + ", mindContent=" + mindContent
                + ", mindPubTime=" + mindPubTime + ", userBean=" + userBean
                + "]";
    }
}
