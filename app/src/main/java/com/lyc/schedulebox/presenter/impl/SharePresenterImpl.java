package com.lyc.schedulebox.presenter.impl;

import com.lyc.schedulebox.presenter.ISharePresenter;
import com.lyc.schedulebox.view.IShareView;

/**
 * Created by lianyuchen on 16/4/7.
 */
public class SharePresenterImpl implements ISharePresenter {

    private IShareView shareView;

    public SharePresenterImpl(IShareView shareView) {
        this.shareView = shareView;
    }

    @Override
    public void shareWXTimeLine() {
        shareView.shareWXTimeLine();
    }

    @Override
    public void shareWXSession() {
        shareView.shareWXSession();
    }

    @Override
    public void shareQQ() {
        shareView.shareQQ();
    }

    @Override
    public void shareQzone() {
        shareView.shareQzone();
    }

    @Override
    public void shareWeibo() {
        shareView.shareWeibo();
    }

}
