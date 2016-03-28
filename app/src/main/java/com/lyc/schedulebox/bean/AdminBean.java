package com.lyc.schedulebox.bean;

/**
 * Created by lianyuchen on 16/3/11.
 */
public class AdminBean {
    private int adminId;
    private String adminName;
    private String adminPwd;
    private String adminPower;
    public int getAdminId() {
        return adminId;
    }
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    public String getAdminName() {
        return adminName;
    }
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public String getAdminPwd() {
        return adminPwd;
    }
    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }
    public String getAdminPower() {
        return adminPower;
    }
    public void setAdminPower(String adminPower) {
        this.adminPower = adminPower;
    }
    @Override
    public String toString() {
        return "AdminBean [adminId=" + adminId + ", adminName=" + adminName
                + ", adminPwd=" + adminPwd + ", adminPower=" + adminPower + "]";
    }
}
