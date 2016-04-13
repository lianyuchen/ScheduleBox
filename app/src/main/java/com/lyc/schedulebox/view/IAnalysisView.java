package com.lyc.schedulebox.view;

import com.lyc.schedulebox.logic.model.AnalysisScheduleListModel;

import java.util.List;

/**
 * Created by lianyuchen on 16/4/13.
 */
public interface IAnalysisView extends IBaseView {

    int getUserId();

    void showAnalysisSchedule(List<AnalysisScheduleListModel.ObjEntity.ListEntity> list);
}
