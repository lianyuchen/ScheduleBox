package com.lyc.schedulebox.presenter.impl;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.IMindInteractor;
import com.lyc.schedulebox.logic.impl.MindInteractorImpl;
import com.lyc.schedulebox.logic.listener.GetMindListListener;
import com.lyc.schedulebox.logic.listener.UploadMindListener;
import com.lyc.schedulebox.logic.model.MindListModel;
import com.lyc.schedulebox.presenter.IMindPresenter;
import com.lyc.schedulebox.view.IMindFragView;
import com.lyc.schedulebox.view.IRecordMindView;

import java.util.List;

/**
 * Created by lianyuchen on 16/4/6.
 */
public class MindPresenterImpl implements IMindPresenter, GetMindListListener, UploadMindListener {

    private IMindInteractor mindInteractor;
    private IMindFragView mindFragView;
    private IRecordMindView recordMindView;

    public MindPresenterImpl(IMindFragView mindFragView) {
        this.mindFragView = mindFragView;
        mindInteractor = new MindInteractorImpl();
    }

    public MindPresenterImpl(IRecordMindView recordMindView) {
        this.recordMindView = recordMindView;
        mindInteractor = new MindInteractorImpl();
    }

    @Override
    public void showMindList(int userId) {
        mindInteractor.getMindList(userId + "", this);
    }

    @Override
    public void pubMind() {
        mindInteractor.uploadMind(recordMindView.getUserId() + "", recordMindView.getMindContent(), this);
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

    @Override
    public void uploadMindSuccess() {
        recordMindView.completePubMind();
    }

    @Override
    public void uploadMindFailed(ErrorObj obj) {
        recordMindView.showLogicFailed(obj);
    }

    @Override
    public void uploadMindError(ErrorObj obj) {
        recordMindView.showNetWorkError(obj);
    }
}
