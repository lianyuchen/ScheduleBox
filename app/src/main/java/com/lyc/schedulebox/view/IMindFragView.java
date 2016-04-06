package com.lyc.schedulebox.view;

import com.lyc.schedulebox.logic.model.MindListModel;

import java.util.List;

/**
 * Created by lianyuchen on 16/4/6.
 */
public interface IMindFragView extends IBaseView {

    void showMindList(List<MindListModel.ObjEntity.ListEntity> list);
}
