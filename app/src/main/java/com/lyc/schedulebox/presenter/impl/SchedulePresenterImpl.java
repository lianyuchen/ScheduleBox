package com.lyc.schedulebox.presenter.impl;

import com.alamkanak.weekview.WeekViewEvent;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.IScheduleInteractor;
import com.lyc.schedulebox.logic.impl.ScheduleInteractorImpl;
import com.lyc.schedulebox.logic.listener.GetScheduleListener;
import com.lyc.schedulebox.presenter.ISchedulePresenter;
import com.lyc.schedulebox.utils.logutils.LogUtils;
import com.lyc.schedulebox.view.IScheduleFragView;

import java.util.List;

/**
 * Created by lianyuchen on 16/3/8.
 */
public class SchedulePresenterImpl implements ISchedulePresenter,GetScheduleListener{
    private IScheduleInteractor mScheduleInteractor;
    private IScheduleFragView mScheduleFragView;

    public SchedulePresenterImpl(IScheduleFragView mScheduleFragView) {
        this.mScheduleFragView = mScheduleFragView;
        this.mScheduleInteractor = new ScheduleInteractorImpl();
    }

    @Override
    public void showSchedule() {
        mScheduleInteractor.getSchedule("5","2016-03-16 00:00:00","2016-03-24 23:59:59", this);
    }

    @Override
    public void getScheduleListSuccess(List<WeekViewEvent> events) {
        LogUtils.i(events.get(0).getStartTime() + ":" + events.get(0).getEndTime());
        mScheduleFragView.showScheduleInfoList(events);
    }

    @Override
    public void getScheduleFailed(ErrorObj obj) {

    }

    @Override
    public void getError(ErrorObj obj) {

    }
}
