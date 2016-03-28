package com.lyc.schedulebox.logic;

import com.lyc.schedulebox.logic.listener.GetScheduleListener;

/**
 * Created by lianyuchen on 16/3/8.
 */
public interface IScheduleInteractor {
    void getSchedule(String userId, String scheduleStartTime, String scheduleEndTime, GetScheduleListener listener);
}
