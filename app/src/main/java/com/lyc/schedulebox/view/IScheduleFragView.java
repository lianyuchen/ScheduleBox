package com.lyc.schedulebox.view;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.List;

/**
 * Created by lianyuchen on 16/3/8.
 */
public interface IScheduleFragView {

    void showScheduleInfoList(List<WeekViewEvent> events);
}
