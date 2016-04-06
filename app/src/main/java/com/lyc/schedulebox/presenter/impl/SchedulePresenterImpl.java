package com.lyc.schedulebox.presenter.impl;

import com.alamkanak.weekview.WeekViewEvent;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.IAddScheduleInteractor;
import com.lyc.schedulebox.logic.IScheduleInteractor;
import com.lyc.schedulebox.logic.impl.ScheduleInteractorImpl;
import com.lyc.schedulebox.logic.listener.AddScheduleListener;
import com.lyc.schedulebox.logic.listener.GetScheduleListener;
import com.lyc.schedulebox.presenter.IAddSchedulePresenter;
import com.lyc.schedulebox.presenter.ISchedulePresenter;
import com.lyc.schedulebox.utils.logutils.LogUtils;
import com.lyc.schedulebox.view.IAddScheduleView;
import com.lyc.schedulebox.view.IScheduleFragView;

import java.util.List;

/**
 * Created by lianyuchen on 16/3/8.
 */
public class SchedulePresenterImpl implements ISchedulePresenter, GetScheduleListener, IAddSchedulePresenter, AddScheduleListener {
    private IScheduleInteractor mScheduleInteractor;
    private IScheduleFragView mScheduleFragView;
    private IAddScheduleView mAddScheduleView;
    private IAddScheduleInteractor mAddScheduleInteractor;

    public SchedulePresenterImpl(IScheduleFragView mScheduleFragView) {
        this.mScheduleFragView = mScheduleFragView;
        this.mScheduleInteractor = new ScheduleInteractorImpl();
    }

    public SchedulePresenterImpl(IAddScheduleView mAddScheduleView) {
        this.mAddScheduleView = mAddScheduleView;
        this.mAddScheduleInteractor = new ScheduleInteractorImpl();
    }

    @Override
    public void showSchedule(int userId, String start, String end) {
        mScheduleInteractor.getSchedule(userId + "", start, end, this);
    }

    @Override
    public void getScheduleListSuccess(List<WeekViewEvent> events) {
        LogUtils.i(events.get(0).getStartTime() + ":" + events.get(0).getEndTime());
        mScheduleFragView.showScheduleInfoList(events);
    }

    @Override
    public void getScheduleFailed(ErrorObj obj) {
        mScheduleFragView.showLogicFailed(obj);
    }

    @Override
    public void getError(ErrorObj obj) {
        mScheduleFragView.showNetWorkError(obj);
    }

    @Override
    public void addSchedule() {
        mAddScheduleInteractor.addSchedule(mAddScheduleView.getUserId() + "",
                mAddScheduleView.getStartTime(), mAddScheduleView.getEndTime(),
                mAddScheduleView.getScheduleType(), mAddScheduleView.getScheduleContent(),
                mAddScheduleView.getScheduleColor(), this);
    }

    @Override
    public void addScheduleSuccess() {
        mAddScheduleView.completeAddSchedule();
    }

    @Override
    public void addFailed(ErrorObj obj) {
        mAddScheduleView.showLogicFailed(obj);
    }

    @Override
    public void addError(ErrorObj obj) {
        mAddScheduleView.showNetWorkError(obj);
    }
}
