package com.lyc.schedulebox.logic.listener;

import com.alamkanak.weekview.WeekViewEvent;
import com.cxyw.suyun.common.net.model.ErrorObj;

import java.util.List;

/**
 * Created by lianyuchen on 16/3/8.
 */
public interface GetScheduleListener {

    void getScheduleListSuccess(List<WeekViewEvent> events);

    void getScheduleFailed(ErrorObj obj);

    void getError(ErrorObj obj);
}
