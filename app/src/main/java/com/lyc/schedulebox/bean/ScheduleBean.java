package com.lyc.schedulebox.bean;

/**
 * Created by lianyuchen on 16/3/11.
 */
public class ScheduleBean {
    private int scheduleId;
    private String scheduleType;
    private String scheduleContent;
    private String scheduleStartTime;
    private String scheduleEndTime;
    private boolean scheduleIsFinished;
    private UserBean userBean;
    public UserBean getUserBean() {
        return userBean;
    }
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    public int getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }
    public String getScheduleType() {
        return scheduleType;
    }
    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }
    public String getScheduleContent() {
        return scheduleContent;
    }
    public void setScheduleContent(String scheduleContent) {
        this.scheduleContent = scheduleContent;
    }

    public String getScheduleStartTime() {
        return scheduleStartTime;
    }
    public void setScheduleStartTime(String scheduleStartTime) {
        this.scheduleStartTime = scheduleStartTime;
    }

    public String getScheduleEndTime() {
        return scheduleEndTime;
    }
    public void setScheduleEndTime(String scheduleEndTime) {
        this.scheduleEndTime = scheduleEndTime;
    }
    public boolean isScheduleIsFinished() {
        return scheduleIsFinished;
    }
    public void setScheduleIsFinished(boolean scheduleIsFinished) {
        this.scheduleIsFinished = scheduleIsFinished;
    }
    @Override
    public String toString() {
        return "ScheduleBean [scheduleId=" + scheduleId + ", scheduleType="
                + scheduleType + ", scheduleContent=" + scheduleContent
                + ", scheduleStartTime=" + scheduleStartTime
                + ", scheduleEndTime=" + scheduleEndTime
                + ", scheduleIsFinished=" + scheduleIsFinished + ", userBean="
                + userBean + "]";
    }
}
