package com.lyc.schedulebox.presenter.impl;

import com.alamkanak.weekview.WeekViewEvent;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.IAddScheduleInteractor;
import com.lyc.schedulebox.logic.IAnalysisInteractor;
import com.lyc.schedulebox.logic.IScheduleInteractor;
import com.lyc.schedulebox.logic.impl.ScheduleInteractorImpl;
import com.lyc.schedulebox.logic.listener.AddScheduleListener;
import com.lyc.schedulebox.logic.listener.GetAnalysisScheduleListener;
import com.lyc.schedulebox.logic.listener.GetScheduleListener;
import com.lyc.schedulebox.logic.listener.ModifyStatusListener;
import com.lyc.schedulebox.logic.model.AnalysisScheduleListModel;
import com.lyc.schedulebox.presenter.IAddSchedulePresenter;
import com.lyc.schedulebox.presenter.IAnalysisSchedulePresenter;
import com.lyc.schedulebox.presenter.ISchedulePresenter;
import com.lyc.schedulebox.utils.logutils.LogUtils;
import com.lyc.schedulebox.view.IAddScheduleView;
import com.lyc.schedulebox.view.IAnalysisView;
import com.lyc.schedulebox.view.IScheduleFragView;

import java.util.List;


/**
 * Created by lianyuchen on 16/3/8.
 */
public class SchedulePresenterImpl implements GetAnalysisScheduleListener, IAnalysisSchedulePresenter,ISchedulePresenter, GetScheduleListener, IAddSchedulePresenter, AddScheduleListener, ModifyStatusListener {
    private IScheduleInteractor mScheduleInteractor;
    private IScheduleFragView mScheduleFragView;
    private IAddScheduleView mAddScheduleView;
    private IAddScheduleInteractor mAddScheduleInteractor;

    private IAnalysisView mAnalysisView;
    private IAnalysisInteractor mAnalysisInteractor;


    public SchedulePresenterImpl(IScheduleFragView mScheduleFragView) {
        this.mScheduleFragView = mScheduleFragView;
        this.mScheduleInteractor = new ScheduleInteractorImpl();
    }

    public SchedulePresenterImpl(IAddScheduleView mAddScheduleView) {
        this.mAddScheduleView = mAddScheduleView;
        this.mAddScheduleInteractor = new ScheduleInteractorImpl();
    }

    public SchedulePresenterImpl(IAnalysisView mAnalysisView) {
        this.mAnalysisView = mAnalysisView;
        this.mAnalysisInteractor = new ScheduleInteractorImpl();
    }

    @Override
    public void showSchedule(int userId, String start, String end) {
        mScheduleInteractor.getSchedule(userId + "", start, end, this);
    }

    @Override
    public void updateScheduleStatus(int scheduleId) {
        mScheduleInteractor.updateScheduleStatus(mScheduleFragView.getUserId() + "", scheduleId + "", "true", this);

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
    public void getAnalysisScheduleListSuccess(List<AnalysisScheduleListModel.ObjEntity.ListEntity> list) {
        mAnalysisView.showAnalysisSchedule(list);
    }

    @Override
    public void getAnalysisScheduleListFailed(ErrorObj obj) {
        mAnalysisView.showLogicFailed(obj);
    }

    @Override
    public void getAnalysisScheduleListError(ErrorObj obj) {
        mAnalysisView.showNetWorkError(obj);
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

    @Override
    public void modifyStatusSuccess() {
        mScheduleFragView.refreshSchedule();
    }

    @Override
    public void modifyStatusFailed(ErrorObj obj) {
        mScheduleFragView.showLogicFailed(obj);
    }

    @Override
    public void modifyStatusError(ErrorObj obj) {
        mScheduleFragView.showNetWorkError(obj);
    }

    @Override
    public void getAnalysisScheduleList() {
        mAnalysisInteractor.getAnalysisScheduleById(mAnalysisView.getUserId()+"",this);
    }
}
