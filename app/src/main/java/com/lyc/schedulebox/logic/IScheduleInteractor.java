package com.lyc.schedulebox.logic;

import com.lyc.schedulebox.logic.listener.GetScheduleListener;
import com.lyc.schedulebox.logic.listener.ModifyStatusListener;

/**
 * Created by lianyuchen on 16/3/8.
 */
public interface IScheduleInteractor {
    void getSchedule(String userId, String scheduleStartTime, String scheduleEndTime, GetScheduleListener listener);

    void updateScheduleStatus(String userId, String scheduleId, String status, ModifyStatusListener listener);
}
