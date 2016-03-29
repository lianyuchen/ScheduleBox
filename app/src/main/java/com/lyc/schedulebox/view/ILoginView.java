package com.lyc.schedulebox.view;

import com.cxyw.suyun.common.net.model.ErrorObj;

/**
 * Created by lianyuchen on 16/3/25.
 */
public interface ILoginView {
    String getUserName();

    String getUserPassword();

    void showNetError(ErrorObj obj);

    void showLoginFailedDialog();

    void startOrtherActivity();
}
