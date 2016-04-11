package com.lyc.schedulebox.logic.impl;

import com.alamkanak.weekview.WeekViewEvent;
import com.cxyw.suyun.common.net.RequestParams;
import com.cxyw.suyun.common.net.callBack.IRequestCallBack;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.common.AppConstants;
import com.lyc.schedulebox.logic.IAddScheduleInteractor;
import com.lyc.schedulebox.logic.IScheduleInteractor;
import com.lyc.schedulebox.logic.listener.AddScheduleListener;
import com.lyc.schedulebox.logic.listener.GetScheduleListener;
import com.lyc.schedulebox.logic.listener.ModifyStatusListener;
import com.lyc.schedulebox.logic.model.BaseModel;
import com.lyc.schedulebox.logic.model.ScheduleListBean;
import com.lyc.schedulebox.utils.ModelUtils;
import com.lyc.schedulebox.utils.NetworkHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianyuchen on 16/3/8.
 */
public class ScheduleInteractorImpl implements IScheduleInteractor, IAddScheduleInteractor {

    @Override
    public void getSchedule(String userId, String scheduleStartTime, String scheduleEndTime, final GetScheduleListener listener) {
        RequestParams params = new RequestParams();
        params.setRequestString("userId", userId);
        params.setRequestString("scheduleStartTime", scheduleStartTime);
        params.setRequestString("scheduleEndTime", scheduleEndTime);
        NetworkHelper.getInstance().post(AppConstants.URL_GET_SCHEDULE_LIST, params, new IRequestCallBack<ScheduleListBean>() {
            @Override
            public void onSuccess(ScheduleListBean result) {
                //LogUtils.i(result.getObj().getList().get(0).getScheduleStartTime());
                List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
                List<ScheduleListBean.ObjEntity.ListEntity> list = result.getObj().getList();
                if (list.size() > 0) {
                    for (int i = 0; i < list.size(); i++) {
                        ScheduleListBean.ObjEntity.ListEntity bean = list.get(i);

                        events.add(ModelUtils.toWeekViewEvent(bean));

                    }
                    listener.getScheduleListSuccess(events);
                } else {
                    listener.getScheduleFailed(new ErrorObj(result.getCode(), result.getCodeMsg()));
                }

            }

            @Override
            public void onError(ErrorObj obj) {
                listener.getError(obj);
            }

            @Override
            public void onFailed(ErrorObj obj) {
                listener.getScheduleFailed(obj);
            }
        }, ScheduleListBean.class);
    }

    @Override
    public void updateScheduleStatus(String userId, String scheduleId, String status, final ModifyStatusListener listener) {
        RequestParams params = new RequestParams();
        params.setRequestString("userId", userId);
        params.setRequestString("scheduleId", scheduleId);
        params.setRequestString("scheduleStatus", status);
        NetworkHelper.getInstance().post(AppConstants.URL_MODIFY_SCHEDULE_STATUS, params, new IRequestCallBack<BaseModel>() {
            @Override
            public void onSuccess(BaseModel result) {
                listener.modifyStatusSuccess();
            }

            @Override
            public void onError(ErrorObj obj) {
                listener.modifyStatusError(obj);
            }

            @Override
            public void onFailed(ErrorObj obj) {
                listener.modifyStatusFailed(obj);
            }
        }, BaseModel.class);
    }

    @Override
    public void addSchedule(String userId, String start, String end, String scheduleType, String content, String color, final AddScheduleListener listener) {
        RequestParams params = new RequestParams();
        params.setRequestString("scheduleType", scheduleType);
        params.setRequestString("scheduleContent", content);
        params.setRequestString("scheduleStartTime", start);
        params.setRequestString("scheduleEndTime", end);
        params.setRequestString("userId", userId);
        params.setRequestString("scheduleColor", color);
        NetworkHelper.getInstance().post(AppConstants.URL_ADD_SCHEDULE, params, new IRequestCallBack<BaseModel>() {
            @Override
            public void onSuccess(BaseModel result) {
                listener.addScheduleSuccess();
            }

            @Override
            public void onError(ErrorObj obj) {
                listener.addError(obj);
            }

            @Override
            public void onFailed(ErrorObj obj) {
                listener.addFailed(obj);
            }
        }, BaseModel.class);
    }
}
