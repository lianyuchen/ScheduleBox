package com.lyc.schedulebox.presenter;

/**
 * Created by lianyuchen on 16/3/8.
 */
public interface ISchedulePresenter {

    void showSchedule(int userId,String start,String end);

    void updateScheduleStatus(int scheduleId);
}
