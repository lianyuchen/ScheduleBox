package com.lyc.schedulebox.ui.listener;

import com.lyc.schedulebox.view.IShareView;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;

/**
 * Created by lianyuchen on 16/4/7.
 */
public class ShareQQUiListener implements IUiListener {

    private IShareView shareView;

    public ShareQQUiListener(IShareView shareView) {
        this.shareView = shareView;
    }

    @Override
    public void onComplete(Object o) {
        shareView.showToast(o);
    }

    @Override
    public void onError(UiError uiError) {
        shareView.showToast(uiError);
    }

    @Override
    public void onCancel() {

    }
}
