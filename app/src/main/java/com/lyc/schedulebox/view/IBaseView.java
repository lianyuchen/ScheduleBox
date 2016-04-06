package com.lyc.schedulebox.view;

import com.cxyw.suyun.common.net.model.ErrorObj;

/**
 * Created by lianyuchen on 16/4/6.
 */
public interface IBaseView {

    void showNetWorkError(ErrorObj obj);

    void showNetWorkError();

    void showLogicFailed(ErrorObj obj);

    void showLogicFailed();
}
