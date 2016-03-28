package com.lyc.schedulebox.logic.impl;

import com.alamkanak.weekview.WeekViewEvent;
import com.cxyw.suyun.common.net.RequestParams;
import com.cxyw.suyun.common.net.callBack.IRequestCallBack;
import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.common.AppConstants;
import com.lyc.schedulebox.logic.IScheduleInteractor;
import com.lyc.schedulebox.logic.listener.GetScheduleListener;
import com.lyc.schedulebox.logic.model.ScheduleListBean;
import com.lyc.schedulebox.utils.ModelUtils;
import com.lyc.schedulebox.utils.NetworkHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lianyuchen on 16/3/8.
 */
public class ScheduleInteractorImpl implements IScheduleInteractor {

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
                for (int i = 0; i < list.size(); i++) {
                    ScheduleListBean.ObjEntity.ListEntity bean = list.get(i);

                    events.add(ModelUtils.toWeekViewEvent(bean));

                }
                listener.getScheduleListSuccess(events);

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
}
