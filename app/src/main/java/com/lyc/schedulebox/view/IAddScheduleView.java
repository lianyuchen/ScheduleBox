package com.lyc.schedulebox.view;

/**
 * Created by lianyuchen on 16/3/31.
 */
public interface IAddScheduleView {

    String getStartTime();

    String getEndTime();

    String getScheduleType();

    void completeAddSchedule();

    int getUserId();

    String getScheduleContent();

    String getScheduleColor();

}
