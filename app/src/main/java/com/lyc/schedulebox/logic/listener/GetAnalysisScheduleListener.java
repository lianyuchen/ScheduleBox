package com.lyc.schedulebox.logic.listener;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.model.AnalysisScheduleListModel;

import java.util.List;

/**
 * Created by lianyuchen on 16/4/13.
 */
public interface GetAnalysisScheduleListener {
    void getAnalysisScheduleListSuccess(List<AnalysisScheduleListModel.ObjEntity.ListEntity> list);

    void getAnalysisScheduleListFailed(ErrorObj obj);

    void getAnalysisScheduleListError(ErrorObj obj);
}
