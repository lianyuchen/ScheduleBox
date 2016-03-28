package com.lyc.schedulebox.view;

import com.cxyw.suyun.common.net.model.ErrorObj;
import com.lyc.schedulebox.logic.model.UserInfoModel;

/**
 * Created by lianyuchen on 16/3/25.
 */
public interface ILoginView {
    String getUserName();

    String getUserPassword();

    void saveUserInfo(UserInfoModel bean);

    void showNetError(ErrorObj obj);

    void showLoginFailedDialog();

    void startOrtherActivity();
}
