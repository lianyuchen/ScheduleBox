package com.lyc.schedulebox.logic.listener;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.model.MindListModel;

import java.util.List;

/**
 * Created by lianyuchen on 16/4/6.
 */
public interface GetMindListListener {

    void getMindListSuccess(List<MindListModel.ObjEntity.ListEntity> list);

    void getMindListFailed(ErrorObj obj);

    void getError(ErrorObj obj);
}
