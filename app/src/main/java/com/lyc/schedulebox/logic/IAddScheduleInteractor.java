package com.lyc.schedulebox.logic;

import com.lyc.schedulebox.logic.listener.AddScheduleListener;

/**
 * Created by lianyuchen on 16/3/31.
 */
public interface IAddScheduleInteractor {

    void addSchedule(String userId, String start, String end, String scheduleType, String content,
                     String color, AddScheduleListener listener);
}
