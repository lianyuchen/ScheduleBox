package com.lyc.schedulebox.logic;

import com.lyc.schedulebox.logic.listener.GetAnalysisScheduleListener;

/**
 * Created by lianyuchen on 16/4/13.
 */
public interface IAnalysisInteractor {

    void getAnalysisScheduleById(String userId, GetAnalysisScheduleListener listener);
}
