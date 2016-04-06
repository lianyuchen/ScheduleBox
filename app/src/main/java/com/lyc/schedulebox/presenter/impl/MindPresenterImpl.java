package com.lyc.schedulebox.presenter.impl;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.IMindInteractor;
import com.lyc.schedulebox.logic.impl.MindInteractorImpl;
import com.lyc.schedulebox.logic.listener.GetMindListListener;
import com.lyc.schedulebox.logic.model.MindListModel;
import com.lyc.schedulebox.presenter.IMindPresenter;
import com.lyc.schedulebox.view.IMindFragView;

import java.util.List;

/**
 * Created by lianyuchen on 16/4/6.
 */
public class MindPresenterImpl implements IMindPresenter,GetMindListListener{

    private IMindInteractor mindInteractor;
    private IMindFragView mindFragView;

    public MindPresenterImpl(IMindFragView mindFragView) {
        this.mindFragView = mindFragView;
        mindInteractor = new MindInteractorImpl();
    }

    @Override
    public void showMindList(int userId) {
        mindInteractor.getMindList(userId + "", this);
    }

    @Override
    public void getMindListSuccess(List<MindListModel.ObjEntity.ListEntity> list) {
        mindFragView.showMindList(list);
    }

    @Override
    public void getMindListFailed(ErrorObj obj) {
        mindFragView.showLogicFailed(obj);
    }

    @Override
    public void getError(ErrorObj obj) {
        mindFragView.showNetWorkError(obj);
    }
}
